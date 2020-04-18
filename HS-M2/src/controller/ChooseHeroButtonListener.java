package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ChooseHeroButtonListener extends AbstractAction
{
	private Controller controller;
	
	 public ChooseHeroButtonListener(Controller controller)
	{
		this.controller=controller;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof ChooseHeroButton)
		{
		  // Here we are checking if the button which was clicked on is a hero 
		  ChooseHeroButton button = (ChooseHeroButton) e.getSource();
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
