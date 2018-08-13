package game.level;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Entity;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.mob.Alien;
import game.graphics.Screen;

public class Section {

	static int height;
	protected int id;
	protected boolean reset;
	public Rectangle hitbox;
	public List<Platform> platforms = new ArrayList<Platform>();
	public List<Block> blocks = new ArrayList<Block>();
	public List<Spike> spikes = new ArrayList<Spike>();
	public List<Coin> coins = new ArrayList<Coin>();
	public List<Alien> aliens = new ArrayList<Alien>();
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Interactable> interactables = new ArrayList<Interactable>();

	public Section(int start) {

	}

	public void render(Screen screen, int dy) {
		for (Platform p : platforms) {
			p.render(screen, dy);
		}
		for (Block b : blocks) {
			b.render(screen, dy);
		}
		for (Spike s : spikes) {
			s.render(screen, dy);
		}
		for (Coin c : coins) {
			c.render(screen, dy);
		}
		for (Alien a : aliens) {
			a.render(screen, dy);
		}
		for (Interactable i : interactables) {
			i.render(screen, dy);
		}

		screen.drawRect(hitbox.x, hitbox.y + dy, hitbox.width, hitbox.height, 0xffff00, false);
	}

	public void update(int dy) {
		for(Alien a: aliens) {
			a.update(dy);
		}
	}
	
	public void hitbox(int start) {
		hitbox = new Rectangle(0, Game.getWindowHeight() - height - start - 31, Game.getWindowWidth(), height);
	}

	public static int getSectionHeight() {
		return height;
	}
}