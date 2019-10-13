package view;

import model.components.GameFigure;
import model.fighting.Move;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequest;

public interface FightView {

    void displayMonsterEncounter(EventRequest request);

    void introduceRound(DisplayRequestBase<Move> request);

    void finalizeRound(DisplayRequestBase<GameFigure> request);

    void heroChooses(DisplayRequestBase<Move> request);

    void displayResult(DisplayRequestBase<GameFigure> request);

}
