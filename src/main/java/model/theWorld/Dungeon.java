package model.theWorld;

import view.DungeonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static model.theWorld.Direction.*;

public class Dungeon {

    private final DungeonView dungeonView;

    private Random randomizer = new Random();
    private int rowSize;
    private int columnSize;
    private DungeonRoom playerRoom;
    private DungeonRoom startRoom;
    private DungeonRoom[][] map;
    private List<DungeonRoom> roomsWithMonster;

    Dungeon(DungeonView dungeonView,
            int rowSize,
            int columnSize,
            DungeonRoom[][] dungeonMap,
            DungeonRoom startRoom,
            List<DungeonRoom> roomsWithMonster) {
        this.dungeonView = dungeonView;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.map = dungeonMap;
        this.startRoom = startRoom;
        this.roomsWithMonster = roomsWithMonster;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public DungeonRoom getPlayerRoom() {
        return playerRoom;
    }

    public DungeonRoom[][] getDungeonMap() {
        return this.map;
    }

    /**
     * Initial set up for the playerPosition and playerRoom
     */
    public void playerEntersDungeon() {
        startRoom.setHasPlayer(true);
        this.playerRoom = startRoom;
    }

    public void removeRoomFromRoomsWithMonster(DungeonRoom roomWithDeadMonster) {
        roomsWithMonster.remove(roomWithDeadMonster);
    }

    /**
     * Finds the room the player is entering and changes all parameter to this
     * room.
     *
     * @param direction the direction the player wants to go
     * @return <code>true</code>if change was successful.
     */
    public boolean playerChangeRoom(Direction direction) {
        int[] coordinates = direction.getCoordinates(playerRoom.getRow(), playerRoom.getColumn());

        if (hasRoom(coordinates[0], coordinates[1])) {
            changeRoom(map[coordinates[0]][coordinates[1]]);
            return true;
        }

        return false;
    }

    /**
     * Set variables for entered room.
     *
     * @param enter
     */
    private void changeRoom(DungeonRoom enter) {
        if (playerRoom.equals(monsterMoves())) {
            return;
        }

        playerRoom.setHasPlayer(false);
        enter.setHasPlayer(true);
        this.playerRoom = enter;

    }

    private DungeonRoom monsterMoves() {
        return checkIfAllMonstersLocked()
                ? moveMonster()
                : null;
    }

    private DungeonRoom moveMonster() {
        DungeonRoom monsterRoom = roomsWithMonster.get(randomizer.nextInt(roomsWithMonster.size()));
        dungeonView.displayDungeonRoom(monsterRoom);

        if (hasFreeRoomToMove(monsterRoom)) {
            // Find the free rooms
            List<DungeonRoom> freeRooms = findFreeRoomsToMoveIn(monsterRoom);

            // Choose one at random
            DungeonRoom nextRoom = freeRooms.get(randomizer.nextInt(freeRooms.size()));

            // remove room form list
            monsterRoom.setHasMonster(false);
            roomsWithMonster.remove(monsterRoom);

            // add new monsterRoom to list
            nextRoom.setHasMonster(true);
            roomsWithMonster.add(nextRoom);

            dungeonView.displayMonsterMovement(nextRoom);
            return nextRoom;
        } else {
            moveMonster();
        }

        return null;
    }

    /**
     * @param row
     * @param column
     * @return <code>true</code> if space contains a room.n
     */
    private boolean hasRoom(int row, int column) {
        return row < rowSize
                && row >= 0
                && column >= 0
                && column < columnSize
                && map[row][column] != null;
    }

    /**
     * Checks if there is a possible movement for one of the monsters.
     * <p>
     * <code>True</code>, when there is at least one possible movement for one
     * of the monsters.
     *
     * @return
     */
    private boolean checkIfAllMonstersLocked() {
        for (DungeonRoom r : roomsWithMonster) {
            if (hasFreeRoomToMove(r)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param previousRoom
     * @return
     */
    private boolean hasFreeRoomToMove(DungeonRoom previousRoom) {
        int row = previousRoom.getRow();
        int colm = previousRoom.getColumn();

        return roomIsFree(UP.getCoordinates(row, colm))
                || roomIsFree(DOWN.getCoordinates(row, colm))
                || roomIsFree(LEFT.getCoordinates(row, colm))
                || roomIsFree(RIGHT.getCoordinates(row, colm));
    }

    /**
     * @param coordinates
     * @return
     */
    private boolean roomIsFree(int[] coordinates) {
        return hasRoom(coordinates[0], coordinates[1]) && (!map[coordinates[0]][coordinates[1]].isExit() && !map[coordinates[0]][coordinates[1]].hasMonster());
    }

    /**
     * @param previousRoom
     * @return
     */
    private List<DungeonRoom> findFreeRoomsToMoveIn(DungeonRoom previousRoom) {
        List<DungeonRoom> freeRooms = new ArrayList<>();
        int row = previousRoom.getRow();
        int colm = previousRoom.getColumn();
        if (roomIsFree(UP.getCoordinates(row, colm))) {
            freeRooms.add(map[row - 1][colm]);
        }
        if (roomIsFree(DOWN.getCoordinates(row, colm))) {
            freeRooms.add(map[row + 1][colm]);
        }
        if (roomIsFree(LEFT.getCoordinates(row, colm))) {
            freeRooms.add(map[row][colm - 1]);
        }
        if (roomIsFree(RIGHT.getCoordinates(row, colm))) {
            freeRooms.add(map[row][colm + 1]);
        }

        return freeRooms;
    }
}
