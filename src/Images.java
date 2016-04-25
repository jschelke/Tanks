package src;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Images {

	public static Image Tanksbackground, SP, MP;
	static int  Icon = 30;
	
	
	static{
		Tanksbackground = new ImageIcon(Images.class.getResource("Images/Tanksbackground.bmp")).getImage();
		SP = new ImageIcon(Images.class.getResource("Images/SP.bmp")).getImage();
	}
}
