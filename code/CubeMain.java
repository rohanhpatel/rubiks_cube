
public class CubeMain {
	
	public static void long_test(CubeWrapper c_wrap) {
		String t_perm = "R U R' U' R' F R2 U' R' U' R U R' F'";
		String y_perm = "F R U' R' U' R U R' F' R U R' U' R' F R F'";
		String jb_perm = "R' U2 R U R' U2 L U' R U L'";
		String u_str = "U";
		String x_rot = "y2";
		c_wrap.run_alg(t_perm);
		c_wrap.printCube();
		System.out.println("next perm: Y-PERM");
		c_wrap.run_alg(y_perm);
		c_wrap.printCube();
		System.out.println("next perm: U");
		c_wrap.run_alg(u_str);
		c_wrap.printCube();
		System.out.println("next perm: y2");
		c_wrap.run_alg(x_rot);
		c_wrap.printCube();
		System.out.println("next perm: J-B PERM");
		c_wrap.run_alg(jb_perm);
		c_wrap.printCube();
	}
	
	public static void main(String[] args) {
		CubeWrapper c = new CubeWrapper();
		String cycle_edge = "M2 U' M U2 M' U' M2";
		String rev_cycle_edge = "M2 U M U2 M' U M2";
		c.run_alg(cycle_edge);
		c.run_alg(rev_cycle_edge);
		c.printCube();
		c.run_alg("E");
		c.printCube();
		c.run_alg("E'");
		c.run_alg("S");
		c.printCube();
	}

}
