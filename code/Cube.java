public class Cube {
	private Face[] faces = {new Face(Color.WHITE, Position.FRONT),
			new Face(Color.RED, Position.RIGHT),
			new Face(Color.BLUE, Position.UP),
			new Face(Color.YELLOW, Position.BACK),
			new Face(Color.ORANGE, Position.LEFT),
			new Face(Color.GREEN, Position.DOWN)};
	
	//default constructor
	public Cube() {}
	
	//cube rotations
	private void cube_rotate(int fc, int ex[], int indices[], int t) {
		while (t > 0) {
			Face f = faces[indices[3]];
			for (int j = 0; j < indices.length; j++) {
				Face tmp = faces[indices[j]];
				faces[indices[j]] = f;
				faces[indices[j]].clock(ex[j]);
				System.out.println("indices[" + j + "] is " + indices[j]);
				System.out.println("ex[" + j + "] is " + ex[j]);
				f = tmp;
			}
			faces[fc].clock(1);
			faces[(fc + 3) % 6].clock(-1);
			t--;
		}
		faces[0].setPos(Position.FRONT);
		faces[1].setPos(Position.RIGHT);
		faces[2].setPos(Position.UP);
		faces[3].setPos(Position.BACK);
		faces[4].setPos(Position.LEFT);
		faces[5].setPos(Position.DOWN);
	}
	
	//need to fix x rotations
	public void x(int t) {
		int indices[] = {0, 2, 3, 5};
		int f = 1;
		//start with right-most index replacing left-most index in indices
		int extra[] = {0, 0, 2, 2};
		cube_rotate(f, extra, indices, t);
	}
	
	public void xp(int t) {
		int indices[] = {0, 5, 3, 2};
		int f = 4;
		//start with right-most index replacing left-most index in indices
		int extra[] = {0, 0, 2, 2};
		cube_rotate(f, extra, indices, t);
	}
	
	public void y(int t) {
		int indices[] = {0, 4, 3, 1};
		int f = 2;
		int extra[] = {0, 0, 0, 0};
		cube_rotate(f, extra, indices, t);
	}
	
	public void yp(int t) {
		int indices[] = {0, 1, 3, 4};
		int f = 5;
		int extra[] = {0, 0, 0, 0};
		cube_rotate(f, extra, indices, t);
	}
	
	public void z(int t) {
		int indices[] = {1, 5, 4, 2};
		int f = 0;
		int extra[] = {1, 1, 1, 1};
		cube_rotate(f, extra, indices, t);
	}
	
	public void zp(int t) {
		int indices[] = {1, 2, 4, 5};
		int f = 3;
		int extra[] = {-1, -1, -1, -1};
		cube_rotate(f, extra, indices, t);
	}
	
	//face rotations
	private void up_clock(int t) {
		while (t > 0) { //goes through up_clock t times
			int indices[] = {0, 4, 3, 1};
			for (int i = 1; i < 4; i++) {
				int j = 0;
				if (i == 3) { j = 3; }
				if (i % 2 == 1) {
					Color c = faces[indices[3]].getCorner(j);
					for (int k = 0; k < indices.length; k++) {
						Color temp = faces[indices[k]].getCorner(j);
						faces[indices[k]].setCorner(j, c);
						c = temp;
					}
				}
				else {
					Color c = faces[indices[3]].getEdge(j);
					for (int k = 0; k < indices.length; k++) {
						Color temp = faces[indices[k]].getEdge(j);
						faces[indices[k]].setEdge(j, c);
						c = temp;
					}
				}
			}
			faces[2].clock(1);
			t--;
		}
	}
	
	public void U(int t) {
		while (t > 0) {
			up_clock(1);
			t--;
		}
	}
	public void R(int t) {
		while (t > 0) {
			zp(1);
			up_clock(1);
			z(1);
			t--;
		}
	}
	public void L(int t) {
		while (t > 0) {
			z(1);
			up_clock(1);
			zp(1);
			t--;
		}
	}
	
	public void D(int t) {
		while (t > 0) {
			z(2);
			up_clock(1);
			z(2);
			t--;
		}
	}
	
	public void F(int t) {
		while (t > 0) {
			x(1);
			up_clock(1);
			xp(1);
			t--;
		}
	}
	
	public void B(int t) {
		while (t > 0) {
			xp(1);
			up_clock(1);
			x(1);
			t--;
		}
	}
	
	public void printOrient() {
		for (int i = 0; i < faces.length; i++) {
			String display = "Face " + faces[i].getCenter().toString() + " which is " + faces[i].getPos().toString() + " has orientation " + faces[i].getOrient();
			System.out.println(display);
		}
		System.out.println();
	}
	
	public void printCube() {
		System.out.println("Printing cube as sideways t, with back on right side of the net");
		System.out.println("All faces will be shown in this orientation from top left to bottom right");
		System.out.println();
		for (int i = 0; i < faces.length; i++) {
			String display = "Displaying " + faces[i].getCenter().toString() + " which is " + faces[i].getPos().toString() + " with orientation " + faces[i].getOrient();
			System.out.println(display);
			for (int j = 0; j < 3; j++) {
				String line = "";
				for (int k = 0; k < 3; k++) {
					line += faces[i].getPiece((j * 3) + k).toString();
					if (k != 2) {
						line += ", "; 
					}
				}
				System.out.println(line);
			}
		}
		System.out.println();
	}
	
}
