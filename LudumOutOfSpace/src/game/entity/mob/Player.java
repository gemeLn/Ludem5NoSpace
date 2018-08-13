package game.entity.mob;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.UIManager;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.Wall;
import game.entity.items.Item;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.level.Level;

public class Player extends Mob {

	private String name;
	private Keyboard input;
	private Sprite sprite;
	private boolean walking = false;
	int halfwidth;
	int spriteSize = 32;
	int halfSpriteSize = spriteSize / 2;
	// private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32,
	// 32, 1);
	// private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32,
	// 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 6);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 6);
	private AnimatedSprite jumpAni = new AnimatedSprite(SpriteSheet.player_jump, 32, 32, 1);

	private AnimatedSprite animSprite = right;

	Sprite player_jump;

	public int coins;

	private int fireRate = 0;
	int ground;
	private double yVel;
	private int xVel;

	private int jump;
	private int speed = 3;
	private int jumpheight = 10;

	public void incSpeed() {
		speed++;
	}

	public void incJumpheight() {
		jumpheight++;
	}

	private int walljump;
	private int wallCD = 800;
	private int walljumpFreezeTime = 300;
	private long leftcd = 0;
	private long rightcd = 0;

	private UIManager ui;
	private Wall leftwall;
	private Wall rightwall;
	private int wallDir = 1;

	public ArrayList<Item> inventory = new ArrayList<Item>();

	public Player(String name, int x, int y, Keyboard input, Level level) {
		this.level = level;
		this.ground = level.ground;
		this.name = name;
		this.x = x;
		this.y = y + 16;
		this.input = input;
		this.leftwall = (Wall) level.wall1;
		this.rightwall = (Wall) level.wall2;
		w = 20;
		halfwidth = w / 2;
		h = 32;
		hitbox = new Rectangle(x, y, w, h);
		sprite = animSprite.getSprite();

		// Player default attributes
		health = 100;

		this.x = 100;

		yVel = 0.0;
		xVel = 0;
		jump = 1;
		walljump = 0;
		coins = 1000;
	}

	public String getName() {
		return name;
	}

	public void update() {
		keys();
		gravity();
		collisions();
		hitbox.x = x + halfSpriteSize - halfwidth;
		hitbox.y = y;

	}

	public int getRealX() {
		return x + halfSpriteSize - halfwidth;
	}

	public void collisions() {
		Rectangle predictedHitbox = new Rectangle((getRealX() + xVel), (int) (y + yVel), w, h);
		boolean yOK = true;
		boolean xOK = true;
		for (Block b : level.getBlocks()) {
			if (b.intersects(predictedHitbox)) {
				int cx1 = b.x + (int) (b.width / 2.0);
				int cy1 = b.y + (int) (b.height / 2.0);
				int cx2 = x + halfSpriteSize;
				int cy2 = y + halfSpriteSize;
				double ang = Math.atan2(cy1 - cy2, cx2 - cx1);
				if ((ang < b.angle && ang > -b.angle) || (ang > b.angleComp && ang < -b.angleComp)) {
					xOK = false;
					if (cx2 > cx1) {
						x = b.x + b.width - halfSpriteSize + halfwidth;
					}
					System.out.println("XCOL");
				} else {
					System.out.println("YCOL");

					yOK = false;
					if (yVel > 0) {
						jump = 1;
						y = b.y - h;
					}
					if (cy2 > cy1) {
						y = b.y + b.height + 1;
					}
				}
			}
		}

		for (Spike spike : level.getSpikes()) {
			if (spike.intersects(hitbox)) {
				Game.game.gameOver();
			}
		}

		for (Alien alien : level.getAlien()) {
			if (alien.getHitbox().intersects(hitbox)) {
				Game.game.gameOver();
				System.out.println("dead");
			}
		}

		for (Coin coin : level.getCoins()) {
			if (coin.hitbox.intersects(hitbox) && !coin.isCollected()) {
				coins++;
				coin.collected();
			}
		}

		for (Platform plat : level.getPlatforms()) {
			if (plat.intersects(predictedHitbox)) {
				if (yVel > 0 && y + h < plat.y + plat.height) {
					y = plat.y - h;
					yOK = false;
					jump = 1;
					resetWallJumps();
				}
				break;
			}
		}

		if (leftwall.getHitbox().intersects(predictedHitbox)) {
			xOK = false;
			x = leftwall.getHitbox().width - 10 - halfSpriteSize + halfwidth;
			// rightwall.resetJump();
			if (leftwall.canJump()) {
				walljump = 1;
				leftwall.delayJump(wallCD);
				rightwall.delayJump(wallCD);
				wallDir = 1;
			}
		}

		else if (rightwall.getHitbox().intersects(predictedHitbox)) {
			xOK = false;
			x = rightwall.getHitbox().x - 32 + halfSpriteSize - halfwidth;
			// leftwall.resetJump();
			if (rightwall.canJump()) {
				walljump = 1;
				leftwall.delayJump(wallCD);
				rightwall.delayJump(wallCD);
				wallDir = -1;
			}

		} else {
			walljump = 0;
		}
		if (xOK) {

			x += xVel;
			if (xVel > 0)
				xVel--;
			else if (xVel < 0)
				xVel++;
			hitbox.x = x + halfSpriteSize - halfwidth;
		} else {
			xVel = 0;
		}

		if (yOK) {
			y += yVel;
			hitbox.y = y;
		} else {
			yVel = 0;
		}

	}

	public void keys() {
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (fireRate > 0)
			fireRate--;
		if (input.up) {
			if (jump > 0) {
				yVel = -jumpheight;
				jump--;
			}
			if (walljump > 0) {
				if (wallDir > 0) {
					leftcd = System.currentTimeMillis() + walljumpFreezeTime;
					animSprite = right;
				} else {
					animSprite = left;
					rightcd = System.currentTimeMillis() + walljumpFreezeTime;
				}
				yVel = -jumpheight;
				walljump--;
				xVel = wallDir * 10;
			}
		}
		if (input.shopkey) {
			boolean hit = false;
			for (Interactable i : level.getInteractables()) {
				if (i.hitbox.intersects(hitbox)) {
					hit = true;
					break;
				}
			}
			if (hit) {
				// OPEN SHOP
				Game.game.state = Game.SHOPSTATE;

			}else{ 
			if(inventory.size()>0){
			if(inventory.get(0).checkAvail()){
			inventory.get(0).use(this);
			inventory.get(0).cooldown();
			}
			}
			}
		}
		if (input.left) {
			walking = true;
			if (System.currentTimeMillis() > leftcd) {
				animSprite = left;
				if (xVel > -speed)
					xVel -= 2;
			}
		} else if (input.right) {
			walking = true;
			if (System.currentTimeMillis() > rightcd) {
				animSprite = right;
				if (xVel < speed)
					xVel += 2;
			}
		} else {
			walking = false;
		}
	}

	public void gravity() {
		yVel += 0.5;
	}

	public double getYVel() {
		return yVel;
	}

	public void resetWallJumps() {
		leftwall.resetJump();
		rightwall.resetJump();
	}

	public void render(Screen screen, int dy) {
		int flip = 0;
		sprite = animSprite.getSprite();
		if (!walking && jump >= 1)
			screen.renderSprite(x, y + dy, Sprite.player_forward, false, animSprite.equals(left));
		else if (jump >= 1) {
			screen.renderSprite(x, y + dy, sprite, false);
		} else
			screen.renderSprite(x, y + dy, jumpAni.getSprite(), false, animSprite.equals(left));
		screen.drawRect(hitbox.x, hitbox.y + dy, hitbox.width, hitbox.height, 0xff0000, false);

	}

}
