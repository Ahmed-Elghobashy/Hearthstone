package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class ChooseFirstHeroView extends JPanel {

	private JButton confirmButton;
	private Hero firstPlayerHero;
	private View frame;
	
	public ChooseFirstHeroView(View frame) 
	{
		this.frame = frame;	
		//this.setLayout(new BorderLayout());

	}

	@Override

	
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/Backgroundl.png").getImage();
	    page.drawImage(background, 0, 0, null);
	}

	
	
	
	
	

}
