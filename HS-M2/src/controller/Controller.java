package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.View;

public class Controller implements GameListener
{
 private Game model;
 private View view;
 private Hero firstPlayerHero;
 private Hero secondPlayerHero;
 private Minion attackingMinion;
 

 
 public Game getModel()
{
	return model;
}

public void setModel(Game model)
{
	this.model = model;
}

public Minion getAttackingMinion()
{
	return attackingMinion;
}

public void setAttackingMinion(Minion attackingMinion)
{
	this.attackingMinion = attackingMinion;
}

public Controller() 
 {
	 try
	{
		view = new View(this);
		choosingFirstHeroButtons();
		
	} catch (IOException | CloneNotSupportedException e)
	{
		JOptionPane.showMessageDialog(view.getCurrentPanel(),"Error happened  while starting the game");
	}
 }

@Override
public void onGameOver()
{
  
}


public View getView()
{
	return view;
}

public void setView(View view)
{
	this.view = view;
}

public void SetFirstPlayerHero(Hero hero)
{
	firstPlayerHero=hero;
}

public void setSecondPlayerHero(Hero hero) 
{
	secondPlayerHero = hero;
	startGame();
}

public void startGame() 
{
	try
	{
		model = new Game(firstPlayerHero, secondPlayerHero);
	} catch (FullHandException e)
	{
		//It is not important to deal with this exception because when intializing the game it is not possible to have
		// a fullHandException
	}
	catch (CloneNotSupportedException e)
	{
		JOptionPane.showMessageDialog(view.getCurrentPanel(),"Error happened  while starting the game");
        
	}
	
}

public void choosingFirstHeroButtons() throws IOException, CloneNotSupportedException 
{
	ArrayList<ChooseHeroButton> firstPlayerHeroes = new ArrayList<ChooseHeroButton>();
	
	ChooseHeroButton paladinButton1 = new ChooseHeroButton(new Paladin(),1);
	ChooseHeroButton warlockButton1 = new ChooseHeroButton(new Warlock(),1);
	ChooseHeroButton mageButton1 = new ChooseHeroButton(new Mage(),1);
	ChooseHeroButton hunterButton1 = new ChooseHeroButton(new Hunter(),1);
	ChooseHeroButton priestButton1 = new ChooseHeroButton(new Priest(),1);
	firstPlayerHeroes.add(paladinButton1);
	firstPlayerHeroes.add(warlockButton1 );
	firstPlayerHeroes.add(mageButton1);
	firstPlayerHeroes.add(hunterButton1);
	firstPlayerHeroes.add(priestButton1);
	ChooseHeroButtonListener heroButtonListener = new ChooseHeroButtonListener(this);
    for (ChooseHeroButton heroButton : firstPlayerHeroes)
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
 
		heroButton.addActionListener(heroButtonListener);
	}
    
	JButton confirmButton = new JButton();
	ConfirmFirstButtonListener confirmFirstButtonListener = new ConfirmFirstButtonListener(this);
	confirmButton.addActionListener(confirmFirstButtonListener);
	confirmButton.add(new JLabel("Confirm"));
	
	for (ChooseHeroButton heroButton : firstPlayerHeroes)
	{
		view.getCurrentPanel().add(heroButton);
	}
	

	view.getCurrentPanel().add(confirmButton);
	view.revalidate();
	view.repaint();
	
}

public void choosingSecondHeroButtons() throws IOException, CloneNotSupportedException
{
	ArrayList<ChooseHeroButton> secondPlayerHeroes = new ArrayList<ChooseHeroButton>();
	
	ChooseHeroButton paladinButton1 = new ChooseHeroButton(new Paladin(),2);
	ChooseHeroButton warlockButton1 = new ChooseHeroButton(new Warlock(),2);
	ChooseHeroButton mageButton1 = new ChooseHeroButton(new Mage(),2);
	ChooseHeroButton hunterButton1 = new ChooseHeroButton(new Hunter(),2);
	ChooseHeroButton priestButton1 = new ChooseHeroButton(new Priest(),2);
	secondPlayerHeroes.add(paladinButton1);
	secondPlayerHeroes.add(warlockButton1 );
	secondPlayerHeroes.add(mageButton1);
	secondPlayerHeroes.add(hunterButton1);
	secondPlayerHeroes.add(priestButton1);	
    for (ChooseHeroButton heroButton : secondPlayerHeroes)
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
    	ChooseHeroButtonListener heroButtonListener = new ChooseHeroButtonListener(this);
		heroButton.addActionListener(heroButtonListener);
	}
    
	JButton confirmButton = new JButton();
	ConfirmSecondButtonListener confirmSecondButtonListener = new ConfirmSecondButtonListener(this);
	confirmButton.addActionListener(confirmSecondButtonListener);
	confirmButton.add(new JLabel("Confirm"));
	
	for (ChooseHeroButton heroButton : secondPlayerHeroes)
	{
		view.getCurrentPanel().add(heroButton);
	}
	

	view.getCurrentPanel().add(confirmButton);
	view.revalidate();
	view.repaint();
}


public Hero getFirstPlayerHero()
{
	return firstPlayerHero;
}

public void setFirstPlayerHero(Hero firstPlayerHero)
{
	this.firstPlayerHero = firstPlayerHero;
}

public Hero getSecondPlayerHero()
{
	return secondPlayerHero;
}

public static void main(String[] args) 
{
	new Controller();
}


 
}
