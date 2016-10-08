package theAdventure;

import java.util.Scanner;

import theWorld.Dungeon;

public class AdventureTime {
	private static Scanner inputScanner;

	public static void main(String[] args) {
		Player hero = new Player("Hero", 5);

		// first dungeon
		Dungeon firstDungeon = new Dungeon(4);
		firstDungeon.displayDungeon();
		firstDungeon.playerEntersDungeon();
		firstDungeon.goThroughtDungeon(hero);

		if (nextDungeon() == 0) {
			System.out.println("NextDungeon");
			Dungeon secondDungeon = new Dungeon(2);
			secondDungeon.goThroughtDungeon(hero);
		}

		System.out.println("After the fight our hero goes home.");

	}

	private static int nextDungeon() {
		System.out.println("Enter the next dungeon: Yes[0]; No[1]");
		inputScanner = new Scanner(System.in);
		int answer = inputScanner.nextInt();

		if (answer == 1 || answer == 0) {
			return answer;
		} else {
			nextDungeon();
		}
		return -1;
	}
}
