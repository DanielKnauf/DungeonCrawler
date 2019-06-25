package model.rpslsFighting;

import model.theComponents.GameFigure;
import view.View;

import java.util.Random;
import java.util.Scanner;

public class Fight {

    private final View view;
    private final GameFigure monster;
    private final GameFigure hero;
    private boolean heroWins;

    public Fight(View view, GameFigure hero, GameFigure monster) {
        this.view = view;
        this.hero = hero;
        this.monster = monster;
        fighting();
    }

    private void fighting() {
        System.out.println("\nYou encountered a monster!!!");
        while (!heroWins) {
            heroChooses();
            monsterChooses();
            fight();
            System.out.println("********");
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
                monster.gotHit();
                if (monster.isDead()) {
                    System.out.println("\nMonster is dead!");
                    heroWins = true;
                }
            } else if (monster.getMove().beats(hero.getMove())) {
                System.out.println("Monster wins.");
                hero.gotHit();
                if (hero.isDead()) {
                    System.out.println("\nHero is dead!");
                    System.out.println("\nYour adventure is over.");
                    System.exit(0);
                }
            }

            System.out.println("--After fight--");
            System.out.println("Hitpoints Monster: " + view.displayHitPoints(monster));
            System.out.println("Hitpoints Hero: " + view.displayHitPoints(hero));
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
        Scanner inputScanner = new Scanner(System.in);
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
