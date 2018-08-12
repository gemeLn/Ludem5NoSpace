package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Platform extends Rectangle {
	private static final long serialVersionUID = -1848741423621673428L;
	Sprite left;
	Sprite middle;
	Sprite right;
	int middleSegments = 0;

	public Platform(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
		if (w % 10 != 0) {
			System.err.print("pLATFform width should be divisibl eby 10");
		}
		left = new Sprite(10, 10, 0, 0, SpriteSheet.platform);
		middle = new Sprite(10, 10, 10, 0, SpriteSheet.platform);
		right = new Sprite(10, 10, 20, 0, SpriteSheet.platform);
		middleSegments = (w / 10) - 2;
	}

	public Platform(String[] s) {
		this(intr(s[0]), intr(s[1]), intr(s[2]), intr(s[3]));
	}

	public Platform clone() {
		return new Platform(x, y, width, height);
	}

	public static int intr(String s) {
		return Integer.parseInt(s);
	}

	public void render(Screen screen, int dy) {
		screen.drawRect(x, y + dy, width, height, 0xff0000, false);
		screen.renderSprite(x, y + dy, left, false);
		for (int i = 1; i <= middleSegments; i++) {
			screen.renderSprite(x + i * 10, y + dy, middle, false);
		}
		screen.renderSprite(x + (middleSegments + 1) * 10, y + dy, right, false);
	}

	public void update(Rectangle e) {
		x = e.x;
		y = e.y;
		height = e.height;
		width = e.width;
	}
}
