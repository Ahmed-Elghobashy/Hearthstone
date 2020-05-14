package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import agent.Agent;
import exceptions.FullHandException;
import model.cards.Card;

public class endTurnListener extends AbstractAction {
       public endTurnListener(Controller controller) {
		super();
		this.controller = controller;
	}
	Controller controller;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 Font font = null;
			try {
				 font= Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")).deriveFont(55f);
		        GraphicsEnvironment ge=	GraphicsEnvironment.getLocalGraphicsEnvironment();
		        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")));
		    
		    } catch (FontFormatException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    
		try
		{	controller.playSfx("sounds/ends.wav");
			controller.setAttackingMinion(null);
			controller.setUsingHeroPower(null);
			controller.setAttackingWithMinonHero(null);
			controller.getModel().getCurrentHero().endTurn();
			Agent agent = controller.getAgent();
			if(agent !=null)
				agent.playTurn();
			controller.updateView();
		} catch ( CloneNotSupportedException e1)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
		}catch (FullHandException e1) {
			
			Card burned=e1.getBurned();
			JOptionPane.showMessageDialog(controller.getView(), e1.getMessage() + " \n card burned : " + burned.getName() + "\n mana cost : " + burned.getManaCost() + "\nrarity : " + burned.getRarity());
			ImageIcon image=imagesl(burned);
			JLabel l=new JLabel(image);
			JLabel b=new JLabel(" Burned");
			 b.setFont(font);
			 b.setForeground(Color.RED);
			 Agent agent = controller.getAgent();
				if(agent !=null)
					agent.playTurn();
			controller.getView().getInfoArea().removeAll();
			controller.getView().getInfoArea().add(l,BorderLayout.CENTER);
			controller.getView().getInfoArea().add(b,BorderLayout.SOUTH);

			controller.updateView();
			controller.getView().revalidate();
			controller.getView().repaint();
		}
	}
public static ImageIcon imagesl (Card card) {
    	
    	if(card.getName().equals("Chromaggus"))
    		 return new ImageIcon("images/chroml.png");
    	if(card.getName().equals("Tirion Fordring"))
    		 return new ImageIcon("images/tirionl.png");
    	if(card.getName().equals("Boulderfist Ogre"))
    		 return new ImageIcon("images/boulderl.png");
    	if(card.getName().equals("Sunwalker"))
    		 return new ImageIcon("images/sunwalkerl.png");
    	if(card.getName().equals("Argent Commander"))
    		 return new ImageIcon("images/argentl.png");
    	if(card.getName().equals("Bloodfen Raptor"))
    		 return new ImageIcon("images/bloodfenl.png");
    	if(card.getName().equals("Chilwind Yeti"))
    		 return new ImageIcon("images/chillwindl.png");
    	if(card.getName().equals("Colossus of the Moon"))
    		 return new ImageIcon("images/colossusl.png");
    	if(card.getName().equals("Core Hound"))
    		 return new ImageIcon("images/corel.png");
    	if(card.getName().equals("Frostwolf Grunt"))
    		 return new ImageIcon("images/frostl.png");
    	if(card.getName().equals("Goldshire Footman"))
    		 return new ImageIcon("images/goldl.png");
    	if(card.getName().equals("The LichKing"))
    		 return new ImageIcon("images/lichl.png");
    	if(card.getName().equals("Stonetusk Boar"))
    		 return new ImageIcon("images/stonel.png");
    	if(card.getName().equals("Wolfrider"))
    		 return new ImageIcon("images/wolfl.png");
    	if(card.getName().equals("Curse of Weakness"))
    		 return new ImageIcon("images/cursel.png");
    	if(card.getName().equals("Divine Spirit"))
    		 return new ImageIcon("images/divinel.png");
    	if(card.getName().equals("Flamestrike"))
    		 return new ImageIcon("images/flamel.png");
    	if(card.getName().equals("Holy Nova"))
    		 return new ImageIcon("images/holyl.png");
    	if(card.getName().equals("Kill Command"))
    		 return new ImageIcon("images/killl.png");
    	if(card.getName().equals("Level Up!"))
    		 return new ImageIcon("images/levell.png");
    	if(card.getName().equals("Multi-Shot"))
    		 return new ImageIcon("images/multil.png");
    	if(card.getName().equals("Polymorph"))
    		 return new ImageIcon("images/polyl.png");
    	if(card.getName().equals("Pyroblast"))
    		 return new ImageIcon("images/pyroblastl.png");
    	if(card.getName().equals("Seal of Champions"))
    		 return new ImageIcon("images/seall.png");
    	if(card.getName().equals("Shadow Word: Death"))
    		 return new ImageIcon("images/shadowl.png");
    	if(card.getName().equals("Siphon Soul"))
    		 return new ImageIcon("images/siphonl.png");
    	if(card.getName().equals("Twisting Nether"))
    		 return new ImageIcon("images/twistingl.png");
    	if(card.getName().equals("Icehowl"))
    		 return new ImageIcon("images/icehowll.png");
    	if(card.getName().equals("Silver Hand Recruit"))
    		 return new ImageIcon("images/silverl.png");
    	if(card.getName().equals("Prophet Velen"))
    		 return new ImageIcon("images/prophetl.png");
    	if(card.getName().equals("Wilfred Fizzlebang"))
    		 return new ImageIcon("images/wilfredl.png");
    	if(card.getName().equals("Kalycgos"))
    		 return new ImageIcon("images/kalecgosl.png");
    	if(card.getName().equals("King Krush"))
    		 return new ImageIcon("images/kingl.png");
    	if(card.getName().equals("Sheep"))
    		 return new ImageIcon("images/sheepl.png");
    	if(card.getName().equals("The LichKing"))
    		 return new ImageIcon("images/lichl.png");
    	
    	
    	
    	
    	
    	return null;
    	
    }


}
