package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class ChooseHeroButtonListener extends AbstractAction
{
	private Controller controller;
	JButton chosen;
	
public ChooseHeroButtonListener(Controller controller)
	 {
		this.controller=controller;
   	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof ChooseHeroButton)
		{ChooseHeroButton button = (ChooseHeroButton) e.getSource();
		Hero hero = button.getHero();
			{   if(!(chosen==null)) {
				chosen.setBorderPainted(false);
			}
			
			    button.setBorderPainted(true);
				chosen =button;
				sound(hero);
				//button.setPressedIcon(pressedIcon);
		  // Here we are checking if the button which was clicked on is a hero 
		  button.setBorder(new LineBorder(new Color(255,215,0)));
		  int playerNumber = button.getPlayernumber();
		  if(playerNumber==1)
		  {
			  controller.setFirstPlayerHero(button.getHero());
		  }
		  else
		  {
			  controller.setSecondPlayerHero(button.getHero());
		  }
			

	}

 }}
public void sound(Hero hero) {
	if(hero instanceof Priest )
		controller.playSfx("sounds/priest.wav");
	if(hero instanceof Mage )
		controller.playSfx("sounds/mages.wav");
	if(hero instanceof Warlock )
		controller.playSfx("sounds/warlock.wav");
	if(hero instanceof Paladin )
		controller.playSfx("sounds/paladin.wav");
	if(hero instanceof Hunter )
		controller.playSfx("sounds/hunter.wav");
}


}
	

