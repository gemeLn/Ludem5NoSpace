package game.entity;

import java.awt.Rectangle;

import game.Game;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Wall extends Entity {
	long nextJump = 0;
	double x = -10;
	double y;
	double w;
	double h;
	boolean right, reset;
	double speed = 0.125;
	int windowwidth = Game.getWindowWidth();
	int tick;
	int dy;
	Sprite sprite;

	public boolean canJump() {
		return System.currentTimeMillis() > nextJump;
	}

	public void resetJump() {
		nextJump = 0;
	}

	public void delayJump(long cd) {
		nextJump = System.currentTimeMillis() + cd;
	}

	public Wall(boolean right) {
		this.right = right;
		if (right)
			x = Game.getWindowWidth();
		h = Game.getWindowHeight();
		w = 10;
		hitbox = new Rectangle((int) x, (int) y, (int) w, (int) h);
		tick = 0;
		sprite = new Sprite(135, 375, 0, 0, SpriteSheet.door);

	}

	public void update() {

		if (tick >= 1200) {
			close();
		}

		if (Game.game.level.player.w > windowwidth - 2 * w) {
			System.out.println("DIE");
			System.out.println(-level.player.y - 375);
		}
		hitbox.x = (int) x;
		hitbox.y = (int) -dy;
		hitbox.width = (int) w;
		hitbox.height = (int) h;
		System.out.println(-dy);
		if (level.levelid != 100) {
			tick++;
		}
	}

	public void reset() {

	}

	public void close() {
		if (w < 145)
			w += speed;
		if (x > 136)
			x -= speed;
		if ((w - 10) % 38 == 0) {
			tick = 0;
		}
	}

	public void render(Screen screen, int dy) {
		if (!right)
			screen.renderSprite((int) (x + w - 135), 0, sprite, false);
		else
			screen.renderSprite((int) (x), 0, sprite, false);

		screen.drawRect((int) x, 0, (int) w, (int) h, 0xff00ff, false);
		this.dy = dy;
	}
}
