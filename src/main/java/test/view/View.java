package test.view;

import model.components.GameFigure;
import view.BaseView;
import viewcontroller.request.api.display.DisplayRequestBase;

import java.util.Scanner;

public class View implements BaseView {

    private final Scanner inputScanner = new Scanner(System.in);

    int getAnswer() {
        return inputScanner.nextInt();
    }

    @Override
    public void displayHealth(DisplayRequestBase<GameFigure> request) {
        GameFigure gameFigure = request.getDisplayItems().get(0);
        System.out.println("Health of " + gameFigure.getName() + ":: " + displayHitPoints(gameFigure) + "\n");
        request.isFinished();
    }

    void displayHealth(GameFigure gameFigure) {
        System.out.println("Health of " + gameFigure.getName() + ":: " + displayHitPoints(gameFigure) + "\n");
    }

    /**
     * Display hitPoints.
     */
    private String displayHitPoints(GameFigure gameFigure) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < gameFigure.getHitPoints(); i++) {
            builder.append(" ❤️ ");
        }
        return builder.toString();
    }

    void displaySeparator() {
        System.out.println("________\n");
    }
}
