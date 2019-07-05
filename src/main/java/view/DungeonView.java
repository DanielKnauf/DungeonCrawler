package view;

import model.theWorld.room.DungeonRoom;

public class DungeonView {

    public void displayMonsterMovement(DungeonRoom room){
        System.out.print("Monster moves to ");
        displayDungeonRoom(room);
    }

    private void displayDungeonRoom(DungeonRoom room){
        System.out.println("Room:: " + room.getRow() + " | " + room.getColumn() + "; Monster: " + room.hasMonster());
    }
}
