package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int width, height;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_lvl.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 224, 96);
	//public static SpriteSheet player_down = new SpriteSheet(player, 1, 0, 1, 0, 32);
	//public static SpriteSheet player_up = new SpriteSheet(player, 2, 0, 1, 1, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 1, 1, 6, 1, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 1, 0, 6, 1, 32);
	public static SpriteSheet player_jump = new SpriteSheet(player, 0, 2, 1, 1, 32);

	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/king_cherno.png", 128, 96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);

	public static SpriteSheet background = new SpriteSheet("/textures/background.png", 270, 1000);
	public static SpriteSheet backgrounds = new SpriteSheet(background, 0, 0, 1, 2, 270, 500);

	public static SpriteSheet bigcoin = new SpriteSheet("/textures/bigcoin.png", 32, 32);
	public static SpriteSheet shop = new SpriteSheet("/textures/shop.png", 32, 32);
	public static SpriteSheet plus = new SpriteSheet("/textures/plus.png", 32, 32);

	public static SpriteSheet plat = new SpriteSheet("/textures/Platform1.png", 80, 10);
	public static SpriteSheet spike = new SpriteSheet("/textures/spike.png", 16, 16);
	public static SpriteSheet block = new SpriteSheet("/textures/block.png", 10, 10);
	public static SpriteSheet door = new SpriteSheet("/textures/door.png", 135, 375);
	public static SpriteSheet x = new SpriteSheet("/textures/x.png", 32, 32);
	public static SpriteSheet battery = new SpriteSheet("/textures/battery.png", 200, 32);
	public static SpriteSheet shopbg = new SpriteSheet("/textures/shopbg.png", 270, 375);

	public static SpriteSheet anC = new SpriteSheet("/textures/sheets/coinAnimation.png", 96, 16);
	public static SpriteSheet animatedCoin = new SpriteSheet(anC, 0, 0, 6, 1, 16);

	public static SpriteSheet alien = new SpriteSheet("/textures/sheets/alien.png", 32 * 4, 32);
	public static SpriteSheet animatedAlien = new SpriteSheet(alien, 0, 0, 4, 1, 32);

	public static SpriteSheet platform = new SpriteSheet("/textures/sheets/platforms.png", 30, 10);
	public static Sprite fireball = new Sprite(32, 32, 0, 0, new SpriteSheet("/textures/fireball.png", 32, 32));
	public static Sprite teleporter = new Sprite(32, 32, 0, 0, new SpriteSheet("/textures/tele.png", 32, 32));

	public static SpriteSheet menu = new SpriteSheet("/textures/menu.png", 270, 375);
	public static SpriteSheet menu2 = new SpriteSheet("/textures/menu2.png", 270, 375);

	public static SpriteSheet overmenu1 = new SpriteSheet("/textures/over1.png", 270, 375);
	public static SpriteSheet overmenu2 = new SpriteSheet("/textures/over2.png", 270, 375);
	public static SpriteSheet overmenu3 = new SpriteSheet("/textures/over3.png", 270, 375);

	public static SpriteSheet credmenu1 = new SpriteSheet("/textures/credits1.png", 270, 375);
	public static SpriteSheet credmenu2 = new SpriteSheet("/textures/credits2.png", 270, 375);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		
		
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize)
								+ (y0 + ya * spriteSize) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSizeX, int spriteSizeY) {
		int xx = x * spriteSizeX;
		int yy = y * spriteSizeY;
		int w = width * spriteSizeX;
		int h = height * spriteSizeY;
		
		
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSizeX * spriteSizeY];
				for (int y0 = 0; y0 < spriteSizeY; y0++) {
					for (int x0 = 0; x0 < spriteSizeX; x0++) {
						spritePixels[x0 + y0 * spriteSizeX] = pixels[(x0 + xa * spriteSizeX)
								+ (y0 + ya * spriteSizeX) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSizeX, spriteSizeY);
				sprites[frame++] = sprite;
			}
		}
		
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}

	public SpriteSheet(String path, int width, int height, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	private void load() {
		try {
			System.out.print("Trying to load: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource("/res" + path));
			System.out.println(" succeeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(" failed!");
		}

	}

}
