package astar;


/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class Point {

    public double x;

    public double y;

    /**
     * 格子X坐标
     */
    public int gridX() {
        return (int) (x / Global.Grid_Width);
    }

    /**
     * 格子Y坐标
     */
    public int gridY() {
        return (int) (y / Global.Grid_Height);
    }
}
