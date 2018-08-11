package game.entity.mob;

import game.entity.Entity;
import game.graphics.Screen;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected int health;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	private int abs(double value) {
		if (value < 0)
			return -1;
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen, int dy);

	protected void shoot(int x, int y, double dir) {
		/*
		*/
	}

}
