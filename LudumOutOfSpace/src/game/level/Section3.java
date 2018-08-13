package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section3 extends Section {

	public Section3(int start) {
		super(start);
		id = 1;
		System.out.println(start);
		platforms.add(new Platform(0, 35 - start, Game.getWindowWidth(), 10));
		blocks.add(new Block(0, 315-start, 120, 10));
		blocks.add(new Block(150, 315-start, 120, 10));
		blocks.add(new Block(50, 255-start, 60, 10));
		blocks.add(new Block(160, 255-start, 60, 10));
		blocks.add(new Block(80, 75-start, 110, 10));
		
		blocks.add(new Block(110, 325-start, 10, 10));
		blocks.add(new Block(110, 325-start+10, 10, 10));
		blocks.add(new Block(150, 325-start, 10, 10));
		blocks.add(new Block(150, 325-start+10, 10, 10));
		
		platforms.add(new Platform(0, 265 - start, 50, 10));
		platforms.add(new Platform(220, 265 - start, 50, 10));
		platforms.add(new Platform(20, 175 - start, 60, 10));
		platforms.add(new Platform(190, 175 - start, 60, 10));
		platforms.add(new Platform(100, 205 - start, 70, 10));
		
		spikes.add(new Spike(120-16, 315-start-16));
		spikes.add(new Spike(150, 315-start-16));
		spikes.add(new Spike(110-16, 255-start-16));
		spikes.add(new Spike(110-32, 255-start-16));
		
		spikes.add(new Spike(160, 255-start-16));
		spikes.add(new Spike(160+16, 255-start-16));
		
		spikes.add(new Spike(Game.getWindowWidth()/2, 70-start-16));
		spikes.add(new Spike((Game.getWindowWidth()/2) + 16, 70-start-16));
		spikes.add(new Spike((Game.getWindowWidth()/2)-16, 70-start-16));
		
		height = 340;
		hitbox(start);

	}
}
