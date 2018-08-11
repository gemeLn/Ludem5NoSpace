package game.entity;

import java.awt.Rectangle;
import java.util.Random;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;

public class Entity {

	protected int x, y,w,h;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	protected Rectangle hitbox;

	public Entity() {
	}

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void update() {
		updateHitbox();
	}

	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite(x, y, sprite, true);
	}
	
	public void updateHitbox() {
		if (hitbox != null) {
			hitbox.x = x;
			hitbox.y = y;
		}
	}
	
	public void updateHitbox(int offX, int offY) {
		if (hitbox != null) {
			hitbox.x = x-offX;
			hitbox.y = y-offY;
		}
	}

	public void remove() {
		//Remove from level
		removed = true;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

}
