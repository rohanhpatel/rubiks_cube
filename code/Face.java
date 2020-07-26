public class Face {
	Color[][] colors = new Color[3][3];
	
	//constructor with color input
	public Face(Color c) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				colors[i][j] = c;
			}
		}
	}
	
	public Color getColor() { //returns the color of the center
		return colors[1][1];
	} 
}
