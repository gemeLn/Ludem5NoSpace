package game.entity.items;

import game.entity.mob.Player;
import game.graphics.SpriteSheet;

public class Fireball extends Item {
	public Fireball() {
		id=1;
		name = "Fireball";
		cost = 15;
		cd = 10;
		description = "Shoots fireball";
		icon = SpriteSheet.fireball;
		hitbox.width = 64;
		hitbox.height = 64;
	}
	
	
	public void use(Player p){
	p.fireball();
	
	}

}
