package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ConfirmSecondButtonListener extends AbstractAction
{
	Controller controller;

	
	public ConfirmSecondButtonListener(Controller controller)
	{
	  	this.controller=controller;
    }
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		controller.playSfx("sounds/button_click.wav");

		// here we are checking if the button clicked on is the confirm button
		//Here we should do something that will take us to the other frame
		if(controller.getSecondPlayerHero() == null )
		{
			JOptionPane.showMessageDialog(controller.getView(),"Please choose the hero");
		}
		else
		{
		 // try {
			controller.toMainView(controller.getFirstPlayerHero(),controller.getSecondPlayerHero());
		//} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		//}
		  

		//}

	}

}
}