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
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;

public class HeroButtonListener extends AbstractAction
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof HeroButton)
		{
			HeroButton button = (HeroButton) e.getSource();
			Hero hero = button.getHero();
			Controller controller = button.getController();
			if (controller.getAttackingMinion()!=null)
			{
				Minion attackingMinionacking = controller.getAttackingMinion();
				Hero attackingHero = controller.getAttackingWithMinonHero();
				try
				{
					attackingHero.attackWithMinion(attackingMinionacking, hero);
					controller.setAttackingMinion(null);
					controller.setAttackingWithMinonHero(null);
					controller.updateView();
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
						| InvalidTargetException e1)
				{
					
					JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
					controller.setAttackingMinion(null);
					controller.setAttackingWithMinonHero(null);

				}
			}
			else 
			{
				Hero usingHeroPower=controller.getUsingHeroPower();
				if(usingHeroPower instanceof Mage)
				{
					Mage mage = (Mage) usingHeroPower;
					controller.setUsingHeroPower(null);
					controller.updateView();
					try
					{
						mage.useHeroPower(hero);
						
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullHandException | FullFieldException | CloneNotSupportedException e1)
					{
						JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						
					}
					
				}
				if(usingHeroPower instanceof Priest)
				{
					Priest priest = (Priest) usingHeroPower;
					
					try
					{
						priest.useHeroPower(hero);
						controller.setUsingHeroPower(null);
						controller.updateView();
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullHandException | FullFieldException | CloneNotSupportedException e1)
					{
						JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						controller.setUsingHeroPower(null);

					}
				}
			}	
		}
		
	}

}
