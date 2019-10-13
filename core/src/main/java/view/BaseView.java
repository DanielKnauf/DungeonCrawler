package view;

import model.components.GameFigure;
import viewcontroller.request.api.display.DisplayRequestBase;

public interface BaseView {

    void displayHealth(DisplayRequestBase<GameFigure> request);
}
