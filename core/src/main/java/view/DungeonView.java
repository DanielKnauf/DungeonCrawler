package view;

import model.components.GameFigure;
import model.world.Direction;
import model.world.Dungeon;
import model.world.room.DungeonRoom;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequest;

public interface DungeonView {

    void displayDungeon(DisplayRequestBase<Dungeon> request);

    void displayMonsterMovement(DisplayRequestBase<DungeonRoom> request);

    void playerEntersDungeon(EventRequest request);

    void requestDirection(DisplayRequestBase<Direction> request);

    void heroLeavesDungeon(DisplayRequestBase<GameFigure> request);

    void displayEndOfAdventure(EventRequest request);
}
