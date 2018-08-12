package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import game.graphics.Screen;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.Sector;
import game.level.Shop;
import game.music.SoundEffect;

public class Game extends Canvas implements Runnable {
	public final static int GAMESTATE = 0;
	public final static int SHOPSTATE = 1;
	public static int state = GAMESTATE;
	private static final long serialVersionUID = 1L;

	private static int width = 1080 / 4;
	private static int height = 1500 / 4;
	private static int scale = 2;
	public static String title = "Rain";

	private Thread thread;
	private JFrame frame;
	public Keyboard key;
	public Level level;
	public Shop shop;
	private boolean running = false;

	private Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	final String[] sectornames = { "sector1" };
	public static Game game;

	public Game() {
		game = this;
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new Level(getWindowWidth(), getWindowHeight());
		shop = new Shop();
		addKeyListener(key);
		Mouse mouse = new Mouse(shop, level.player);
		addMouseListener(mouse);
	}

	public static int getWindowWidth() {
		return width;
	}

	public static int getWindowHeight() {
		return height;
	}

	public synchronized void start() {
		running = true;
		Sector.init(sectornames);
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		SoundEffect.init();
		SoundEffect.volume = SoundEffect.Volume.LOW;
		new SoundEffect("background.wav", true);
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		if (state == GAMESTATE)
			level.update();
		else if (state == SHOPSTATE)
			shop.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		if (state == GAMESTATE) {
			level.render(screen);

			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
			Graphics g = bs.getDrawGraphics();

			g.setColor(new Color(0xff00ff));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0, 0, width * scale, height * scale, null);
			level.score.render(g, ((level.player.getY() - 375) * -1) - 57 + "");
			level.coin.render(g, (level.player.coins + ""));
			g.dispose();
			bs.show();
		} else if (state == SHOPSTATE) {
			shop.render(screen);
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
			Graphics g = bs.getDrawGraphics();
			g.setColor(new Color(0xff00ff));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0, 0, width * scale, height * scale, null);
			g.dispose();
			bs.show();

		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}

}
