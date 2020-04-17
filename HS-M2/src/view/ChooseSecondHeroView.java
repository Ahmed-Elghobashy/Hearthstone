package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import exceptions.FullHandException;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class ChooseSecondHeroView extends JPanel implements ActionListener
{
	private JButton confirmButton;
	private Hero firstPlayerHero;
	private View frame;
	
	
	public ChooseSecondHeroView(View frame) 
	{
		this.frame = frame;
		
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
			  firstPlayerHero=button.getHero();
		  }
		  else
		  {
			 //goToThesecondHeroView
		  }
			
		}
		else if (e.getSource() instanceof JButton)
		{    
			JButton button = (JButton) e.getSource();
			// here we are checking if the button clicked on is the confirm button
			//Here we should do something that will take us to the other frame
			if(firstPlayerHero == null )
			{
				JOptionPane.showMessageDialog(this,"Please choose the hero");
			}
			else
			{
			
				try {
					frame.goToGameView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		}
		
	}
	
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/Background.jpg").getImage();
	    page.drawImage(background, 0, 0, null);
	}

	
	
	
	
	

}