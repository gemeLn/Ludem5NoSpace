package game.entity;

import java.awt.Rectangle;

import game.Game;
import game.graphics.Screen;

public class Wall extends Entity {
	long nextJump = 0;
	double x = -10;
	double y;
	double w;
	double h;
	boolean right;
	double speed = 0.125;
	int windowwidth = Game.getWindowWidth();
	int tick;
	int dy;

	public boolean canJump() {
		return System.currentTimeMillis()>nextJump;
	}
	public void resetJump() {
		nextJump=0;
	}
	public void delayJump(long cd) {
		nextJump = System.currentTimeMillis()+cd;
	}
	

	public Wall(boolean right) {
		this.right = right;
		if (right)
			x = Game.getWindowWidth();
		h = Game.getWindowHeight();
		w = 10;
		hitbox = new Rectangle((int) x, (int) y, (int) w, (int) h);
		tick = 0;

	}

	public void update() {

		if (tick >= 1200) {
			close();
		}

		if (Game.main.level.player.w > windowwidth - 2 * w) {
			System.out.println("DIE");
		}
		hitbox.x = (int) x;
		hitbox.y = (int) y - dy;
		hitbox.width = (int) w;
		hitbox.height = (int) h;
		tick++;
	}

	public void close() {
		w += speed;
		if (right)
			x -= speed;
		if ((w - 10) % 38 == 0) {
			tick = 0;
		}
	}

	public void render(Screen screen, int dy) {
		screen.drawRect((int) x, 0, (int) w, (int) h, 0xffffff, false);
		this.dy = dy;
	}
}
