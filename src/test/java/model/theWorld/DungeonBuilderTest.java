package model.theWorld;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DungeonBuilderTest {

    private DungeonBuilder builder;

    @Before
    public void setup() {
        builder = new DungeonBuilder();
    }

    @Test
    public void rowSizeIsToSmall_dungeonIs2x2() {
        Dungeon dungeon = builder.generateDungeon(1, 2, 2);

        assertEquals(2, dungeon.getRowSize());
    }

    @Test
    public void columnSizeIsToSmall_dungeonIs2x2() {
        Dungeon dungeon = builder.generateDungeon(2, 1, 2);

        assertEquals(2, dungeon.getColumnSize());
    }
}
