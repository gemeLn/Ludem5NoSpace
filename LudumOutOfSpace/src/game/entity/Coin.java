package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Coin extends Entity{
	
	private boolean collected;
	
	public Coin(int x, int y) {
		super(x, y, new Sprite(16, 16, 0, 0, SpriteSheet.coin));
		this.x = x;
		this.y = y;
		this.sprite = new Sprite(16, 16, 0, 0, SpriteSheet.coin);
		hitbox = new Rectangle(x, y, 16, 16);
		w = 16;
		h = 16;
	}
	
	public void render(Screen screen, int dy) {
		if(!collected) {
			screen.drawRect(x, y + dy, w, h, 0xff00ff, false);
			screen.renderSprite(x, y + dy, sprite, false);
		}
	}
	
	public void update(Rectangle e) {
		x = e.x;
		y = e.y;
		h = e.height;
		w = e.width;
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = 16;
		hitbox.height = 16;
	}
	
	public void collected() {
		collected = true;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
}
	