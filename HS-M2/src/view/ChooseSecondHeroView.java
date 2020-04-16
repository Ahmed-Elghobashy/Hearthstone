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
	private ArrayList<HeroButton> firstPlayerHeroes;
	private JButton confirmButton;
	private Hero firstPlayerHero;
	private View frame;
	private Controller controller;
	
	
	public ChooseSecondHeroView(View frame, Controller controller) throws IOException, CloneNotSupportedException
	{
		firstPlayerHeroes = new ArrayList<HeroButton>();
		this.frame = frame;
		HeroButton paladinButton1 = new HeroButton(new Paladin(),1);
		HeroButton warlockButton1 = new HeroButton(new Warlock(),1);
		HeroButton mageButton1 = new HeroButton(new Mage(),1);
		HeroButton hunterButton1 = new HeroButton(new Hunter(),1);
		HeroButton priestButton1 = new HeroButton(new Priest(),1);
		firstPlayerHeroes.add(paladinButton1);
		firstPlayerHeroes.add(warlockButton1 );
		firstPlayerHeroes.add(mageButton1);
		firstPlayerHeroes.add(hunterButton1);
		firstPlayerHeroes.add(priestButton1);
		this.controller=controller;
		
	    for (HeroButton heroButton : firstPlayerHeroes)
		{
	    	heroButton.setBorderPainted(false);
	    	heroButton.setBorder(null);
	    	if(heroButton.getHero() instanceof Paladin)
	    	{
	    		heroButton.setIcon(new ImageIcon("images/paladin.png"));
	    	}
	    	if(heroButton.getHero() instanceof Mage)
	    	{
	    		heroButton.setIcon(new ImageIcon("images/mage.png"));
	    	}
	    	if(heroButton.getHero() instanceof Warlock)
	    	{
	    		heroButton.setIcon(new ImageIcon("images/warlock.png"));
	    	}
	    	
	    	if(heroButton.getHero() instanceof Hunter)
	    	{
	    		heroButton.setIcon(new ImageIcon("images/hunter.png"));
	    	}
	    	if(heroButton.getHero() instanceof Priest)
	    	{
	    		heroButton.setIcon(new ImageIcon("images/priest.png"));
	    	}
			heroButton.addActionListener(this);
		}
	    
		confirmButton = new JButton();
		confirmButton.addActionListener(this);
		confirmButton.add(new JLabel("Confirm"));
		
		for (HeroButton heroButton : firstPlayerHeroes)
		{
			this.add(heroButton);
		}
		
	
		this.add(confirmButton);
		
		
		
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
			
				frame.goToGameView();
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