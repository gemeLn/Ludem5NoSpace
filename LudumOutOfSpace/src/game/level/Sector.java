package game.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sector {
	private static Sector[] sectorlist;

	public static void init(String... filenames) {
		try {
			BufferedReader br;
			for (String filename : filenames) {
				System.out.println("LOADING "+filename+"...");
				br = new BufferedReader(
						new InputStreamReader(Sector.class.getResourceAsStream("/res/levels/" + filename)));
				String line = br.readLine();
				while (line != null) {
					System.out.println(line);
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
