package model.theWorld;

import model.theWorld.room.DungeonRoom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DungeonRoomTest {

    private DungeonRoom room;

    @Before
    public void setup() {
        room = new DungeonRoom(false, 0, -1);
    }

    @Test
    public void constructorWithoutMonster_valuesCorrectSet() {
        assertFalse(room.hasMonster());
        assertFalse(room.hasPlayer());
        assertFalse(room.isExit());
        assertEquals(0, room.getRow());
        assertEquals(-1, room.getColumn());
        assertEquals(0, room.getPossibleDirections().size());
    }

    @Test
    public void constructorWithMonster_valuesCorrectSet() {
        room = new DungeonRoom(true, -1, 0);

        assertTrue(room.hasMonster());
        assertFalse(room.hasPlayer());
        assertFalse(room.isExit());
        assertEquals(-1, room.getRow());
        assertEquals(0, room.getColumn());
        assertEquals(0, room.getPossibleDirections().size());
    }

    @Test
    public void addNewPossibleDirectionTwice_justAddedOnce() {
        room.addPossibleDirection(Direction.UP);

        room.addPossibleDirection(Direction.UP);

        assertEquals(1, room.getPossibleDirections().size());
        assertEquals(Direction.UP, room.getPossibleDirections().get(0));
    }

    @Test
    public void verify_hasPlayer(){
        room.setHasPlayer(true);

        assertTrue(room.hasPlayer());

        room.setHasPlayer(false);

        assertFalse(room.hasPlayer());
    }

    @Test
    public void verify_hasMonster(){
        room.setHasMonster(true);

        assertTrue(room.hasMonster());

        room.setHasMonster(false);

        assertFalse(room.hasMonster());
    }

    @Test
    public void verify_isExit(){
        room.markAsExit();

        assertTrue(room.isExit());
    }
}
