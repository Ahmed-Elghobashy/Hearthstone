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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import agent.Agent;
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
 private SpellButton chosenSpell;
 private String firstPlayerName;
 private String secondPlayerName;
 private ArrayList<JButton> firstHeroHandCards;
 private ArrayList<JButton> secondHeroHandCards;
 private ArrayList <MinionButton> firstHeroField;
 private ArrayList <MinionButton> secondHeroField;
 private Hero usingHeroPower;
 private Agent agent;
 private JLabel firstInfo;
 private JLabel secondInfo;
 private JLabel name1;
 private JLabel name2;


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
    	 heroButton.setBorder(BorderFactory.createEmptyBorder());
		 heroButton.setContentAreaFilled(false);

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
    	 heroButton.setBorder(BorderFactory.createEmptyBorder());
		 heroButton.setContentAreaFilled(false);

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
		if(true)
			agent = new Agent(model, this);
		if(model.getCurrentHero()==secondPlayerHero && agent!=null)
			agent.playTurn();
		model.setListener(this);
	} catch (FullHandException | CloneNotSupportedException e) {
		JOptionPane.showMessageDialog(this.getView(), e.getMessage());
	}
	if(this.getFirstPlayerName()==null||this.getFirstPlayerName().equals(""))
		this.setFirstPlayerName("Guest");
	if(this.getSecondPlayerName()==null||this.getFirstPlayerName().equals(""))
		this.setSecondPlayerName("Guest");

	JPanel firstHero=new JPanel(new BorderLayout());
	JPanel secondHero=new JPanel(new BorderLayout());
	firstHero.setOpaque(false);
	secondHero.setOpaque(false);
	firstInfo=new JLabel("Health : "+ first.getCurrentHP() +'\n'+"   Mana : "+first.getCurrentManaCrystals());
	 secondInfo=new JLabel("Health : "+ second.getCurrentHP() +'\n'+"   Mana : "+second.getCurrentManaCrystals());
	  name1=new JLabel(this.firstPlayerName +"   Total: "+this.firstPlayerHero.getTotalManaCrystals() );
	  name2=new JLabel(this.secondPlayerName+"   Total: "+this.firstPlayerHero.getTotalManaCrystals());
	 HeroButton firstHeroImage=new HeroButton(first,this);
	 HeroButton secondHeroImage=new HeroButton(second,this);
	 HeroButtonListener l=new HeroButtonListener();
	 secondHeroImage.addActionListener(l);
	 firstHeroImage.addActionListener(l);
	 firstHeroImage.setIcon(chooseHeroImage(first));
	 secondHeroImage.setIcon(chooseHeroImage(second));
	 firstHeroImage.setBorder(BorderFactory.createEmptyBorder());
	 firstHeroImage.setContentAreaFilled(false);
	 secondHeroImage.setBorder(BorderFactory.createEmptyBorder());
	 secondHeroImage.setContentAreaFilled(false); 
	 firstHero.add(firstHeroImage,BorderLayout.NORTH);
	 secondHero.add(secondInfo,BorderLayout.CENTER);
	 secondHero.add(name2,BorderLayout.SOUTH);
	 secondHero.add(secondHeroImage,BorderLayout.NORTH);
	 firstHero.add(firstInfo,BorderLayout.CENTER);
	 firstHero.add(name1,BorderLayout.SOUTH);
	 view.getFirstHero().add(firstHero,BorderLayout.CENTER);
	 view.getSecondHero().add(secondHero,BorderLayout.CENTER);
	 
	 UseHeroPowerListener listener = new UseHeroPowerListener(this); 
	 HeroButton power1=new HeroButton(firstPlayerHero, this);
	 HeroButton power2=new HeroButton(secondPlayerHero,this);
	 power2.setIcon(new ImageIcon("images/powerbutton3.png"));
	 power1.setIcon(new ImageIcon("images/powerbutton3.png"));
	 power1.setPreferredSize(new Dimension(100,150));
	 power1.setBorder(BorderFactory.createEmptyBorder());
	 power1.setContentAreaFilled(false);
	 power2.setBorder(BorderFactory.createEmptyBorder());
	 power2.setContentAreaFilled(false);
	 power1.addActionListener(listener);
	 power2.addActionListener(listener);
	 JButton endTurn=new JButton();
	 endTurn.setIcon(new ImageIcon("images/end2.png"));
	 endTurn.setBorder(BorderFactory.createEmptyBorder());
	 endTurn.setContentAreaFilled(false);
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
				if(agent!=null)
					agent.playTurn();
				updateView();
			} catch (FullHandException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
			
		}
	});
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
			 minionButton.setPreferredSize(new Dimension(109,150));
			 MinionButtonListener listener = new MinionButtonListener(this);
			 minionButton.addActionListener(listener);
				 minionButton.setIcon(images(i));
				 minionButton.setBorder(BorderFactory.createEmptyBorder());
				    minionButton.setContentAreaFilled(false);
			 this.firstHeroHandCards.add(minionButton);
			 
		 }
		 else 
		 {
			SpellButton spellButton = new SpellButton((Spell) i,firstPlayerHero,this);
		    SpellButtonListener listener  = new SpellButtonListener(this);
			spellButton.setPreferredSize(new Dimension(109,150));
		    spellButton.addActionListener(listener);
		    spellButton.setIcon(images(i));
		    spellButton.setBorder(BorderFactory.createEmptyBorder());
		    spellButton.setContentAreaFilled(false);
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
			 minionButton.setPreferredSize(new Dimension(109,150));
			 MinionButtonListener listener = new MinionButtonListener(this);
			 minionButton.addActionListener(listener);
			 minionButton.setIcon(images(i));
			    minionButton.setBorder(BorderFactory.createEmptyBorder());
			    minionButton.setContentAreaFilled(false);
			 this.secondHeroHandCards.add(minionButton);
		 }
		 else
		 {
		 SpellButton spellButton = new SpellButton((Spell) i,secondPlayerHero,this);
		 spellButton.setPreferredSize(new Dimension(109,150));
		 SpellButtonListener listener  = new SpellButtonListener(this);
		 spellButton.addActionListener(listener);
		 spellButton.setIcon(images(i));
		    spellButton.setBorder(BorderFactory.createEmptyBorder());
		    spellButton.setContentAreaFilled(false);
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
		button.setPreferredSize(new Dimension(110,150));
		MinionButtonListener listener = new MinionButtonListener(this);
		button.addActionListener(listener);
		firstHeroField.add(button);
			 button.setIcon(images(minion));
	    button.setBorder(BorderFactory.createEmptyBorder());
	    button.setContentAreaFilled(false);
		view.getFirstHeroField().add(button);
	}
	
	for (Minion minion:second.getField())
	{
		MinionButton button = new MinionButton(minion, secondPlayerHero, this, true);
		button.setPreferredSize(new Dimension(110,150));
		MinionButtonListener listener = new MinionButtonListener(this);
		button.addActionListener(listener);
			 button.setIcon(images(minion));
	    button.setBorder(BorderFactory.createEmptyBorder());
	    button.setContentAreaFilled(false);
		view.getSecondHeroField().add(button);
	}
	 view.revalidate();
	 view.repaint();
	
	
}

public void updateView()
{
//	name1.setText(this.firstPlayerName +"   Total: "+this.firstPlayerHero.getTotalManaCrystals() );
//	name2.setText(this.secondPlayerName+"   Total: "+this.firstPlayerHero.getTotalManaCrystals());
	firstInfo.setText("Health : "+ this.getFirstPlayerHero().getCurrentHP() +'\n'+"   Mana : "+this.getFirstPlayerHero().getCurrentManaCrystals());
	 secondInfo.setText("Health : "+ this.getSecondPlayerHero().getCurrentHP() +'\n'+"   Mana : "+this.getSecondPlayerHero().getCurrentManaCrystals());
	updateField();
	updateHand();
	view.revalidate();
    view.repaint();
}






public void setChosenSpell(SpellButton chosenSpell) {
	this.chosenSpell = chosenSpell;
}

public SpellButton getChosenSpell() {
	return chosenSpell;
}
public static ImageIcon images (Card card) {
	
	if(card.getName().equals("Chromaggus"))
		 return new ImageIcon("images/chrom.png");
	if(card.getName().equals("Tirion Fordring"))
		 return new ImageIcon("images/tirion2.png");
	if(card.getName().equals("Boulderfist Ogre"))
		 return new ImageIcon("images/boulderfist3.png");
	if(card.getName().equals("Sunwalker"))
		 return new ImageIcon("images/sunwalker5.png");
	if(card.getName().equals("Argent Commander"))
		 return new ImageIcon("images/argent.png");
	if(card.getName().equals("Bloodfen Raptor"))
		 return new ImageIcon("images/bloodfen.png");
	if(card.getName().equals("Chilwind Yeti"))
		 return new ImageIcon("images/chillwind.png");
	if(card.getName().equals("Colossus of the Moon"))
		 return new ImageIcon("images/colossus.png");
	if(card.getName().equals("Core Hound"))
		 return new ImageIcon("images/core.png");
	if(card.getName().equals("Frostwolf Grunt"))
		 return new ImageIcon("images/frost.png");
	if(card.getName().equals("Goldshire Footman"))
		 return new ImageIcon("images/goldshire.png");
	if(card.getName().equals("The LichKing"))
		 return new ImageIcon("images/lich.png");
	if(card.getName().equals("Stonetusk Boar"))
		 return new ImageIcon("images/stonetusk.png");
	if(card.getName().equals("Wolfrider"))
		 return new ImageIcon("images/wolfrider.png");
	if(card.getName().equals("Curse of Weakness"))
		 return new ImageIcon("images/curse2.png");
	if(card.getName().equals("Divine Spirit"))
		 return new ImageIcon("images/divine2.png");
	if(card.getName().equals("Flamestrike"))
		 return new ImageIcon("images/flame2.png");
	if(card.getName().equals("Holy Nova"))
		 return new ImageIcon("images/holy2.png");
	if(card.getName().equals("Kill Command"))
		 return new ImageIcon("images/kill2.png");
	if(card.getName().equals("Level Up!"))
		 return new ImageIcon("images/level2.png");
	if(card.getName().equals("Multi-Shot"))
		 return new ImageIcon("images/multi2.png");
	if(card.getName().equals("Polymorph"))
		 return new ImageIcon("images/polymorph2.png");
	if(card.getName().equals("Pyroblast"))
		 return new ImageIcon("images/pyroblast2.png");
	if(card.getName().equals("Seal of Champions"))
		 return new ImageIcon("images/seal2.png");
	if(card.getName().equals("Shadow Word: Death"))
		 return new ImageIcon("images/shadow2.png");
	if(card.getName().equals("Siphon Soul"))
		 return new ImageIcon("images/siphon2.png");
	if(card.getName().equals("Twisting Nether"))
		 return new ImageIcon("images/twisting2.png");
	if(card.getName().equals("Icehowl"))
		 return new ImageIcon("images/icehowl2.png");
	if(card.getName().equals("Silver Hand Recruit"))
		 return new ImageIcon("images/silver2.png");
	if(card.getName().equals("Prophet Velen"))
		 return new ImageIcon("images/prophet2.png");
	if(card.getName().equals("Wilfred Fizzlebang"))
		 return new ImageIcon("images/wilfred2.png");
	if(card.getName().equals("Kalycgos"))
		 return new ImageIcon("images/kalecgos2.png");
	if(card.getName().equals("King Krush"))
		 return new ImageIcon("images/king2.png");
	if(card.getName().equals("Sheep"))
		 return new ImageIcon("images/sheep2.png");
	if(card.getName().equals("The LichKing"))
		 return new ImageIcon("images/lich.png");
	
	
	
	
	
	return null;
	
}
public static ImageIcon chooseHeroImage(Hero h) {
	if(h instanceof Paladin)
	{
		return(new ImageIcon("images/paladin9.png"));
	}
	if(h instanceof Mage)
	{
		return(new ImageIcon("images/mage3.png"));
	}
	if(h instanceof Warlock)
	{
		return(new ImageIcon("images/warlock2.png"));
	}
	
	if(h instanceof Hunter)
	{
		return(new ImageIcon("images/hunter2.png"));
	}
	if(h instanceof Priest)
	{
		return(new ImageIcon("images/priest2.png"));
	}
return null;
}
 
}
