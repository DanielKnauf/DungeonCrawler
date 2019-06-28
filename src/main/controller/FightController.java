package controller;

import model.rpslsFighting.Move;
import model.theComponents.GameFigure;
import view.FightingView;

class FightController {

    private final FightingView fightingView;

    FightController(FightingView fightingView) {
        this.fightingView = fightingView;
    }

    void startFighting(GameFigure hero, GameFigure monster) {

        while (!hero.isDead() && !monster.isDead()) {
            chooseMoves(hero, monster);
            fightingView.displaySeparator();
            fight(hero, monster);
        }
    }

    private void chooseMoves(GameFigure hero, GameFigure monster) {
        monster.setMove(monster.makeMoveAtRandom());
        hero.setMove(fightingView.heroChooses());
    }

    /**
     * Fighting the monster
     */
    private void fight(GameFigure hero, GameFigure monster) {
        Move heroMove = hero.getMove();
        Move monsterMove = monster.getMove();

        if (monsterMove != null && heroMove != null) {
            fightingView.introduceRound(monsterMove, heroMove);

            if (monsterMove.equals(heroMove)) {
                fightingView.displayResult(null, null);
            } else if (heroMove.beats(monsterMove)) {
                fightingView.displayResult(hero, monster);
                monster.gotHit();
            } else if (monsterMove.beats(heroMove)) {
                fightingView.displayResult(monster, hero);
                hero.gotHit();
            }

            fightingView.finalizeRound(monster, hero);
        }
    }
}
