package model_AdventureGame.theWorld;

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

	public Room[][] getDungeonMapp() {
		return this.map;
	}

	/**
	 * Initial set up for the playerPosition and playerRoom
	 */
	public void playerEntersDungeon() {
		startRoom.setHasPlayer(true);
		this.playerRoom = startRoom;
	}

	/**
	 * Finds the room the player is entering and changes all parameter to this
	 * room.
	 * <p>
	 * Returns true when change was successful.
	 * 
	 * @param direction
	 *            the direction the player wants to go
	 * @return boolean
	 */
	public boolean playerChangeRoom(Direction direction) {

		switch (direction) {

		case LEFT:
			if (hasRoom(playerRoom.getRow(), playerRoom.getColumn() - 1)) {
				changeRoom(map[playerRoom.getRow()][playerRoom.getColumn() - 1]);
				return true;
			}
			break;
		case RIGHT:
			if (hasRoom(playerRoom.getRow(), playerRoom.getColumn() + 1)) {
				changeRoom(map[playerRoom.getRow()][playerRoom.getColumn() + 1]);
				return true;
			}
			break;

		case UP:
			if (hasRoom(playerRoom.getRow() - 1, playerRoom.getColumn())) {
				changeRoom(map[playerRoom.getRow() - 1][playerRoom.getColumn()]);
				return true;
			}
			break;

		case DOWN:
			if (hasRoom(playerRoom.getRow() + 1, playerRoom.getColumn())) {
				changeRoom(map[playerRoom.getRow() + 1][playerRoom.getColumn()]);
				return true;
			}
			break;
		}
		return false;
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
