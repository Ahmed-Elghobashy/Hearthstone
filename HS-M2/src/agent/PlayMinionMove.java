package agent;

import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class PlayMinionMove extends HearthstoneMove implements ManaCostingMove
{
	
	
	private Minion minion;
	private Hero playinHero;
	
	
	
	public Minion getMinion()
	{
		return minion;
	}



	public void setMinion(Minion minion)
	{
		this.minion = minion;
	}



	public Hero getPlayinHero()
	{
		return playinHero;
	}



	public void setPlayinHero(Hero playinHero)
	{
		this.playinHero = playinHero;
	}



	public PlayMinionMove(Minion minion, Hero playinHero)
	{
		super();
		this.minion = minion;
		this.playinHero = playinHero;
	}



	public int getManaCost()
	{
		
		return minion.getManaCost();
	}

}
