package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Vender;

public class SectionShop extends Section {

	public SectionShop(int start) {
		super(start);
		id=100;
		height = 175;
		hitbox = new Rectangle (0, Game.getWindowHeight() - height - start, Game.getWindowWidth(), height - 32);
		interactables.add(new Vender());
		
	}

}
