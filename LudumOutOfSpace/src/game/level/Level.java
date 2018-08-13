package game.level;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Entity;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.Wall;
import game.entity.mob.Alien;
import game.entity.mob.Player;
import game.graphics.AnimatedAll;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.graphics.ui.UILabel;
import game.music.SoundEffect;
import game.util.Vector2i;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	protected int nextLevel;
	public int dY = 0;
	public int ground = 350;
	public static Wall wall1;
	public static Wall wall2;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Alien> aliens = new ArrayList<Alien>();
	private List<Platform> platforms = new ArrayList<Platform>();
	private List<Block> blocks = new ArrayList<Block>();
	private List<Spike> spikes = new ArrayList<Spike>();
	private List<Coin> coins = new ArrayList<Coin>();
	public List<Section> sections = new ArrayList<Section>();
	public List<Interactable> interactables = new ArrayList<Interactable>();
	public Player player;
	private Sprite sprite, sprite2;
	public int inter = -1;
	public UILabel score, coin, section;
	public int sectionsUntilShop = 8;
	public int sectionNumber;
	public SoundEffect background;
	public int tick;
	private int spriteY;

	// public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		AnimatedAll.init();
		spriteY = -1000+375;
		
		SoundEffect.volume = SoundEffect.Volume.LOW;
		background = new SoundEffect("background.wav", true);
		
		inter = -1;
		entities = new ArrayList<Entity>();
		aliens = new ArrayList<Alien>();
		platforms = new ArrayList<Platform>();
		blocks = new ArrayList<Block>();
		spikes = new ArrayList<Spike>();
		coins = new ArrayList<Coin>();
		sections = new ArrayList<Section>();
		interactables = new ArrayList<Interactable>();
		
		this.width = width;
		this.height = height;
		wall1 = new Wall(false);
		wall2 = new Wall(true);
		platforms.add(new Platform(100, 260, 40, 10));
		platforms.add(new Platform(0, 200, Game.getWindowWidth(), 10));
		platforms.add(new Platform(0, ground, Game.getWindowWidth(), 200));
		aliens.add(new Alien(platforms.get(1)));
		blocks.add(new Block(100, 300, 30, 10));
		spikes.add(new Spike(200, 330));
		// spikes.add(new Spike(100, 180, 16, 16));
		coins.add(new Coin(100, 180));
		nextLevel = Game.getWindowHeight() - 200;

		addShop();

		sectionNumber = 1;

		sprite = new Sprite(270, 1000, 0, 0, SpriteSheet.background);
		sprite2 = new Sprite(270, 1000, 0, 0, SpriteSheet.background);
		score = new UILabel(new Vector2i(120, 120), 0 + "");
		coin = new UILabel(new Vector2i(200, 120), 0 + "");
		section = new UILabel(new Vector2i(300, 120), 0 + "");

		// generateLevel();

		// KEEP THIS LAST
		player = new Player("Matty", 100, 350, Game.game.key, this);
	}

	public int levelid;

	public void update() {
		tick++;
		if(tick % 1 == 0) {
			spriteY+= 5;
			if(spriteY > 375)
				spriteY = -1000+375;
		}
		AnimatedAll.update();

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		wall1.update();
		wall2.update();
		
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update(dY);
		}
		
		player.update();
		if (player.getY() + dY <= 150) {
			dY -= (int) player.getYVel();
			if (player.getYVel() == 0)
				dY += 5;
		} else if (player.getY() + dY >= 320) {
			dY -= (int) player.getYVel();
			if (player.getYVel() == 0)
				dY -= 5;
		}

		for (int i = inter - 2; i < sections.size(); i++) {
			if (i >= 0 && sections.get(i).hitbox.contains(player.getX(), player.getY())) {
				levelid = sections.get(i).id;
				if (i != inter && i > inter) {
					sectionNumber++;
					wall1.reset();
					wall2.reset();
					inter = i;
				}
				if (sections.size() == inter + 1) {
					sectionsUntilShop--;
					if (sectionsUntilShop < 2) {
						addShop();
						sectionsUntilShop = 10;
					} else {
						addSection();
					}
				}
			}
		}
		if(inter > -2 && inter != -1)
			sections.get(inter+1).update(dY);
		if(inter > -1)
			sections.get(inter).update(dY);
		if(inter > 0)
			sections.get(inter-1).update(dY);

		remove();
	}

	public void addShop() {
		sections.add(new SectionShop(nextLevel));
		nextLevel += SectionShop.getSectionHeight();
	}

	public void addSection() {
		switch ((int) (Math.random()) + 4) {
		case 0:
			sections.add(new Section1(nextLevel));
			nextLevel += Section1.getSectionHeight();
			break;
		case 1:
			sections.add(new Section2(nextLevel));
			nextLevel += Section2.getSectionHeight();
			break;
		case 2:
			sections.add(new Section3(nextLevel));
			nextLevel += Section3.getSectionHeight();
			break;
		case 3:
			sections.add(new Section4(nextLevel));
			nextLevel += Section4.getSectionHeight();
			break;
		case 4:
			sections.add(new Section5(nextLevel));
			nextLevel += Section5.getSectionHeight();
			break;
		case 5:
			sections.add(new Section6(nextLevel));
			nextLevel += Section6.getSectionHeight();
			break;
		}
		
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved())
				entities.remove(i);
		}
	}

	/*
	 * public boolean tileCollision(int x, int y, int size, int xOffset, int
	 * yOffset) { boolean solid = false; for (int c = 0; c < 4; c++) { int xt = (x -
	 * c % 2 * size + xOffset) >> 4; int yt = (y - c / 2 * size + yOffset) >> 4; if
	 * (getTile(xt, yt).solid()) solid = true; } return solid; }
	 */

	public void render(Screen screen) {
		screen.renderSprite(0, spriteY, sprite, false);
		screen.drawRect(0, 320, Game.getWindowWidth(), 1, 0xffffff, false);
		screen.drawRect(0, 150, Game.getWindowWidth(), 1, 0xffffff, false);

		for (Platform p : platforms) {
			p.render(screen, dY);
		}
		for (Block b : blocks) {
			b.render(screen, dY);
		}
		for (Spike s : spikes) {
			s.render(screen, dY);
		}

		for (Coin c : coins) {
			c.render(screen, dY);
		}


		if (inter > -2) {
			if (inter == -1) {
				sections.get(0).render(screen, dY);

			} else {
				for (int i = inter; i < sections.size(); i++) {
					sections.get(i).render(screen, dY);
					if (i - 1 != -1)
						sections.get(i - 1).render(screen, dY);
					if (i - 2 >= 0)
						sections.get(i - 2).render(screen, dY);
				}
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen, dY);
		}
		for (Alien a : aliens) {
			a.render(screen, dY);
		}
		wall1.render(screen, dY);
		wall2.render(screen, dY);
		player.render(screen, dY);
	}

	public void add(Entity e) {
		e.init(this);
		entities.add(e);
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.equals(e))
				continue;
			int x = entity.getX();
			int y = entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}

	public List<Block> getBlocks() {
		if (inter >= 0) {
			List<Block> blocks = new ArrayList<Block>();
			if (inter == 0)
				blocks.addAll(this.blocks);
			else
				blocks.addAll(sections.get(inter - 1).blocks);
			blocks.addAll(sections.get(inter).blocks);
			return blocks;
		}
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public List<Spike> getSpikes() {
		if (inter >= 0) {
			List<Spike> spikes = new ArrayList<Spike>();
			if (inter == 0)
				spikes.addAll(this.spikes);
			else
				spikes.addAll(sections.get(inter - 1).spikes);
			spikes.addAll(sections.get(inter).spikes);
			return spikes;
		}
		return spikes;
	}

	public void setSpikes(List<Spike> spikes) {
		this.spikes = spikes;
	}

	public List<Interactable> getInteractables() {
		if (inter >= 0) {
			List<Interactable> interactables = new ArrayList<Interactable>();
			if (inter == 0)
				interactables.addAll(this.interactables);
			else
				interactables.addAll(sections.get(inter - 1).interactables);
			interactables.addAll(sections.get(inter).interactables);
			return interactables;
		}
		return interactables;
	}

	public void setInteractables(List<Interactable> interactables) {
		this.interactables = interactables;
	}

	public List<Platform> getPlatforms() {
		if (inter >= 0) {
			List<Platform> platforms = new ArrayList<Platform>();
			if (inter == 0)
				platforms.addAll(this.platforms);
			else
				platforms.addAll(sections.get(inter - 1).platforms);
			platforms.addAll(sections.get(inter).platforms);
			return platforms;
		}
		return platforms;
	}

	public List<Coin> getCoins() {
		if (inter >= 0) {
			List<Coin> coins = new ArrayList<Coin>();
			if (inter == 0)
				coins.addAll(this.coins);
			else
				coins.addAll(sections.get(inter - 1).coins);
			coins.addAll(sections.get(inter).coins);
			return coins;
		}
		return coins;
	}
	
	public List<Alien> getAlien() {
		if (inter >= 0) {
			List<Alien> aliens = new ArrayList<Alien>();
			if (inter == 0)
				aliens.addAll(this.aliens);
			else
				aliens.addAll(sections.get(inter - 1).aliens);
			aliens.addAll(sections.get(inter).aliens);
			return aliens;
		}
		return aliens;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

}
