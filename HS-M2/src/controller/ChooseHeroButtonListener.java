package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

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
			{   if(!(chosen==null)) {
				chosen.setBorderPainted(false);
			}
			
			    button.setBorderPainted(true);
				chosen =button;
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

 }}}
	

