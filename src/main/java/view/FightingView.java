package view;

import model.rpslsFighting.Move;
import model.theComponents.GameFigure;

import java.util.Scanner;

public class FightingView extends View {

    public void introduceRound(Move monsterMove, Move heroMove) {
        System.out.println("-- Before round --");
        System.out.println("Monster chooses: " + monsterMove);
        System.out.println("Hero chooses: " + heroMove);
    }

    public void finalizeRound(GameFigure monster, GameFigure hero) {
        System.out.println("-- After round --");
        displayHealth(monster);
        displayHealth(hero);
    }

    /**
     * Hero chooses weapon.
     */
    public Move heroChooses() {
        System.out.println("Choose your weapon:");

        for (Move entry : Move.values()) {
            System.out.println("[" + entry.ordinal() + "]: " + entry.getName());
        }

        System.out.print("Your weapon: ");
        Scanner inputScanner = new Scanner(System.in);

        return Move.values()[inputScanner.nextInt()];
    }

    public void displayResult(GameFigure winner, GameFigure loser) {
        if (winner == null || loser == null) {
            System.out.println("Draw");
        } else {
            System.out.println(String.format("%s wins.", winner.getName()));

            if (loser.isDead()) {
                System.out.println(String.format("\n%s is dead!", loser.getName()));
            }
        }

    }
}
