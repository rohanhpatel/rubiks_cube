import java.util.*;
import java.awt.*;

public class Piece {
	private Map<Color, Position> color_pos = new HashMap<Color, Position>();
	
	Piece(Position[] p, Color[] c){
		if (c.length == p.length) {
			for (int i = 0; i < c.length; i++) {
				color_pos.put(c[i], p[i]);
			}
		}
	}
	
	public Color[] getColors() {
		Iterator<Color> itr = color_pos.keySet().iterator();
		Color[] ret = new Color[color_pos.size()];
		int ind = 0;
		while (itr.hasNext()) {
			ret[ind] = itr.next();
			ind++;
		}
		return ret;
	}
	
	public Position[] getPositions() {
		Iterator<Position> itr = color_pos.values().iterator();
		Position[] ret = new Position[color_pos.size()];
		int ind = 0;
		while (itr.hasNext()) {
			ret[ind] = itr.next();
			ind++;
		}
		return ret;
	}
	
	public Color getColor(Position p) {
		Color[] colors = getColors();
		for (int i = 0; i < colors.length; i++) {
			if (color_pos.get(colors[i]) == p) { return colors[i]; }
		}
		return null;
	}
	
	public Position getPosition(Color c) {
		if (color_pos.containsKey(c)) { return color_pos.get(c); }
		else { return null; }
	}
	
	public char type() {
		if (color_pos.size() == 1) { return 't'; }
		else if (color_pos.size() == 2) { return 'e'; }
		else if (color_pos.size() == 3) { return 'c'; }
		else { return 'n'; }
	}
	
	public void rot(int n, Position p) {
		while (n < 0) { n += 4; }
		Position[] rot_arr = p.clock();
		Position[] pos_arr = getPositions();
		Color[] color_arr = getColors();
		for (int i = 0; i < pos_arr.length; i++) {
			if (pos_arr[i] == p) { continue; }
			int j = 0;
			while (j < rot_arr.length) {
				if (rot_arr[j] == pos_arr[i]) { break; }
				j++;
			}
			j += n;
			j %= 4;
			color_pos.replace(color_arr[i], rot_arr[j]);
		}
	}
	
	public boolean isOn(Position p) {
		Position[] pos_arr = getPositions();
		for (int i = 0; i < pos_arr.length; i++) {
			if (pos_arr[i] == p) { return true; }
		}
		return false;
	}
	
}
