package skiplist;

import java.util.Random;

public class SkipList {

    final int HEAD_VAL = -1;// 链表头结点
    final Node HEAD = new Node(HEAD_VAL);

    Node head;// 起点head
    int levels;//
    int length;// 原链表节点个数

    public SkipList() {
	head = HEAD;
	levels = 1;
	length = 1;
    }

    public void add(int num) {
	Node node = head;
	// 可能会生成索引的位置,使用数组记录这些节点
	Node[] nodes = new Node[levels];
	int i = 0;
	while (node != null) {
	    while (node.right != null && node.right.val < num) {
		node = node.right;
	    }
	    nodes[i++] = node;
	    // 继续查找下一层的位置,如果为空则到达了下一层
	    node = node.down;
	}
	// 原链表中的节点
	node = nodes[--i];//
	Node newNode = new Node(num, node.right, null);
	node.right = newNode;
	length++;
	// 生成索引
	addIndicesByCoinFlip(newNode, nodes, i);
    }

    public boolean erase(int num) {
	boolean exist = false;
	// 目标值的左节点
	Node node = get(num, head);
	while (node != null) {
	    Node targetNode = node.right;// 要删除的节点
	    node.right = targetNode.right;
	    targetNode.right = null;
	    exist = true;
	    // 继续往下删
	    node = get(num, node.down);
	}
	if (exist) {
	    length--;
	}

	return exist;
    }

    /**
     * 从head开始 从左到右，从上到下依次查找
     * 
     * @param target
     * @return
     */
    public boolean search(int target) {
	Node n = get(target, head);
	return n != null;
    }

    public Node get(int target, Node from) {
	Node n = from;
	while (n != null) {
	    // 1.在同一层级上向右查找，直到链表结尾，或者找到
	    while (n.right != null && n.right.val < target) {
		n = n.right;
	    }
	    // 2.若找到，返回true
	    Node right = n.right; // 要查找的节点
	    if (right != null && right.val == target) {
		return n; // 返回要查找的节点的前一个
	    }
	    // 3.若右侧数据较大，向下一层
	    n = n.down;
	}
	return null;
    }

    /**
     * 抛硬币方式决定是否给新节点建立索引
     * 
     * @param newNode
     * @param nodes
     * @param indices 源数据所在层
     */
    private void addIndicesByCoinFlip(Node newNode, Node[] nodes, int indices) {
	Node downNode = newNode;
	Random random = new Random();
	int coins = random.nextInt(2);
	while (coins == 1 && levels < (length >> 6)) {
	    // 层内加索引
	    if (indices > 0) {
		Node prev = nodes[--indices];
		Node newIndex = new Node(newNode.val, prev.right, downNode);
		prev.right = newIndex;
		downNode = newIndex;
		coins = random.nextInt(2);
	    } else {
		// 额外索引层
		Node newIndex = new Node(newNode.val, null, downNode);
		//
		// Node newHead = new Node(HEAD_VAL, newIndex, head);
		// head = newHead;
		head.right = newIndex;
		levels++;
	    }
	}
    }
}
