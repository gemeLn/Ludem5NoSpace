package game.entity;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Vender extends Interactable {
	public Vender() {
		sprite = new Sprite(32, 32, 0, 0, SpriteSheet.shop);
	}

	@Override
	public void action() {

	}

	public void render(Screen screen, int dy) {
		screen.renderSprite(100, 100 + dy, sprite, false);
	}

}
