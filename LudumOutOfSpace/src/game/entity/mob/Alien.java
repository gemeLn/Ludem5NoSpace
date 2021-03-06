package game.entity.mob;

import java.awt.Rectangle;

import game.Game;
import game.graphics.AnimatedAll;
import game.graphics.Screen;
import game.level.Level;;

public class Alien extends Enemy {

	int dir;
	Rectangle connected;
	boolean die;

	public Alien(Rectangle hitbox) {
		super((int) (Math.random() * (hitbox.width - hitbox.x)) + hitbox.x, hitbox.y - 32, 32, 32);
		while ((((int) Level.wall1.getXD()) + Level.wall1.getWD() > x - 1) || ((Level.wall2.getXD() < x + 33))) {
			x = (int) (Math.random() * (hitbox.width - hitbox.x));
		}

		xVel = 1;
		dir = 1;
		connected = hitbox;
	}

	public void render(Screen screen, int dy) {
		if (!die) {
			screen.renderSprite(x, y + dy, AnimatedAll.get("alien"), false, dir != 1);
			//screen.drawRect(hitbox.x, hitbox.y + dy, hitbox.width, hitbox.height, 0xff00ff, false);
		}
	}

	public void die() {
		hitbox.width = 0;
		hitbox.height = 0;
		hitbox.x = 1000;
		die = true;
	}

	public void update(int dy) {
		if(50 >= Game.getWindowWidth() - 2 * w)
			die();
		x += xVel * dir;
		hitbox.x = x;
		hitbox.y = y;
		if ((!(connected.contains((int) x, (int) y + 33))) || (!(connected.contains((int) x + 32, (int) y + 33)))
				|| (((int) Level.wall1.getXD()) + Level.wall1.getWD() > x - 1) || ((Level.wall2.getXD() < x + 33))) {
			dir *= -1;
			x += xVel * dir;
		}
	}

}
