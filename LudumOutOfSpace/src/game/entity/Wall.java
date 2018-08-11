package game.entity;

import game.Game;
import game.graphics.Screen;

public class Wall extends Entity {
	boolean right;
	int speed = 1;

	public Wall(boolean right) {
		this.right = right;
		if (right)
			x = Game.getWindowWidth()/2;
		h = Game.getWindowHeight();
	}

	public void update() {
		w += speed;
		if (right)
			x -= speed;

	}

	public void render(Screen screen) {
		screen.drawRect(x, 0, w, h, 0xffffff, false);
	}
}
