package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;

public  class SpellButton extends JButton
{
	public Controller getController()
	{
		return controller;
	}



	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	private Spell spell;
	private Hero hero ;
	private Controller controller;
	
	public SpellButton(Spell spell, Hero hero, Controller controller)
	{
		super();
		this.spell = spell;
		this.hero = hero;
		this.controller = controller;
		this.setText(spell.getName());
		this.addMouseListener(new MouseAdapter()
		{
			 public void mouseEntered(MouseEvent e)
			 {
				 if(SwingUtilities.isRightMouseButton(e))
				 {
					 JPopupMenu popup = new JPopupMenu();
					 String name  = "Spell name : " + spell.getName() ;
					 String spellType = spellType(spell);
					 String manaCost = "mana cost: " + spell.getManaCost();
					 String rarity = "rarity :" + spell.getRarity();
					 popup.add(new JMenuItem(name));
					 popup.add(new JMenuItem(spellType));
					 popup.add(new JMenuItem(manaCost));
					 popup.add(new JMenuItem(rarity));
					 
				 }
				 
			 }
		});
	}
	
	
	
	public Spell getSpell()
	{
		return spell;
	}
	public void setSpell(Spell spell)
	{
		this.spell = spell;
	}
	public Hero getHero()
	{
		return hero;
	}
	public void setHero(Hero hero)
	{
		this.hero = hero;
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
