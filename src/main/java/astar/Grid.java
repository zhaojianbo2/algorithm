package astar;

/**
 * 
 * @author WinkeyZhao
 * @note 移动辅助格子
 *
 */
public class Grid {
    // 格子X坐标
    public int x;
    // 格子Y坐标
    public int y;
    // 中心点像素坐标
    public Point center;
    // 格子类型掩码
    public int mask;
    // 唯一标识
    public int key;

    public Grid(int x, int y, boolean[] masks) {
	this.x = x;
	this.y = y;
	int mask = 0;
	key = (x << 16) | y;
	for (int i = 0; i < masks.length; i++) {
	    if (masks[i]) {
		mask |= (1 << i);
	    }
	}
	this.center = centerFromGrid(x, y);
	this.mask = mask;
    }

    public boolean hasMask(int mask) {
	return mask >= 0 && (this.mask & mask) != 0;
    }

    /** 使用格子坐标创建中心点位置 */
    private Point centerFromGrid(int x, int y) {
	Point p = new Point();
	p.x = x * Global.Grid_Width + Global.Grid_Width / 2d;
	p.y = y * Global.Grid_Height + Global.Grid_Height / 2d;
	return p;
    }
}
