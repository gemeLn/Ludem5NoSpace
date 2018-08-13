package game.level;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.mob.Alien;

public class Section2 extends Section {
	
	public Section2(int start) {
		super(start);
		id = 2;
		System.out.println(start);
		platforms.add(new Platform(100, 260 - start, 40, 10));
		platforms.add(new Platform(0, 200 - start, Game.getWindowWidth(), 10));
		blocks.add(new Block(100,300 - start,30,10));
		coins.add(new Coin(120, 275 - start));
		coins.add(new Coin(170, 170 - start));
		coins.add(new Coin(170, 170 - start));
		aliens.add(new Alien(platforms.get(1)));
		height = 175;
		hitbox(start);
	}
	
}
