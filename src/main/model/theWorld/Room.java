package model.theWorld;

import java.util.ArrayList;

public class Room {

	private boolean hasPlayer = false;
	private boolean hasMonster;
	private int row;
	private int colm;
	private ArrayList<Direction> possibleDirections = new ArrayList<Direction>();
	private boolean isExit = false;

	public Room(boolean hasMonster, int row, int colm) {
		this.setHasMonster(hasMonster);
		this.row = row;
		this.colm = colm;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return colm;
	}

	@Override
	public String toString() {
		return "Room:: " + row + " | " + colm + "; Monster: " + hasMonster;
	}

	/**
	 * Mark this room as an exit.
	 */
	public void isExit() {
		this.isExit = true;
	}

	/**
	 * Returns true, when room is the exit of the dungeon.
	 * 
	 * @return boolean
	 */
	public boolean checkForExit() {
		return isExit;
	}

	public boolean getHasMonster() {
		return hasMonster;
	}

	public void setHasMonster(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}

	public void addPossibleDirection(Direction direction) {
		if (!possibleDirections.contains(direction)) {
			possibleDirections.add(direction);
		}
	}

	public ArrayList<Direction> getDirections() {
		return possibleDirections;
	}

	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}

	public boolean getHasPlayer() {
		return hasPlayer;
	}

}
