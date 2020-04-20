package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.CannotAttackException;
import exceptions.InvalidTargetException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.minions.Minion;
import model.heroes.Hero;

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
				Hero attackingHero = button.getHero();
				try
				{
					attackingHero.attackWithMinion(attackingMinionacking, hero);
					attackingMinionacking=null;
					//Here we should update the view
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
						| InvalidTargetException e1)
				{
					
					JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
				}
			}
		}
		
	}

}
