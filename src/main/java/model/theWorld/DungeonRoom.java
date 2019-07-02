package model.theWorld;

public class DungeonRoom extends Room {

    private boolean hasPlayer;
    private boolean hasMonster;
    private boolean isExit;

    public DungeonRoom(boolean hasMonster, int row, int column) {
        super(row, column);
        this.hasMonster = hasMonster;
    }

    /**
     * Mark this room as an exit.
     */
    void markAsExit() {
        this.isExit = true;
    }

    /**
     * @return <code>true</code>,  when this room is an exit of the dungeon.
     */
    public boolean isExit() {
        return isExit;
    }

    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public boolean hasMonster() {
        return hasMonster;
    }

    void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }
}
