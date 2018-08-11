package game.entity.mob;

import java.awt.Rectangle;

import javax.swing.UIManager;

import game.entity.platform.Platform;
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
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, spriteSize, spriteSize, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, spriteSize, spriteSize, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, spriteSize, spriteSize, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, spriteSize, spriteSize, 3);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;
	int ground;
	private double yVel;
	private int jump;
	private int wallNum = 0;

	private UIManager ui;

	public Player(String name, int x, int y, Keyboard input, Level level) {
		this.level = level;
		this.ground = level.ground;
		this.name = name;
		this.x = x;
		this.y = y + 16;
		this.input = input;
		w = 20;
		halfwidth = w / 2;
		h = 32;
		hitbox = new Rectangle(x, y, w, h);
		sprite = Sprite.player_forward;
		// Player default attributes
		health = 100;

		this.x = 100;

		yVel = 0.0;
		jump = 1;
	}

	public String getName() {
		return name;
	}

	public void update() {
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (fireRate > 0)
			fireRate--;
		if (input.up) {
			animSprite = up;
			if (jump > 0) {
				yVel = -9;
				System.out.println(yVel);
				jump--;
			}
		}
		if (input.left) {
			animSprite = left;
			x -= 3;
		} else if (input.right) {
			animSprite = right;
			x += 3;
		}
		gravity();
		Rectangle predictedHitbox = new Rectangle(x + halfSpriteSize - halfwidth, (int) (y + yVel), w, h);
		boolean yOK = true;
		for (Platform plat : level.platforms) {
			if (plat.intersects(predictedHitbox)) {
				if (yVel > 0 && y + h < plat.y + plat.height) {
					y = plat.y - h;
					yVel = 0;
					jump = 1;
					wallNum = 0;
					yOK = false;
				}

			}
		}
		if (yOK) {
			y += yVel;
			hitbox = predictedHitbox;
		}
		System.out.println(level.entities.get(0).getWidth() * 2 + 1);
		if (level.entities.get(0).getHitbox().intersects(predictedHitbox)) {
			x = level.entities.get(0).getHitbox().width - 10 - halfSpriteSize + halfwidth;
		}

		else if (level.entities.get(1).getHitbox().intersects(predictedHitbox)) {
			x = level.entities.get(1).getHitbox().x - 32 + halfSpriteSize - halfwidth;
		}
	}

	public void gravity() {
		yVel += 0.5;
	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, sprite, flip);
		screen.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height, 0xff0000, false);

	}

}
