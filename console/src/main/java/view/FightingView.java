package view;

import model.components.GameFigure;
import model.fighting.Move;
import view.FightView;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequest;

import java.util.Scanner;

public class FightingView extends View implements FightView {

    public void displayMonsterEncounter(EventRequest request) {
        displaySeparator();
        System.out.println("You encountered a monster!!!");
        request.isFinished();
    }

    public void introduceRound(DisplayRequestBase<Move> request) {
        System.out.println("-- Before round --");
        System.out.println("Monster chooses: " + request.getDisplayItems().get(0));
        System.out.println("Hero chooses: " + request.getDisplayItems().get(1));
        request.isFinished();
    }

    public void finalizeRound(DisplayRequestBase<GameFigure> request) {
        System.out.println("-- After round --");
        displayHealth(request.getDisplayItems().get(0));
        displayHealth(request.getDisplayItems().get(1));
        request.isFinished();
    }

    /**
     * Hero chooses weapon.
     */
    public void heroChooses(DisplayRequestBase<Move> request) {
        displaySeparator();
        System.out.println("Choose your weapon:");

        for (Move entry : request.getDisplayItems()) {
            System.out.println("[" + entry.ordinal() + "]: " + entry.getName());
        }

        System.out.print("Your weapon: ");
        Scanner inputScanner = new Scanner(System.in);

        request.isFinished(Move.values()[inputScanner.nextInt()]);
    }

    public void displayResult(DisplayRequestBase<GameFigure> request) {
        GameFigure winner = request.getDisplayItems().get(0);
        GameFigure loser = request.getDisplayItems().get(1);

        if (winner == null || loser == null) {
            System.out.println("Draw");
        } else {
            System.out.println(String.format("%s wins.", winner.getName()));

            if (loser.isDead()) {
                System.out.println(String.format("\n%s is dead!", loser.getName()));
            }
        }

        request.isFinished();
    }
}
