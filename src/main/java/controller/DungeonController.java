package controller;

import model.theComponents.Hero;
import model.theComponents.Monster;
import model.theWorld.Dungeon;
import model.theWorld.DungeonBuilder;
import view.View;

public class DungeonController {

    private final View view;
    private final DungeonBuilder dungeonBuilder;
    private final FightController fightController;

    public DungeonController(View view,
                             DungeonBuilder dungeonBuilder,
                             FightController fightController) {
        this.view = view;
        this.dungeonBuilder = dungeonBuilder;
        this.fightController = fightController;
    }

    void enterADungeon(Hero hero, int rooms, int rowSize, int columnSize) {
        Dungeon dungeon = dungeonBuilder.generateDungeon(rowSize, columnSize, rooms);
        goThroughDungeon(hero, dungeon);
    }

    private void goThroughDungeon(Hero hero, Dungeon dungeon) {
        dungeon.playerEntersDungeon();
        view.playerEntersDungeon(dungeon);

        while (dungeon.playerChangeRoom(view.chooseNextMove(dungeon.getPlayerRoom()))) {
            view.displayDungeon(dungeon);

            if (dungeon.getPlayerRoom().hasMonster()) {
                view.displayMonsterEncounter();

                Monster monster = new Monster("Monster");

                while (!hero.isDead() && !monster.isDead()) {
                    fightController.startFightingRound(hero, monster);
                }

                if (hero.isDead()) {
                    view.displayEndOfAdventure(true);
                    System.exit(0);
                }

                dungeon.getPlayerRoom().setHasMonster(false);
                dungeon.removeRoomFromRoomsWithMonster(dungeon.getPlayerRoom());
                view.displayHealth(hero);
                view.displayDungeon(dungeon);
            }

            if (dungeon.getPlayerRoom().isExit()) {
                view.heroLeavesDungeon(hero);
                break;
            }
        }
    }
}
