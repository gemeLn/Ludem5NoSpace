package game.level;

import game.Game;
import game.entity.Coin;
import game.entity.Platform;
import game.entity.Spike;
import game.entity.mob.Alien;

public class Section1 extends Section {

	public Section1(int start) {
		super(start);
		id = 1;
		System.out.println(start);
		coins.add(new Coin(100, 280 - start));
		coins.add(new Coin(120, 280 - start));
		coins.add(new Coin(140, 280 - start));
		platforms.add(new Platform(50, 200 - start, Game.getWindowWidth()-100, 10));
		platforms.add(new Platform(0, 35 - start, Game.getWindowWidth(), 10));
		spikes.add(new Spike(255, 230-start));
		spikes.add(new Spike(0, 230-start));
		spikes.add(new Spike((Game.getWindowWidth()/2), 210-start));
		spikes.add(new Spike((Game.getWindowWidth()/2)-16, 210-start));
		spikes.add(new Spike((Game.getWindowWidth()/2)-32, 210-start));
		spikes.add(new Spike((Game.getWindowWidth()/2)-48, 210-start));
		spikes.add(new Spike((Game.getWindowWidth()/2)+16, 210-start));
		spikes.add(new Spike((Game.getWindowWidth()/2)+32, 210-start));
		aliens.add(new Alien(platforms.get(0)));
		height = 340;
		hitbox(start);

	}
}
