package controller;

import javax.swing.JButton;

import model.cards.minions.Minion;
import model.heroes.Hero;

public class MinionButton extends JButton
{
	private Minion minion;
	private Hero player ;
	private Controller controller;
	private Boolean onField;
	

	
	
	
	public MinionButton(Minion minion, Hero player, Controller controller, Boolean onField)
	{
		super();
		this.minion = minion;
		this.player = player;
		this.controller = controller;
		this.onField = onField;
	}
	
	public Boolean isOnField()
	{
		return onField;
	}

	public void setOnField(Boolean onField)
	{
		this.onField = onField;
	}

	public Minion getMinion()
	{
		return minion;
	}
	public void setMinion(Minion minion)
	{
		this.minion = minion;
	}
	public Controller getController()
	{
		return controller;
	}
	public Hero getPlayer()
	{
		return player;
	}

	public void setPlayer(Hero player)
	{
		this.player = player;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	

}
