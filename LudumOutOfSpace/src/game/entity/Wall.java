package game.entity;

import java.awt.Rectangle;

import game.Game;
import game.graphics.Screen;

public class Wall extends Entity{
	double x = - 10;
	double y;
	double w;
	double h;
	boolean right;
	double speed = 0.125;
	int windowwidth = Game.getWindowWidth();

	public Wall(boolean right) {
		this.right = right;
		if (right)
			x = Game.getWindowWidth() + 10;
		h = Game.getWindowHeight();
		hitbox = new Rectangle((int)x, (int)y, (int)w, (int)h);

	}

	public void update() {
		w += speed;
		if (right)
			x -= speed;
		if(Game.main.level.player.w>windowwidth-2*w) {
			System.out.println("DIE");
		}
		hitbox.x = (int) x;
		hitbox.y = (int) y;
		hitbox.width = (int) w;
		hitbox.height = (int) h;
	}

	public void render(Screen screen) {
		screen.drawRect((int) x, 0, (int) w, (int) h, 0xffffff, false);
	}
}
