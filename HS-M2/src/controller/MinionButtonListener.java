package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class MinionButtonListener extends AbstractAction
{
	Controller controller;
	Hero currentHero;
	
	 public MinionButtonListener(Controller controller)
	{
		currentHero=controller.getModel().getCurrentHero();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof MinionButton)
		{
		  MinionButton button = (MinionButton) e.getSource();
		  Minion minion = button.getMinion();
		  Hero minionPlayer = button.getPlayer();
		  //If the attacking minion in controller is null that means we are choosing this minion to be the attacking minion
		  if (button.isOnField())
		  {	  
		   if(controller.getAttackingMinion() == null)
		   {
			  if(isCurrentHero(minionPlayer) && isPlayerMinion(minion))
			  {
				
				controller.setAttackingMinion(minion);
			  }
			  else 
			  {
				//Here we should handle the case when the other player is trying to choose the opposing hero minion
			  }
				 
		    }
		   else 
		    {
			   Minion attackingMinion = controller.getAttackingMinion();
			 
			   	try
			   {
			   		minionPlayer.attackWithMinion(attackingMinion, minion);
			   		//here we should update the view
			   } catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
					   | NotSummonedException e1)
			   	{
				   JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
			   	}
			   
		     }
		  }
		  else 
		  {
//			  if(inTheCurrentHeroHand(minion))
//			  {
			   try
			{
				minionPlayer.playMinion(minion);
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1)
			{
				JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
			}
//			  else
//			  {
//				  JOptionPane.showMessageDialog(controller.getView(),"You can not do any action in your ");
//			  }
		  }
		}
		  

	}
	
	
	// checks if the minion is of the first player and if it is on the field
	public boolean isPlayerMinion(Minion minion)
	{
		for (Minion m : controller.getModel().getCurrentHero().getField())
		  if (m==minion)
			  return true;
		  return false;
	}

	public boolean isCurrentHero(Hero hero)
	{
		if(hero==controller.getModel().getCurrentHero())
			return true;
		return false;
	}
	
	
	public boolean inTheCurrentHeroHand(Minion minion)
	{
		for (Card m : currentHero.getHand())
		 if (m==minion)
			 return true;
		return false;
	}
	public boolean ofTheSameHero(Minion minion)
	{
		
		for (Minion m :currentHero.getField() )
			if(m==minion)
				return true;
		return false;
		
		
	}

}
