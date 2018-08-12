package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;

public class Coin extends Entity{
	
	
	
	public Coin(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = 16;
		hitbox.height = 16;
		w = 16;
		h = 16;
	}
	
	public void render(Screen screen, int dy) {
		screen.drawRect(x, y +dy, w, h, 0xff0000, false);
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
	
}
