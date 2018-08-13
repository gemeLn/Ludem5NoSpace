package game.entity.items;

import java.awt.Rectangle;

import game.graphics.Sprite;
import game.entity.mobs.Player;
public class Item {
	long nextAvail = 0;
	public int cost;
	public int cd;
	public Rectangle hitbox = new Rectangle();
	public String name;
	public String description;
	public Sprite icon;
	public int id;
	
	public void cooldown(){
	nextAvail+=1000*cd;}
	
	public boolean checkAvail(){
	return System.currentTimeMillis()>nextAvail;
	}
	
	public void use(Player p){}

}
