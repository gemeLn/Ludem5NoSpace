package game.entity.mob;

import java.awt.Point;
import java.awt.Rectangle;

import game.Game;
import game.graphics.AnimatedAll;
import game.graphics.Screen;
;

public class Alien extends Enemy{

	int dir;
	Rectangle connected;
	
	public Alien(Rectangle hitbox) {
		super((int)Math.random()*(hitbox.width-hitbox.x) + hitbox.x, hitbox.y-32, 32, 32);
		xVel = 1;
		dir = 1;
		connected = hitbox;
	}
	
	public void render(Screen screen, int dy) {
		screen.renderSprite(x, y+dy, AnimatedAll.get("alien"), false, dir != 1);
	}
	
	public void update() {
		System.out.println(x);
		x += xVel * dir;
		if((!(connected.contains((int)x,(int)y+33)))|| x < 0 || x > Game.getWindowWidth()) {
			dir *= -1;
			x += xVel * dir;
		}
	}


}
