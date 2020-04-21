package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.cards.minions.Minion;
import model.heroes.Hero;

public class MinionButton extends JButton
{
	private Minion minion;
	private Hero player ;
	private Controller controller;
	private Boolean onField;
	

	
	
	
	public MinionButton(Minion minion, Hero player, Controller controller, Boolean onField)
	{
		super();
		int num =  0;
		this.setText(minion.getName());
		this.minion = minion;
		this.player = player;
		this.controller = controller;
		this.onField = onField;
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{	
				if(SwingUtilities.isRightMouseButton(e))
				{
				JPopupMenu popUp = new JPopupMenu();
				String name = minion.getName();
				String currentHealth ="current health: "+ minion.getCurrentHP();
				String maxHealth ="max health: "+ minion.getMaxHP();
				String manaCost = "mana cost: " + minion.getManaCost();
				String rarity ="rarity: "+ minion.getRarity();
				popUp.add(new JMenuItem(name));
				popUp.add(new JMenuItem(currentHealth));
				popUp.add(new JMenuItem(maxHealth));
				popUp.add(new JMenuItem(manaCost));
				popUp.add(new JMenuItem(rarity));
				popUp.show(e.getComponent(),e.getX(),e.getY());
				}
				
				
			  	
			}
		});
	}
	
	public Boolean isOnField()
	{
		return onField;
	}

	public void setOnField(Boolean onField)
	{
		this.onField = onField;
	}

	public Minion getMinion()
	{
		return minion;
	}
	public void setMinion(Minion minion)
	{
		this.minion = minion;
	}
	public Controller getController()
	{
		return controller;
	}
	public Hero getPlayer()
	{
		return player;
	}

	public void setPlayer(Hero player)
	{
		this.player = player;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	public void popupMenu()
	{
		String name = minion.getName();
		String currentHealth =""+ minion.getCurrentHP();
		String maxHealth =""+ minion.getMaxHP();
		String manaCost = "" + minion.getManaCost();
		String rarity = minion.getRarity() +"" ;
		JPopupMenu popUp = new JPopupMenu();
		popUp.add(new JMenuItem(name));
		popUp.add(new JMenuItem(currentHealth));
		this.add(popUp);
	}
	

}
