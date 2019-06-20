package model.theWorld;

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

		@Override
		public int[] getCoordinates(int row, int column) {
			int[] coordinates = { row - 1, column };
			return coordinates;
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

		@Override
		public int[] getCoordinates(int row, int column) {
			int[] coordinates = { row + 1, column };
			return coordinates;
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

		@Override
		public int[] getCoordinates(int row, int column) {
			int[] coordinates = { row, column + 1 };
			return coordinates;
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

		@Override
		public int[] getCoordinates(int row, int column) {
			int[] coordinates = { row, column - 1 };
			return coordinates;
		}

	};

	public abstract String getName();

	public abstract Direction getOpposite();

	public abstract int[] getCoordinates(int row, int column);

	public static Direction randomDirection() {
		Random randomizer = new Random();
		return values()[randomizer.nextInt(4)];
	}
}
