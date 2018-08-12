package game.level;

import java.awt.Rectangle;

import game.Game;
import game.entity.Vender;

public class SectionShop extends Section {

	public SectionShop(int start) {
		super(start);
		id = 100;
		height = 175;
		hitbox(start);
		interactables.add(new Vender(100, 320 - start));

	}

}
