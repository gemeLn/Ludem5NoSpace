package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Spike extends Rectangle {
	private static final long serialVersionUID = -4952865016088589373L;
	
	Sprite sprite;

	public Spike(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		sprite = new Sprite(16, 16, 0, 0, SpriteSheet.spike);
	}

	public void update() {
	}

	public void render(Screen screen, int dy) {
		screen.renderSprite(x, y + dy, sprite, false);
		//screen.drawRect(x, y + dy, width, height, 0x00ff00, true);
	}
}
