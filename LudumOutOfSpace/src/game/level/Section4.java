package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section4 extends Section {

	public Section4(int start) {
		super(start);
		id = 1;
		System.out.println(start);
		platforms.add(new Platform(0, 5 - start, Game.getWindowWidth(), 10));
		
		blocks.add(new Block(90, 305-start, 10, 10));
		blocks.add(new Block(60, 255-start, 10, 10));
		blocks.add(new Block(30, 205-start, 10, 10));
		
		blocks.add(new Block(70, 165-start, 200, 10));
		blocks.add(new Block(70, 115-start, 120, 10));
		
		platforms.add(new Platform(190, 115 - start, 80, 10));
		platforms.add(new Platform(190, 55 - start, 80, 10));
		
		spikes.add(new Spike(190, 65 - start));
		spikes.add(new Spike(190+16, 65 - start));
		spikes.add(new Spike(190+16*2, 65 - start));
		spikes.add(new Spike(190+16*3, 65 - start));
		spikes.add(new Spike(190+16*4, 65 - start));

		
		height = 370;
		hitbox(start);

	}
}
