package game.entity.items;

import game.graphics.SpriteSheet;

public class Fireball extends Item {
	public Fireball() {
		name = "Fireball";
		cost = 10;
		cd = 10;
		description = "Shoots fireball";
		icon = SpriteSheet.fireball;
		hitbox.width = 64;
		hitbox.height = 64;
	}

}
