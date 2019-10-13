package test.view;

import model.components.GameFigure;
import model.world.Direction;
import model.world.Dungeon;
import model.world.room.DungeonRoom;
import view.DungeonView;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequest;

import java.util.List;

public class DungeoningView extends View implements DungeonView {

    @Override
    public void playerEntersDungeon(EventRequest request) {
        System.out.println("Player enters the deep dark dungeon.");
        request.isFinished();
    }

    @Override
    public void displayDungeon(DisplayRequestBase<Dungeon> request) {
        Dungeon dungeon = request.getDisplayItems().get(0);
        DungeonRoom[][] dungeonMap = dungeon.getDungeonMap();
        System.out.println("\nMap of the Dungeon");
        for (int row = 0; row < dungeon.getRowSize(); row++) {
            System.out.print(row);
            for (int colm = 0; colm < dungeon.getColumnSize(); colm++) {
                if (dungeonMap[row][colm] == null) {
                    System.out.print(" ####### ");
                } else {
                    System.out.print(displayRoom(dungeonMap[row][colm]));
                }
            }
            System.out.print("\n");
        }
        displayPlayerPosition(dungeon.getPlayerRoom());

        request.isFinished();
    }

    private void displayPlayerPosition(DungeonRoom playerRoom) {
        System.out.println("--> Player is at room " + playerRoom.getRow() + " | " + playerRoom.getColumn());
    }

    private String displayRoom(DungeonRoom room) {
        String roomDisplay = " [ _._ ] ";
        // ⇡⇠⇢⇣
        if (room.hasPlayer() && room.hasMonster()) {
            roomDisplay = roomDisplay.replace("_._", "PvM");
        }
        if (room.hasPlayer()) {
            roomDisplay = roomDisplay.replace('.', 'P');
        }
        if (room.hasMonster()) {
            roomDisplay = roomDisplay.replace('.', 'M');
        }
        if (room.isExit()) {
            roomDisplay = roomDisplay.replace('.', 'E');
        }

        return roomDisplay;
    }

    @Override
    public void requestDirection(DisplayRequestBase<Direction> request) {
        // Display options
        displaySeparator();
        List<Direction> possibleDirections = request.getDisplayItems();
        System.out.println("Choose the next room you want to explore.");
        int counter = 0;
        for (Direction d : possibleDirections) {
            System.out.println("[" + counter + "]: " + d.getName());
            counter++;
        }

        // Get answer from player
        int answer = -1;
        while (answer < 0 || answer >= possibleDirections.size()) {
            System.out.print("Your move: ");
            answer = getAnswer();
            if (answer < 0 || answer >= possibleDirections.size()) {
                System.out.println("FAIL. Choose again.");
            }
        }

        request.isFinished(possibleDirections.get(answer));
    }

    public void displayMonsterMovement(DisplayRequestBase<DungeonRoom> request) {
        DungeonRoom room = request.getDisplayItems().get(0);

        if (room == null) {
            request.isFinished();
            return;
        }

        System.out.print("Monster moves to ");
        System.out.println("Room:: " + room.getRow() + " | " + room.getColumn() + "; Monster: " + room.hasMonster());
        request.isFinished();
    }

    @Override
    public void heroLeavesDungeon(DisplayRequestBase<GameFigure> request) {
        displaySeparator();
        System.out.println("End of Dungeon\nHero gets out of the dungeon alive.");
        displayHealth(request);
        request.isFinished();
    }

    @Override
    public void displayEndOfAdventure(EventRequest request) {
        displaySeparator();
        System.out.println("Your adventure is over.");
        request.isFinished();
        System.exit(0);
    }

}
