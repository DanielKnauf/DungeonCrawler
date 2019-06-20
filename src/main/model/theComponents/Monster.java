package model.theComponents;

import java.util.Random;

public class Monster extends GameFigure {

	public Monster(String name) {
		super(name);
		determineMonsterHealth();
	}

	/**
	 * 
	 * @return
	 */
	private void determineMonsterHealth() {
		int health = 1;
		Random randomizer = new Random();
		int healthBonus = randomizer.nextInt(3);
		health += healthBonus;
		this.setHitPoints(health + healthBonus);
	}
}
