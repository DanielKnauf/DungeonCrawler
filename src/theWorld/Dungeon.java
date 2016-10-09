package theWorld;

import java.util.Random;

import rpslsFighting.Fight;
import theAdventure.Player;

public class Dungeon {
	private int size;
	private int rooms;
	private Room[][] map;

	public Dungeon(int size, int rooms) {
		this.size = size;
		this.rooms = rooms;
		initDungeonMap();
		boolean hasMonster = false;
		for (int i = 0; i < rooms; i++) {

			Room newRoom;

			if (hasMonster) {
				newRoom = new Room(hasMonster);
				hasMonster = false;

			} else {
				newRoom = new Room(hasMonster);
				hasMonster = true;
			}

			map[i][1] = newRoom;
		}

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

	public void goThroughtDungeon(Player hero) {
		for (int i = 0; i < size; i++) {

			if (getRoom(i).getHasMonster()) {
				System.out.println("\nYou encountered a monster!!!");
				new Fight(hero, new Player("Monster", determineHealth()));
				System.out.println("Health of the Hero: " + hero.displayHitpoints());
			}
			if (i + 1 < size) {
				playerChangeRoom(getRoom(i), getRoom(i + 1));
			}
		}
	}

	private int determineHealth() {
		int health = 1;
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(size);
		health += healthBonus;
		return health;
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
		displayDungeon();
	}

	public void playerChangeRoom(Room exit, Room enter) {
		exit.setHasPlayer(false);
		enter.setHasPlayer(true);
		displayDungeon();
	}

}
