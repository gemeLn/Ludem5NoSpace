package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Vender extends Interactable {
	int x, y;

	public Vender(int x, int y) {
		sprite = new Sprite(32, 32, 0, 0, SpriteSheet.shop);
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x, y, 32, 32);
	}

	@Override
	public void action() {

	}

	public void render(Screen screen, int dy) {
		screen.renderSprite(x, y + dy, sprite, false);
	}

}
