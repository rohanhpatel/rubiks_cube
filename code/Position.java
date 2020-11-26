public enum Position { //using sideways cross as net (1-3-1-1 and front face is middle of the 3 stack)
	FRONT {
		public String toString() {
			return "front";
		}
		@Override
		public Position[] clock() {
			return new Position[] {LEFT, UP, RIGHT, DOWN};
		}
		@Override
		public Position across() {
			return BACK;
		}
		@Override
		public int[] coords(){
			return new int[] {1, 1};
		}
	}, 
	RIGHT {
		
		public String toString() {
			return "right";
		}
		@Override
		public Position[] clock() {
			return new Position[] {FRONT, UP, BACK, DOWN};
		}
		@Override
		public Position across() {
			return LEFT;
		}
		@Override
		public int[] coords(){
			return new int[] {2, 1};
		}
	},
	UP {
		
		public String toString() {
			return "up";
		}
		@Override
		public Position[] clock() {
			return new Position[] {LEFT, BACK, RIGHT, FRONT};
		}
		@Override
		public Position across() {
			return DOWN;
		}
		@Override
		public int[] coords(){
			return new int[] {1, 0};
		}
	},
	BACK {
		public String toString() {
			return "back";
		}
		@Override
		public Position[] clock() {
			return new Position[] {RIGHT, UP, LEFT, DOWN};
		}
		@Override
		public Position across() {
			return FRONT;
		}
		@Override
		public int[] coords(){
			return new int[] {3, 1};
		}
	},
	LEFT {
		public String toString() {
			return "left";
		}
		@Override
		public Position[] clock() {
			return new Position[] {BACK, UP, FRONT, DOWN};
		}
		@Override
		public Position across() {
			return RIGHT;
		}
		@Override
		public int[] coords(){
			return new int[] {0, 1};
		}
	},
	DOWN {
		public String toString() {
			return "down";
		}
		@Override
		public Position[] clock() {
			return new Position[] {LEFT, FRONT, RIGHT, BACK};
		}
		@Override
		public Position across() {
			return UP;
		}
		@Override
		public int[] coords(){
			return new int[] {1, 2};
		}
	};

	public Position[] clock() { return null; }
	public Position left() { return clock()[0]; }
	public Position up() { return clock()[1]; }
	public Position right() { return clock()[2]; }
	public Position down() { return clock()[3]; }
	public Position across() { return null; }
	public int[] coords() { return null; }
}
