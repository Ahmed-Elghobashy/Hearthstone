package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;

public class InfoListener extends MouseAdapter  {
	public InfoListener(Controller controller) {
		super();
		this.controller = controller;
	}

	Controller controller;
	
	
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof SpellButton|| e.getSource() instanceof MinionButton) {
			controller.getView().getInfoArea().removeAll();
			JButton button = (JButton)e.getSource();
			if(!button.isEnabled())
				return;
			JLabel image=new JLabel();
			JTextArea details=new JTextArea();
		    details.setPreferredSize(new Dimension(300,100));
		    details.setOpaque(false);
			if(button instanceof MinionButton) {
			Minion minion =((MinionButton) button).getMinion();
			image.setIcon(imagesl(minion));
		    
		    details.setText("  Current HP : "+ minion.getCurrentHP()+
		    		           "    Max HP : "+ minion.getMaxHP()+"\n"+"  Mana cost: " + minion.getManaCost() +"        Attack: " + minion.getAttack()+"\n"+
		    		           "  Rarity: "+ minion.getRarity()+"\n");
		    if(minion.isSleeping())
				details.setText(details.getText()+"  Sleeping"+"                ");
		    if(minion.isDivine())
		          details.setText(details.getText()+"  Divine"+"\n");
			if(minion.isTaunt())
				details.setText(details.getText()+"  Taunt");}
			else {Spell spell=((SpellButton) button).getSpell();
			image.setIcon(imagesl(spell));
			details.setText("  "+spellType(spell)+"\n"+"  Rarity : "+spell.getRarity()+"\n"+"  Mana Cost : "+spell.getManaCost());
			}
			 Font font = null;
				try {
					 font= Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")).deriveFont(15f);
			        GraphicsEnvironment ge=	GraphicsEnvironment.getLocalGraphicsEnvironment();
			        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/Friz Quadrata TT Regular.ttf")));
			    
			    } catch (FontFormatException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
		    
		    details.setForeground(Color.WHITE);
		    details.setFont(font);
			this.controller.getView().getInfoArea().add(image,BorderLayout.CENTER);
			this.controller.getView().getInfoArea().add(details,BorderLayout.SOUTH);
			this.controller.getView().revalidate();
			this.controller.getView().repaint();
			
		}
		
	}

    /**
     * Invoked when the mouse exits a component.
     * @param e the event to be processed
     */
    public void mouseExited(MouseEvent e) {
    	controller.getView().getInfoArea().removeAll();
    	this.controller.getView().revalidate();
		this.controller.getView().repaint();

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
    public static String spellType(Spell spell)
	{
		
		String retString = "Spell type is: ";
	
		if(spell instanceof FieldSpell)
		{    if(retString.length()>16)
			    retString = retString + ",";
			 retString =retString+"FieldSpell";
		}
		if(spell instanceof HeroTargetSpell)
		{	if(retString.length()>16)
		    	retString = retString + ",";
			 retString =retString+"HeroTargetSpell";
		}
		if(spell instanceof LeechingSpell)
		{	if(retString.length()>16)
		    	retString = retString + ",";
			 retString =retString+"LeechingSpell";
		}
		if(spell instanceof AOESpell)
		{	if(retString.length()>16)
		    	retString = retString + ",";
			 retString =retString+"AOESpell";
		}
		if(spell instanceof MinionTargetSpell)
		{	if(retString.length()>16)
				retString = retString + ",";
			retString=retString+"MinionTargetSpell";
		}
		return retString;
	}


}
