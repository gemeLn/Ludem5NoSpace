package game.level;

import java.awt.Rectangle;

import game.Game;

public class SectionShop extends Section {

	public SectionShop(int start) {
		super(start);
		height = 175;
		hitbox = new Rectangle (0, Game.getWindowHeight() - height - start, Game.getWindowWidth(), height - 32);
	}

}
