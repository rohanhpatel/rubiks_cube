
public class CubeMain {
	
	public static void long_test(CubeWrapper c_wrap) {
//		String t_perm = "R U R' U' R' F R2 U' R' U' R U R' F'";
//		String y_perm = "F R U' R' U' R U R' F' R U R' U' R' F R F'";
//		String jb_perm = "R' U2 R U R' U2 L U' R U L'";
//		String u_str = "U";
//		String x_rot = "y2";
//		c_wrap.run_alg(t_perm);
//		c_wrap.printCube();
//		System.out.println("next perm: Y-PERM");
//		c_wrap.run_alg(y_perm);
//		c_wrap.printCube();
//		System.out.println("next perm: U");
//		c_wrap.run_alg(u_str);
//		c_wrap.printCube();
//		System.out.println("next perm: y2");
//		c_wrap.run_alg(x_rot);
//		c_wrap.printCube();
//		System.out.println("next perm: J-B PERM");
//		c_wrap.run_alg(jb_perm);
//		c_wrap.printCube();
	}
	
	public static void main(String[] args) {
		CubeWrapper c = new CubeWrapper();
//		c.random_scramble(10);
//		//maybe we can also implement a solver??
//		c.printCube();
		c.dispCube(null);
		c.run_alg("r2", true);
//		c.right(1);
//		c.printCube();
	}

}
