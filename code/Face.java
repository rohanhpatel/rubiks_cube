public class Face {
//	private Face[] connected = new Face[4];
//	private int f_index = 0;
	private Color[][] pieces = new Color[3][3];
	private Position pos;
	private int orient = 0; //keeps track of orientation w.r.t. original orientation
							//every turn counter-clockwise is an increase of 1 in orient
	
	public Face(Color c, Position p) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				pieces[i][j] = c;
			}
		}
		pos = p;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position p) {
		pos = p;
	}
	
	public int getOrient() {
		return orient;
	}
	
	public void clock(int o) {
		orient += o;
		while (orient < 0) {
			orient += 4;
		}
		orient = orient % 4;
	}
	
	public Color getCenter() {
		return pieces[1][1];
	}
	
	public Color getPiece(int i) {
		switch(i) {
		case 0:
			return getCorner(0);
		case 1:
			return getEdge(0);
		case 2:
			return getCorner(3);
		case 3:
			return getEdge(1);
		case 4:
			return getCenter();
		case 5:
			return getEdge(3);
		case 6:
			return getCorner(1);
		case 7:
			return getEdge(2);
		case 8:
			return getCorner(2);
		}
		return Color.WHITE;	
	}
	
	public Color getColor(int coords[]) {
		return pieces[coords[0]][coords[1]];
	}
	
	public void setColor(int coords[], Color c) {
		pieces[coords[0]][coords[1]] = c;
	}
	
	public int[] getEdgeCrds(int i) {
		//i = 0: top, i = 1: left, i = 2: bottom, i = 3: right
		int edge_num = (orient + i) % 4;
		if (edge_num < 2) {
			return new int[] {edge_num, edge_num + 1};
		}
		else {
			edge_num -= 2;
			return new int[]{2 - edge_num, 1 - edge_num};
		}
	}
	
	public int[] getCornerCrds(int i) {
		//i = 0: top-left, i = 1: bottom-left, i = 2: bottom-right, i = 3: top-right
		int corner_num = (orient + i) % 4;
		if (corner_num < 2) {
			return new int[] {corner_num * 2, 0};
		}
		else {
			corner_num -= 2;
			return new int[] {2 * (1 - corner_num), 2};
		}
	}
	
	public Color getEdge(int i) {
		return getColor(getEdgeCrds(i));
	}
	
	public void setEdge(int i, Color c) {
		setColor(getEdgeCrds(i), c);
	}
	
	public Color getCorner(int i) {
		return getColor(getCornerCrds(i));
	}
	
	public void setCorner(int i, Color c) {
		setColor(getCornerCrds(i), c);
	}
}
