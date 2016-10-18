package controller_AdventureGame.theAdventure;

import model_AdventureGame.rpslsFighting.Fight;
import model_AdventureGame.theComponents.Hero;
import model_AdventureGame.theComponents.Monster;
import model_AdventureGame.theWorld.Dungeon;
import model_AdventureGame.theWorld.DungeonBuilder;
import view.AdventureGame.View;

public class AdventureTime {
	private static DungeonBuilder dungeonBuilder = new DungeonBuilder();
	private static Hero hero;
	private static View view;

	public static void main(String[] args) {
		view = new View();
		hero = new Hero("John", 5);

		// first dungeon
		Dungeon firstDungeon = dungeonBuilder.generateDungeon(7, 4, 11);
		goThroughtDungeon(firstDungeon);

		if (view.nextDungeon() == 0) {
			Dungeon secondDungeon = dungeonBuilder.generateDungeon(4, 5, 5);
			goThroughtDungeon(secondDungeon);
		}

		System.out.println("After the adventure our hero goes home.");

	}

	private static void goThroughtDungeon(Dungeon dungeon) {
		dungeon.playerEntersDungeon();
		view.playerEntersDungeon(dungeon);

		while (dungeon.playerChangeRoom(view.choooseNextMove(dungeon.getPlayerRoom()))) {
			view.displayDungeon(dungeon);

			if (dungeon.getPlayerRoom().getHasMonster()) {
				System.out.println("\nYou encountered a monster!!!");
				new Fight(hero, new Monster("Monster"));
				dungeon.getPlayerRoom().setHasMonster(false);
				dungeon.removeRoomFromRoomsWithMonster(dungeon.getPlayerRoom());
				System.out.println("Health of the Hero: " + hero.displayHitpoints());
				view.displayDungeon(dungeon);
			}

			if (dungeon.getPlayerRoom().checkForExit()) {
				System.out.println("________\nEnd of Dungeon");
				break;
			}

		}
	}

}
