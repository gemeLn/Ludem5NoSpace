package game.entity.items;

import java.awt.Rectangle;

import game.graphics.Sprite;

public class Item {
	public int cost;
	public int cd;
	public Rectangle hitbox = new Rectangle();
	public String name;
	public String description;
	public Sprite icon;

}
