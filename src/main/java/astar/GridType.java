package astar;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public enum GridType {
    /** 阻挡 */
    Block(0),
    /** 安全区 */
    Safe(1),
    /** 飞行 */
    Fly(2);

    public final int OFFSET;

    public final int MASK;

    GridType(int offset) {
	this.OFFSET = offset;
	this.MASK = (1 << offset);
    }
}
