package model.theWorld;

import model.theWorld.map.DungeonMap;
import model.utils.RandomNumberGenerator;

import java.util.List;

public class DungeonBuilderHelper {

    private final RandomNumberGenerator numberGenerator;

    public DungeonBuilderHelper(RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    boolean hasRoomMonster() {
        return numberGenerator.getRandomInteger(3) == 0;
    }

    DungeonRoom determineStartRoom(int rowSize, int columnSize) {
        return new DungeonRoom(false, numberGenerator.getRandomInteger(rowSize), numberGenerator.getRandomInteger(columnSize));
    }

    Direction determineNextDirection(List<Direction> possibleDirections) {
        return possibleDirections.get(numberGenerator.getRandomInteger(possibleDirections.size()));
    }

    DungeonMap generateDungeonMap(int rowSize, int columnSize) {
        return new DungeonMap(rowSize, columnSize);
    }
}
