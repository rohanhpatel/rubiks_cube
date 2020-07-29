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

	//helper functions for piece getter/setter methods
	private int[] getPieceCrds(int x, int y) {
		if (x == 1 && y == 1) {
			//center piece
			return new int[] {x, y};
		}
		else if (x == 1 || y == 1) {
			//edge piece
			switch (x) {
			case 0:
				return getEdgeCrds(0);
			case 1:
				if (y == 0) {
					return getEdgeCrds(1);
				}
				return getEdgeCrds(3);
			case 2:
				return getEdgeCrds(2);
			}
		}
		else {
			//corner piece
			switch (y) {
			case 0:
				if (x == 0) {
					return getCornerCrds(0);
				}
				return getCornerCrds(1);
			case 2:
				if (x == 0) {
					return getCornerCrds(3);
				}
				return getCornerCrds(2);
			}
		}
		return new int[2];
	}
	
	private int[] getEdgeCrds(int i) {
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
	
	private int[] getCornerCrds(int i) {
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
	
	//get and set color using coordinates
	private Color getColor(int coords[]) {
		return pieces[coords[0]][coords[1]];
	}
	
	private void setColor(int coords[], Color c) {
		pieces[coords[0]][coords[1]] = c;
	}
	
	//actual getter/setter functions
	public Color getPiece(int x, int y) {
		return getColor(getPieceCrds(x, y));
	}
	
	public void setPiece(int x, int y, Color c) {
		setColor(getPieceCrds(x, y), c);
	}
	
	public void printFace() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Color clr = getPiece(i, j);
				char c = '0';
				switch (clr) {
				case YELLOW:
					c = 'y';
					break;
				case WHITE:
					c = 'w';
					break;
				case GREEN:
					c = 'g';
					break;
				case BLUE:
					c = 'b';
					break;
				case RED:
					c = 'r';
					break;
				case ORANGE:
					c = 'o';
					break;
				}
				String pr = Character.toString(c); 
				if (j != 2) {
					pr += " | ";
				}
				System.out.print(pr);
			}
			System.out.println();
			if (i != 2) {
				System.out.println("---------");
			}
		}
	}
}
