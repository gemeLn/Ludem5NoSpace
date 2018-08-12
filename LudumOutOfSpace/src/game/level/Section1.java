package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Block;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section1 extends Section {
	
	public Section1(int start) {
		super(start);
		id = 1;
		System.out.println(start);
		platforms.add(new Platform(0, 0 - start, 0, Game.getWindowHeight()));
		platforms.add(new Platform(Game.getWindowWidth(), 0 - start, 0, Game.getWindowHeight()));
		platforms.add(new Platform(100, 260 - start, 40, 10));
		platforms.add(new Platform(0, 200 - start, Game.getWindowWidth(), 10));
		blocks.add(new Block(100,300 - start,30,10));
		height = 175;
		hitbox = new Rectangle(0, Game.getWindowHeight() - height - start, Game.getWindowWidth(), height);
		
	}
	
	public void render(Screen screen, int dy) {
		for (Platform p : platforms) {
			p.render(screen, dy);
		}
		for(Block b:blocks) {
			b.render(screen, dy);
		}
		for(Spike s:spikes) {
			s.render(screen, dy);
		}
	}

	public void update() {

	}
}
