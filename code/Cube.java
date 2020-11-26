import java.util.*;
import java.awt.*;

public class Cube {
	private Map<Color, Position> color_faces = new HashMap<Color, Position>();
	private Piece[] all_pieces;
	private Face[] faces = new Face[] {
		new Face(Position.LEFT),
		new Face(Position.UP),
		new Face(Position.FRONT),
		new Face(Position.DOWN),
		new Face(Position.RIGHT),
		new Face(Position.BACK)
	};
	
	//default constructor
	public Cube() {
		color_faces.put(Color.WHITE, Position.FRONT);
		color_faces.put(Color.YELLOW, Position.BACK);
		color_faces.put(Color.BLUE, Position.UP);
		color_faces.put(Color.GREEN, Position.DOWN);
		color_faces.put(Color.RED, Position.RIGHT);
		color_faces.put(Color.ORANGE, Position.LEFT);
		reset();
	}
	
	private Piece[] genPieces(Map<Color, Position> cf) {
		//Implement this function
		Piece[] ret = new Piece[26];
		Color[] colors = new Color[] {Color.WHITE, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE};
		Position[] faces = new Position[] {Position.FRONT, Position.BACK, Position.UP, Position.DOWN, Position.RIGHT, Position.LEFT};
		int ind = 0;
		for (int i = 0; i < 6; i++) { //centers
//			System.out.println("ind is " + ind + " for i = " + i);
			ret[ind] = new Piece(new Position[] {faces[i]}, new Color[] {colors[i]});
			ind++;
			for (int j = i+1; j < 6; j++) { //edges
				if (i%2 == 0 && j == i+1) { continue; }
//				System.out.println("ind is " + ind + " for j = " + j);
				ret[ind] = new Piece(new Position[] {faces[i], faces[j]}, new Color[] {colors[i], colors[j]});
				ind++;
				for (int k = j+1; k < 6; k++) {
					if ((j%2 == 0 && k == j+1) || (i%2 == 0 && k == i+1)) { continue; }
//					System.out.println("ind is " + ind + " for k = " + k);
					ret[ind] = new Piece(new Position[] {faces[i], faces[j], faces[k]}, new Color[] {colors[i], colors[j], colors[k]});
					ind++;
				}
			}
		}
		return ret;
	}
	
	public void norm_rot(int n, Position f) {
		for (int i = 0; i < all_pieces.length; i++) {
			if (all_pieces[i].isOn(f)) {
				all_pieces[i].rot(n, f);
			}
		}
		setFaces();
	}
	
	public void mid_rot(int n, Position dir, Position opp_dir) {
		//rotates mid layer clockwise in p1 direction
		if (dir != opp_dir.across()) { System.out.println("Invalid mid_rot"); return; }
		else {
			for (int i = 0; i < all_pieces.length; i++) {
				Piece p = all_pieces[i];
				if (!p.isOn(dir) && !p.isOn(opp_dir)) {
					p.rot(n, dir);
				}
			}
			setFaces();
		}
	}
	
	private void setFaces() {
		for (int i = 0; i < faces.length; i++) {
			faces[i].setFace(all_pieces);
		}
	}
	
	public Face[] getFaces() { return faces; }
	
	public void printCube() {
		System.out.println("Now printing out the cube");
		for (int i = 0; i < faces.length; i++) {
			System.out.println("Printing " + faces[i].getPos() + " face"); 
			faces[i].printFace();
		}
		System.out.println();
	}
	
	public void reset() {
		all_pieces = genPieces(color_faces);
		setFaces();
	}
	
	
	
}
