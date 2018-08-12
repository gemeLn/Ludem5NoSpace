package game.music;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;


public class backgroundMusic {
	
	public static void playMusic() {
		File music;
		try {
			music = new File("/music/background.mp3");
			AudioInputStream ais = AudioSystem.getAudioInputStream(music);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.start();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
}
