package model.world;

import model.world.room.DungeonRoom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.RandomNumberGenerator;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DungeonBuilderUtilsTest {

    private final RandomNumberGenerator numberGeneratorMock = Mockito.mock(RandomNumberGenerator.class);

    private DungeonBuilderUtils utils;

    @Before
    public void setup() {
        utils = new DungeonBuilderUtils(numberGeneratorMock);
    }

    @Test
    public void determineCorrect_nextDirection() {
        Mockito.when(numberGeneratorMock.getRandomInteger(3)).thenReturn(1);

        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);

        assertEquals(Direction.DOWN, utils.determineNextDirection(directions));
    }

    @Test
    public void determineCorrect_startRoom() {
        Mockito.when(numberGeneratorMock.getRandomInteger(2)).thenReturn(1);

        DungeonRoom room = utils.determineStartRoom(2, 2);

        assertFalse(room.hasMonster());
        assertEquals(1, room.getRow());
        assertEquals(1, room.getColumn());
    }
}
