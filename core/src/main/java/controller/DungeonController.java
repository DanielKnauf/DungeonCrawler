package controller;

import model.components.Hero;
import model.components.Monster;
import model.world.Direction;
import model.world.Dungeon;
import model.world.DungeonBuilder;
import model.world.room.DungeonRoom;
import viewcontroller.request.RequestHandler;
import viewcontroller.request.api.input.InputRequestCallback;

import static viewcontroller.request.Event.*;

public class DungeonController {

    private final RequestHandler requestHandler;
    private final DungeonBuilder dungeonBuilder;
    private final FightController fightController;

    public DungeonController(
            RequestHandler requestHandler,
            DungeonBuilder dungeonBuilder,
            FightController fightController) {
        this.requestHandler = requestHandler;
        this.dungeonBuilder = dungeonBuilder;
        this.fightController = fightController;
    }

    public void enterDungeon(Hero hero, int rooms, int rowSize, int columnSize) {
        Dungeon dungeon = dungeonBuilder.generateDungeon(rowSize, columnSize, rooms);
        goThroughDungeon(hero, dungeon);
    }

    private void goThroughDungeon(Hero hero, Dungeon dungeon) {
        dungeon.playerEntersDungeon();

        requestHandler.startViewRequest(ENTER_DUNGEON);
        displayDungeon(dungeon);
        playerMove(hero, dungeon);
    }

    private void playerMove(Hero hero, Dungeon dungeon) {
        InputRequestCallback<Direction> directionCallback = answer -> {

            DungeonRoom monsterRoom = dungeon.moveMonsters();

            if (monsterRoom != null) {
                requestHandler.startViewRequest(MONSTER_MOVEMENT, monsterRoom);
            }

            boolean couldChange = dungeon.playerChangeRoom(answer, monsterRoom);

            if (couldChange) {
                displayDungeon(dungeon);
                evaluatePlayerStep(hero, dungeon);
            }
        };

        requestHandler.startViewRequest(DIRECTION_INPUT, directionCallback, Direction.class, dungeon.getPlayerRoom().getPossibleDirections());
    }

    private void evaluatePlayerStep(Hero hero, Dungeon dungeon) {
        if (dungeon.getPlayerRoom().hasMonster()) {
            requestHandler.startViewRequest(MONSTER_ENCOUNTER);
            handleMonsterEncounter(hero, dungeon);
        } else {
            isHeroAtEndOfDungeon(dungeon, hero);
        }
    }

    private void handleMonsterEncounter(Hero hero, Dungeon dungeon) {
        Monster monster = new Monster("Monster");

        while (!hero.isDead() && !monster.isDead()) {
            fightController.startFightingRound(hero, monster);
        }

        if (hero.isDead()) {
            requestHandler.startViewRequest(HERO_IS_DEAD);
        }

        dungeon.getPlayerRoom().setHasMonster(false);
        dungeon.removeRoomFromRoomsWithMonster(dungeon.getPlayerRoom());

        requestHandler.startViewRequest(HEALTH_BAR, hero);

        displayDungeon(dungeon);

        isHeroAtEndOfDungeon(dungeon, hero);
    }

    private void displayDungeon(Dungeon dungeon) {
        requestHandler.startViewRequest(DUNGEON_MAP, dungeon);
    }

    private void isHeroAtEndOfDungeon(Dungeon dungeon, Hero hero) {
        if (dungeon.getPlayerRoom().isExit()) {
            requestHandler.startViewRequest(LEAVE_DUNGEON, hero);
        } else {
            playerMove(hero, dungeon);
        }
    }
}
