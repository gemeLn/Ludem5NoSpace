package game.level;

import java.awt.Rectangle;

import game.Game;
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
	int barLength = 180;
	int speedLength = 0;
	int jumpLength = 0;
	Sprite plus;
	Sprite x;
	Sprite battery;
	public Rectangle speedButton = new Rectangle(40, 100, 64, 64);
	public Rectangle jumpButton = new Rectangle(40, 200, 64, 64);
	public Rectangle exitButton = new Rectangle(10, 10, 64, 64);

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
			jumpLength = (int) (jump * barLength / maxJump);

		}
	}

	public void buySpeed() {
		if (speed < speedCosts.length && player.coins > speedCosts[speed]) {
			speed++;
			player.incSpeed();
			speedLength = (int) (speed * barLength / maxSpeed);
		}
	}

	public void exit() {
		Game.game.state = Game.GAMESTATE;
	}

	public void render(Screen screen) {
		screen.renderSprite(speedButton.x / 2, speedButton.y / 2, plus, false);
		screen.renderSprite(jumpButton.x / 2, jumpButton.y / 2, plus, false);
		screen.renderSprite(exitButton.x / 2, exitButton.y / 2, x, false);
		
		screen.renderSprite(speedButton.x / 2 + 50, speedButton.y / 2, battery, false);
		screen.renderSprite(jumpButton.x / 2 + 50, jumpButton.y / 2, battery, false);
		for (int i = 0; i < speed; i++) {
			screen.fillRect(speedButton.x / 2 + 54, speedButton.y / 2 + 4, speedLength, 24, 0xffff00, false);
		}
		for (int i = 0; i < jump; i++) {
			screen.fillRect(jumpButton.x / 2 + 54, jumpButton.y / 2 + 4, jumpLength, 24, 0xffff00, false);
		}

	}

}
