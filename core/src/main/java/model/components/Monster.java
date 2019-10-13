package model.components;

import java.util.Random;

public class Monster extends GameFigure {

	public Monster(String name) {
		super(name);
		determineMonsterHealth();
	}

	private void determineMonsterHealth() {
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(3);
		this.setHitPoints(1 + healthBonus);
	}
}
