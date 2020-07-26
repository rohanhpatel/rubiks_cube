
public class CubeMain {
	
	public static void t_perm(Cube c) {
		c.right(1);
		c.up(1);
		c.right(-1);
		c.up(-1);
		c.right(-1);
		c.front(-1);
		c.right(2);
		c.up(-1);
		c.right(-1);
		c.up(-1);
		c.right(1);
		c.up(1);
		c.right(-1);
		c.front(-1);
	}
	
	public static void main(String[] args) {
		Cube c = new Cube();
		System.out.println("before");
		c.printCube();
		c.front(1);
		System.out.println("after 1 B()");
		c.printCube();
//		c.printCube();

	}

}
