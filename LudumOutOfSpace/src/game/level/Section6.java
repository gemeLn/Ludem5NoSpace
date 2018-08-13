package game.level;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section6 extends Section {

	public Section6(int start) {
		super(start);
		id = 6;
    
    platforms.add(new Platform(0,0-start,445,10);
    blocks.add(new Block(0,10-start,405,10));
           spikes.add(new Spike(240,40-start,205,10));
                 platforms.add(new Platform(0,50-start,205,10));
                  blocks.add(new Block(30,50-start,415,10));
                  blocks.add(new Block(90,60-start,185,50));
                  spikes.add(new Spike(0,90-start,195,10));
    
    
		platforms.add(new Platform(0, 5 - start, Game.getWindowWidth(), 10));
		platforms.add(new Platform(0, 295 - start, 60, 10));
		platforms.add(new Platform(200, 235 - start, 70, 10));
		platforms.add(new Platform(200, 175 - start, 70, 10));
		platforms.add(new Platform(200, 125 - start, 70, 10));
		platforms.add(new Platform(140, 105 - start, 10, 10));
		platforms.add(new Platform(90, 85 - start, 10, 10));
		platforms.add(new Platform(40, 65 - start, 10, 10));
		
		blocks.add(new Block(60, 295-start, 210, 10));
		
		spikes.add(new Spike(100-16,295-start-16));
		spikes.add(new Spike(100+16,295-start-16));
		spikes.add(new Spike(100,295-start-16));
		spikes.add(new Spike(170,295-start-16));
		spikes.add(new Spike(170+16,295-start-16));
		spikes.add(new Spike(170+32,295-start-16));
		spikes.add(new Spike(Game.getWindowWidth()-16,235-start-16));
		spikes.add(new Spike(200,175-start-16));
		spikes.add(new Spike(216,175-start-16));
		
		
		
		height = 370;
		hitbox(start);

	}
}
