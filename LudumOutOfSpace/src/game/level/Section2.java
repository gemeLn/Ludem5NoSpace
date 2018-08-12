package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Block;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section2 extends Section {
	
	public Section2(int start) {
		super(start);
		id = 2;
		System.out.println(start);
		platforms.add(new Platform(100, 260 - start, 40, 10));
		platforms.add(new Platform(0, 200 - start, Game.getWindowWidth(), 10));
		blocks.add(new Block(100,300 - start,30,10));
		spikes.add(new Spike(200, 330 - start));
		height = 175;
		hitbox(start);
	}
	

	public void update() {

	}
}
