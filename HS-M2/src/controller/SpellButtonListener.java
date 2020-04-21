package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.spells.*;
import model.heroes.Hero;

public class SpellButtonListener extends AbstractAction {

	private Hero current;
	private Controller controller;
	public SpellButtonListener(Controller c) {
		current=c.getModel().getCurrentHero();
		this.controller=c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	  if(e.getSource() instanceof SpellButton ) {
		  SpellButton button=(SpellButton)e.getSource();
		  Spell spell=button.getSpell();
		  if(spell instanceof  CurseOfWeakness||spell instanceof  Flamestrike||spell instanceof  HolyNova
				 || spell instanceof  LevelUp||spell instanceof  MultiShot||spell instanceof  TwistingNether) {
			  try {
			  if(spell instanceof AOESpell)
				  current.castSpell((AOESpell)spell, controller.getModel().getOpponent().getField());
			  else
				  current.castSpell((FieldSpell) spell);}
			  catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
				  JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
				}
			  controller.updateView();
			  return;
		  }else {
			  if(controller.getChosenSpell() == button) {
				  controller.setChosenSpell(null);
			  }else {
				  controller.setChosenSpell(button);
			  }
				  
			  
		  }
		  
		  
	  }

	}

}
