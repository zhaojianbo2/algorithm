package astar;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class AStarNode implements Comparable<AStarNode> {

    // 格子
    public Grid grid;
    // 权值
    public int weight;
    //
    public AStarNode parent;
    
    public AStarNode min;

    public AStarNode(Grid grid) {
	this.grid = grid;
    }

    @Override
    public int compareTo(AStarNode o) {
	return this.weight - o.weight;
    }
    @Override
    public boolean equals(Object source) {
	if(source instanceof AStarNode) {
	    AStarNode sourceNode = (AStarNode) source;
            return sourceNode.grid.x == this.grid.x && sourceNode.grid.y == this.grid.y;
	}
        return false;
    }
}
