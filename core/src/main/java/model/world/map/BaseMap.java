package model.world.map;

import model.world.room.Room;

abstract class BaseMap<R extends Room> implements Map<R> {

    private final int rowMax;
    private final int columnMax;
    final R[][] map;

    BaseMap(int row, int column) {
        rowMax = row;
        columnMax = column;
        this.map = initMap(row, column);
    }

    protected abstract R[][] initMap(int rowSize, int columnSize);

    @Override
    public boolean hasRoomAt(int row, int column) {
        return isInMapBoundaries(row, column)
                && map[row][column] != null;
    }

    @Override
    public boolean isEmptyAt(int row, int column) {
        return isInMapBoundaries(row, column)
                && map[row][column] == null;
    }

    private boolean isInMapBoundaries(int row, int column) {
        return row >= 0
                && row < rowMax
                && column >= 0
                && column < columnMax;
    }
}
