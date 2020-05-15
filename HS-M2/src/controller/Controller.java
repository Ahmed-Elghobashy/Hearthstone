package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

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
import view.Background;
import view.MainMenuPanel;
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
 private Hero usingHeroPower;
 private Agent agent;
 private int gameMode;
 private JPanel firstHero;
 private JPanel secondHero;
 private JLabel firstInfo;
 private JLabel secondInfo;
 private JLabel name1;
 private JLabel name2;
 private Clip backgroundClip;
 private float backGroundMusic=0.8f;
 private Clip sfxClip;
 private double sfxSound=1.0f;
 private JPanel buttons;
 boolean burned=false;
 static final int MULTY_MODE=0;
 static final int AI_MODE_EASY=1;
 static final int AI_MODE_MEDIUM=3;
 static final int AI_MODE_HARD=5;


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
	 playBackgroundMusic();
	 view = new View(this);
	 getMainMenuButtons();
	
 }

public void getMainMenuButtons()
{
	JLabel logo=new JLabel(new ImageIcon("buttons/logo.png"));
	JButton multyPlayerButton = new JButton();
	 buttons= new JPanel(new GridLayout(4,0));
	multyPlayerButton.setSize(new Dimension(550,100));
	buttons.setBorder(BorderFactory.createEmptyBorder());
	buttons.setOpaque(false);
	multyPlayerButton.setIcon(new ImageIcon("buttons/multi9.png"));
	multyPlayerButton.setBorder(BorderFactory.createEmptyBorder());
	multyPlayerButton.setContentAreaFilled(false);
	multyPlayerButton.setBorderPainted(false);
	multyPlayerButton.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameMode = Controller.MULTY_MODE;
			playSfx("sounds/button_click.wav");
			view.goToChooseFirstHero();
			try
			{
				choosingFirstHeroButtons();
				firstPlayerName  = JOptionPane.showInputDialog(view.getCurrentPanel(), "Please enter your name : ","Player 1");
			} catch (IOException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	
	JButton singlePlayerButton = new JButton();
	singlePlayerButton.setSize(new Dimension(550,100));
	singlePlayerButton.setIcon(new ImageIcon("buttons/single9.png"));
	singlePlayerButton.setBorder(BorderFactory.createEmptyBorder());
	singlePlayerButton.setContentAreaFilled(false);
	singlePlayerButton.setBorderPainted(false);
	singlePlayerButton.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			playSfx("sounds/button_click.wav");
			chooseDifficulty();
		}
	});
	view.getMainmMenu().add(logo);
	buttons.add(singlePlayerButton);
	buttons.add(multyPlayerButton);
	view.getMainmMenu().add(buttons);
	view.revalidate();
	view.repaint();
	
}

public void chooseDifficulty()
{
	buttons.removeAll();
	
	JLabel logo=new JLabel(new ImageIcon("buttons/logo.png"));
	
	JButton easyButton = new JButton();
	easyButton.setBorder(BorderFactory.createEmptyBorder());
	easyButton.setContentAreaFilled(false);
	easyButton.setIcon(new ImageIcon("buttons/easy1.png"));
	easyButton.setSize(550, 110);
	easyButton.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameMode=Controller.AI_MODE_EASY;
			playSfx("sounds/button_click.wav");
			view.goToChooseFirstHero();
			try
			{
				choosingFirstHeroButtons();
				firstPlayerName  = JOptionPane.showInputDialog(view.getCurrentPanel(), "Please enter your name : ","Player 1");
			} catch (IOException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	JButton mediumButton = new JButton();
	mediumButton.setBorder(BorderFactory.createEmptyBorder());
	mediumButton.setContentAreaFilled(false);
	mediumButton.setSize(550,110);
	mediumButton.setIcon(new ImageIcon("buttons/medium1.png"));
	mediumButton.setBorder(null);
	mediumButton.setBorderPainted(false);
	mediumButton.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameMode=Controller.AI_MODE_MEDIUM;
			playSfx("sounds/button_click.wav");
			view.goToChooseFirstHero();
			try
			{
				choosingFirstHeroButtons();
				firstPlayerName  = JOptionPane.showInputDialog(view.getCurrentPanel(), "Please enter your name : ","Player 1");
			} catch (IOException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	JButton hardButton = new JButton();
	hardButton.setBorder(BorderFactory.createEmptyBorder());
	hardButton.setContentAreaFilled(false);
	hardButton.setIcon(new ImageIcon("buttons/hard1.png"));
	hardButton.setSize(550,110);
	hardButton.setBorder(null);
	hardButton.setBorderPainted(false);
	hardButton.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameMode=Controller.AI_MODE_HARD;
			playSfx("sounds/button_click.wav");
			view.goToChooseFirstHero();
			try
			{
				choosingFirstHeroButtons();
				firstPlayerName  = JOptionPane.showInputDialog(view.getCurrentPanel(), "Please enter your name : ","Player 1");
			} catch (IOException | CloneNotSupportedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	buttons.add(easyButton);
	buttons.add(mediumButton);
	buttons.add(hardButton);
	view.revalidate();
	view.repaint();
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
  
	view.getCurrentPanel().removeAll();
	  Font font = null;
		try {
			 font= Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")).deriveFont(65f);
	        GraphicsEnvironment ge=	GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")));
	    
	    } catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 JLabel over =new JLabel();
	 over.setIcon(new ImageIcon("buttons/gameover.png"));

	JLabel text =new JLabel();
	 String winningHero = "                           ";
	  if(firstPlayerHero.getCurrentHP()==0)
		  winningHero = winningHero + secondPlayerName +" WON !!";
	  else 
		  winningHero = winningHero + firstPlayerName + " WON !!";
	  text.setText(winningHero);
	  text.setFont(font);
	  text.setForeground(Color.RED);
	  JPanel test=new JPanel();
	  test.setOpaque(false);
	  JLabel space =new JLabel("         ");
	  space.setFont(font);
	  test.add(space);
	  test.add(over);
	  JButton ok = new JButton();
	  ok.setIcon(new ImageIcon("buttons/exit.png"));
	  ok.setPreferredSize(new Dimension(400,250));
	  ok.setOpaque(false);
	   ok.setBorder(BorderFactory.createEmptyBorder());
		 ok.setContentAreaFilled(false);
	  ok.setSize(new Dimension(2,10));
	  ok.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			view.dispose();
			System.exit(0);
			
		}
	});
	 
	  this.view.getCurrentPanel().add(test,BorderLayout.NORTH);
	  this.view.getCurrentPanel().add(text,BorderLayout.CENTER);
	  this.view.getCurrentPanel().add(ok,BorderLayout.SOUTH);
	  this.view.revalidate();
	  this.view.repaint();  
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
    		heroButton.setIcon(new ImageIcon("images/paladinh.png"));
    	}
    	if(heroButton.getHero() instanceof Mage)
    	{
    		heroButton.setIcon(new ImageIcon("images/magehhh.png"));
    	}
    	if(heroButton.getHero() instanceof Warlock)
    	{
    		heroButton.setIcon(new ImageIcon("images/warlockh.png"));
    	}
    	
    	if(heroButton.getHero() instanceof Hunter)
    	{
    		heroButton.setIcon(new ImageIcon("images/hunterh.png"));
    	}
    	if(heroButton.getHero() instanceof Priest)
    	{
    		heroButton.setIcon(new ImageIcon("images/priesth.png"));
    	}
 
		heroButton.addActionListener(heroButtonListener);
	}
    
	JButton confirmButton = new JButton();
	confirmButton.setPreferredSize(new Dimension(400,140));
	confirmButton.setBorder(BorderFactory.createEmptyBorder());
	confirmButton.setContentAreaFilled(false);

	confirmButton.setIcon(new ImageIcon("images/choose2.png"));
	ConfirmFirstButtonListener confirmFirstButtonListener = new ConfirmFirstButtonListener(this);
	confirmButton.addActionListener(confirmFirstButtonListener);

	JPanel heros =new JPanel();
	heros.setOpaque(false);
	for (ChooseHeroButton heroButton : firstPlayerHeroes)
	{
		heros.add(heroButton);
	}
	JLabel logol =new JLabel(new ImageIcon("buttons/test6.png"));
	view.getCurrentPanel().add(logol,BorderLayout.NORTH);
	view.getCurrentPanel().add(heros,BorderLayout.CENTER);
	view.getCurrentPanel().add(confirmButton,BorderLayout.SOUTH);
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
    		heroButton.setIcon(new ImageIcon("images/paladinh.png"));
    	}
    	if(heroButton.getHero() instanceof Mage)
    	{
    		heroButton.setIcon(new ImageIcon("images/magehhh.png"));
    	}
    	if(heroButton.getHero() instanceof Warlock)
    	{
    		heroButton.setIcon(new ImageIcon("images/warlockh.png"));
    	}
    	
    	if(heroButton.getHero() instanceof Hunter)
    	{
    		heroButton.setIcon(new ImageIcon("images/hunterh.png"));
    	}
    	if(heroButton.getHero() instanceof Priest)
    	{
    		heroButton.setIcon(new ImageIcon("images/priesth.png"));
    	}
    	
		heroButton.addActionListener(heroButtonListener);
	}
    
	JButton confirmButton = new JButton();
	confirmButton.setPreferredSize(new Dimension(400,140));
	confirmButton.setBorder(BorderFactory.createEmptyBorder());
	confirmButton.setContentAreaFilled(false);

	confirmButton.setIcon(new ImageIcon("buttons/choose.png"));
	ConfirmSecondButtonListener confirmSecondButtonListener = new ConfirmSecondButtonListener(this);
	confirmButton.addActionListener(confirmSecondButtonListener);
	
	JPanel heros =new JPanel();
	heros.setOpaque(false);
	
	for (ChooseHeroButton heroButton : secondPlayerHeroes)
	{
		heros.add(heroButton);
	}
	

	JLabel logol =new JLabel(new ImageIcon("buttons/test6.png"));
	view.getCurrentPanel().add(logol,BorderLayout.NORTH);
	view.getCurrentPanel().add(heros,BorderLayout.CENTER);
	view.getCurrentPanel().add(confirmButton,BorderLayout.SOUTH);
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
	model=null;
	try {
		
		view.goToGameView(first, second);
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {
		model=new Game (first,second);
		model.setListener(this);
	} catch (FullHandException | CloneNotSupportedException e) {
		JOptionPane.showMessageDialog(this.getView(), e.getMessage());
	}
	if(this.getFirstPlayerName()==null||this.getFirstPlayerName().equals(""))
		this.setFirstPlayerName("Guest");
	if(this.getSecondPlayerName()==null||this.getFirstPlayerName().equals(""))
		this.setSecondPlayerName("Guest");
   Font font = null;
	try {
		 font= Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")).deriveFont(12f);
        GraphicsEnvironment ge=	GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")));
    
    } catch (FontFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	 firstHero=new JPanel(new BorderLayout());
     secondHero=new JPanel(new BorderLayout());
	firstHero.setOpaque(false);
	secondHero.setOpaque(false);
	firstInfo=new JLabel("Health : "+ first.getCurrentHP() +'\n'+"   Mana : "+first.getCurrentManaCrystals());
	 secondInfo=new JLabel("Health : "+ second.getCurrentHP() +'\n'+"   Mana : "+second.getCurrentManaCrystals());
	  name1=new JLabel(this.firstPlayerName +"   Total: "+this.firstPlayerHero.getTotalManaCrystals() );
	  name2=new JLabel(this.secondPlayerName+"   Total: "+this.firstPlayerHero.getTotalManaCrystals());
	firstInfo.setForeground(Color.WHITE);
	secondInfo.setForeground(Color.WHITE);
	firstInfo.setFont(font);
	secondInfo.setFont(font);
	name1.setFont(font);
	name2.setFont(font);
	view.getCardsLeft().setFont(font);
	view.getCardsLeft2().setFont(font);
	name1.setForeground(Color.WHITE);
	name2.setForeground(Color.WHITE);
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
	 view.getCardsLeft().setForeground(Color.WHITE);
	 view.getCardsLeft2().setForeground(Color.WHITE);
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
	 endTurn.setIcon(new ImageIcon("images/end4.png"));
	 endTurn.setBorder(BorderFactory.createEmptyBorder());
	 endTurn.setContentAreaFilled(false);
//	 endTurn.addActionListener(new ActionListener()
//	{
//		
//		@Override
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				attackingMinion=null;
//				usingHeroPower=null;
//				attackingWithMinonHero=null;
//				model.getCurrentHero().endTurn();
//				if(agent!=null)
//					agent.playTurn();
//				updateView();
//			} catch (FullHandException | CloneNotSupportedException e1)
//			{
//				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(view, e1.getMessage());
//			}
//			
//		}
//	});
	 endTurn.addActionListener((new endTurnListener(this)));
	 if(gameMode!=MULTY_MODE)
	 {	 
			agent = new Agent(model, this);
		if(model.getCurrentHero()==secondPlayerHero && agent!=null)
			agent.playTurn();
	 }	
	 view.getButtons().add(power2);
	 view.getButtons().add(endTurn);
	 view.getButtons().add(power1);	 
     updateHand();
}

public Agent getAgent()
{
	return agent;
}

public void setAgent(Agent agent)
{
	this.agent = agent;
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
			 minionButton.addMouseListener(new InfoListener(this));
			 minionButton.setIcon(images(i));
			 minionButton.setDisabledIcon(new ImageIcon("images/cardback.png"));
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
		    spellButton.addMouseListener(new InfoListener(this));
		    spellButton.setIcon(images(i));
		    spellButton.setDisabledIcon(new ImageIcon("images/cardback.png"));
		    spellButton.setBorder(BorderFactory.createEmptyBorder());
		    spellButton.setContentAreaFilled(false);
		    this.firstHeroHandCards.add(spellButton);
		    
		 }
	 }
	 for(JButton i:this.firstHeroHandCards) {
		 if(model.getCurrentHero()!=firstPlayerHero)
			 i.setEnabled(false);
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
			 minionButton.addMouseListener(new InfoListener(this));
			 minionButton.setIcon(images(i));
			 minionButton.setDisabledIcon(new ImageIcon("images/cardback.png"));
			 minionButton.setBorder(BorderFactory.createEmptyBorder());
			 minionButton.setContentAreaFilled(false);
			 this.secondHeroHandCards.add(minionButton);
		 }
		 else
		 {
		 SpellButton spellButton = new SpellButton((Spell) i,secondPlayerHero,this);
		 spellButton.setPreferredSize(new Dimension(109,150));
		 SpellButtonListener listener  = new SpellButtonListener(this);
		 spellButton.addMouseListener(new InfoListener(this));
		 spellButton.addActionListener(listener);
		 spellButton.setIcon(images(i));
		 spellButton.setDisabledIcon(new ImageIcon("images/cardback.png"));
		 spellButton.setBorder(BorderFactory.createEmptyBorder());
		 spellButton.setContentAreaFilled(false);
		 this.secondHeroHandCards.add(spellButton);

		 
		 }
	 }
	 for(JButton i:this.secondHeroHandCards) {
		 if(model.getCurrentHero()!=secondPlayerHero)
			 i.setEnabled(false);
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
	for (Minion minion : first.getField())
	{
		MinionButton button = new MinionButton(minion, firstPlayerHero, this, true);
		button.setPreferredSize(new Dimension(110,150));
		MinionButtonListener listener = new MinionButtonListener(this);
		button.addActionListener(listener);
		button.addMouseListener(new InfoListener(this));
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
		button.addMouseListener(new InfoListener(this));
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
	name1.setText(this.firstPlayerName +"   Total: "+this.firstPlayerHero.getTotalManaCrystals() );
	name2.setText(this.secondPlayerName+"   Total: "+this.firstPlayerHero.getTotalManaCrystals());
	firstInfo.setText("Health : "+ this.getFirstPlayerHero().getCurrentHP() +'\n'+"   Mana : "+this.getFirstPlayerHero().getCurrentManaCrystals());
	 secondInfo.setText("Health : "+ this.getSecondPlayerHero().getCurrentHP() +'\n'+"   Mana : "+this.getSecondPlayerHero().getCurrentManaCrystals());
	updateField();
	/*if(this.model.getCurrentHero()==this.firstPlayerHero)
		this.firstHero.setBorder(new MatteBorder(0,0,2,0,new Color(255,215,0)));
	else
		this.firstHero.setBorder(null);
	if(this.model.getCurrentHero()==this.secondPlayerHero)
		this.secondHero.setBorder(new MatteBorder(0,0,2,0,Color.BLACK));
	else
		this.secondHero.setBorder(null);*/
	if(!burned)
		view.getInfoArea().removeAll();
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

public int getGameMode()
{
	return gameMode;
}

public void setGameMode(int gameMode)
{
	this.gameMode = gameMode;
}

public void playBackgroundMusic()
{
	File musicFile = new File("sounds/Game_of_Thrones.wav");
	try
	{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
		backgroundClip = AudioSystem.getClip();
		backgroundClip.open(audioInput);
		FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * backGroundMusic) + gainControl.getMinimum();
		gainControl.setValue(gain);
		// Reduce volume by 10 decibels.

		backgroundClip.start(); 
		backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void playSfx(String filePath)
{
	File musicFile = new File(filePath);
	try
	{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
		if(sfxClip!=null)
		{
			sfxClip.close();
		}
		sfxClip = AudioSystem.getClip();
		sfxClip.open(audioInput);
		sfxClip.start(); 
		
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
 
}
