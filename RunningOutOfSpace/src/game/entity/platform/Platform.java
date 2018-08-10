package game.entity.platform;

import game.entity.Entity;
import game.graphics.Screen;

public abstract class Platform extends Entity{
	
	public abstract void collide();
	
	public abstract void update();

	public abstract void render(Screen screen);
}
