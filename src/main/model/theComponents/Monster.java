package model.theComponents;

import java.util.Random;

public class Monster extends GameFigure {

	public Monster(String name) {
		super(name);
		determineMonsterHealth();
	}

	private void determineMonsterHealth() {
		int health = 1;
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(3);
		this.setHitPoints(health + healthBonus);
	}
}
