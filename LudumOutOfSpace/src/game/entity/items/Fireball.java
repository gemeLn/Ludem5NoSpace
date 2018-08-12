package game.entity.items;

import game.graphics.SpriteSheet;

public class Fireball extends Item {
	public Fireball() {
		name = "Fireball";
		cost = 10;
		cd = 10;
		description = "Shoots fireball costs " + cost;
		icon = SpriteSheet.fireball;
		hitbox.width = 32;
		hitbox.height = 32;
	}

}
