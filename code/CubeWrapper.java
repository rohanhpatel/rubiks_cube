import java.util.Random;

public class CubeWrapper {
	Cube c = new Cube();
	public CubeWrapper() {
		System.out.println("This cube uses the Western color scheme");
		System.out.println("It starts with white in the front, red on the right, and blue on the top");
		System.out.println();
	}
	
	public void run_alg(String str) {
		String[] steps = str.split(" ");
		for (int i = 0; i < steps.length; i++) {
			run_command(steps[i]);
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
			c.right(amt);
			break;
		case 'L':
			c.left(amt);
			break;
		case 'U':
			c.up(amt);
			break;
		case 'B':
			c.back(amt);
			break;
		case 'D':
			c.down(amt);
			break;
		case 'F':
			c.front(amt);
			break;
		case 'x':
			c.x(amt);
			break;
		case 'y':
			c.y(amt);
			break;
		case 'z':
			c.z(amt);
			break;
		case 'r':
			c.little_r(amt);
			break;
		case 'l':
			c.little_l(amt);
			break;
		case 'u':
			c.little_u(amt);
			break;
		case 'd':
			c.little_d(amt);
			break;
		case 'f':
			c.little_f(amt);
			break;
		case 'b':
			c.little_b(amt);
			break;
		case 'M':
			c.middle(amt);
			break;
		case 'E':
			c.equator(amt);
			break;
		case 'S':
			c.standing(amt);
			break;
		}
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
