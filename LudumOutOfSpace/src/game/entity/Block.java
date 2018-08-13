package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Block extends Rectangle {
	private static final long serialVersionUID = -4952865016088589373L;
	int segments = 0;
	Sprite block = new Sprite(10, 10, 0, 0, SpriteSheet.block);

	public Block(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		if (w % 10 != 0) {
			System.err.println("WDITH MUST BE DIV 10");
		}
		segments = w / 10;
	}

	public void update() {
	}

	public void render(Screen screen, int dy) {
		screen.drawRect(x, y + dy, width, height, 0x00ff00, true);
		for(int i=0;i<segments;i++) {
			screen.renderSprite(x+10*i, y+dy, block, false);
		}
	}
}
