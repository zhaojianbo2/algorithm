package astar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class AstarPath {

    // 开始X坐标
    private int startX;
    // 开始Y坐标
    private int startY;
    // 目标X坐标
    private int targetX;
    // 目标Y坐标
    private int targetY;
    // 格子的信息
    private Grid[][] grids;

    public AstarPath(Point startPo, Point targetPo, Grid[][] grids) {

	if (startPo.x < 0) {
	    startPo.x = 0;
	}
	if (startPo.y < 0) {
	    startPo.y = 0;
	}

	if (startPo.gridX() >= grids.length) {
	    startPo.x = startPo.x - 1;
	}

	if (startPo.gridY() >= grids[0].length) {
	    startPo.y = startPo.y - 1;
	}

	if (targetPo.x < 0) {
	    targetPo.x = 0;
	}

	if (targetPo.y < 0) {
	    targetPo.y = 0;
	}
	if (targetPo.gridX() >= grids.length) {
	    targetPo.x = targetPo.x - 1;
	}

	if (targetPo.gridY() >= grids[0].length) {
	    targetPo.y = targetPo.y - 1;
	}

	this.startX = startPo.gridX();
	this.startY = startPo.gridY();
	this.targetX = targetPo.gridX();
	this.targetY = targetPo.gridY();
	this.grids = grids;
    }

    /**
     * 
     * @param maxStep     最大步数
     * @param aside       如果终点不可达，是否找旁边的点
     * @param inflection  是否过滤出拐点
     * @param filterTypes 阻挡类型数组
     * @return
     */
    public LinkedList<Point> findPaths(int maxStep, boolean aside, boolean inflection, GridType... filterTypes) {

	// 阻挡mask
	int mask = 0;
	for (GridType filterType : filterTypes) {
	    mask |= filterType.MASK;
	}
	return findPaths(maxStep, aside, inflection, mask);
    }

    /**
     * 
     * @param maxStep
     * @param aside
     * @param inflection
     * @param filterMask
     * @return
     */
    private LinkedList<Point> findPaths(int maxStep, boolean aside, boolean inflection, int filterMask) {
	LinkedList<Point> paths = new LinkedList<>();
	if (startX == targetX && startY == targetY) {
	    return paths;
	}
	if (maxStep <= 0) {
	    return paths;
	}
	maxStep = Math.max(maxStep, Global.MAX_STEP);
	AStarNode startNode = new AStarNode(grids[startX][startY]);
	AStarNode endNode = new AStarNode(grids[targetX][targetY]);
	endNode.weight = Integer.MAX_VALUE;
	// 开放列表(优先队列 二叉堆)
	PriorityQueue<AStarNode> waitNodes = new PriorityQueue<AStarNode>();
	// 关闭列表
	Set<Integer> passNodes = new HashSet<>();
	// 把第一个节点放入开放列表
	waitNodes.add(startNode);
	// 已访问
	passNodes.add(startNode.grid.key);

	int step = 0;
	while ((step < maxStep || maxStep == -1) && !waitNodes.isEmpty()) {
	    // 取出优先级最高的格子
	    AStarNode curNode = waitNodes.poll();
	    step++;
	    if (curNode.grid.equals(endNode.grid)) {
		// 到达终点
		endNode = curNode;
		break;
	    }

	    // 获取相邻可通过格子
	    List<Grid> roundGrids = getRoundGrids(curNode.grid, filterMask);
	    for (Grid grid : roundGrids) {
		// 已遍历过或者有阻挡
		if (passNodes.contains(grid.key)) {
		    continue;
		}
		// 当前找到可移动格子的节点
		AStarNode roundNode = new AStarNode(grid);
		// 从哪个格子移动过去的
		roundNode.parent = curNode;
		// 计算权值(f = g+h,g默认都一样,所以在h上表现耗费代价)
		int weight = countWeight(grid, endNode.grid);
		roundNode.weight = weight;
		if (weight < endNode.weight) {
		    endNode.min = roundNode;
		    endNode.weight = weight;
		}
		// 加入开放列表
		waitNodes.add(roundNode);
		// 加入遍历过格子
		passNodes.add(grid.key);
	    }
	    // 还没有到endnode,但是已经不可达了，说明endnode是被围住了，没有可达路劲，aside 为true则加入min
	    if (aside && waitNodes.isEmpty()) {
		waitNodes.offer(endNode.min);
		break;
	    }
	}
	AStarNode node = null;
	if (endNode.parent != null) {
	    // 已经找到终点
	    node = endNode;
	} else if (!waitNodes.isEmpty()) {
	    // 到达寻路最大步数,或者已经找到死胡同最后一步
	    node = waitNodes.poll();
	}

	// 没有找到
	if (node == null) {
	    return paths;
	}
	// 最后的路径点和开始相同,则没有路径
	if (node.equals(startNode)) {
	    return paths;
	}

	// 加入最后的节点
	byte lastDir = -1;
	while (node.parent != null) {
	    Grid g1 = node.grid;
	    Grid g2 = node.parent.grid;
	    node = node.parent;
	    if (inflection) {
		byte dir = countDir(g1.x, g1.y, g2.x, g2.y);
		if (dir != lastDir) {
		    lastDir = dir;
		    paths.push(g1.center);
		}
	    } else {
		paths.push(g1.center);
	    }
	}
	if (!paths.peek().equals(startNode.grid.center)) {
	    paths.push(startNode.grid.center);
	}

	return paths;
    }

    /**
     * 获取周围可通过的格子
     * 
     * @param grid
     * @param filterMask
     * @return
     */
    private List<Grid> getRoundGrids(Grid grid, int filterMask) {
	// 最大8个
	List<Grid> grids = new ArrayList<>(8);
	for (int i = 0; i < 9; i++) {
	    // 排除本身
	    if (i == 4) {
		continue;
	    }
	    int x = grid.x + i % 3 - 1;
	    int y = grid.y + i / 3 - 1;
	    if (x < 0 || x >= this.grids.length || y < 0 || y >= this.grids[0].length) {
		continue;
	    }
	    // 是否阻挡
	    Grid rGrid = this.grids[x][y];
	    if (rGrid.hasMask(filterMask)) {
		continue;
	    }
	    grids.add(rGrid);
	}
	return grids;
    }

    /**
     * 启发函数(曼哈顿距离)
     * 
     * @param start
     * @param end
     * @return
     */
    private int countWeight(Grid start, Grid end) {
	return Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
    }

    /** 计算方向 */
    private byte countDir(int x1, int y1, int x2, int y2) {
	if (x2 > x1) {
	    if (y2 > y1) {
		return 3;
	    } else if (y2 == y1) {
		return 2;
	    } else {
		return 1;
	    }
	} else if (x2 < x1) {
	    if (y2 > y1) {
		return 5;
	    } else if (y2 == y1) {
		return 6;
	    } else {
		return 7;
	    }
	}
	return 0;
    }
}
