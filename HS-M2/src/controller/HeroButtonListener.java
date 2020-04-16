package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.HeroButton;

public class HeroButtonListener extends AbstractAction
{
	private Controller controller;
	
	 public HeroButtonListener(Controller controller)
	{
		this.controller=controller;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof HeroButton)
		{
		  // Here we are checking if the button which was clicked on is a hero 
		  HeroButton button = (HeroButton) e.getSource();
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

 }
	
}
