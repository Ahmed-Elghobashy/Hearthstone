package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel
{
	
	public MainMenuPanel()
	{
		super();
		
	}
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/MainMenu.jpg").getImage();
	    page.drawImage(background, 0, 0, null);
	}
	
}
