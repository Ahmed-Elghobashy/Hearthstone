package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ConfirmFirstButtonListener extends AbstractAction
{
    Controller controller;
	
	public ConfirmFirstButtonListener(Controller controller)
	{
		this.controller=controller;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{    
			JButton button = (JButton) e.getSource();
			// here we are checking if the button clicked on is the confirm button
			//Here we should do something that will take us to the other frame
			if(controller.getFirstPlayerHero() == null )
			{
				JOptionPane.showMessageDialog(controller.getView(),"Please choose the hero");
			}
			else
			{
			  controller.getView().goToChooseSecondHeroView();

			}
		}
	}

}
