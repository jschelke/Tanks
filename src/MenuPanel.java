package src;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	private JButton SingleplayerButton, MultiplayerButton, QuitButton, DesertButton, MountainButton, PlainButton;
	private Tanks mainscreen;

	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;
		
		this.setLayout(null);
		
		SingleplayerButton = new JButton("Singleplayer");
		SingleplayerButton.addActionListener(this);
		SingleplayerButton.setBounds(10, 10, 130, 30);

		MultiplayerButton = new JButton("Multiplayer");
		MultiplayerButton.addActionListener(this);
		MultiplayerButton.setBounds(150, 10, 130, 30);

		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		QuitButton.setBounds(850, 600, 130, 30);
		
		DesertButton = new JButton("Desert");
		DesertButton.addActionListener(this);
		DesertButton.setBounds(10, 50, 130, 30);
		DesertButton.setBackground(Color.YELLOW);
		
		MountainButton = new JButton("Mountains");
		MountainButton.addActionListener(this);
		MountainButton.setBounds(150, 50, 130, 30);
		MountainButton.setBackground(Color.GRAY);
		
		PlainButton = new JButton("Plain");
		PlainButton.addActionListener(this);
		PlainButton.setBounds(300, 50, 130, 30);
		PlainButton.setBackground(Color.green);

		this.add(SingleplayerButton);
		this.add(MultiplayerButton);
		this.add(QuitButton);
		this.add(DesertButton);
		this.add(MountainButton);
		this.add(PlainButton);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(Images.Tanksbackground, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SingleplayerButton) {
			mainscreen.switchPanel(new PlayPaneel());

		} else if (e.getSource() == MultiplayerButton) {
			mainscreen.switchPanel(new PlayPaneel());

		} else {
			System.exit(0);
		}

	}

}