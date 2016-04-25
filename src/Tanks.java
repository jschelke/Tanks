package src;

import java.awt.*;
import javax.swing.*;

public class Tanks extends JFrame {
	private JPanel ActivePanel;
	public static final int width = 1000;
	public static final int heigth = 700;
	
	public Tanks(){
		ActivePanel = new MenuPaneel(this); // Dit is het beginscherm
		add(ActivePanel);
	}
	public void switchPaneel(JPanel Activate){ //Referentie saloondefenders
		remove(ActivePanel);
		ActivePanel = Activate;
		add(ActivePanel);
		ActivePanel.requestFocusInWindow();
		
		validate();
		repaint();
	}
	
	public void switchPanel() {
		switchPaneel(new MenuPaneel(this)); //terug naar het begin
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Tanks");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width, heigth);
		window.setResizable(false);
		xvjkhglkgxd
		
		window.setVisible(true);
	}
	
	

}
