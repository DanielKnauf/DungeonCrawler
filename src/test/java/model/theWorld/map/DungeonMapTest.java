package model.theWorld.map;

import model.theWorld.room.DungeonRoom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DungeonMapTest {

    private DungeonMap map;

    @Before
    public void setupBasicMap() {
        map = new DungeonMap(2, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void mapIsInitializedCorrect_notTooMuchRows() {
        assertNotNull(map.getMap());
        assertNull(map.getRoom(0, 0));
        assertNull(map.getRoom(0, 1));
        assertNull(map.getRoom(1, 0));
        assertNull(map.getRoom(1, 1));
        assertNull(map.getRoom(2, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void mapIsInitializedCorrect_notTooMuchColumns() {
        assertNotNull(map.getMap());
        assertNull(map.getRoom(0, 0));
        assertNull(map.getRoom(0, 1));
        assertNull(map.getRoom(1, 0));
        assertNull(map.getRoom(1, 1));
        assertNull(map.getRoom(1, 2));
    }

    @Test
    public void roomIsAddedCorrectly() {
        DungeonRoom newRoom = new DungeonRoom(true, 1, 1);

        map.addRoomToMap(newRoom);

        assertSame(newRoom, map.getRoom(1, 1));
    }

    @Test
    public void roomAt_rowInputIncorrect_belowZero_false() {
        assertFalse(map.hasRoomAt(-1, 1));

        assertFalse(map.isEmptyAt(-1, 1));
    }

    @Test
    public void roomAt_rowInputIncorrect_isSameAsMaxRow_false() {
        assertFalse(map.hasRoomAt(2, 1));

        assertFalse(map.isEmptyAt(2, 1));
    }

    @Test
    public void roomAt_columnInputIncorrect_belowZero_false() {
        assertFalse(map.hasRoomAt(1, -1));

        assertFalse(map.isEmptyAt(1, -1));
    }

    @Test
    public void roomAt_columnInputIncorrect_isSameAsMaxRow_false() {
        assertFalse(map.hasRoomAt(1, 2));

        assertFalse(map.isEmptyAt(1, 2));
    }

    @Test
    public void roomAt_correctInput_roomIsNull_false() {
        assertFalse(map.hasRoomAt(1, 1));

        assertTrue(map.isEmptyAt(1, 1));
    }

    @Test
    public void roomAt_correctInput_roomIsPresent_true() {
        map.addRoomToMap(new DungeonRoom(false, 1, 1));

        assertTrue(map.hasRoomAt(1, 1));

        assertFalse(map.isEmptyAt(1, 1));
    }


}
