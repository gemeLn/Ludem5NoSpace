package game.level;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.Game;
import game.entity.Entity;
import game.entity.Wall;
import game.entity.mob.Player;
import game.graphics.Screen;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	protected Rectangle ground;
	protected int dY;

	private List<Entity> entities = new ArrayList<Entity>();

	public Player player;
	public Wall leftWall = new Wall(true);
	public Wall rightWall = new Wall(false);
	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	// public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		// generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		// generateLevel();
		ground = new Rectangle(0, 1080 / 3 - 20, 1920 / 3, 50);
	}

	protected void loadLevel(String path) {
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		player.update();
		if(player.getY() <= 200) {
			dY += player.getY();
			player.setY(200);
		} else if(player.getY() >= Game.getWindowHeight()) {
			dY += player.getY();
			player.setY(Game.getWindowHeight());
		}
		leftWall.update();
		rightWall.update();
		if (!player.getHitbox().intersects(ground)) {
			player.gravity();
			if (player.getHitbox().intersects(ground)) {
				player.resetGravity(ground.y - 16);
			}
		}
		remove();
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

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}

		player.render(screen);
		leftWall.render(screen);
		rightWall.render(screen);
		screen.drawRect(ground.x, ground.y, ground.width, ground.height, 0xff0000, false);
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

	// Grass = 0xFF00FF00
	// Flower = 0xFFFFFF00
	// Rock = 0xFF7F7F00
	/*
	 * public Tile getTile(int x, int y) { if (x < 0 || y < 0 || x >= width || y >=
	 * height) return Tile.voidTile; if (tiles[x + y * width] ==
	 * Tile.col_spawn_floor) return Tile.spawn_floor; if (tiles[x + y * width] ==
	 * Tile.col_spawn_grass) return Tile.spawn_grass; if (tiles[x + y * width] ==
	 * Tile.col_spawn_hedge) return Tile.spawn_hedge; if (tiles[x + y * width] ==
	 * Tile.col_spawn_wall1) return Tile.spawn_wall1; if (tiles[x + y * width] ==
	 * Tile.col_spawn_wall2) return Tile.spawn_wall2; if (tiles[x + y * width] ==
	 * Tile.col_spawn_water) return Tile.spawn_water; return Tile.voidTile; }
	 */
}
