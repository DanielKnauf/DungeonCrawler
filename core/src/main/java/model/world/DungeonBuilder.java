package model.world;

import model.world.map.DungeonMap;
import model.world.map.DungeonMapBuilder;
import model.world.room.DungeonRoom;

import java.util.ArrayList;
import java.util.List;

public class DungeonBuilder {

    private final DungeonBuilderUtils utils;
    private final DungeonMapBuilder builder;

    private int rowSize;
    private int columnSize;
    private int rooms;
    private int numberOfAddedRooms;
    private DungeonRoom startRoom;
    private List<DungeonRoom> roomsWithMonster;
    private DungeonMap dungeonMap;

    public DungeonBuilder(DungeonBuilderUtils utils,
                          DungeonMapBuilder builder) {
        this.utils = utils;
        this.builder = builder;
    }

    /**
     * Create a new dungeon map and fills it with rooms.
     *
     * @param rowSize    the size of the new dungeon
     * @param columnSize
     * @param rooms      the number of rooms inside the dungeon
     * @return finished dungeon
     */
    public Dungeon generateDungeon(int rowSize, int columnSize, int rooms) {
        this.numberOfAddedRooms = 0;

        // Dungeon must at least be 2x2 large
        if (rowSize < 2) {
            rowSize = 2;
        }
        if (columnSize < 2) {
            columnSize = 2;
        }

        // Dungeon must have at least two rooms (Start and Exit)
        if (rooms < 2) {
            rooms = 2;
        }

        // Fit the number of rooms do not fit inside the dungeonMap the rooms
        // are reduced
        if (rooms > rowSize * columnSize) {
            rooms = rowSize * columnSize;
        }

        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.rooms = rooms;
        this.roomsWithMonster = new ArrayList<>();

        dungeonMap = builder.generateDungeonMap(rowSize, columnSize);
        addRoomsToMap();
        return new Dungeon(this.rowSize, this.columnSize, dungeonMap.getMap(), startRoom, roomsWithMonster);
    }

    /**
     * Fills dungeon with rooms. Number of added rooms is determined by
     * parameter <code>rooms</code>
     */
    private void addRoomsToMap() {
        addStartRoom();
        determineNextRoom(startRoom);
    }

    /**
     * Randomly determine if room has a monster in it.
     * <p>
     * Chance that room has a monster is one of three.
     *
     * @return boolean
     */
    private boolean hasMonster() {
        return utils.hasRoomMonster();
    }

    /**
     * Adds entrance point to new dungeon.
     */
    private void addStartRoom() {
        this.startRoom = utils.determineStartRoom(rowSize, columnSize);
        dungeonMap.addRoomToMap(startRoom);
        numberOfAddedRooms++;
    }

    /**
     * Adds new rooms to the dungeon map.
     * <p>
     * Randomly determines a direction in which the next room for the dungeon
     * lies.
     *
     * @param previousRoom the room from where the new room is determined
     */
    private void determineNextRoom(DungeonRoom previousRoom) {
        while (numberOfAddedRooms < rooms && !checkForDeadLock(previousRoom)) {
            // Determine possible directions
            List<Direction> possibleDirections = findPossibleDirections(previousRoom);

            // Choose one direction at random
            Direction direction = utils.determineNextDirection(possibleDirections);

            // Get coordinates for next square
            int[] nextCoordinates = direction.getCoordinates(previousRoom.getRow(), previousRoom.getColumn());
            // Add room to the square
            addNewRoomToDungeonMap(nextCoordinates[0], nextCoordinates[1]);
        }
    }

    /**
     * Check for possible squares in which the new room can be placed.
     *
     * @param previousRoom the room from where the next square is searched.
     * @return ArrayList of possible squares
     */
    private List<Direction> findPossibleDirections(DungeonRoom previousRoom) {
        List<Direction> possibleDirection = new ArrayList<>();
        int previousRow = previousRoom.getRow();
        int previousColumn = previousRoom.getColumn();

        // Check each direction and at it to the arrayList if it is a free
        // square
        if (hasNoRoom(previousRow - 1, previousColumn)) {
            possibleDirection.add(Direction.UP);
        }
        if (hasNoRoom(previousRow + 1, previousColumn)) {
            possibleDirection.add(Direction.DOWN);
        }
        if (hasNoRoom(previousRow, previousColumn - 1)) {
            possibleDirection.add(Direction.LEFT);
        }
        if (hasNoRoom(previousRow, previousColumn + 1)) {
            possibleDirection.add(Direction.RIGHT);
        }

        return possibleDirection;
    }

    /**
     * Checks if the room has a free square for the next room around it.
     * <p>
     * <code>True</code>, when room has no free square around it.
     *
     * @param previousRoom the room from which the next room is searched
     * @return boolean
     */
    private boolean checkForDeadLock(DungeonRoom previousRoom) {
        int row = previousRoom.getRow();
        int column = previousRoom.getColumn();

        return hasRoom(row + 1, column)
                && hasRoom(row - 1, column)
                && hasRoom(row, column + 1)
                && hasRoom(row, column - 1);

    }

    /**
     * Checks if the determined square is empty and within the size of the
     * dungeon.
     *
     * @param newRow  the row of the square for the new room
     * @param newColm the column of the square for the new room
     */
    private void addNewRoomToDungeonMap(int newRow, int newColm) {
        if (hasNoRoom(newRow, newColm)) {
            DungeonRoom newRoom;

            if (hasMonster()) {
                newRoom = new DungeonRoom(true, newRow, newColm);
                roomsWithMonster.add(newRoom);
            } else {
                newRoom = new DungeonRoom(false, newRow, newColm);
            }
            numberOfAddedRooms++;
            if (numberOfAddedRooms == rooms) {
                newRoom.markAsExit();
            }

            updateDirections(newRoom);

            dungeonMap.addRoomToMap(newRoom);

            if (numberOfAddedRooms < rooms) {
                determineNextRoom(newRoom);
            }
        }
    }

    /**
     * Adds possible direction to new room and updates direction of adjacent
     * rooms.
     *
     * @param newRoom the newly added room
     */
    private void updateDirections(DungeonRoom newRoom) {

        if (hasRoom(newRoom.getRow() - 1, newRoom.getColumn())) {
            newRoom.addPossibleDirection(Direction.UP);
            dungeonMap.getRoom(newRoom.getRow() - 1, newRoom.getColumn()).addPossibleDirection(Direction.UP.getOpposite());
        }

        if (hasRoom(newRoom.getRow() + 1, newRoom.getColumn())) {
            newRoom.addPossibleDirection(Direction.DOWN);
            dungeonMap.getRoom(newRoom.getRow() + 1, newRoom.getColumn()).addPossibleDirection(Direction.DOWN.getOpposite());

        }
        if (hasRoom(newRoom.getRow(), newRoom.getColumn() - 1)) {
            newRoom.addPossibleDirection(Direction.LEFT);
            dungeonMap.getRoom(newRoom.getRow(), newRoom.getColumn() - 1).addPossibleDirection(Direction.LEFT.getOpposite());

        }
        if (hasRoom(newRoom.getRow(), newRoom.getColumn() + 1)) {
            newRoom.addPossibleDirection(Direction.RIGHT);
            dungeonMap.getRoom(newRoom.getRow(), newRoom.getColumn() + 1).addPossibleDirection(Direction.RIGHT.getOpposite());

        }
    }

    /**
     * @param row  the row of the square that is checked
     * @param colm the column of the square that is checked
     * @return <code>true</code> when given coordinates contains no room.
     */
    private boolean hasNoRoom(int row, int colm) {
        return dungeonMap.isEmptyAt(row, colm);
    }

    /**
     * @param row  the row of the square that is checked
     * @param colm the column of the square that is checked
     * @return <code>true</code> when given coordinates contains to a room.
     */
    private boolean hasRoom(int row, int colm) {
        return dungeonMap.hasRoomAt(row, colm);
    }
}
