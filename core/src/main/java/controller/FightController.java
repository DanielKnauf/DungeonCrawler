package controller;

import model.components.GameFigure;
import model.fighting.Move;
import viewcontroller.request.RequestHandler;
import viewcontroller.request.api.input.InputRequestCallback;

import java.util.Arrays;

import static viewcontroller.request.Event.*;

public class FightController {

    private final RequestHandler requestHandler;

    public FightController(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void startFightingRound(GameFigure hero, GameFigure monster) {
        chooseMoves(hero, monster);
    }

    private void chooseMoves(GameFigure hero, GameFigure monster) {
        monster.setMove(monster.makeMoveAtRandom());

        InputRequestCallback<Move> moveCallback = answer -> {
            hero.setMove(answer);
            fight(hero, monster);
        };

        requestHandler.startViewRequest(CHOOSE_MOVE, moveCallback, Move.class, Arrays.asList(Move.values()));
    }

    private void fight(GameFigure hero, GameFigure monster) {
        Move heroMove = hero.getMove();
        Move monsterMove = monster.getMove();

        if (monsterMove != null && heroMove != null) {
            requestHandler.startViewRequest(INTRODUCE_FIGHT_ROUND, monsterMove, heroMove);

            if (monsterMove.equals(heroMove)) {
                requestHandler.startViewRequest(FIGHT_RESULT, null, null);
            } else if (heroMove.beats(monsterMove)) {
                monster.gotHit();
                requestHandler.startViewRequest(FIGHT_RESULT, hero, monster);
            } else if (monsterMove.beats(heroMove)) {
                hero.gotHit();
                requestHandler.startViewRequest(FIGHT_RESULT, monster, hero);
            }

            requestHandler.startViewRequest(FINALIZE_FIGHT_ROUND, monster, hero);
        }
    }
}

