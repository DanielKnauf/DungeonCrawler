package rpslsFighting;

import java.util.Random;
import java.util.Scanner;

import theComponents.GameFigure;

public class Fight {

	private boolean heroWins = false;
	private GameFigure monster;
	private GameFigure hero;
	private Scanner inputScanner;

	public Fight(GameFigure hero, GameFigure monster) {
		this.hero = hero;
		this.monster = monster;
		fighting();
	}

	private void fighting() {
		while (!heroWins) {
			heroChooses();
			monsterChooses();
			fight();
			System.out.println("********\n");
		}
	}

	/**
	 * Fighting the monster
	 */
	private void fight() {
		if (monster.getMove() != null && hero.getMove() != null) {
			System.out.println("--Before fight--");
			System.out.println("Monster chooses: " + monster.getMove());
			System.out.println("Hero chooses: " + hero.getMove());

			if (monster.getMove().equals(hero.getMove())) {
				System.out.println("Draw");
			} else if (hero.getMove().beats(monster.getMove())) {
				System.out.println("Hero wins.");
				if (monster.gotHit()) {
					System.out.println("\nMonster is dead!");
					heroWins = true;
				}
				;
			} else if (monster.getMove().beats(hero.getMove())) {
				System.out.println("Monster wins.");
				if (hero.gotHit()) {
					System.out.println("\nHero is dead!");
					System.out.println("\nYour adventure is over.");
					System.exit(0);
				}
			}
			System.out.println("--After fight--");
			System.out.println("Hitpoints Monster: " + monster.displayHitpoints());
			System.out.println("Hitpoints Hero: " + hero.displayHitpoints());
		}
	}

	/**
	 * Hero chooses weapon.
	 */
	private void heroChooses() {
		System.out.println("Choose your weapon:");
		int counter = 0;
		for (Move entry : Move.values()) {
			System.out.println("[" + counter + "]: " + entry.getName());
			counter++;
		}

		System.out.print("Your weapon: ");
		inputScanner = new Scanner(System.in);
		hero.setMove(Move.values()[inputScanner.nextInt()]);
	}

	/**
	 * Monster chooses move at random.
	 */
	private void monsterChooses() {
		Random randomizer = new Random();
		int moveNumber = randomizer.nextInt(5);
		monster.setMove(Move.values()[moveNumber]);
	}

}
