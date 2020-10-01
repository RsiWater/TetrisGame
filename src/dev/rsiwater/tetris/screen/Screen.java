package dev.rsiwater.tetris.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Screen 
{
	public static final int WIDTH = 720, HEIGHT = 720;
	public static final String NAME = "Tetris";
	private JFrame frame;
	private ScreenCanvas canvas;
	
	public Screen()
	{
		frame = new JFrame(NAME); 
		canvas = new ScreenCanvas();
		canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	}
	
	public void display()
	{
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
		
		frame.setVisible(true);
	}
	
	public ScreenCanvas getCanvas() {return canvas;}
	public JFrame getFrame() {return frame;}
	
	public class ScreenCanvas extends Canvas
	{
		public void paint(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 100, 100);
		}
	}

}

