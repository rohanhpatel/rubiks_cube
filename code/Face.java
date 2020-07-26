public class Face {
	
	private Color[][] colors = new Color[3][3];
	
	//constructor with color input
	public Face(Color c) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				colors[i][j] = c;
			}
		}
	}
	
	public Color getCenter() { //returns the color of the center
		return colors[1][1];
	}
	
	public Color getColor(int[] coords) {
		return colors[coords[0]][coords[1]];
	}
	
	public void setColor(int[] coords, Color c) {
		colors[coords[0]][coords[1]] = c;
	}
	
	private void clockwise(int[][] coords) {
		Color col = colors[coords[3][0]][coords[3][1]];
		for (int i = 0; i < 4; i++) {
			Color temp = getColor(coords[i]);
			setColor(coords[i], col);
			col = temp;
		}
	}
	
	public void clockRotate() {
		//edges are (0, 1), (1, 0), (1, 2), (2, 1)
		int[][] edge_coords = {{0, 1}, {1, 2}, {2, 1}, {1, 0}};
		int[][] corner_coords = {{0, 0}, {0, 2}, {2, 2}, {2, 0}};
		clockwise(edge_coords);
		clockwise(corner_coords);
	}
	
	public void printFace() {
		for (int i = 0; i < 3; i++) {
			String line = "";
			for (int j = 0; j < 3; j++) {
				line += colors[i][j].toString();
				if (j != 2) {
					line += ", ";
				}
			}
			System.out.println(line);
		}
	}
}
