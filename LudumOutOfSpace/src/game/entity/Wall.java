package game.entity;

import game.Game;
import game.graphics.Screen;

public class Wall extends Entity {
	boolean left;

	public Wall(boolean left) {
		this.left = left;
	}

	public void update() {
	}

	public void render(Screen screen) {
		screen.drawRect(0, 0, 0, Game.getWindowHeight(), 0xffffff, true);
		
	}
}
