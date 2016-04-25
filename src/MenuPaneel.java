package src;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class MenuPaneel extends JPanel implements ActionListener {
	private JButton SingleplayerButton, MultiplayerButton, QuitButton;
	private static TankPaneel mainscreen;

	public MenuPaneel(TankPaneel mainscreen ) {
		this.mainscreen = mainscreen;
		
		SingleplayerButton = new JButton("Singleplayer");
		SingleplayerButton.addActionListener(this);
		
		MultiplayerButton = new JButton("Multiplayer");
		MultiplayerButton.addActionListener(this);
		
		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);	
		
		SingleplayerButton.setPreferredSize(new Dimension(130,30));
		MultiplayerButton.setPreferredSize(new Dimension(130,30));
		QuitButton.setPreferredSize(new Dimension(130,30));
		
		this.add(SingleplayerButton);
		this.add(MultiplayerButton);
		this.add(QuitButton);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(Images.Tanksbackground, 0, 0, this.getWidth(), this.getHeight(), this);
	}


	public static void main(String[] args) {
		JFrame Menuframe = new JFrame("Tanks");
		Menuframe.setSize(700, 700);
		Menuframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Menuframe.setLocation(330,10);

		JPanel hoofdpaneel = new MenuPaneel(mainscreen);
		Menuframe.add(hoofdpaneel);

		Menuframe.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SingleplayerButton){
			mainscreen.switchPanel(new SingleplayerPaneel(mainscreen));
			
		
		}else if(e.getSource()== MultiplayerButton){
			mainscreen.switchPanel(new MultiplayerPaneel(mainscreen));
			
		}else{
			System.exit(0);
		}
		

	}
}

