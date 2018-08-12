package game.entity.mob;

import game.graphics.AnimatedAll;
import game.graphics.Screen;
import game.graphics.Sprite;

public class Alien extends Enemy{

	int dir;
	
	public Alien(int x, int y, int w, int h, Sprite s) {
		super(x, y, w, h, s);
		xVel = 3;
		
	}
	
	public void render(Screen screen, int dy) {
		screen.renderSprite(x, y+dy, AnimatedAll.get("Alien"), false);
	}
	
	public void update() {
		x = xVel*dir;
	}


}
