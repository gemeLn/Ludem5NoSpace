package game.entity.mob;

import java.awt.Rectangle;

import game.entity.Entity;
import game.entity.Platform;
import game.graphics.Sprite;

public class Enemy extends Entity {
	
	double xVel, yVel;
	
	public Enemy(int x, int y, int w, int h, Sprite s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sprite = s;
		hitbox = new Rectangle(x, y, w, h);
	}
	public Enemy(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		hitbox = new Rectangle(x, y, w, h);
	}
	
	
	public void collisions() {

	}

	public Enemy(String[] s) {
		
	}
}
