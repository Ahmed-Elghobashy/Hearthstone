package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;

public class MinionButtonListener extends AbstractAction
{
	Controller controller;
	Hero currentHero;
	
	 public MinionButtonListener(Controller controller)
	{
		currentHero=controller.getModel().getCurrentHero();
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof MinionButton)
		{	
		  MinionButton button = (MinionButton) e.getSource();
		  Minion minion = button.getMinion();
		  Hero minionPlayer = button.getPlayer();
		  if(controller.getAttackingMinion()==minion)
		  {
			  controller.setAttackingMinion(null);
			  controller.setAttackingWithMinonHero(null);
		  }
		  //If the attacking minion in controller is null that means we are choosing this minion to be the attacking minion
		  if (button.isOnField())
		  {	  
		   if(controller.getAttackingMinion() == null && controller.getUsingHeroPower()==null)
		   {
			  if(true)
			  {
				controller.setAttackingMinion(minion);
				controller.setAttackingWithMinonHero(minionPlayer);
				return;
			  }
			 
				 
		    }
		   else 
		    {
			   if(controller.getUsingHeroPower()!=null)
			   {
				   if(controller.getUsingHeroPower() instanceof Priest)
				   {
					   Priest priest = (Priest) controller.getUsingHeroPower();
					   try
					 {
						priest.useHeroPower(minion);
						controller.setUsingHeroPower(null);
						controller.updateView();
						return;
					 } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullHandException | FullFieldException | CloneNotSupportedException e1)
					 {
						JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						controller.setUsingHeroPower(null);
						return;
					 }
					   
				   }
				   if(controller.getUsingHeroPower() instanceof Mage)
				   {
					   Mage mage = (Mage) controller.getUsingHeroPower();
					   try
					   {
						   mage.useHeroPower(minion);
						   controller.setUsingHeroPower(null);
						   controller.updateView();
						   return;
					   } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							   | FullHandException | FullFieldException | CloneNotSupportedException e1)
					   {
						   JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						   controller.setUsingHeroPower(null);
						   return;
					   }
					   
				   }
			   }
			 else
			 {
				   
			   //this code here is for attacking the minion 
				 
				 Minion attackingMinion = controller.getAttackingMinion();
			 
				 try
				 {
					 controller.getAttackingWithMinonHero().attackWithMinion(attackingMinion, minion);
					 controller.setAttackingMinion(null);
					 controller.setAttackingWithMinonHero(null);
					controller.updateView();
				 } catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
						 | NotSummonedException e1)
				 {
					 JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
					 controller.setAttackingMinion(null);
					 controller.setAttackingWithMinonHero(null);

				 }
			   
			 }
		    }
		  }
		  else 
		  {

			if(controller.getAttackingMinion()!=null)
			{
				JOptionPane.showMessageDialog(controller.getView(),"You can not attack a minion that your opponent has not summoned yet");
				controller.setAttackingMinion(null);
				return;
			}
			 
			controller.setUsingHeroPower(null);
			   try
			{
				minionPlayer.playMinion(minion);
			    button.setOnField(true);
			    controller.updateView();
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1)
			{
				JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
			}

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
	


}
