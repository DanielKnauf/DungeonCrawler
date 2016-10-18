package model_AdventureGame.theComponents;

import model_AdventureGame.rpslsFighting.Move;

public class GameFigure {

	private Move move;
	private int hitPoints;
	private String name;

	public GameFigure(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public Move getMove() {
		return this.move;
	}

	public void setHitPoints(int hitpoints) {
		this.hitPoints = hitpoints;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public boolean gotHit() {
		hitPoints--;

		if (hitPoints == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Display hitpoints.
	 */
	public String displayHitpoints() {
		String displayedHitpoint = "";
		for (int i = 0; i < hitPoints; i++) {
			displayedHitpoint += " ❤️ ";
		}
		return displayedHitpoint;
	}

}
