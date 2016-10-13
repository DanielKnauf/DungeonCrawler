package theWorld;

import java.util.Random;

public enum Direction {
	UP {
		@Override
		public String getName() {
			return "go up";
		}

		@Override
		public Direction getOpposite() {
			return DOWN;
		}
	},
	DOWN {
		@Override
		public String getName() {
			return "go down";
		}

		@Override
		public Direction getOpposite() {
			return UP;
		}
	},
	RIGHT {
		@Override
		public String getName() {
			return "go right";
		}

		@Override
		public Direction getOpposite() {
			return LEFT;
		}
	},
	LEFT {
		@Override
		public String getName() {
			return "go left";
		}

		@Override
		public Direction getOpposite() {
			return RIGHT;
		}

	};

	public abstract String getName();

	public abstract Direction getOpposite();

	public static Direction randomDirection() {
		Random randomizer = new Random();
		return values()[randomizer.nextInt(4)];
	}
}
