package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameAudio {

	Clip clip;
	URL gameAudioURL[] = new URL[30];
	
	public GameAudio() {
		
		gameAudioURL[0] = getClass().getResource("/audio/theme_song.wav");
		gameAudioURL[1] = getClass().getResource("/audio/pick_up_item.wav");
		gameAudioURL[2] = getClass().getResource("/audio/open_door.wav");
		//gameAudioURL[3] = getClass().getResource("/audio/door_lock.wav");
		gameAudioURL[3] = getClass().getResource("/audio/powerup.wav");
		
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(gameAudioURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		
		catch (Exception e) {
			
		}
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		clip.stop();
		
	}
}
