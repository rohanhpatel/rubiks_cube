import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class CubeWrapper {
	Cube c = new Cube();
	public CubeWrapper() {
		System.out.println("This cube uses the Western color scheme");
		System.out.println("It starts with white in the front, red on the right, and blue on the top");
		System.out.println();
	}
	
	public void run_alg(String str, boolean steps) {
		String[] step_arr = str.split(" ");
		for (int i = 0; i < step_arr.length; i++) {
			run_command(step_arr[i]);
			String inp = "Step " + i + " - " + step_arr[i];
			if (steps) { dispCube(inp); } 
		}
	}
	
	
	private void run_command(String str) {
		int amt = 0;
		if (str.length() == 1) {
			amt = 1;
		}
		else if (str.charAt(1) == '2') {
			amt = 2;
		}
		else if (str.charAt(1) == '\'') {
			amt = -1;
		}
		switch (str.charAt(0)) {
		case 'R':
			c.norm_rot(amt, Position.RIGHT);
			break;
		case 'L':
			c.norm_rot(amt, Position.LEFT);
			break;
		case 'U':
			c.norm_rot(amt, Position.UP);
			break;
		case 'B':
			c.norm_rot(amt, Position.BACK);
			break;
		case 'D':
			c.norm_rot(amt, Position.DOWN);
			break;
		case 'F':
			c.norm_rot(amt, Position.FRONT);
			break;
		case 'x':
			c.norm_rot(amt, Position.RIGHT);
			c.norm_rot(-amt, Position.LEFT);
			c.mid_rot(amt, Position.RIGHT, Position.LEFT);
			break;
		case 'y':
			c.norm_rot(amt, Position.UP);
			c.norm_rot(-amt, Position.DOWN);
			c.mid_rot(amt, Position.UP, Position.DOWN);
			break;
		case 'z':
			c.norm_rot(amt, Position.FRONT);
			c.norm_rot(-amt, Position.BACK);
			c.mid_rot(amt, Position.FRONT, Position.BACK);
			break;
		case 'r':
			c.norm_rot(amt, Position.RIGHT);
			c.mid_rot(amt, Position.RIGHT, Position.LEFT);
			break;
		case 'l':
			c.norm_rot(amt, Position.LEFT);
			c.mid_rot(amt, Position.LEFT, Position.RIGHT);
			break;
		case 'u':
			c.norm_rot(amt, Position.UP);
			c.mid_rot(amt, Position.UP, Position.DOWN);
			break;
		case 'd':
			c.norm_rot(amt, Position.DOWN);
			c.mid_rot(amt, Position.DOWN, Position.UP);
			break;
		case 'f':
			c.norm_rot(amt, Position.FRONT);
			c.mid_rot(amt, Position.FRONT, Position.BACK);
			break;
		case 'b':
			c.norm_rot(amt, Position.BACK);
			c.mid_rot(amt, Position.BACK, Position.FRONT);
			break;
		case 'M':
			c.mid_rot(amt, Position.LEFT, Position.RIGHT);
			break;
		case 'E':
			c.mid_rot(amt, Position.DOWN, Position.UP);
			break;
		case 'S':
			c.mid_rot(amt, Position.FRONT, Position.BACK);
			break;
		}
	}
	
	public void dispCube(String str) {
		if (str == null) { str = "Rubik's Cube"; }
		JFrame frame = new JFrame(str);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.ipadx = 10;
		con.ipady = 10;
		Face[] faces = c.getFaces();
//		System.out.println(faces.length);
		for (int i = 0; i < faces.length; i++) {
//			System.out.println("before: " + frame.getContentPane().getComponents().length);
			JLabel new_face = new JLabel(faces[i].getLabel());
			int[] cnates = faces[i].getPos().coords();
			con.gridx = cnates[0];
			con.gridy = cnates[1];
			frame.getContentPane().add(new_face, con);
//			System.out.println("after: " + frame.getContentPane().getComponents().length);
		}
		frame.setVisible(true);
	}
	
	public void printCube() { c.printCube(); }
	
	public void reset() { c.reset(); }
	
	public void random_scramble(int size) {
		reset(); //start with cube reset to scramble cube
		char select[] = {'R', 'L', 'U', 'D', 'F', 'B', 'r', 'l', 'u', 'd', 'f', 'b', 'M', 'E', 'S', 'x', 'y', 'z'};
		String scramble = "";
		Random rand = new Random();
		//18 different possible turns, and 3 possible amounts of rotations
		int turn_upper = select.length;
		int rot_upper = 3;
		int prev = 0;
		for (int i = 0; i < size; i++) {
			int turn = rand.nextInt(turn_upper);
			int amt = rand.nextInt(rot_upper);
			if (amt == 0) { amt = -1; } //handles the amount cases;
			char am = '0';
			switch (amt) {
			case -1:
				am = '\'';
				break;
			case 1:
				am = 0;
				break;
			case 2:
				am = '2';
				break;
			}
			char cr = '0';
			while (prev == turn) {
				//10% chance that turn will be the same rotation
				int chance = rand.nextInt(10);
				if (chance == 0) { break; }
				turn = rand.nextInt(turn_upper);
			}
			prev = turn;
			cr = select[turn];
			String input = Character.toString(cr);
			if (am != 0) {
				input += Character.toString(am);
			}
			run_command(input);
			scramble += input;
			if (i != size - 1) {
				scramble += " ";
			}
		}
		System.out.println("The scramble was " + scramble);
		
	}
}
