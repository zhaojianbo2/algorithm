package astar;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {

	Grid[][] grids = new Grid[10][10];
	for (int i = 0; i < grids.length; i++) {
	    for (int j = 0; j < grids.length; j++) {
		boolean[] masks = new boolean[1];
		masks[0] = false;
		Grid g2 = new Grid(i, j, masks);
		grids[i][j] = g2;
	    }
	}

	Point startPo = new Point();
	startPo.x = 0;
	startPo.y = 0;
	grids[1][1].mask = 1;
	grids[1][2].mask = 1;
	grids[1][3].mask = 1;
	grids[9][8].mask = 1;
	grids[8][8].mask = 1;
	grids[8][9].mask = 1;
	Point targetPo = new Point();
	targetPo.x = 99;
	targetPo.y = 99;
	AstarPath a = new AstarPath(startPo, targetPo, grids);
	LinkedList<Point> r = a.findPaths(100,true,true,GridType.Block);
	while(!r.isEmpty()) {
	    Point p = r.pop();
	    System.out.println(" x:"+p.gridX()+" y:"+p.gridY());
	}
    }
}
