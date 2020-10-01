package dev.rsiwater.tetris.state;

import java.awt.Graphics;

import dev.rsiwater.tetris.game.Asset;
import dev.rsiwater.tetris.game.Game;

public class MenuState extends State 
{
	private int cursorX, cursorY, originOptionX;
	private boolean ifPressedUp, ifPressedDown;
	
	public MenuState(Game game) 
	{
		super(game);
		originOptionX = 710;
		cursorY = 205;
	}

	int frames = 0;
	boolean shifting = false;
	float v = 1;
	public void tick() 
	{
		if(originOptionX > 410)frames++;
		if(frames > 40)
		{
			shifting = true;
			frames = 0;
		}
		if(game.getInputHandler().up && !ifPressedUp)
		{
			ifPressedUp = true;
			cursorX++;
			cursorY++;
		}
		else ifPressedUp = false;
		if(game.getInputHandler().down && !ifPressedDown)
		{
			ifPressedDown = true;
			cursorX--;
			cursorY--;
		}
		else ifPressedDown = false;
		if(frames >= 1 && originOptionX > 410 && shifting)
		{
			originOptionX -= (int)v;
			if(originOptionX < 510)
			{
				if(v > 2)v -= 0.5;
			}
			else
			{
				if(v < 10)v += 1;
			}
			frames = 0;
		}
	}

	public void render(Graphics g) 
	{
		g.drawImage(Asset.welcomeBG, 0, 0, null);
		g.drawImage(Asset.options, originOptionX, 205, null);
		if(originOptionX <= 410)g.drawImage(Asset.cursor, 310, cursorY, null);
	}

}
