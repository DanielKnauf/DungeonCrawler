package model_AdventureGame.theWorld;

import java.util.Random;

public class DungeonBuilder {
	private Random randomizer = new Random();
	private int size;
	private int rooms;
	private int numberOfAddedRooms;
	private Room[][] dungeonMap;
	private Room startRoom;

	public DungeonBuilder() {

	}

	/**
	 * Create a new dungeon map and fills it with rooms.
	 * 
	 * @param size
	 *            the size of the new dungeon
	 * @param rooms
	 *            the number of rooms inside the dungeon
	 * 
	 * @return finished dungeon
	 */
	public Dungeon generateDungeon(int size, int rooms) {
		if (size < 2) {
			size = 2;
		}
		if (rooms < 2) {
			rooms = 2;
		}
		if (rooms > size * size) {
			rooms = size * size;
		}

		this.size = size;
		this.rooms = rooms;
		initDungeonMap();
		addRoomsToMap();
		return new Dungeon(this.size, this.rooms, dungeonMap, startRoom);

	}

	/**
	 * Initializing the dungeon map with blanks.
	 */
	private void initDungeonMap() {
		this.dungeonMap = new Room[size][size];
		for (int row = 0; row < size; row++) {
			for (int colm = 0; colm < size; colm++) {
				dungeonMap[row][colm] = null;
			}
		}
	}

	/**
	 * Fills dungeon with rooms. Number of added rooms is determined by
	 * parameter <code>rooms</code>
	 */
	private void addRoomsToMap() {
		this.numberOfAddedRooms = 0;
		addStartRoom();
		determineNextRoom(startRoom);
	}

	/**
	 * Randomly determine if room has a monster in it.
	 * <p>
	 * Chance that room has a monster is one of three.
	 * 
	 * @return boolean
	 */
	private boolean hasMonster() {
		boolean hasMonster = false;
		// TODO: improve calculation.
		int random = randomizer.nextInt(3);
		if (random == 0) {
			hasMonster = true;
		}
		return hasMonster;
	}

	/**
	 * Adds entrance point to new dungeon.
	 */
	private void addStartRoom() {
		this.startRoom = new Room(false, randomizer.nextInt(size), randomizer.nextInt(size));
		dungeonMap[startRoom.getRow()][startRoom.getColumn()] = startRoom;
		numberOfAddedRooms++;
	}

	/**
	 * Adds new rooms to the dungeon map.
	 * <p>
	 * Randomly determines a direction in which the next room for the dungeon
	 * lies. Switch case chooses which is the next square of the map to look at.
	 * 
	 * @param previousRoom
	 *            the room from where the new room is determined
	 */
	private void determineNextRoom(Room previousRoom) {

		while (numberOfAddedRooms < rooms && !checkForDeadLock(previousRoom)) {

			Direction direction = Direction.randomDirection();
			int newRow = -1;
			int newColm = -1;
			switch (direction) {
			case UP:
				newRow = previousRoom.getRow() - 1;
				newColm = previousRoom.getColumn();
				break;
			case LEFT:
				newRow = previousRoom.getRow();
				newColm = previousRoom.getColumn() - 1;
				break;
			case DOWN:
				newRow = previousRoom.getRow() + 1;
				newColm = previousRoom.getColumn();
				break;
			case RIGHT:
				newRow = previousRoom.getRow();
				newColm = previousRoom.getColumn() + 1;
				break;
			}
			addNewRoomToDungeonMap(previousRoom, direction, newRow, newColm);

		}
	}

	/**
	 * CHecks if the room has a free square for the next room around it.
	 * <p>
	 * <code>True</code>, when room has no free square around it.
	 * 
	 * @param previousRoom
	 *            the room from which the next room is searched
	 * @return boolean
	 */
	private boolean checkForDeadLock(Room previousRoom) {
		int row = previousRoom.getRow();
		int colm = previousRoom.getColumn();

		if (!hasNoRoom(row + 1, colm) && !hasNoRoom(row - 1, colm) && !hasNoRoom(row, colm + 1)
				&& !hasNoRoom(row, colm - 1)) {
			return true;
		}

		return false;

	}

	/**
	 * Checks if the determined square is empty and within the size of the
	 * dungeon.
	 * 
	 * @param previousRoom
	 *            the room from where the new room is determined
	 * @param directionToNewRoom
	 *            the direction with which the new room is reached from the
	 *            previous room
	 * @param newRow
	 *            the row of the square for the new room
	 * @param newColm
	 *            the column of the square for the new room
	 */
	private void addNewRoomToDungeonMap(Room previousRoom, Direction directionToNewRoom, int newRow, int newColm) {
		Room newRoom;
		if (hasNoRoom(newRow, newColm)) {
			newRoom = new Room(hasMonster(), newRow, newColm);
			numberOfAddedRooms++;
			if (numberOfAddedRooms == rooms) {
				newRoom.isExit();
			}
			newRoom = updateDirections(newRoom, directionToNewRoom);

			dungeonMap[newRow][newColm] = newRoom;

			previousRoom.addPossibleDirection(directionToNewRoom);
			if (numberOfAddedRooms < rooms) {
				determineNextRoom(newRoom);
			}
		}
	}

	/**
	 * Adds the direction to go to the previous room to possible directions of
	 * new room.
	 * 
	 * @param newRoom
	 * @param directionToRoom
	 * @return room with updated possible directions
	 */
	private Room updateDirections(Room newRoom, Direction directionToRoom) {
		newRoom.addPossibleDirection(directionToRoom.getOpposite());
		return newRoom;
	}

	/**
	 * Is true, when space contains no room.
	 * 
	 * @param row
	 *            the row of the square that is checked
	 * @param colm
	 *            the column of the square that is checked
	 * @return boolean
	 */
	private boolean hasNoRoom(int row, int colm) {
		if (row >= size || row < 0 || colm < 0 || colm >= size) {
			return false;
		}
		return dungeonMap[row][colm] == null;
	}
}
