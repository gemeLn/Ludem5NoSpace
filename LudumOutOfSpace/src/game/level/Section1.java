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
		platforms.add(new Platform(100, 260 - start, 40, 10));
		coins.add(new Coin(100, 280 - start));
		coins.add(new Coin(120, 280 - start));
		coins.add(new Coin(140, 280 - start));
		platforms.add(new Platform(0, 200 - start, Game.getWindowWidth(), 10));
		blocks.add(new Block(100, 300 - start, 30, 10));
		height = 175;
		hitbox(start);

	}
}
