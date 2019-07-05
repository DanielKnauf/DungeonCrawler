package model.theWorld;

import model.theWorld.map.DungeonMap;
import model.theWorld.map.DungeonMapBuilder;
import model.theWorld.room.DungeonRoom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import view.DungeonView;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DungeonBuilderTest {

    private final DungeonBuilderUtils builderUtilsMock = mock(DungeonBuilderUtils.class);
    private final DungeonView dungeonViewMock = mock(DungeonView.class);
    private final DungeonMapBuilder dungeonBuilderMock = mock(DungeonMapBuilder.class);

    private DungeonBuilder builder;

    @Before
    public void setup() {
        when(dungeonBuilderMock.generateDungeonMap(anyInt(), anyInt())).thenAnswer((Answer<DungeonMap>) this::buildDungeonMapAnswer);

        builder = new DungeonBuilder(builderUtilsMock, dungeonViewMock, dungeonBuilderMock);
    }

    private DungeonMap buildDungeonMapAnswer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        return args[0] instanceof Integer && args[1] instanceof Integer
                ? new DungeonMap((Integer) args[0], (Integer) args[1])
                : new DungeonMap(0, 0);
    }

    @Test
    public void rowSizeIsTooSmall_dungeonIs2x2() {
        when(builderUtilsMock.determineStartRoom(anyInt(), anyInt())).thenReturn(new DungeonRoom(false, 0, 0));
        when(builderUtilsMock.determineNextDirection(any(List.class))).thenReturn(Direction.DOWN);

        Dungeon dungeon = builder.generateDungeon(1, 2, 2);

        verify(dungeonBuilderMock).generateDungeonMap(2, 2);
        assertEquals(2, dungeon.getRowSize());
    }

    @Test
    public void columnSizeIsTooSmall_dungeonIs2x2() {
        when(builderUtilsMock.determineStartRoom(anyInt(), anyInt())).thenReturn(new DungeonRoom(false, 0, 0));
        when(builderUtilsMock.determineNextDirection(any(List.class))).thenReturn(Direction.DOWN);

        Dungeon dungeon = builder.generateDungeon(2, 1, 2);

        verify(dungeonBuilderMock).generateDungeonMap(2, 2);
        assertEquals(2, dungeon.getColumnSize());
    }

    @Test
    public void verify_simpleDungeon_roomNumberTooSmall_twoRooms_oneStart_oneExit() {
        DungeonRoom startRoom = new DungeonRoom(false, 1, 1);

        when(builderUtilsMock.determineStartRoom(3, 3)).thenReturn(startRoom);
        when(builderUtilsMock.determineNextDirection(any(List.class))).thenReturn(Direction.UP);

        Dungeon dungeon = builder.generateDungeon(3, 3, 1);

        assertSame(startRoom, dungeon.getDungeonMap()[1][1]);

        assertTrue(dungeon.getDungeonMap()[0][1].isExit());
        assertFalse(dungeon.getDungeonMap()[0][1].hasMonster());
    }

    @Test
    public void verify_fourRooms_oneStart_oneExit_allHaveMonsters() {
        DungeonRoom startRoom = new DungeonRoom(false, 3, 0);

        when(builderUtilsMock.hasRoomMonster()).thenReturn(true);
        when(builderUtilsMock.determineStartRoom(4, 2)).thenReturn(startRoom);
        when(builderUtilsMock.determineNextDirection(any(List.class))).thenReturn(Direction.UP);

        Dungeon dungeon = builder.generateDungeon(4, 2, 4);

        assertSame(startRoom, dungeon.getDungeonMap()[3][0]);

        assertFalse(dungeon.getDungeonMap()[2][0].isExit());
        assertTrue(dungeon.getDungeonMap()[2][0].hasMonster());

        assertFalse(dungeon.getDungeonMap()[1][0].isExit());
        assertTrue(dungeon.getDungeonMap()[1][0].hasMonster());

        assertTrue(dungeon.getDungeonMap()[0][0].isExit());
        assertTrue(dungeon.getDungeonMap()[0][0].hasMonster());
    }

    /**
     * r r e x
     * r r s x
     * x x x x
     * x x x x
     */
    @Test
    public void verify_sixRooms_haveCorrectPossibleDirections() {
        DungeonRoom startRoom = new DungeonRoom(false, 1, 2);

        when(builderUtilsMock.hasRoomMonster()).thenReturn(true);
        when(builderUtilsMock.determineStartRoom(4, 4)).thenReturn(startRoom);
        when(builderUtilsMock.determineNextDirection(any(List.class))).thenAnswer((Answer<Direction>) this::determineDirection);

        Dungeon dungeon = builder.generateDungeon(4, 4, 6);

        assertSame(startRoom, dungeon.getDungeonMap()[1][2]);
        assertEquals(2, dungeon.getDungeonMap()[1][2].getPossibleDirections().size());
        assertTrue(dungeon.getDungeonMap()[1][2].getPossibleDirections().contains(Direction.UP));
        assertTrue(dungeon.getDungeonMap()[1][2].getPossibleDirections().contains(Direction.LEFT));

        assertFalse(dungeon.getDungeonMap()[1][1].isExit());
        assertEquals(3, dungeon.getDungeonMap()[1][1].getPossibleDirections().size());
        assertTrue(dungeon.getDungeonMap()[1][1].getPossibleDirections().contains(Direction.UP));
        assertTrue(dungeon.getDungeonMap()[1][1].getPossibleDirections().contains(Direction.LEFT));
        assertTrue(dungeon.getDungeonMap()[1][1].getPossibleDirections().contains(Direction.RIGHT));

        assertFalse(dungeon.getDungeonMap()[1][0].isExit());
        assertEquals(2, dungeon.getDungeonMap()[1][0].getPossibleDirections().size());
        assertTrue(dungeon.getDungeonMap()[1][0].getPossibleDirections().contains(Direction.UP));
        assertTrue(dungeon.getDungeonMap()[1][0].getPossibleDirections().contains(Direction.RIGHT));

        assertFalse(dungeon.getDungeonMap()[0][0].isExit());

        assertFalse(dungeon.getDungeonMap()[0][1].isExit());
        assertEquals(3, dungeon.getDungeonMap()[0][1].getPossibleDirections().size());
        assertTrue(dungeon.getDungeonMap()[0][1].getPossibleDirections().contains(Direction.DOWN));
        assertTrue(dungeon.getDungeonMap()[0][1].getPossibleDirections().contains(Direction.LEFT));
        assertTrue(dungeon.getDungeonMap()[0][1].getPossibleDirections().contains(Direction.RIGHT));

        assertTrue(dungeon.getDungeonMap()[0][2].isExit());
    }

    private Direction determineDirection(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();

        if (args[0] instanceof List) {
            List list = (List) args[0];
            switch (list.size()) {
                case 4:
                case 3:
                    return Direction.LEFT;
                case 2:
                    return Direction.UP;
                default:
                    return Direction.RIGHT;
            }
        }

        return null;
    }
}
