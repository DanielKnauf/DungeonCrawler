package model.theWorld;

import model.theWorld.map.DungeonMap;
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

    private final DungeonView dungeonViewMock = mock(DungeonView.class);
    private final DungeonBuilderHelper helper = mock(DungeonBuilderHelper.class);

    private DungeonBuilder builder;

    @Before
    public void setup() {
        when(helper.generateDungeonMap(anyInt(), anyInt())).thenAnswer((Answer<DungeonMap>) this::buildDungeonMapAnswer);

        builder = new DungeonBuilder(dungeonViewMock, helper);
    }

    private DungeonMap buildDungeonMapAnswer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        return new DungeonMap((Integer) args[0], (Integer) args[1]);
    }

    @Test
    public void rowSizeIsToSmall_dungeonIs2x2() {
        when(helper.determineStartRoom(anyInt(), anyInt())).thenReturn(new DungeonRoom(false, 0, 0));
        when(helper.determineNextDirection(any(List.class))).thenReturn(Direction.DOWN);

        Dungeon dungeon = builder.generateDungeon(1, 2, 2);

        verify(helper).generateDungeonMap(2, 2);
        assertEquals(2, dungeon.getRowSize());
    }

    @Test
    public void columnSizeIsToSmall_dungeonIs2x2() {
        when(helper.determineStartRoom(anyInt(), anyInt())).thenReturn(new DungeonRoom(false, 0, 0));
        when(helper.determineNextDirection(any(List.class))).thenReturn(Direction.DOWN);

        Dungeon dungeon = builder.generateDungeon(2, 1, 2);

        verify(helper).generateDungeonMap(2, 2);
        assertEquals(2, dungeon.getColumnSize());
    }

    @Test
    public void verify_simpleDungeon_twoRooms_oneStart_oneExit_noMonster() {
        DungeonRoom startRoom = new DungeonRoom(false, 1, 1);

        when(helper.determineStartRoom(3, 3)).thenReturn(startRoom);
        when(helper.determineNextDirection(any(List.class))).thenReturn(Direction.UP);

        Dungeon dungeon = builder.generateDungeon(3, 3, 2);

        assertSame(startRoom, dungeon.getDungeonMap()[1][1]);
        assertTrue(dungeon.getDungeonMap()[0][1].isExit());
    }
}
