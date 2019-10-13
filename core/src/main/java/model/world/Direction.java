package model.world;

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
            return new int[]{row - 1, column};
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
            return new int[]{row + 1, column};
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
            return new int[]{row, column - 1};
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
            return new int[]{row, column + 1};
        }
    };

    public abstract String getName();

    public abstract Direction getOpposite();

    public abstract int[] getCoordinates(int row, int column);
}
