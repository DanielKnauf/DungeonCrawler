package model_AdventureGame.theWorld;

import java.util.ArrayList;
import java.util.Random;

public class DungeonBuilder {
	private Random randomizer = new Random();
	private int rowSize;
	private int colmSize;
	private int rooms;
	private int numberOfAddedRooms;
	private Room[][] dungeonMap;
	private Room startRoom;
	private ArrayList<Room> roomsWithMonster = new ArrayList<Room>();

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
	public Dungeon generateDungeon(int rowSize, int colmSize, int rooms) {
		// Dungeon must at least be 2x2 large
		if (rowSize < 2) {
			rowSize = 2;
		}
		if (colmSize < 2) {
			colmSize = 2;
		}

		// Dungeon must have at least two rooms (Start and Exit)
		if (rooms < 2) {
			rooms = 2;
		}

		// Fit the number of rooms do not fit inside the dungeonMap the rooms
		// are reduced
		if (rooms > rowSize * colmSize) {
			rooms = rowSize * colmSize;
		}

		this.rowSize = rowSize;
		this.colmSize = colmSize;
		this.rooms = rooms;
		initDungeonMap();
		addRoomsToMap();
		return new Dungeon(this.rowSize, this.colmSize, this.rooms, dungeonMap, startRoom, roomsWithMonster);

	}

	/**
	 * Initializing the dungeon map with blanks.
	 */
	private void initDungeonMap() {
		this.dungeonMap = new Room[rowSize][colmSize];
		for (int row = 0; row < rowSize; row++) {
			for (int colm = 0; colm < colmSize; colm++) {
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
		this.startRoom = new Room(false, randomizer.nextInt(rowSize), randomizer.nextInt(colmSize));
		dungeonMap[startRoom.getRow()][startRoom.getColumn()] = startRoom;
		numberOfAddedRooms++;
	}

	/**
	 * Adds new rooms to the dungeon map.
	 * <p>
	 * Randomly determines a direction in which the next room for the dungeon
	 * lies.
	 * 
	 * @param previousRoom
	 *            the room from where the new room is determined
	 */
	private void determineNextRoom(Room previousRoom) {

		while (numberOfAddedRooms < rooms && !checkForDeadLock(previousRoom)) {
			// Determine possible directions
			ArrayList<Direction> possibleDirections = findPossibleDirections(previousRoom);
			// Choose one direction at random
			Direction direction = possibleDirections.get(randomizer.nextInt(possibleDirections.size()));
			// Get coordinates for next square
			int[] nextCoordinates = direction.getCoordinates(previousRoom.getRow(), previousRoom.getColumn());
			// Add room to the square
			addNewRoomToDungeonMap(previousRoom, nextCoordinates[0], nextCoordinates[1]);

		}
	}

	/**
	 * Check for possible squares in which the new room can be placed.
	 * 
	 * @param previousRoom
	 *            the room from where the next square is searched.
	 * @return ArrayList of possible squares
	 */
	private ArrayList<Direction> findPossibleDirections(Room previousRoom) {
		ArrayList<Direction> possibleDirection = new ArrayList<>();
		int previousRow = previousRoom.getRow();
		int previousColumn = previousRoom.getColumn();

		// Check each direction and at it to the arrayList if it is a free
		// square
		if (hasNoRoom(previousRow - 1, previousColumn)) {
			possibleDirection.add(Direction.UP);
		}
		if (hasNoRoom(previousRow + 1, previousColumn)) {
			possibleDirection.add(Direction.DOWN);
		}
		if (hasNoRoom(previousRow, previousColumn - 1)) {
			possibleDirection.add(Direction.LEFT);
		}
		if (hasNoRoom(previousRow, previousColumn + 1)) {
			possibleDirection.add(Direction.RIGHT);
		}

		return possibleDirection;
	}

	/**
	 * Checks if the room has a free square for the next room around it.
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
	 * @param newRow
	 *            the row of the square for the new room
	 * @param newColm
	 *            the column of the square for the new room
	 */
	private void addNewRoomToDungeonMap(Room previousRoom, int newRow, int newColm) {
		Room newRoom;
		if (hasNoRoom(newRow, newColm)) {
			if (hasMonster()) {
				newRoom = new Room(true, newRow, newColm);
				roomsWithMonster.add(newRoom);
			} else {
				newRoom = new Room(false, newRow, newColm);
			}
			numberOfAddedRooms++;
			if (numberOfAddedRooms == rooms) {
				newRoom.isExit();
			}
			newRoom = updateDirections(newRoom);

			dungeonMap[newRow][newColm] = newRoom;

			if (numberOfAddedRooms < rooms) {
				determineNextRoom(newRoom);
			}
		}
	}

	/**
	 * Adds possible direction to new room and updates direction of adjacent
	 * rooms.
	 * 
	 * @param newRoom
	 *            the newly added room
	 * @return room with updated possible directions
	 */
	private Room updateDirections(Room newRoom) {

		if (hasRoom(newRoom.getRow() - 1, newRoom.getColumn())) {
			newRoom.addPossibleDirection(Direction.UP);
			dungeonMap[newRoom.getRow() - 1][newRoom.getColumn()].addPossibleDirection(Direction.UP.getOpposite());
		}

		if (hasRoom(newRoom.getRow() + 1, newRoom.getColumn())) {
			newRoom.addPossibleDirection(Direction.DOWN);
			dungeonMap[newRoom.getRow() + 1][newRoom.getColumn()].addPossibleDirection(Direction.DOWN.getOpposite());

		}
		if (hasRoom(newRoom.getRow(), newRoom.getColumn() - 1)) {
			newRoom.addPossibleDirection(Direction.LEFT);
			dungeonMap[newRoom.getRow()][newRoom.getColumn() - 1].addPossibleDirection(Direction.LEFT.getOpposite());

		}
		if (hasRoom(newRoom.getRow(), newRoom.getColumn() + 1)) {
			newRoom.addPossibleDirection(Direction.RIGHT);
			dungeonMap[newRoom.getRow()][newRoom.getColumn() + 1].addPossibleDirection(Direction.RIGHT.getOpposite());

		}

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
		if (row >= rowSize || row < 0 || colm < 0 || colm >= colmSize) {
			return false;
		}
		return dungeonMap[row][colm] == null;
	}

	/**
	 * Is true, when space contains a room.
	 * 
	 * @param row
	 *            the row of the square that is checked
	 * @param colm
	 *            the column of the square that is checked
	 * @return
	 */
	private boolean hasRoom(int row, int colm) {
		if (row >= rowSize || row < 0 || colm < 0 || colm >= colmSize) {
			return false;
		}
		return dungeonMap[row][colm] != null;
	}
}
