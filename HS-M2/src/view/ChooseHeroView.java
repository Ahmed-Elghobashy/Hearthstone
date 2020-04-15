package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class ChooseHeroView extends JPanel implements ActionListener
{
	private ArrayList<HeroButton> firstPlayerHeroes;
	private ArrayList<HeroButton> secondPlayerHeroes;
	private JButton confirmButton;
	private Hero firstPlayerHero;
	private Hero secondPlayerHero;
	private View frame;
	
	public ChooseHeroView(View frame) throws IOException, CloneNotSupportedException
	{
		firstPlayerHeroes = new ArrayList<HeroButton>();
		secondPlayerHeroes = new ArrayList<HeroButton>();
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
		
		
	    for (HeroButton heroButton : firstPlayerHeroes)
		{
	    	JLabel name = new JLabel(heroButton.getHero().getName());
	    	heroButton.add(name);
			heroButton.addActionListener(this);
		}
	    
	    
	    HeroButton paladinButton2 = new HeroButton(new Paladin(),2);
		HeroButton warlockButton2 = new HeroButton(new Warlock(),2);
		HeroButton mageButton2 = new HeroButton(new Mage(),2);
		HeroButton hunterButton2 = new HeroButton(new Hunter(),2);
		HeroButton priestButton2 = new HeroButton(new Priest(),2);
	
		
		secondPlayerHeroes.add(paladinButton2);
		secondPlayerHeroes.add(warlockButton2);
		secondPlayerHeroes.add(mageButton2);
		secondPlayerHeroes.add(hunterButton2);
		secondPlayerHeroes.add(priestButton2);
		
		for (HeroButton heroButton : secondPlayerHeroes)
		{
			JLabel name = new JLabel(heroButton.getHero().getName());
	    	heroButton.add(name);
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
			if(firstPlayerHero == null || secondPlayerHero == null )
			{
				JOptionPane.showMessageDialog(this,"Please choose the hero");
			}
			else
			{
			  frame.goToGameView();
			}
		}
		
	}
	
	
	

}
