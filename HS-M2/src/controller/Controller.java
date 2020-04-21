package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
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
 private Hero attackingWithMinonHero;
 private String firstPlayerName;
 private String secondPlayerName;
 private ArrayList<JButton> firstHeroHandCards;
 private ArrayList<JButton> secondHeroHandCards;
 private ArrayList <MinionButton> firstHeroField;
 private ArrayList <MinionButton> secondHeroField;
 private Hero usingHeroPower;
 private SpellButton chosenSpell;



 
 public Hero getAttackingWithMinonHero()
{
	return attackingWithMinonHero;
}

public void setAttackingWithMinonHero(Hero attackingWithMinonHero)
{
	this.attackingWithMinonHero = attackingWithMinonHero;
}
 
 public Hero getUsingHeroPower()
{
	return usingHeroPower;
}

public void setUsingHeroPower(Hero usingHeroPower)
{
	this.usingHeroPower = usingHeroPower;
}

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
		 firstPlayerName  = JOptionPane.showInputDialog(view.getCurrentPanel(), "Please enter your name : ","Player 1");
		// JButton endTurn=new JButton("end turn");
		 //view.getButtons().add(endTurn);		
	} catch (IOException | CloneNotSupportedException e)
	{
		JOptionPane.showMessageDialog(view.getCurrentPanel(),"Error happened  while starting the game");
	}
 }


public String getFirstPlayerName()
{
	return firstPlayerName;
}

public void setFirstPlayerName(String firstPlayerName)
{
	this.firstPlayerName = firstPlayerName;
}

public String getSecondPlayerName()
{
	return secondPlayerName;
}

public void setSecondPlayerName(String secondPlayerName)
{
	this.secondPlayerName = secondPlayerName;
}

@Override
public void onGameOver()
{
  
  JFrame gameOverFrame = new JFrame();
  gameOverFrame.setPreferredSize(new Dimension(200,300));
  gameOverFrame.setSize(new Dimension(200,300));
  gameOverFrame.setLocationRelativeTo(null);
  gameOverFrame.toFront();
  JButton ok = new JButton("OK");
  ok.setPreferredSize(new Dimension(2,10));
  ok.setSize(new Dimension(2,10));
  ok.addActionListener(new ActionListener()
{
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		view.dispose();
		gameOverFrame.dispose();
		System.exit(0);
		
	}
});
  gameOverFrame.addWindowListener(new WindowAdapter()
{	
	@Override
	public void windowClosed(WindowEvent e)
	{
		view.dispose();
		gameOverFrame.dispose();
		System.exit(0);
	}
	
	
});
  String winningHero = "";
  if(firstPlayerHero.getCurrentHP()==0)
	  winningHero = winningHero + secondPlayerName +" WON !!";
  else 
	  winningHero = winningHero + firstPlayerName + " WON !!";
  gameOverFrame.add(new JLabel(winningHero),new BorderLayout().NORTH);
  gameOverFrame.add(ok);
  gameOverFrame.revalidate();
  gameOverFrame.repaint();
  gameOverFrame.setVisible(true);
  
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
		view.getChooseHeroView().add(heroButton);
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
	ChooseHeroButtonListener heroButtonListener = new ChooseHeroButtonListener(this);
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
    	
		heroButton.addActionListener(heroButtonListener);
	}
    
	JButton confirmButton = new JButton();
	ConfirmSecondButtonListener confirmSecondButtonListener = new ConfirmSecondButtonListener(this);
	confirmButton.addActionListener(confirmSecondButtonListener);
	confirmButton.add(new JLabel("Confirm"));
	
	for (ChooseHeroButton heroButton : secondPlayerHeroes)
	{
		view.getSecondHeroView().add(heroButton);
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
public void toMainView (Hero first,Hero second) {
	try {
		
		view.goToGameView(first, second);
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {
		model=new Game (first,second);
		first.setTotalManaCrystals(10);
		first.setCurrentManaCrystals(10);
		second.setTotalManaCrystals(10);
		second.setCurrentManaCrystals(20);
		model.setListener(this);
	} catch (FullHandException | CloneNotSupportedException e) {
		JOptionPane.showMessageDialog(this.getView(), e.getMessage());
	}
	 
	 UseHeroPowerListener listener = new UseHeroPowerListener(this); 
	 HeroButton power1=new HeroButton(firstPlayerHero, this);
	 HeroButton power2=new HeroButton(secondPlayerHero,this);
	 power1.addActionListener(listener);
	 power2.addActionListener(listener);
	 JButton endTurn=new JButton("end turn");
	 endTurn.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				attackingMinion=null;
				usingHeroPower=null;
				attackingWithMinonHero=null;
				model.getCurrentHero().endTurn();
				updateView();
			} catch (FullHandException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
			
		}
	});
	 HeroButton firstHeroButton = new HeroButton(firstPlayerHero, this);
	 firstHeroButton.addActionListener(new HeroButtonListener());
	 view.getFirstHero().add(firstHeroButton);
	 HeroButton secondHeroButton = new HeroButton(secondPlayerHero, this);
	 secondHeroButton.addActionListener(new HeroButtonListener());
	 view.getSecondHero().add(secondHeroButton);
	 view.getButtons().add(power2);
	 view.getButtons().add(endTurn);
	 view.getButtons().add(power1);
	 
     updateHand();
}

public void updateHand()
{   
	Hero first = this.firstPlayerHero;
	Hero second = this.secondPlayerHero;
	view.getCardsLeft().setText("Cards Left :"+first.getDeck().size());
	view.getCardsLeft2().setText("Cards Left :"+second.getDeck().size());
	view.getFirstHeroHand().removeAll();
	view.getSecondHeroHand().removeAll();
	firstHeroHandCards=new ArrayList<JButton>();
	 int n=1;
	 for(Card i:first.getHand()) {
		 if(i instanceof Minion) {
			 MinionButton minionButton = new MinionButton((Minion) i, first, this, false);
			 minionButton.setPreferredSize(new Dimension(100,190));
			 MinionButtonListener listener = new MinionButtonListener(this);
			 minionButton.addActionListener(listener);
			 this.firstHeroHandCards.add(minionButton);
		 }
		 else 
		 {
			SpellButton spellButton = new SpellButton((Spell) i,firstPlayerHero,this);
		    SpellButtonListener listener  = new SpellButtonListener(this);
			spellButton.setPreferredSize(new Dimension(100,190));
		    spellButton.addActionListener(listener);
		    this.firstHeroHandCards.add(spellButton);
		    
		 }
	 }
	 for(JButton i:this.firstHeroHandCards) {
		 view.getFirstHeroHand().add(i);
	 }
	  n=1;
	  secondHeroHandCards=new ArrayList<JButton>();
	 for(Card i:second.getHand()) {
		 if(i instanceof Minion)
		 {
			 MinionButton minionButton = new MinionButton((Minion) i, second, this, false);
			 minionButton.setPreferredSize(new Dimension(100,190));
			 MinionButtonListener listener = new MinionButtonListener(this);
			 minionButton.addActionListener(listener);
			 this.secondHeroHandCards.add(minionButton);
		 }
		 else
		 {
		 SpellButton spellButton = new SpellButton((Spell) i,secondPlayerHero,this);
		 spellButton.setPreferredSize(new Dimension(100,190));
		 SpellButtonListener listener  = new SpellButtonListener(this);
		 spellButton.addActionListener(listener);
		 this.secondHeroHandCards.add(spellButton);

		 }
	 }
	 for(JButton i:this.secondHeroHandCards) {
		 view.getSecondHeroHand().add(i);
	 }
	 view.revalidate();
	 view.repaint();
}

public void updateField()
{
	Hero first = firstPlayerHero;
	Hero second = secondPlayerHero;
	view.getFirstHeroField().removeAll();
	view.getSecondHeroField().removeAll();
	firstHeroField = new ArrayList<MinionButton>();
	secondHeroField = new ArrayList<MinionButton>();
	for (Minion minion : first.getField())
	{
		MinionButton button = new MinionButton(minion, firstPlayerHero, this, true);
		button.setPreferredSize(new Dimension(100,190));
		MinionButtonListener listener = new MinionButtonListener(this);
		button.addActionListener(listener);
		firstHeroField.add(button);
		view.getFirstHeroField().add(button);
	}
	
	for (Minion minion:second.getField())
	{
		MinionButton button = new MinionButton(minion, secondPlayerHero, this, true);
		button.setPreferredSize(new Dimension(100,190));
		MinionButtonListener listener = new MinionButtonListener(this);
		button.addActionListener(listener);
		view.getSecondHeroField().add(button);
	}
	 view.revalidate();
	 view.repaint();
	
	
}

public void updateView()
{
	updateField();
	updateHand();
	view.revalidate();
    view.repaint();
}




public SpellButton getChosenSpell() {
	return chosenSpell;
}

public void setChosenSpell(SpellButton chosenSpell) {
	this.chosenSpell = chosenSpell;
}
 
}
