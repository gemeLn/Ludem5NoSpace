package game.entity.mob;

import java.awt.Rectangle;

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
	
	public void update(Rectangle wall1, Rectangle wall2, int dy) {
		x += xVel * dir;
		hitbox.x = x;
		hitbox.y = y;
		if((!(connected.contains((int)x,(int)y+33)))||(!(connected.contains((int)x+32,(int)y+33))) || (wall1.contains((int)x-1,(int)y+dy)) || (wall2.contains((int)x+33,(int)y+dy))) {
			dir *= -1;
			x += xVel * dir;
		}
	}


}
