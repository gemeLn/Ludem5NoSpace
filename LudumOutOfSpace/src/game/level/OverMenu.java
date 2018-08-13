package game.level;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class OverMenu {
	public Sprite menu1 = new Sprite(270, 375, 0, 0, SpriteSheet.overmenu1);
	public Sprite menu2 = new Sprite(270, 375, 0, 0, SpriteSheet.overmenu2);
	public Sprite menu3 = new Sprite(270, 375, 0, 0, SpriteSheet.overmenu3);
	public Sprite sprite = menu1;
	public Rectangle restartHitbox = new Rectangle(80, 160, 136, 70);
	public Rectangle creditHitbox = new Rectangle(324, 160, 136, 70);

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderSprite(0, 0, sprite, false);
	}
}
