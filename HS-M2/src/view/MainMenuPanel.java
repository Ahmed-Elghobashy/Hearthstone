package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel
{
	
	public MainMenuPanel()
	{
		super();
		this.setLayout(new GridLayout(2,0));
		
	}
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/start.png").getImage();
	    page.drawImage(background, 0, 0, null);
	}
	
}
