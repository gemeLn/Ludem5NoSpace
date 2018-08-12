package game.level;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.entity.Block;
import game.entity.Entity;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.Wall;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.graphics.ui.UILabel;
import game.util.Vector2i;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	protected int nextLevel;
	public int dY = 0;
	public int ground = 350;
	public List<Entity> entities = new ArrayList<Entity>();
	private List<Platform> platforms = new ArrayList<Platform>();
	private List<Block> blocks = new ArrayList<Block>();
	private List<Spike> spikes = new ArrayList<Spike>();
	public List<Section> sections = new ArrayList<Section>();
	public Player player;
	private Sprite sprite;
	public int inter = -1;
	public UILabel score;

	// public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		getPlatforms().add(new Platform(0, 0, 0, Game.getWindowHeight()));
		getPlatforms().add(new Platform(Game.getWindowWidth(), 0, 0, Game.getWindowHeight()));
		getPlatforms().add(new Platform(100, 260, 40, 10));
		getPlatforms().add(new Platform(0, 200, Game.getWindowWidth(), 10));
		getPlatforms().add(new Platform(0, ground, Game.getWindowWidth(), 200));
		getBlocks().add(new Block(100, 300, 30, 10));
		getSpikes().add(new Spike(200, 330, 30, 10));
		nextLevel = Game.getWindowHeight() - 200;
		add(new Wall(false));
		add(new Wall(true));

		addSection();
		addSection();
		
		sprite = new Sprite(270, 375, 0, 0, SpriteSheet.background);
		score = new UILabel(new Vector2i(120, 120), 0 + "");
		
		// generateLevel();

		// KEEP THIS LAST
		player = new Player("Matty", 100, 350, Game.main.key, this);
	}

	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
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

		for (int i = inter-2; i < sections.size(); i++) {
			if (i >= 0 && sections.get(i).hitbox.contains(player.getX(), player.getY())) {
				inter = i;
				if (sections.size() == inter + 1)
					addSection();
			}
		}
		remove();
	}

	public void addSection() {
		switch ((int) (Math.random() + 1)) {
		case 0:
			sections.add(new Section1(nextLevel));
			nextLevel += Section1.getSectionHeight();
			break;
		case 1:
			sections.add(new Section2(nextLevel));
			nextLevel += Section2.getSectionHeight();
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
		screen.renderSprite(0, 0, sprite, false);
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

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen, dY);
		}
		if (inter > -2) {
			if (inter == -1) {
				sections.get(0).render(screen, dY);

			} else {
				for (int i = inter; i < sections.size(); i++) {
					sections.get(i).render(screen, dY);
					if (i - 1 != -1)
						sections.get(i - 1).render(screen, dY);
				}
			}
		}
		
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

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

}
