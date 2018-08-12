package game.music;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundEffect {
		// bullet
		   
		   // Nested class for specifying volume
		   public static enum Volume {
		      MUTE, LOW, MEDIUM, HIGH
		   }
		   
		   public static Volume volume = Volume.LOW;
		   
		   // Each sound effect has its own clip, loaded with its own sound file.
		   private Clip clip;
		   
		   // Constructor to construct each element of the enum with its own sound file.
		   public SoundEffect(String soundFileName) {
		      try {
		         // Use URL (instead of File) to read from disk and JAR.
			         // Use URL (instead of File) to read from disk and JAR.
			         InputStream url = this.getClass().getResourceAsStream("/res/music/" + soundFileName);
			         // Set up an audio input stream piped from the sound file.
			         InputStream bufferedIn = new BufferedInputStream(url);
			         
			         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			         // Get a clip resource.
			         clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audioInputStream);
		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
		      FloatControl gainControl = 
		    		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		    		gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
		      play();
		   }
		   
		   public SoundEffect(String soundFileName, boolean bool) {
			      try {
			         // Use URL (instead of File) to read from disk and JAR.
			    	  System.out.print("Trying to load: " + "/res/music/" + soundFileName  + "...");
			         InputStream url = this.getClass().getResourceAsStream("/res/music/" + soundFileName);
			         // Set up an audio input stream piped from the sound file.
			         InputStream bufferedIn = new BufferedInputStream(url);
			         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			         // Get a clip resource.
			         clip = AudioSystem.getClip();
			         // Open audio clip and load samples from the audio input stream.
			         clip.open(audioInputStream);
			         System.out.println(" succeeded!");
			      } catch (Exception e) {
			    	  System.err.println(" failed!");
			      }
			    		Thread thread = new Thread(new Runnable() {

			    		    @Override
			    		    public void run() {
			    		        clip.loop(Clip.LOOP_CONTINUOUSLY);
			    		        try {
									Thread.sleep(10000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}       
			    		    }
			    		            
			    		});
			    		        
			    		thread.start();
			   }
		   
		   // Play or Re-play the sound effect from the beginning, by rewinding.
		   public void play() {
		      if (volume != Volume.MUTE) {
		         clip.setFramePosition(0); // rewind to the beginning
		         clip.start();     // Start playing
		      }
		   }
		   
		   // Optional static method to pre-load all the sound files.
		   public static void init() {
		   }
		}