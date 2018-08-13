package game.entity.items;

import game.entity.mob.Player;
import game.graphics.SpriteSheet;

public class Tele extends Item {
	public Tele() {
		id = 2;
		name = "Teleporter";
		cost = 30;
		cd = 10;
		description = "Telerports Straight UP";
		icon = SpriteSheet.teleporter;
		hitbox.width = 64;
		hitbox.height = 64;
	}
	
	public void use(Player p){
		p.setY(p.getY() - 50);
	}

}
