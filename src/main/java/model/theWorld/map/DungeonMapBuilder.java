package model.theWorld.map;

import model.theWorld.Direction;
import model.theWorld.room.DungeonRoom;
import model.utils.RandomNumberGenerator;

import java.util.List;

public class DungeonMapBuilder {

    private final RandomNumberGenerator numberGenerator;

    public DungeonMapBuilder(RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public DungeonMap generateDungeonMap(int rowSize, int columnSize) {
        return new DungeonMap(rowSize, columnSize);
    }

    public DungeonRoom determineStartRoom(int rowSize, int columnSize) {
        return new DungeonRoom(false, numberGenerator.getRandomInteger(rowSize), numberGenerator.getRandomInteger(columnSize));
    }

    public Direction determineNextDirection(List<Direction> possibleDirections) {
        return possibleDirections.get(numberGenerator.getRandomInteger(possibleDirections.size()));
    }
}
