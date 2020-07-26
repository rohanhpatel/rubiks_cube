public class Cube {
	
	Face[] faces = new Face[6];
	
	public Cube() {
		for (int i = 0; i < 6; i++) {
			faces[i] = new Face(Color.WHITE);
		}
	}
	
}
