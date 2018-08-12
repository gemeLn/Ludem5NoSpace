package game.level;

import java.awt.Rectangle;

import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Shop {
	Player player;
	int[] speedCosts = { 1, 2, 4, 8 };
	int[] jumpCosts = { 1, 11, 13 };
	int speed = 0;
	int jump = 0;
	Sprite plus;
	public Rectangle speedButton = new Rectangle(10, 50, 64, 64);
	public Rectangle jumpButton = new Rectangle(10, 150, 64, 64);

	public Shop(Player p) {
		this.player = p;
		plus = new Sprite(32, 32, 0, 0, SpriteSheet.plus);
	}

	public void update() {

	}

	public void buyJump() {
		if (jump < jumpCosts.length && p.coins > jumpCosts[jump]) {
			jump++;
			player.incJumpheight();

		}
	}

	public void buySpeed() {
		if (speed < speedCosts.length && p.coins > speedCosts[speed]) {
			speed++;
			player.incSpeed();
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(speedButton.x / 2, speedButton.y / 2, plus, false);
		screen.renderSprite(jumpButton.x / 2, jumpButton.y / 2, plus, false);

	}

}
