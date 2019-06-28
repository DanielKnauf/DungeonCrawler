package model.rpslsFighting;

import model.theComponents.GameFigure;
import view.FightingView;

public class Fight {

    private final FightingView fightingView;
    private final GameFigure monster;
    private final GameFigure hero;
    private boolean heroWins;

    public Fight(FightingView fightingView, GameFigure hero, GameFigure monster) {
        this.fightingView = fightingView;
        this.hero = hero;
        this.monster = monster;
        fighting();
    }

    private void fighting() {
        fightingView.displayMonsterEncounter();
        while (!heroWins) {
            fight(monster.makeMoveAtRandom(), fightingView.heroChooses());
            fightingView.displaySeparator();
        }
    }

    /**
     * Fighting the monster
     */
    private void fight(Move monsterMove, Move heroMove) {
        if (monsterMove != null && heroMove != null) {
            fightingView.introduceRound(monsterMove, heroMove);

            if (monsterMove.equals(heroMove)) {
                fightingView.displayResult(null, null);
            } else if (heroMove.beats(monsterMove)) {
                fightingView.displayResult(hero, monster);
                monster.gotHit();
                if (monster.isDead()) {
                    heroWins = true;
                }
            } else if (monsterMove.beats(heroMove)) {
                fightingView.displayResult(monster, hero);
                hero.gotHit();
                if (hero.isDead()) {
                    fightingView.displayEndOfAdventure();
                    System.exit(0);
                }
            }

            fightingView.finalizeRound(monster, hero);
        }
    }
}
