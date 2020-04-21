package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.Spell;
import model.heroes.Hero;

public class AOESpellListener extends AbstractAction
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof SpellButton)
		{
			SpellButton button = (SpellButton) e.getSource();
			if(button.getSpell() instanceof AOESpell)
			{	
			AOESpell spell = (AOESpell) button.getSpell();	
			Hero spellPlayer = button.getHero();
			Controller controller = button.getController();
			ArrayList<Minion> oppField = controller.getModel().getOpponent().getField();
			try
			{
				spellPlayer.castSpell(spell,oppField);
			} catch (NotYourTurnException | NotEnoughManaException e1)
			{
				JOptionPane.showMessageDialog(controller.getView(),e1.getMessage());
				
			}
			}
			
		}

	}

}
