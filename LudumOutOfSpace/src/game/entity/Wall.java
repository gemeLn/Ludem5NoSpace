package game.entity;

import java.awt.Rectangle;

import game.Game;
import game.entity.mob.Player;
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

	public void update(int i, Player player) {
		if (tick >= 1200) {
			close();
		} 
		if(reset){
			if (w < 145)
				w -= 4*speed;
			if (x > 136)
				x += 4*speed;
			if ((w - 10) <= 0) {
				tick = 0;
				reset = false;
				if (w < 145)
					w = 10;
				if (x > 136)
					x = Game.getWindowWidth();
			}
		}

		if (player.w - 16 > windowwidth - 2 * w) {
			Game.game.gameOver(player.y);
		}
		hitbox.x = (int) x;
		hitbox.y = (int) -dy;
		hitbox.width = (int) w;
		hitbox.height = (int) h;
		if (i != 100) {
			tick++;
		}
	}

	public void reset() {
		tick = 0;
		reset = true;
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

		//screen.drawRect((int) x, 0, (int) w, (int) h, 0xff00ff, false);
		this.dy = dy;
	}

	public double getXD() {
		return x;
	}

	public double getWD() {
		return w;
	}
}
