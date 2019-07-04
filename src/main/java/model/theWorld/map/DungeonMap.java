package model.theWorld.map;

import model.theWorld.room.DungeonRoom;

public class DungeonMap extends BaseMap<DungeonRoom> {

    public DungeonMap(int rowSize, int columnSize) {
        super(rowSize, columnSize);
    }

    protected DungeonRoom[][] initMap(int rowSize, int columnSize) {
        DungeonRoom[][] dungeonMap = new DungeonRoom[rowSize][columnSize];

        for (int row = 0; row < rowSize; row++) {
            for (int colm = 0; colm < columnSize; colm++) {
                dungeonMap[row][colm] = null;
            }
        }
        return dungeonMap;
    }

    @Override
    public void addRoomToMap(DungeonRoom newRoom) {
        map[newRoom.getRow()][newRoom.getColumn()] = newRoom;
    }

    @Override
    public DungeonRoom[][] getMap() {
        return map;
    }

    @Override
    public DungeonRoom getRoom(int row, int column) {
        return map[row][column];
    }
}

