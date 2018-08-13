package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Block;
import game.entity.Coin;
import game.entity.Interactable;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section1 extends Section {

	public Section1(int start) {
		super(start);
		id = 1;
		System.out.println(start);
		coins.add(new Coin(100, 280 - start));
		coins.add(new Coin(120, 280 - start));
		coins.add(new Coin(140, 280 - start));
		platforms.add(new Platform(50, 200 - start, Game.getWindowWidth()-100, 10));
		platforms.add(new Platform(0, 0 - start, Game.getWindowWidth(), 10));
		spikes.add(new Spike(255, 230-start));
		spikes.add(new Spike(0, 230-start));
		height = 375;
		hitbox(start);

	}
}
