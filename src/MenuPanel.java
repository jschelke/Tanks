package src;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
	private JButton SingleplayerButton, MultiplayerButton, QuitButton, DesertButton, MountainButton, PlainButton;
	private Tanks mainscreen;

	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;
		
		SingleplayerButton = new JButton("Singleplayer");
		SingleplayerButton.addActionListener(this);

		MultiplayerButton = new JButton("Multiplayer");
		MultiplayerButton.addActionListener(this);

		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		
		DesertButton = new JButton("Desert");
		DesertButton.addActionListener(this);
		
		MountainButton = new JButton("Mountains");
		MountainButton.addActionListener(this);
		
		PlainButton = new JButton("Plain");
		PlainButton.addActionListener(this);

		SingleplayerButton.setPreferredSize(new Dimension(130, 30));
		MultiplayerButton.setPreferredSize(new Dimension(130, 30));
		QuitButton.setPreferredSize(new Dimension(130, 30));
		DesertButton.setPreferredSize(new Dimension(130,30));
		MountainButton.setPreferredSize(new Dimension(130,30));
		PlainButton.setPreferredSize(new Dimension(130,30));

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