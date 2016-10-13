package controller_AdventureGame.theAdventure;

import java.util.Random;

import model_AdventureGame.rpslsFighting.Fight;
import model_AdventureGame.theComponents.Hero;
import model_AdventureGame.theComponents.Monster;
import theWorld.Dungeon;
import theWorld.DungeonBuilder;
import view.AdventureGame.View;

public class AdventureTime {
	private static DungeonBuilder dungeonBuilder = new DungeonBuilder();
	private static Hero hero;
	private static View view;

	public static void main(String[] args) {
		view = new View();
		hero = new Hero("John", 5);

		// first dungeon
		Dungeon firstDungeon = dungeonBuilder.generateDungeon(6, 15);
		goThroughtDungeon(firstDungeon);

		if (view.nextDungeon() == 0) {
			System.out.println("NextDungeon");
			Dungeon secondDungeon = dungeonBuilder.generateDungeon(4, 5);
			goThroughtDungeon(secondDungeon);
		}

		System.out.println("After the adventure our hero goes home.");

	}

	private static void goThroughtDungeon(Dungeon dungeon) {
		dungeon.displayDungeon();
		dungeon.playerEntersDungeon();
		view.displayPlayerPositon(dungeon.getPlayerRoom());

		while (dungeon.playerChangeRoom(View.choooseNextMove(dungeon.getPlayerRoom()))) {
			dungeon.displayDungeon();

			if (dungeon.getPlayerRoom().getHasMonster()) {
				System.out.println("\nYou encountered a monster!!!");
				new Fight(hero, new Monster("Monster", determineMonsterHealth(dungeon)));
				dungeon.getPlayerRoom().setHasMonster(false);
				System.out.println("Health of the Hero: " + hero.displayHitpoints());
				dungeon.displayDungeon();
			}

			if (dungeon.getPlayerRoom().checkForExit()) {
				System.out.println("________\nEnd of Dungeon");
				break;
			}

		}
	}

	/**
	 * 
	 * @param dungeon
	 * @return
	 */
	private static int determineMonsterHealth(Dungeon dungeon) {
		int health = 1;
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(dungeon.getSize());
		health += healthBonus / 3;
		return health;
	}

}
