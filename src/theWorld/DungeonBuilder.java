package theWorld;

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

	public Dungeon generateDungeon(int size, int rooms) {
		if (size < 2) {
			size = 2;
		}
		if (rooms < 2) {
			rooms = 2;
		}
		if (rooms > size) {
			rooms = size;
		}

		this.size = size;
		this.rooms = rooms;

		generateDungeonMap();
		return new Dungeon(this.size, this.rooms, dungeonMap, startRoom);

	}

	private void generateDungeonMap() {
		initDungeonMap();
		addRoomsToMap();

	}

	/**
	 * Initializing the dungeon map with blanks.
	 * 
	 * @return
	 */
	private void initDungeonMap() {
		this.dungeonMap = new Room[size][size];
		for (int row = 0; row < size; row++) {
			for (int colm = 0; colm < size; colm++) {
				dungeonMap[row][colm] = null;
			}
		}
	}

	private void addRoomsToMap() {
		this.numberOfAddedRooms = 0;
		addStartRoom();
		determineNextRoom(startRoom);
	}

	/**
	 * Determine if room has a monster in it.
	 * 
	 * @return
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
		dungeonMap[startRoom.getX()][startRoom.getY()] = startRoom;
		numberOfAddedRooms++;
	}

	private void determineNextRoom(Room previousRoom) {
		while (numberOfAddedRooms < rooms) {
			Direction direction = Direction.randomDirection();
			int newRow;
			int newColm;
			switch (direction) {
			case UP:
				newRow = previousRoom.getX() - 1;
				newColm = previousRoom.getY();
				addNewRoomToDungeonMap(previousRoom, direction, newRow, newColm);
				break;
			case LEFT:
				newRow = previousRoom.getX();
				newColm = previousRoom.getY() - 1;
				addNewRoomToDungeonMap(previousRoom, direction, newRow, newColm);
				break;
			case DOWN:
				newRow = previousRoom.getX() + 1;
				newColm = previousRoom.getY();
				addNewRoomToDungeonMap(previousRoom, direction, newRow, newColm);
				break;
			case RIGHT:
				newRow = previousRoom.getX();
				newColm = previousRoom.getY() + 1;
				addNewRoomToDungeonMap(previousRoom, direction, newRow, newColm);
				break;
			}
		}
	}

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

	private Room updateDirections(Room newRoom, Direction directionToRoom) {
		newRoom.addPossibleDirection(directionToRoom.getOpposite());
		return newRoom;
	}

	/**
	 * Is true, when space contains no room.
	 * 
	 * @param row
	 * @param colm
	 * @return
	 */
	private boolean hasNoRoom(int row, int colm) {
		if (row >= size || row < 0 || colm < 0 || colm >= size) {
			return false;
		}

		return dungeonMap[row][colm] == null;
	}
}
