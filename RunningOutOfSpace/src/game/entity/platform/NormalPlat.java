package game.entity.platform;

import java.awt.Rectangle;

import game.graphics.Screen;

public class NormalPlat extends Platform{

	
	public NormalPlat(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;	
		
	}
	
	public void update() {
		
	}

	
	public void render(Screen screen) {
		
	}

	public void collide() {
		
	}

}
