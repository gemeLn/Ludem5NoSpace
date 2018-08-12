package game.level;

import game.graphics.Screen;

public class Shop {
	int[] speedCosts = { 1, 2, 4, 8 };
	int[] jumpCosts = { 1, 11, 13 };
	int speed = 0;
	int jump = 0;

	public void update() {

	}

	public void render(Screen screen) {
		screen.drawRect(0, 0, 100, 100, 0x00ffff, false);
	}

}
