package dev.rsiwater.tetris.state;

import dev.rsiwater.tetris.entity.*;
import dev.rsiwater.tetris.game.*;

import java.awt.Graphics;

public class GameState extends State 
{
	private Player player;
	private SettleBlock settleblock;
	public GameState(Game game)
	{
		super(game);
		settleblock=new SettleBlock(game);
		player = new Player(game,settleblock);
	}
	
	@Override
	public void tick() 
	{
		player.tick();
		settleblock.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(game.getAsset().BG, 0, 0, null);
		g.drawImage(game.getAsset().scoreBoard, 453, 169, null);
		g.drawImage(game.getAsset().GameStage, 40, 40, null);
		g.drawImage(game.getAsset().grid, 40, 40, null);
		player.render(g);
		settleblock.render(g);
	}

}
