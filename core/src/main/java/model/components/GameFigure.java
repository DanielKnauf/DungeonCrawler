package model.components;

import model.fighting.Move;
import utils.StringUtils;

import java.util.Random;

public abstract class GameFigure {

    private String name;
    private int hitPoints;
    private Move move;

    GameFigure(String name) {
        this.name = validateName(name);
    }

    GameFigure(String name, int hitPoints) {
        this.name = validateName(name);
        this.hitPoints = validateHitPoints(hitPoints);
    }

    private String validateName(String name) {
        return StringUtils.isEmpty(name) ? "Alice" : name;
    }

    private int validateHitPoints(int hitPoints) {
        return hitPoints <= 0 ? 1 : hitPoints;
    }

    public String getName() {
        return this.name;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return this.move;
    }

    void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void gotHit() {
        hitPoints--;
    }

    public boolean isDead() {
        return hitPoints <= 0;
    }

    /**
     * GameFigure chooses move at random.
     */
    public Move makeMoveAtRandom() {
        Random randomizer = new Random();
        int moveNumber = randomizer.nextInt(5);
        return Move.values()[moveNumber];
    }
}
