package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class ChooseHeroView extends JFrame implements ActionListener
{
	private ArrayList<HeroButton> firstPlayerHeroes;
	private ArrayList<HeroButton> secondPlayerHeroes;
	private JButton confirmButton;
	private Hero firstPlayerHero;
	private Hero secondPlayerHero;
	
	public ChooseHeroView() throws IOException, CloneNotSupportedException
	{
		firstPlayerHeroes = new ArrayList<HeroButton>();
		secondPlayerHeroes = new ArrayList<HeroButton>();
		
		firstPlayerHeroes.add( new HeroButton(new Paladin(),1));
		firstPlayerHeroes.add( new HeroButton(new Warlock(),1));
		firstPlayerHeroes.add( new HeroButton(new Mage(),1));
		firstPlayerHeroes.add( new HeroButton(new Hunter(),1));
		firstPlayerHeroes.add( new HeroButton(new Priest(),1));
		
	    for (HeroButton heroButton : firstPlayerHeroes)
		{
			heroButton.addActionListener(this);
		}
		
		secondPlayerHeroes.add( new HeroButton(new Paladin(),2));
		secondPlayerHeroes.add( new HeroButton(new Warlock(),2));
		secondPlayerHeroes.add( new HeroButton(new Mage(),2));
		secondPlayerHeroes.add( new HeroButton(new Hunter(),2));
		secondPlayerHeroes.add( new HeroButton(new Priest(),2));
		
		for (HeroButton heroButton : secondPlayerHeroes)
		{
			heroButton.addActionListener(this);
		}
		confirmButton = new JButton();
		confirmButton.addActionListener(this);
		confirmButton.add(new JLabel("Confirm"));
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
			  secondPlayerHero=button.getHero();
		  }
			
		}
		else if (e.getSource() instanceof JButton)
		{    
			JButton button = (JButton) e.getSource();
			// here we are checking if the button clicked on is the confirm button
			//Here we should do something that will take us to the other frame
		}
		
	}
	

}
