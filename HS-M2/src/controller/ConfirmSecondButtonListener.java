package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ConfirmSecondButtonListener extends AbstractAction
{
	Controller controller;
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		// here we are checking if the button clicked on is the confirm button
		//Here we should do something that will take us to the other frame
		if(controller.getSecondPlayerHero() == null )
		{
			JOptionPane.showMessageDialog(controller.getView(),"Please choose the hero");
		}
		else
		{
		  controller.getView().goToGameView();

		}

	}

}
