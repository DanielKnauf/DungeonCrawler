package theWorld;

import java.util.Random;

import rpslsFighting.Fight;
import theAdventure.Player;

public class Dungeon {
	private int rooms;
	private Room[][] map;

	public Dungeon(int rooms) {
		this.setRooms(rooms);
		initDungeonMap();
		boolean hasMonster = false;
		for (int i = 0; i < rooms; i++) {

			Room newRoom;

			if (hasMonster) {
				newRoom = new Room(hasMonster);
				hasMonster = false;

				map[i][2] = newRoom;

			} else {
				newRoom = new Room(hasMonster);
				hasMonster = true;
			}

			map[i][1] = newRoom;
		}

	}

	private void initDungeonMap() {
		map = new Room[rooms][rooms];
		for (int j = 0; j < rooms; j++) {
			for (int i = 0; i < rooms; i++) {
				map[j][i] = null;
			}
		}
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public void displayDungeon() {
		System.out.println("Map of the Dungeon");
		for (int j = 0; j < rooms; j++) {
			for (int i = 0; i < rooms; i++) {
				if (map[j][i] == null) {
					System.out.print(" _ ");
				} else {
					System.out.print(map[j][i].displayRoom());
				}
			}
			System.out.print("\n");
		}
	}

	public void goThroughtDungeon(Player hero) {
		for (int i = 0; i < rooms; i++) {
			if (getRoom(i).getHasMonster()) {
				System.out.println("\nYou encountered a monster!!!");
				new Fight(hero, new Player("Monster", determineHealth()));
				System.out.println("Health of the Hero: " + hero.displayHitpoints());
			}
		}
	}

	private int determineHealth() {
		int health = 1;
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(rooms);
		health += healthBonus;
		return health;
	}

	public Room getRoom(int roomNumber) {
		if (roomNumber >= rooms) {
			System.out.println("RoomNumber is too high");
			return null;
		} else {
			return map[roomNumber][1];
		}
	}

}
