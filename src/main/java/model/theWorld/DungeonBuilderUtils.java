package model.theWorld;

import model.utils.RandomNumberGenerator;

public class DungeonBuilderUtils {

    private final RandomNumberGenerator numberGenerator;

    public DungeonBuilderUtils(RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    boolean hasRoomMonster() {
        return numberGenerator.getRandomInteger(3) == 0;
    }
}
