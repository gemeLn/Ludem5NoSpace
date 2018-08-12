package game.entity.mob;

import game.graphics.Sprite;

public class Alien extends Enemy{

	int dir;
	
	public Alien(int x, int y, int w, int h, Sprite s) {
		super(x, y, w, h, s);
		xVel = 3;
		
	}
	
	public void render() {
		
	}
	
	public void update() {
		x = xVel*dir;
	}


}
