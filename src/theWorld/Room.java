package theWorld;

public class Room {

	private boolean hasMonster;
	private Direction direction;

	public Room(boolean hasMonster) {
		this.setHasMonster(hasMonster);
		this.direction = Direction.LEFT;
	}

	public boolean getHasMonster() {
		return hasMonster;
	}

	public void setHasMonster(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}

	public Direction getDirection() {
		return direction;
	}

	public String displayRoom() {
		if (hasMonster) {
			return "[ \\o/ ]";
		}
		return "[     ]";
	}
}
