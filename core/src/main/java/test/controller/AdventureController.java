package test.controller;

import controller.DungeonController;
import model.components.Hero;
import test.view.AdventuringView;

public class AdventureController {

    private int rowSize = 2;
    private int columnSize = 3;
    private int rooms = 3;

    private final AdventuringView view;
    private final DungeonController dungeonController;
    private Hero hero;

    public AdventureController(AdventuringView view,
                               DungeonController dungeonController) {
        this.view = view;
        this.dungeonController = dungeonController;
        setupAdventure();
    }

    private void setupAdventure() {
        hero = new Hero("John", 5);
    }

    public void goOnAnAdventure() {
        /*
         * Hero must go through one dungeon so his journey can be called an
         * adventure
         */
        dungeonController.enterDungeon(hero, rooms, rowSize, columnSize);

        // Can decide to go into the next dungeon
        while (view.nextDungeon() == 0) {
            rowSize++;
            columnSize++;
            rooms = rooms * 2;
            dungeonController.enterDungeon(hero, rooms, rowSize, columnSize);
        }

        view.displayEndOfAdventure();
        System.exit(0);
    }
}
