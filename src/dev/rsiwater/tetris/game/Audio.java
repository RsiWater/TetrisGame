package dev.rsiwater.tetris.game;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio 
{
	public static void startMusic()
	{
		try
		{
			File file = new File("res/Audio.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		}
		catch (Exception e)
		{
			System.out.println("error path of music!");
		}
	}
}
