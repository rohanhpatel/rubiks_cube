import java.awt.*;

public class Face {
	private Piece[][] face = new Piece[3][3];
	private Position pos;
	
	public Face(Position p) {
		pos = p;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setFace(Piece[] all_pieces) {
		for (int i = 0; i < all_pieces.length; i++) {
			Piece p = all_pieces[i];
			if (p.isOn(pos)) {
				char c = p.type();
				switch(c) {
					case 't':
						face[1][1] = p;
						break;
					case 'e':
						Position other_pos = pos;
						Position[] p_pos = p.getPositions();
						for (int j = 0; j < p_pos.length; j++) {
							if (p_pos[j] != pos) { other_pos = p_pos[j]; break; }
						}
						if (other_pos == pos.up()) { face[0][1] = p; }
						else if (other_pos == pos.right()) { face[1][2] = p; }
						else if (other_pos == pos.left()) { face[1][0] = p; }
						else if (other_pos == pos.down()) { face[2][1] = p; }
						break;
					case 'c':
						Position[] other_posis = new Position[2];
						int ind = 0;
						Position[] p_posis = p.getPositions();
						for (int j = 0; j < p_posis.length; j++) {
							if (p_posis[j] != pos) { other_posis[ind] = p_posis[j]; ind++; }
						}
						if (other_posis[0] == pos.up() || other_posis[1] == pos.up()) {
							if (other_posis[0] == pos.left() || other_posis[1] == pos.left()) {
								face[0][0] = p;
								break;
							}
							else if (other_posis[0] == pos.right() || other_posis[1] == pos.right()) {
								face[0][2] = p;
								break;
							}
						}
						if (other_posis[0] == pos.down() || other_posis[1] == pos.down()) {
							if (other_posis[0] == pos.left() || other_posis[1] == pos.left()) {
								face[2][0] = p;
								break;
							}
							else if (other_posis[0] == pos.right() || other_posis[1] == pos.right()) {
								face[2][2] = p;
								break;
							}
						}
				}
			}
		}
	}
	
	public void printFace() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Color clr = face[i][j].getColor(pos);
				char c = '0';
				if (clr == Color.YELLOW) { c = 'y'; }
				else if (clr == Color.WHITE) { c = 'w'; }
				else if (clr == Color.GREEN) { c = 'g'; }
				else if (clr == Color.BLUE) { c = 'b'; }
				else if (clr == Color.RED) { c = 'r'; }
				else if (clr == Color.ORANGE) { c = 'o'; }
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
	
	public String getLabel() {
		String ret = "<html>";
		//ret += pos.toString() + "<br>";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Color clr = face[i][j].getColor(pos);
				char c = '0';
				if (clr == Color.YELLOW) { c = 'y'; }
				else if (clr == Color.WHITE) { c = 'w'; }
				else if (clr == Color.GREEN) { c = 'g'; }
				else if (clr == Color.BLUE) { c = 'b'; }
				else if (clr == Color.RED) { c = 'r'; }
				else if (clr == Color.ORANGE) { c = 'o'; }
				String pr = Character.toString(c); 
				if (j != 2) {
					pr += " | ";
				}
				ret += pr;
			}
			ret += "<br>";
			if (i != 2) {
				ret += "---------<br>";
			}
		}
		ret += "</html>";
		return ret;
	}
}
