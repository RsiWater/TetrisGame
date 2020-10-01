package dev.rsiwater.tetris.game;

import dev.rsiwater.tetris.screen.*;
import dev.rsiwater.tetris.state.*;
import dev.rsiwater.tetris.inputhandler.*;

import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

public class Game implements Runnable
{
	private Graphics g;
	private BufferStrategy bs;
	
	private Screen screen;
	private BufferedImage sheet;
	private Asset asset;
	private State state;
	private InputHandler inputHandler;
	private boolean running = false;

	public Game()
	{
		asset = new Asset();
	}
	public Asset getAsset() {return asset;}
	public InputHandler getInputHandler() {return inputHandler;}
	public void init()
	{
		screen = new Screen();
		inputHandler = new InputHandler();
		screen.getFrame().addKeyListener(inputHandler);
		screen.display();
		asset.init();
		
		state = new GameState(this);
		state.setState(state);
	}
	
	public synchronized void start()
	{
		running = true;
		//Audio.startMusic();
		new Thread(this).start();
	}
	public synchronized void stop()
	{
		running = false;
	}
	
	public void tick() //this function is for update program logic function.
	{
		inputHandler.tick();
		
		if(state.getState() != null) state.tick();
	}
	public void render() //this function is for update screen graphic.
	{
		bs = screen.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			screen.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear screen from here
		g.clearRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		//drawing something from here
		
		if(state.getState() != null)state.render(g);
		
		
		//ending drawing
		bs.show();
		g.dispose();
	}
	//below is calculating screen framing. no need to modify.
	@Override
	public void run() 
	{
		long lastTimer = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		double delta = 0;
		int frame = 0;
		
		long lastTimerPerSec = System.currentTimeMillis();
		
		init();
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTimer) / nsPerTick;
			lastTimer = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				delta--;
				frame++;
			}
			if(System.currentTimeMillis() - lastTimerPerSec >= 1000)
			{
				System.out.println("frames = " + frame);
				frame = 0;
				lastTimerPerSec+=1000;
			}
		}
	}
}
