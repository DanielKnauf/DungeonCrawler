package theWorld;

import java.util.Random;

public class DungeonBuilder {

	private int size;
	private int rooms;

	public DungeonBuilder() {

	}

	public Dungeon generateDungeon(int size, int rooms) {
		if (rooms > size) {
			rooms = size;
		}

		this.size = size;
		this.rooms = rooms;

		Room[][] dungeonMap = generateDungeonMap();
		return new Dungeon(size, rooms, dungeonMap);

	}

	private Room[][] generateDungeonMap() {
		Room[][] dungeonMap = initDungeonMap();
		dungeonMap = addRoomsToMap(dungeonMap);

		return dungeonMap;
	}

	private Room[][] addRoomsToMap(Room[][] dungeonMap) {

		for (int i = 0; i < rooms; i++) {
			boolean hasMonster = hasMonster();
			Room newRoom;

			if (hasMonster) {
				newRoom = new Room(hasMonster, i, 1);
				newRoom.addPossibleDirection(Direction.RIGHT);

				hasMonster = false;
				dungeonMap[i][2] = new Room(hasMonster, i, 2);
				dungeonMap[i][2].addPossibleDirection(Direction.LEFT);

			} else {
				newRoom = new Room(hasMonster, i, 1);
				hasMonster = true;
			}
			newRoom.addPossibleDirection(Direction.DOWN);

			if (i + 1 == rooms) {
				newRoom.isExit();
			}
			dungeonMap[i][1] = newRoom;
		}

		return dungeonMap;
	}

	private Room[][] initDungeonMap() {
		Room[][] dungeonMap = new Room[size][size];
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				dungeonMap[j][i] = null;
			}
		}
		return dungeonMap;
	}

	/**
	 * TODO: improve calculation Determine if room has a monster in it.
	 * 
	 * @return
	 */
	private boolean hasMonster() {
		boolean hasMonster = false;
		Random randomizer = new Random();
		int random = randomizer.nextInt(3);
		System.out.println("Monster:" + random);
		if (random == 0) {
			hasMonster = true;
		}
		return hasMonster;
	}
}
