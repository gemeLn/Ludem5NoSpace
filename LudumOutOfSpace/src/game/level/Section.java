package game.level;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import game.entity.Block;
import game.entity.Platform;
import game.entity.Spike;
import game.graphics.Screen;

public class Section {
	
	static int height;
	protected int id;
	public Rectangle hitbox;
	public List<Platform> platforms = new ArrayList<Platform>();
	public List<Block> blocks = new ArrayList<Block>();
	public List<Spike> spikes = new ArrayList<Spike>();
	
	public Section(int start) {
		
	}
	
	public void render(Screen screen, int dy) {
		
	}
	
	public void update() {
		
	}
	
	public static int getSectionHeight() {
		return height;
	}
}