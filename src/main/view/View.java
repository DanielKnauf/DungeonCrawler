package view;

import model.theComponents.GameFigure;
import model.theComponents.Hero;
import model.theWorld.Direction;
import model.theWorld.Dungeon;
import model.theWorld.DungeonRoom;

import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner inputScanner = new Scanner(System.in);

    public Direction chooseNextMove(DungeonRoom playerRoom) {
        // Display options
        displaySeparator();
        List<Direction> possibleDirections = playerRoom.getPossibleDirections();
        System.out.println("Choose the next room you want to explore.");
        int counter = 0;
        for (Direction d : possibleDirections) {
            System.out.println("[" + counter + "]: " + d.getName());
            counter++;
        }

        // Get answer from player
        int answer = -1;
        while (answer < 0 || answer >= possibleDirections.size()) {
            System.out.println("Your move: ");
            answer = getAnswer();
            if (answer < 0 || answer >= possibleDirections.size()) {
                System.out.println("FAIL. Choose again.");
            }
        }
        return possibleDirections.get(answer);
    }

    public int nextDungeon() {
        System.out.println("Enter the next dungeon: Yes[0]; No[1]");
        int answer = getAnswer();

        if (answer == 1 || answer == 0) {
            return answer;
        } else {
            nextDungeon();
        }
        return -1;
    }

    private int getAnswer() {
        return inputScanner.nextInt();
    }

    public void displayDungeon(Dungeon dungeon) {
        DungeonRoom[][] dungeonMap = dungeon.getDungeonMapp();
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
        displayPlayerPositon(dungeon.getPlayerRoom());
    }

    private void displayPlayerPositon(DungeonRoom playerRoom) {
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

    public void playerEntersDungeon(Dungeon dungeon) {
        System.out.println("Player enters the deep dark dungeon.");
        displayDungeon(dungeon);
    }

    public void displayHealth(GameFigure gameFigure) {
        System.out.println("Health of " + gameFigure.getName() + ":: " + displayHitPoints(gameFigure) + "\n");
    }

    /**
     * Display hitPoints.
     */
    String displayHitPoints(GameFigure gameFigure) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < gameFigure.getHitPoints(); i++) {
            builder.append(" ❤️ ");
        }
        return builder.toString();
    }

    public void heroLeavesDungeon(Hero hero) {
        displaySeparator();
        System.out.println("End of Dungeon" + "\nHero gets out of the dungeon alive.");
        displayHealth(hero);
    }

    public void displaySeparator() {
        System.out.println("________\n");
    }
}
