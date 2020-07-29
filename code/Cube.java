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
		if (t < 0) {
			for (int i = 0; i < ex.length; i++) {
				ex[i] *= -1;
			}
			int temp = indices[3];
			indices[3] = indices[1];
			indices[1] = temp;
			t *= -1;
			fc = (fc + 3) % 6;
		}
		while (t > 0) {
			Face f = faces[indices[3]];
			for (int j = 0; j < indices.length; j++) {
				Face tmp = faces[indices[j]];
				faces[indices[j]] = f;
				faces[indices[j]].clock(ex[j]);
//				System.out.println("indices[" + j + "] is " + indices[j]);
//				System.out.println("ex[" + j + "] is " + ex[j]);
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
	
	public void x(int t) { //rotate whole cube on R
		int indices[] = {0, 2, 3, 5};
		int f = 1;
		//start with right-most index replacing left-most index in indices
		int extra[] = {0, 0, 2, 2};
		cube_rotate(f, extra, indices, t);
	}
	
	public void y(int t) { //rotate whole cube on U
		int indices[] = {0, 4, 3, 1};
		int f = 2;
		int extra[] = {0, 0, 0, 0};
		cube_rotate(f, extra, indices, t);
	}
	
	public void z(int t) { //rotate whole cube on F
		int indices[] = {1, 5, 4, 2};
		int f = 0;
		int extra[] = {1, 1, 1, 1};
		cube_rotate(f, extra, indices, t);
	}
	
	//face rotations
	private void clock_rot(int t, char c, int p) { //c == 'n' gives U
		boolean opp = false;
		if (t < 0) {
			opp = true;
			t *= -1;
		}
		while (t > 0) { //goes through up_clock t times
			//do cube rotation
			switch (c) {
			case 'x':
				x(p);
				break;
			case 'y':
				y(p);
				break;
			case 'z':
				z(p);
				break;
			}
			//do rotation
			int indices[] = {0, 4, 3, 1};
			if (opp) { indices[1] = 1; indices[3] = 4;}
			for (int i = 0; i < 3; i++) {
				Color clr = faces[indices[3]].getPiece(0, i);
				for (int j = 0; j < indices.length; j++) {
					Color temp = faces[indices[j]].getPiece(0, i);
					faces[indices[j]].setPiece(0, i, clr);
					clr = temp;
				}
			}
//			int i = 1;
//			if (opp) { i = 3; }
//			while (i <= 3 && i >= 1) {
//				int j = 0;
//				if (i == 3) { j = 3; }
//				if (i % 2 == 1) {
//					Color clr = faces[indices[3]].getCorner(j);
//					for (int k = 0; k < indices.length; k++) {
//						Color temp = faces[indices[k]].getCorner(j);
//						faces[indices[k]].setCorner(j, clr);
//						clr = temp;
//					}
//				}
//				else {
//					Color clr = faces[indices[3]].getEdge(j);
//					for (int k = 0; k < indices.length; k++) {
//						Color temp = faces[indices[k]].getEdge(j);
//						faces[indices[k]].setEdge(j, clr);
//						clr = temp;
//					}
//				}
//				if (opp) { i--; }
//				else { i++; }
//			}
			if (opp) {faces[2].clock(-1);}
			else { faces[2].clock(1);}
			//do inverse rotation
			switch (c) {
			case 'x':
				x(-p);
				break;
			case 'y':
				y(-p);
				break;
			case 'z':
				z(-p);
				break;
			}
			t--;
		}
	}
	
	
	//r, u, b, l, f, and d
	public void little_r(int t) { left(t); x(t); }
	public void little_l(int t) { right(t); x(-t); }
	public void little_u(int t) { down(t); y(t); }
	public void little_d(int t) { up(t); y(-t); }
	public void little_f(int t) { back(t); z(t); }
	public void little_b(int t) { front(t); z(-t); }
	//each rotation works on it's own, but kind of dies when put together in a long string
	public void up(int t) { clock_rot(t, 'n', 0); }
	public void right(int t) { clock_rot(t, 'z', -1); }
	public void left(int t) { clock_rot(t, 'z', 1); }
	public void down(int t) { clock_rot(t, 'z', 2); }
	public void front(int t) { clock_rot(t, 'x', 1); }
	public void back(int t) { clock_rot(t, 'x', -1); }
	//middle slices, M(R), E(U), and S(F)
	public void middle(int t) { little_l(t); left(-t); }
	public void equator(int t) { little_d(t); down(-t); }
	public void standing(int t) { little_f(t); front(-t); }
	
	public void printOrient() {
		for (int i = 0; i < faces.length; i++) {
			String display = "Face " + faces[i].getPiece(1, 1) + " which is " + faces[i].getPos() + " has orientation " + faces[i].getOrient();
			System.out.println(display);
		}
		System.out.println();
	}
	
	public void printCube() {
		System.out.println("Printing cube as sideways t, with back on right side of the net");
		System.out.println("All faces will be shown in this orientation from top left to bottom right");
		System.out.println();
		for (int i = 0; i < faces.length; i++) {
			String display = "Displaying " + faces[i].getPiece(1, 1) + " which is " + faces[i].getPos() + " with orientation " + faces[i].getOrient();;
			System.out.println(display);
			faces[i].printFace();
		}
		System.out.println();
	}
	
	public void reset() {
		faces[0] = new Face(Color.WHITE, Position.FRONT);
		faces[1] = new Face(Color.RED, Position.RIGHT);
		faces[2] = new Face(Color.BLUE, Position.UP);
		faces[3] = new Face(Color.YELLOW, Position.BACK);
		faces[4] = new Face(Color.ORANGE, Position.LEFT);
		faces[5] = new Face(Color.GREEN, Position.DOWN);
	}
	
}
