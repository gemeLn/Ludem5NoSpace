package game.entity.mob;

import java.awt.Rectangle;

import javax.swing.UIManager;

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
	
	private double yVel;
	private int jump;
	
	private UIManager ui;

	public Player(String name, int x, int y, Keyboard input) {
		this.name = name;
		this.x = x;
		this.y = y+16;
		this.input = input;
		sprite = Sprite.player_forward;
		
		// Player default attributes
		health = 100;
		
		this.x = 100;
		
		hitbox = new Rectangle(this.x - 17, this.y - 17, 34, 34);
		yVel = 0.0;
		jump = 0;
	}
	
	public String getName() {
		return name;
	}

	public void update() {	
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (input.up) {
			animSprite = up;
			//ya -= 2;
			if (jump < 1) {
				if(input.store)
					yVel = -11;
				else
					yVel = -8.5;
				jump++;
			}
		} else if (input.down) {
			animSprite = down;
			ya += 2;
		}
		if (input.left) {
			animSprite = left;
			if (input.speed) {
				xa -= 5;
			} else
				xa -= 3;
		} else if (input.right) {
			animSprite = right;
			if (input.speed) {
				xa += 5;
			} else
				xa += 3;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		
		updateHitbox(17, 17);	
		y += yVel;
	}
	
	public void gravity() {
		yVel += 0.5;	
		updateHitbox(17, 17);
	}

	private void clear() {

	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, flip);
			
	}

	public void resetGravity(int yChange) {
		y = yChange;
		yVel = 0;
		jump = 0;
	}
}
