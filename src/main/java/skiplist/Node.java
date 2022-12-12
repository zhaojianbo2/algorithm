package skiplist;

public class Node {

    public int val;
    public Node right, down;

    public Node(int val) {
	this(val, null, null);
    }

    public Node(int val, Node right, Node down) {
	this.val = val;
	this.right = right;
	this.down = down;
    }
}
