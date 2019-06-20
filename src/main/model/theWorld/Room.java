package model.theWorld;

import java.util.ArrayList;

public class Room {

    private int row;
    private int column;
    private boolean hasPlayer;
    private boolean hasMonster;
    private boolean isExit;
    private final ArrayList<Direction> possibleDirections = new ArrayList<>();

    public Room(boolean hasMonster, int row, int column) {
        this.hasMonster = hasMonster;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * Mark this room as an exit.
     */
    public void markAsExit() {
        this.isExit = true;
    }

    /**
     * Returns true, when room is the exit of the dungeon.
     *
     * @return boolean
     */
    public boolean isExit() {
        return isExit;
    }

    public void putInMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public boolean hasMonster() {
        return hasMonster;
    }

    public void putInPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void addPossibleDirection(Direction direction) {
        if (!possibleDirections.contains(direction)) {
            possibleDirections.add(direction);
        }
    }

    public ArrayList<Direction> getPossibleDirections() {
        return possibleDirections;
    }

    @Override
    public String toString() {
        return "Room:: " + row + " | " + column + "; Monster: " + hasMonster;
    }
}
