package game.entity.mob;

import java.awt.Rectangle;

import game.graphics.AnimatedAll;
import game.graphics.Screen;
;

public class Alien extends Enemy{

	int dir;
	Rectangle connected;
	
	public Alien(int x, int y, Rectangle hitbox) {
		super((int)Math.random()*(hitbox.width-hitbox.x) + hitbox.x, hitbox.y-32, 32, 32);
		xVel = 1;
		dir = 1;
	}
	
	public void render(Screen screen, int dy) {
		screen.renderSprite(x, y+dy, AnimatedAll.get("alien"), false);
	}
	
	public void update() {
		x += xVel*dir;
	}


}
