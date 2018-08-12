package game.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.entity.items.Fireball;
import game.entity.items.Item;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;

public class Shop {
	public ArrayList<Item> availableShop = new ArrayList<Item>();
	Player player;
	int[] speedCosts = { 1, 2, 4, 8 };
	int[] jumpCosts = { 1, 11, 13 };
	String speedCost = "x " + speedCosts[0];
	String jumpCost = "x " + jumpCosts[0];
	int speed = 0;
	int jump = 0;
	int maxSpeed = speedCosts.length;
	int maxJump = jumpCosts.length;
	int barLength = 180;
	int speedLength = 0;
	int jumpLength = 0;
	int smallcoinx = 160;
	Keyboard key;
	Sprite plus;
	Sprite x;
	Sprite battery;
	Sprite bigcoin;
	Sprite coin;
	public Rectangle speedButton = new Rectangle(30, 125, 64, 64);
	public Rectangle jumpButton = new Rectangle(30, 250, 64, 64);
	public Rectangle exitButton = new Rectangle(10, 10, 64, 64);

	public Shop(Player p, Keyboard key) {
		availableShop.add(new Fireball());
		this.player = p;
		this.key = key;
		plus = new Sprite(32, 32, 0, 0, SpriteSheet.plus);
		x = new Sprite(32, 32, 0, 0, SpriteSheet.x);
		battery = new Sprite(200, 32, 0, 0, SpriteSheet.battery);
		bigcoin = new Sprite(32, 32, 0, 0, SpriteSheet.bigcoin);
		coin = new Sprite(16, 16, 0, 0, SpriteSheet.animatedCoin);
	}

	public void update() {
		if (key.esc) {
			exit();
		}
		int itemsize = availableShop.size();
		for (int i = 0; i < itemsize; i++) {
			availableShop.get(i).hitbox.x = 10 + i * 32;
			availableShop.get(i).hitbox.y = 190;
		}

	}

	public void buySpeed() {
		if (speed < speedCosts.length) {
			if (player.coins > speedCosts[speed]) {
				player.coins -= speedCosts[speed];
				speed++;
				player.incSpeed();
				speedLength = (int) (speed * barLength / maxSpeed);
				if (speed < maxSpeed) {
					speedCost = "x " + speedCosts[speed];
				} else {
					speedCost = "MAXED OUT";
				}
			}
		}
	}

	public void buyJump() {
		if (jump < jumpCosts.length) {
			if (player.coins > jumpCosts[jump]) {
				player.coins -= jumpCosts[jump];
				jump++;
				player.incJumpheight();
				jumpLength = (int) (jump * barLength / maxJump);
				if (jump < maxJump) {
					jumpCost = "x " + jumpCosts[jump];
				} else {
					jumpCost = "MAXED OUT";
				}
			}
		}
	}

	public void exit() {
		Game.game.state = Game.GAMESTATE;
	}

	public void render(Screen screen) {
		screen.renderSprite(speedButton.x / 2, speedButton.y / 2, plus, false);
		screen.renderSprite(jumpButton.x / 2, jumpButton.y / 2, plus, false);
		screen.renderSprite(exitButton.x / 2, exitButton.y / 2, x, false);
		screen.renderSprite(80, exitButton.y / 2, bigcoin, false);

		screen.renderSprite(speedButton.x / 2 + 40, speedButton.y / 2, battery, false);
		screen.renderSprite(jumpButton.x / 2 + 40, jumpButton.y / 2, battery, false);

		screen.renderSprite(smallcoinx, 45, coin, false);
		screen.renderSprite(smallcoinx, 105, coin, false);

		for (int i = 0; i < speed; i++) {
			screen.fillRect(speedButton.x / 2 + 44, speedButton.y / 2 + 4, speedLength, 24, 0xffff00, false);
		}
		for (int i = 0; i < jump; i++) {
			screen.fillRect(jumpButton.x / 2 + 44, jumpButton.y / 2 + 4, jumpLength, 24, 0xffff00, false);
		}
		int itemsize = availableShop.size();
		for (int i = 0; i < itemsize; i++) {
			screen.renderSprite(availableShop.get(i).hitbox.x, availableShop.get(i).hitbox.y, availableShop.get(i).icon,
					false);
		}

	}

	public void drawStrings(Graphics g) {
		g.setColor(Color.white);
		g.setFont(Game.bigShopFont);
		g.drawString("x " + player.coins, 230, exitButton.y + 45);
		g.setFont(Game.smallShopFont);
		g.drawString("Available Items", 210, 350);
		g.drawString("Speed +1", 2 * smallcoinx - 100, 115);
		g.drawString("Jump +1", 2 * smallcoinx - 100, 235);
		g.drawString(speedCost, 2 * smallcoinx + 40, 115);
		g.drawString(jumpCost, 2 * smallcoinx + 40, 235);

	}

}
