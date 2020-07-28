public class CubeWrapper {
	Cube c = new Cube();
	public CubeWrapper() {
		
	}
	
	public void run_alg(String str) {
		String[] steps = str.split(" ");
		for (int i = 0; i < steps.length; i++) {
			run_command(steps[i]);
			System.out.println(steps[i]);
			c.printOrient();
		}
	}
	
	public void run_command(String str) {
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
}
