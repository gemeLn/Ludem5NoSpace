package game.entity.platform;

import java.awt.Rectangle;

import game.graphics.Screen;

public class Platform extends Rectangle {
	private static final long serialVersionUID = -1848741423621673428L;

	public Platform(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
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

	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, 0xff0000, false);
	}
}
