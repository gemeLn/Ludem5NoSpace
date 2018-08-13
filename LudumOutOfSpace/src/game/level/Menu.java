package game.level;

import java.awt.Rectangle;

import game.Game;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Menu {
	Sprite menu1 = new Sprite(270, 375, 0, 0, SpriteSheet.menu);
	Sprite menu2 = new Sprite(270, 375, 0, 0, SpriteSheet.menu2);
	Sprite sprite = menu1;

	public void sprite1() {
		sprite = menu1;
	}

	public void sprite2() {
		sprite = menu2;
	}

	public void startGame() {
		Game.menuTheme.stop();
		Game.game.state = Game.GAMESTATE;
		Game.game.level.soundStart();
	}

	public Rectangle start = new Rectangle(205, 430, 130, 65);

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderSprite(0, 0, sprite, false);
	}
}
