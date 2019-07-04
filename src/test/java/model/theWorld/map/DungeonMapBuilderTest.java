package model.theWorld.map;

import model.theWorld.Direction;
import model.theWorld.room.DungeonRoom;
import model.utils.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DungeonMapBuilderTest {

    private final RandomNumberGenerator numberGeneratorMock = mock(RandomNumberGenerator.class);
    private DungeonMapBuilder builder;

    @Before
    public void setup() {
        builder = new DungeonMapBuilder(numberGeneratorMock);
    }

    @Test
    public void generateCorrect_dungeonMap(){
        DungeonMap dungeonMap = builder.generateDungeonMap(3,3);
        assertEquals(3, dungeonMap.getMap().length);
        assertEquals(3, dungeonMap.getMap()[0].length);
        assertEquals(3, dungeonMap.getMap()[1].length);
        assertEquals(3, dungeonMap.getMap()[2].length);
    }

    @Test
    public void determineCorrect_startRoom() {
        when(numberGeneratorMock.getRandomInteger(2)).thenReturn(1);

        DungeonRoom room = builder.determineStartRoom(2, 2);

        assertFalse(room.hasMonster());
        assertEquals(1, room.getRow());
        assertEquals(1, room.getColumn());
    }

    @Test
    public void determineCorrect_nextDirection() {
        when(numberGeneratorMock.getRandomInteger(3)).thenReturn(1);

        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);

        assertEquals(Direction.DOWN, builder.determineNextDirection(directions));
    }
}
