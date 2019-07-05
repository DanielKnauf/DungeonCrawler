package model.theWorld;

import model.theWorld.room.DungeonRoom;
import model.utils.RandomNumberGenerator;

import java.util.List;

public class DungeonBuilderUtils {

    private final RandomNumberGenerator numberGenerator;

    public DungeonBuilderUtils(RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    boolean hasRoomMonster() {
        return numberGenerator.getRandomInteger(3) == 0;
    }

    Direction determineNextDirection(List<Direction> possibleDirections) {
        return possibleDirections.get(numberGenerator.getRandomInteger(possibleDirections.size()));
    }

    DungeonRoom determineStartRoom(int rowSize, int columnSize) {
        return new DungeonRoom(false, numberGenerator.getRandomInteger(rowSize), numberGenerator.getRandomInteger(columnSize));
    }
}
