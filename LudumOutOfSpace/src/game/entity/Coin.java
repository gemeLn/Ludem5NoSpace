package game.entity;

import java.awt.Rectangle;

import game.graphics.AnimatedAll;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Coin extends Entity{
	
	private boolean collected;
	private AnimatedSprite animatedSprite;
	
	public Coin(int x, int y) {
		super(x, y, new Sprite(16, 16, 0, 0, SpriteSheet.animatedCoin));
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x, y, 16, 16);
		w = 16;
		h = 16;
	}
	
	public void render(Screen screen, int dy) {
		if(!collected) {
			screen.drawRect(x, y + dy, w, h, 0xff00ff, false);
			screen.renderSprite(x, y + dy, AnimatedAll.get("coin"), false);
		}
	}
	
	public void update() {
	}
	
	public void collected() {
		collected = true;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
}
	