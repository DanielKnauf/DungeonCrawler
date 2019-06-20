package controller;

import model.rpslsFighting.Fight;
import model.theComponents.Hero;
import model.theComponents.Monster;
import model.theWorld.Dungeon;
import model.theWorld.DungeonBuilder;
import view.View;

public class AdventureTime {
    private static int rowSize = 2;
    private static int colmSize = 3;
    private static int rooms = 3;

    private static DungeonBuilder dungeonBuilder = new DungeonBuilder();
    private static Hero hero;
    private static View view;

    public static void main(String[] args) {
        view = new View();
        hero = new Hero("John", 5);

        /*
         * Hero must go through one dungeon so his journey can be called an
         * adventure
         */
        Dungeon firstDungeon = dungeonBuilder.generateDungeon(rowSize, colmSize, rooms);
        goThroughtDungeon(firstDungeon);

        // Can decide to go into the next dungeon
        while (view.nextDungeon() == 0) {
            rowSize++;
            colmSize++;
            rooms = rooms * 2;
            Dungeon secondDungeon = dungeonBuilder.generateDungeon(rowSize, colmSize, rooms);
            goThroughtDungeon(secondDungeon);
        }

        System.out.println("After the adventure our hero goes home.");

    }

    private static void goThroughtDungeon(Dungeon dungeon) {
        dungeon.playerEntersDungeon();
        view.playerEntersDungeon(dungeon);

        while (dungeon.playerChangeRoom(view.choooseNextMove(dungeon.getPlayerRoom()))) {
            view.displayDungeon(dungeon);

            if (dungeon.getPlayerRoom().getHasMonster()) {
                new Fight(hero, new Monster("Monster"));
                dungeon.getPlayerRoom().setHasMonster(false);
                dungeon.removeRoomFromRoomsWithMonster(dungeon.getPlayerRoom());
                view.displayHealth(hero);
                view.displayDungeon(dungeon);
            }

            if (dungeon.getPlayerRoom().checkForExit()) {
                view.heroLeavesDungeon(hero);
                break;
            }

        }
    }

}
