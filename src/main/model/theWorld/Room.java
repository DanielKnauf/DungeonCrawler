package model.theWorld;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {

    private int row;
    private int column;
    private final List<Direction> possibleDirections = new ArrayList<>();

    public Room(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void addPossibleDirection(Direction direction) {
        if (!possibleDirections.contains(direction)) {
            possibleDirections.add(direction);
        }
    }

    public List<Direction> getPossibleDirections() {
        return possibleDirections;
    }
}
