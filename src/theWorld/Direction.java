package theWorld;

public enum Direction {
	UP {
		@Override
		public String getName() {
			return "go up";
		}
	},
	DOWN {
		@Override
		public String getName() {
			return "go down";
		}
	},
	RIGHT {
		@Override
		public String getName() {
			return "go right";
		}
	},
	LEFT {
		@Override
		public String getName() {
			return "go left";
		}

	};

	public abstract String getName();
}
