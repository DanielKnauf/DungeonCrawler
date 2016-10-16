package model_AdventureGame.theWorld;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author d-knauf
 *
 */
public class Dungeon {
	private Random randomizer = new Random();
	private int size;
	private Room playerRoom;
	private Room startRoom;
	private Room[][] map;
	private ArrayList<Room> roomsWithMonster;

	public Dungeon(int size, int rooms, Room[][] dungeonMap, Room startRoom, ArrayList<Room> roomsWithMonster) {
		this.size = size;
		this.map = dungeonMap;
		this.startRoom = startRoom;
		this.roomsWithMonster = roomsWithMonster;

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

	public void removeRoomFromRoomsWithMonster(Room roomWithDeadMonster) {
		roomsWithMonster.remove(roomWithDeadMonster);
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

		int[] coordinates = direction.getCoordinates(playerRoom.getRow(), playerRoom.getColumn());

		if (hasRoom(coordinates[0], coordinates[1])) {
			changeRoom(map[coordinates[0]][coordinates[1]]);
			return true;
		}

		return false;
	}

	/**
	 * Set variables for entered room.
	 * 
	 * @param enter
	 */
	private void changeRoom(Room enter) {
		if (playerRoom.equals(monsterMoves())) {

		} else {
			playerRoom.setHasPlayer(false);
			enter.setHasPlayer(true);
			this.playerRoom = enter;
		}
	}

	private Room monsterMoves() {
		// Choose a monster at random

		if (checkIfAllMonstersLocked()) {
			return moveMonster();
		} else {
			System.out.println("All monsters are locked. No monster is moved.");
			return null;
		}
	}

	private Room moveMonster() {
		Room monsterRoom = roomsWithMonster.get(randomizer.nextInt(roomsWithMonster.size()));
		System.out.println(monsterRoom.toString());

		if (hasFreeRoomToMove(monsterRoom)) {
			// Find the free rooms
			ArrayList<Room> freeRooms = findFreeRoomsToMoveIn(monsterRoom);

			// Choose one at random
			Room nextRoom = freeRooms.get(randomizer.nextInt(freeRooms.size()));

			// remove room form list
			monsterRoom.setHasMonster(false);
			roomsWithMonster.remove(monsterRoom);

			// add new monsterRoom to list
			nextRoom.setHasMonster(true);
			roomsWithMonster.add(nextRoom);

			System.out.println("Monster moves to " + nextRoom.toString());
			return nextRoom;
		} else

		{
			moveMonster();
		}

		return null;

	}

	/**
	 * Is true, when space contains a room.
	 * 
	 * @param row
	 * @param colm
	 * @return
	 */
	private boolean hasRoom(int row, int colm) {
		if (row >= size || row < 0 || colm < 0 || colm >= size) {
			return false;
		}

		return map[row][colm] != null;
	}

	/**
	 * Checks if there is a possible movement for one of the monsters.
	 * <p>
	 * <code>True</code>, when there is at least one possible movement for one
	 * of the monsters.
	 * 
	 * @return boolean
	 */
	private boolean checkIfAllMonstersLocked() {
		for (Room r : roomsWithMonster) {

			if (hasFreeRoomToMove(r)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * 
	 * @param previousRoom
	 * @return
	 */
	private boolean hasFreeRoomToMove(Room previousRoom) {
		int row = previousRoom.getRow();
		int colm = previousRoom.getColumn();

		if (roomIsFree(row + 1, colm) || roomIsFree(row - 1, colm) || roomIsFree(row, colm + 1)
				|| roomIsFree(row, colm - 1)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param row
	 * @param colm
	 * @return
	 */
	private boolean roomIsFree(int row, int colm) {

		if (hasRoom(row, colm)) {
			if (map[row][colm].checkForExit() || map[row][colm].getHasMonster()) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param previousRoom
	 * @return
	 */
	private ArrayList<Room> findFreeRoomsToMoveIn(Room previousRoom) {
		ArrayList<Room> freeRooms = new ArrayList<>();
		int row = previousRoom.getRow();
		int colm = previousRoom.getColumn();
		if (roomIsFree(row + 1, colm)) {
			freeRooms.add(map[row + 1][colm]);
		}
		if (roomIsFree(row - 1, colm)) {
			freeRooms.add(map[row - 1][colm]);
		}
		if (roomIsFree(row, colm + 1)) {
			freeRooms.add(map[row][colm + 1]);
		}
		if (roomIsFree(row, colm - 1)) {
			freeRooms.add(map[row][colm - 1]);
		}

		return freeRooms;
	}
}
