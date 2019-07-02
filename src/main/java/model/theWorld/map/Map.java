package model.theWorld.map;

import model.theWorld.Room;

public interface Map<R extends Room>  {

    void addRoomToMap(R room);

    R[][] getMap();

    R getRoom(int row, int column);

    boolean hasRoomAt(int row, int column);

    boolean isEmptyAt(int row, int column);
}