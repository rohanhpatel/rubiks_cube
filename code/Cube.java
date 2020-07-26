public class Cube {
	Face[] faces = {new Face(Color.WHITE), new Face(Color.RED), new Face(Color.BLUE), new Face(Color.YELLOW), new Face(Color.ORANGE), new Face(Color.GREEN)};
	// (F)ront, (R)ight, (U)p, (B)ack, (L)eft, (D)own
	// FRUBLD
	public Cube() {
		
	}
	
	private void right(int times) {
		for (int t = 0; t < times; t++) {
			//one iteration of R
			for (int i = 0; i < 3; i++) { //goes through the 3 right pieces on each face
				int[] coords = {i, 2};
				Color c = faces[5].getColor(coords);
				int j = 0;
				while (j < faces.length) {
					Color temp = faces[j].getColor(coords);
					faces[j].setColor(coords, c);
					c = temp;
					if (j % 3 == 0) { j += 2; }
					else if (j % 3 == 2) { j += 1; }
				}
			}
			faces[1].clockRotate(); //rotate right side clockwise
		}
	}
	
	public void R() {
		right(1);
	}
	
	public void R2() {
		right(2);
	}
	
	public void Rp() {
		right(3);
	}
	
	public void printCube() {
		for (int i = 0; i < 6; i++) {
			String adding = "";
			switch(i) {
			case 0:
				adding = "front";
				break;
			case 1:
				adding = "right";
				break;
			case 2:
				adding = "up";
				break;
			case 3:
				adding = "back";
				break;
			case 4:
				adding = "left";
				break;
			case 5:
				adding = "down";
				break;
			}
			String display = "Displaying " + adding;
			System.out.println(display);
			faces[i].printFace();
		}
	}
	
	
}
