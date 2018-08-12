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
	int maxSpeed = speedCosts.length;
	int maxJump = jumpCosts.length;
	int barLength = 200;
	int speedLength = 0;
	int jumpLength = 0;
	Sprite plus;
	Sprite x;
	Sprite battery;
	public Rectangle speedButton = new Rectangle(40, 50, 64, 64);
	public Rectangle jumpButton = new Rectangle(40, 150, 64, 64);

	public Shop(Player p) {
		this.player = p;
		plus = new Sprite(32, 32, 0, 0, SpriteSheet.plus);
		x = new Sprite(32, 32, 0, 0, SpriteSheet.x);
		battery = new Sprite(200, 32, 0, 0, SpriteSheet.battery);
	}

	public void update() {

	}

	public void buyJump() {
		if (jump < jumpCosts.length && player.coins > jumpCosts[jump]) {
			jump++;
			player.incJumpheight();

		}
	}

	public void buySpeed() {
		if (speed < speedCosts.length && player.coins > speedCosts[speed]) {
			speed++;
			player.incSpeed();
			speedLength = (int) (speed * barLength / maxSpeed);
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(speedButton.x / 2, speedButton.y / 2, plus, false);
		screen.renderSprite(jumpButton.x / 2, jumpButton.y / 2, plus, false);
		screen.renderSprite(speedButton.x/2+64, speedButton.y/2, battery, false);
		for (int i = 0; i < speed; i++) {
			screen.fillRect(speedButton.x / 2 + 64, speedButton.y / 2, speedLength, 32, 0xffff00, false);
		}

	}

}
