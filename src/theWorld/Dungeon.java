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
		System.out.println("\nMap of the Dungeon");
		for (int j = 0; j < rooms; j++) {
			System.out.print(j);
			for (int i = 0; i < rooms; i++) {

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
		for (int i = 0; i < rooms; i++) {

			if (getRoom(i).getHasMonster()) {
				System.out.println("\nYou encountered a monster!!!");
				new Fight(hero, new Player("Monster", determineHealth()));
				System.out.println("Health of the Hero: " + hero.displayHitpoints());
			}
			if (i + 1 < rooms) {
				playerChangeRoom(getRoom(i), getRoom(i + 1));
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
