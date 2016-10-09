package theWorld;

public class Dungeon {
	private int size;
	private int rooms;
	private Room playerRoom;
	private Room[][] map;

	public Dungeon(int size, int rooms) {
		this.size = size;
		this.rooms = rooms;
		initDungeonMap();
		boolean hasMonster = false;
		for (int i = 0; i < rooms; i++) {

			Room newRoom;

			if (hasMonster) {
				newRoom = new Room(hasMonster, i, 1);
				newRoom.addPossibleDirection(Direction.RIGHT);

				hasMonster = false;
				map[i][2] = new Room(hasMonster, i, 2);
				map[i][2].addPossibleDirection(Direction.LEFT);

			} else {
				newRoom = new Room(hasMonster, i, 1);
				hasMonster = true;
			}
			newRoom.addPossibleDirection(Direction.DOWN);

			map[i][1] = newRoom;
		}

	}

	public int getSize() {
		return this.size;
	}

	public Room getPlayerRoom() {
		return playerRoom;
	}

	private void initDungeonMap() {
		map = new Room[size][size];
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				map[j][i] = null;
			}
		}
	}

	public void displayDungeon() {
		System.out.println("\nMap of the Dungeon");
		for (int j = 0; j < size; j++) {
			System.out.print(j);
			for (int i = 0; i < size; i++) {

				if (map[j][i] == null) {
					System.out.print(" _______ ");
				} else {
					System.out.print(map[j][i].displayRoom());
				}
			}
			System.out.print("\n");
		}
	}

	public Room getRoom(int roomNumber) {
		if (roomNumber >= size) {
			System.out.println("RoomNumber is too high");
			return null;
		} else {
			return map[roomNumber][1];
		}
	}

	public void playerEntersDungeon() {
		map[0][1].setHasPlayer(true);
		this.playerRoom = map[0][1];
		displayDungeon();
	}

	public boolean playerChangeRoom(Direction direction) {

		switch (direction) {

		case LEFT:
			if (hasRoom(playerRoom.getX(), playerRoom.getY() - 1)) {
				Room enter = map[playerRoom.getX()][playerRoom.getY() - 1];
				changeRoom(enter);
				return true;
			} else {
				return false;
			}
		case RIGHT:
			if (hasRoom(playerRoom.getX(), playerRoom.getY() + 1)) {
				Room enter = map[playerRoom.getX()][playerRoom.getY() + 1];
				changeRoom(enter);
				return true;
			} else {
				return false;
			}

		case UP:
			if (hasRoom(playerRoom.getX() + 1, playerRoom.getY())) {
				Room enter = map[playerRoom.getX() + 1][playerRoom.getY()];
				changeRoom(enter);
				return true;
			} else {
				return false;
			}

		case DOWN:
			if (hasRoom(playerRoom.getX() + 1, playerRoom.getY())) {
				Room enter = map[playerRoom.getX() + 1][playerRoom.getY()];
				changeRoom(enter);
				return true;
			} else {
				return false;
			}
		default:
			return false;

		}
	}

	private void changeRoom(Room enter) {
		playerRoom.setHasPlayer(false);
		enter.setHasPlayer(true);
		this.playerRoom = enter;
	}

	/**
	 * Is true, when space contains a room.
	 * 
	 * @param j
	 * @param i
	 * @return
	 */
	private boolean hasRoom(int j, int i) {
		return map[j][i] != null;
	}

}
