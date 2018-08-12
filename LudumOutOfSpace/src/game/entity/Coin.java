package game.entity;

import java.awt.Rectangle;

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
		this.sprite = new Sprite(16, 16, 0, 0, SpriteSheet.animatedCoin);
		animatedSprite = new AnimatedSprite(SpriteSheet.animatedCoin, 16, 16, 5);
		hitbox = new Rectangle(x, y, 16, 16);
		w = 16;
		h = 16;
	}
	
	public void render(Screen screen, int dy) {
		if(!collected) {
			screen.drawRect(x, y + dy, w, h, 0xff00ff, false);
			screen.renderSprite(x, y + dy, animatedSprite.getSprite(), false);
		}
	}
	
	public void update() {
		animatedSprite.update(10);
	}
	
	public void collected() {
		collected = true;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
}
	