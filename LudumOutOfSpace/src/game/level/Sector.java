package game.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.entity.mob.Enemy;
import game.entity.platform.Platform;

public class Sector {
	String type;
	int id;
	int speed;
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private static Sector[] sectorlist;

	public static void init(String... filenames) {
		try {
			BufferedReader br;
			int length = filenames.length;
			sectorlist = new Sector[length];
			for (int i = 0; i < length; i++) {
				String filename = filenames[i];
				Sector newSector = new Sector();
				System.out.println("LOADING " + filename + "...");
				br = new BufferedReader(
						new InputStreamReader(Sector.class.getResourceAsStream("/res/levels/" + filename)));
				String tag = "";
				String line = br.readLine();
				while (line != null) {
					if (line.charAt(0) == '-') {
						tag = line.substring(1).toLowerCase();
					} else {
						switch (tag) {
						case "type":
							newSector.type = line;
							break;
						case "id":
							newSector.id = Integer.parseInt(line);
							break;
						case "speed":
							newSector.speed = Integer.parseInt(line);
							break;
						case "platforms":
							newSector.platforms.add(new Platform(line.split(",")));
							break;
						case "enemies":
							newSector.enemies.add(new Enemy(line.split(",")));
							break;
						}
					}
					line = br.readLine();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
