package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Paladin;
import model.heroes.Warlock;

public class UseHeroPowerListener extends AbstractAction
{
	
	Controller controller;

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof HeroButton)
		{
			HeroButton button= (HeroButton) e.getSource();
			Hero hero = button.getHero();
			
			if(hero!=controller.getModel().getCurrentHero())
			{
				  JOptionPane.showMessageDialog(controller.getView(),"You can not do any action in your opponent's turn");
				  return;

			}
			controller.setAttackingMinion(null);
			if( hero instanceof Paladin || hero instanceof Hunter || hero instanceof Warlock)
			{
				try
				{
					hero.useHeroPower();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1)
				{
			
				  JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
				}
			}
			else 
			{
				controller.setUsingHeroPower(hero);
			}
		}

	}

}
