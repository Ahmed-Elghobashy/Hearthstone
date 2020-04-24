package agent;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.processing.Generated;
import javax.naming.spi.DirStateFactory.Result;

import controller.Controller;
import engine.Game;

public class GameTree
{
	private Game game;
	private Agent agent;
	private ArrayList<GameTree> nodes;
	ArrayList<Integer> result;
	
	public GameTree(Game game,Agent agent)
	{
		this.game=game;
		this.agent=agent;
	}
	
	private Game generateGame(ArrayList<HearthstoneMove> moves)
	{
		try
		{
			Game retGame = agent.cloneGame();
			Agent agent2 = new Agent(retGame, agent.getController());
			agent2.generatingAllManaCostingMoves();
			agent2.generateAllPossibleMinionAttacks();
			agent2.playTurnMoves(moves);
			return retGame;
		} catch (IOException | CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public void generateAllPossibleGames()
	{
		nodes = new ArrayList<GameTree>();
		agent.generateAllTurnMoves();
		for (ArrayList<HearthstoneMove> moves : agent.getAllTurnMoves())
		{
			Game game = generateGame(moves);
			Agent agent2 = new Agent(game, agent.getController(), agent.getOpponentHero(), !agent.isMaximizing());
			nodes.add(new GameTree(game, agent2));
		}
	}

	private int evaluateGame(Game game , int depth)
	{
		if(depth==agent.getMaxDepth())
		{
			if(agent.isMaximizing())
				return agent.clalcCurrentState(agent.getOpponentHero(),agent.getAgentHero());
			else
				return agent.clalcCurrentState(agent.getAgentHero(), agent.getOpponentHero());
		}
			generateAllPossibleGames();
			 result = new ArrayList<Integer>();
			for (GameTree tree : nodes)
			{
				result.add(evaluateGame(tree.game,depth+1));
			}
			if(agent.isMaximizing())
			{
				return Collections.max(result);
			}
			else 
			{
				return Collections.min(result);
			}
	}
	
	public ArrayList<HearthstoneMove> chooseMove()
	{
		generateAllPossibleGames();
		int resulting =evaluateGame(game, 0);
		int index =0;
		for(int i : result)
		{
			if(i==resulting)
				break;
			index++;
		}
		return agent.getAllTurnMoves().get(index);
	}
	
}	
