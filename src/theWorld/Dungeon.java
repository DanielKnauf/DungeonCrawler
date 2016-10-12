package theWorld;

public class Dungeon {
	private int size;
	private Room playerRoom;
	private Room startRoom;
	private Room[][] map;

	public Dungeon(int size, int rooms, Room[][] dungeonMap, Room startRoom) {
		this.size = size;
		this.map = dungeonMap;
		this.startRoom = startRoom;
	}

	public int getSize() {
		return this.size;
	}

	public Room getPlayerRoom() {
		return playerRoom;
	}

	public void displayDungeon() {
		System.out.println("\nMap of the Dungeon");
		for (int j = 0; j < size; j++) {
			System.out.print(j);
			for (int i = 0; i < size; i++) {

				if (map[j][i] == null) {
					System.out.print(" ####### ");
				} else {
					System.out.print(map[j][i].displayRoom());
				}
			}
			System.out.print("\n");
		}
	}

	public void playerEntersDungeon() {
		startRoom.setHasPlayer(true);
		this.playerRoom = startRoom;
		displayDungeon();
	}

	public boolean playerChangeRoom(Direction direction) {

		switch (direction) {

		case LEFT:
			if (hasRoom(playerRoom.getX(), playerRoom.getY() - 1)) {
				changeRoom(map[playerRoom.getX()][playerRoom.getY() - 1]);
				return true;
			} else {
				return false;
			}
		case RIGHT:
			if (hasRoom(playerRoom.getX(), playerRoom.getY() + 1)) {
				changeRoom(map[playerRoom.getX()][playerRoom.getY() + 1]);
				return true;
			} else {
				return false;
			}

		case UP:
			if (hasRoom(playerRoom.getX() - 1, playerRoom.getY())) {
				changeRoom(map[playerRoom.getX() - 1][playerRoom.getY()]);
				return true;
			} else {
				return false;
			}

		case DOWN:
			if (hasRoom(playerRoom.getX() + 1, playerRoom.getY())) {
				changeRoom(map[playerRoom.getX() + 1][playerRoom.getY()]);
				return true;
			} else {
				return false;
			}
		default:
			return false;

		}
	}

	/**
	 * Set variables for entered room.
	 * 
	 * @param enter
	 */
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
