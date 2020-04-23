package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel {
	View frame;
	public Background(View frame) 
	{
		this.frame = frame;	

	}
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/test3.png").getImage();
	    page.drawImage(background, 0, 0, null);
	}
}
