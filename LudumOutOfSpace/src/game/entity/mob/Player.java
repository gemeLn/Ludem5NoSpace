package game.entity.mob;

import java.awt.Rectangle;

import javax.swing.UIManager;

import game.Game;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;

public class Player extends Mob {

	private String name;
	private Keyboard input;
	private Sprite sprite;
	private boolean walking = false;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;
	int ground;
	private double yVel;
	private int jump;

	private UIManager ui;

	public Player(String name, int x, int y, Keyboard input, int ground) {
		this.ground = 300;
		this.name = name;
		this.x = x;
		this.y = y + 16;
		this.input = input;
		w = 32;
		h = 32;
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
				yVel = -8.5;
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
		y += yVel;
		if (y + h > ground) {
			y = ground-h;
			yVel = 0;
			jump=1;
		}
	}

	public void gravity() {
		yVel += 0.5;
	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, flip);

	}


}
