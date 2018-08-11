package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;

public class Spike extends Rectangle {
	private static final long serialVersionUID = -4952865016088589373L;

	public Spike(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public void update() {
	}

	public void render(Screen screen, int dy) {
		screen.drawRect(x, y + dy, width, height, 0x00ff00, true);
	}
}
