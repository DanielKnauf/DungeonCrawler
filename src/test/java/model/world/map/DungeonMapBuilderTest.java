package model.world.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DungeonMapBuilderTest {

    private DungeonMapBuilder builder;

    @Before
    public void setup() {
        builder = new DungeonMapBuilder();
    }

    @Test
    public void generateCorrect_dungeonMap() {
        DungeonMap dungeonMap = builder.generateDungeonMap(3, 3);
        assertEquals(3, dungeonMap.getMap().length);
        assertEquals(3, dungeonMap.getMap()[0].length);
        assertEquals(3, dungeonMap.getMap()[1].length);
        assertEquals(3, dungeonMap.getMap()[2].length);
    }
}
