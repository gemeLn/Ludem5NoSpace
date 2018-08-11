package game.entity.mob;

import game.entity.Entity;
import game.graphics.Sprite;

public class Enemy extends Entity {
	public Enemy(int x, int y, int w, int h, Sprite s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sprite = s;
	}

	public Enemy(String[] s) {
	}
}
