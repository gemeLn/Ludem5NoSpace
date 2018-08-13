package game.level;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class CreditMenu {
	public Sprite menu1 = new Sprite(270, 375, 0, 0, SpriteSheet.credmenu1);
	public Sprite menu2 = new Sprite(270, 375, 0, 0, SpriteSheet.credmenu2);
	public Sprite sprite = menu1;
	public Rectangle restartHitbox = new Rectangle(400, 270, 120, 70);

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderSprite(0, 0, sprite, false);
	}
}
