package src;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
	private JButton PlayButton, QuitButton;
	private Tanks mainscreen;
	private JComboBox environment;//kies kleur terrein
	private String[] environmentChoices = {"Plains","Desert", "Arctic"};// keuzes voor de ComboBox
	private int environmentChoice = 0;

	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;
		
		PlayButton = new JButton("Play");
		PlayButton.addActionListener(this);

		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		
		environment = new JComboBox(environmentChoices);
		environment.setSelectedIndex(0);
		environment.addActionListener(this);
		

		PlayButton.setPreferredSize(new Dimension(130, 30));
		QuitButton.setPreferredSize(new Dimension(130, 30));

		this.add(PlayButton);
		this.add(QuitButton);
		this.add(environment);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(Images.Tanksbackground, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PlayButton) {
			mainscreen.switchPanel(new PlayPaneel());
		} else if (e.getSource() == environment) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection =  (String) ((JComboBox) e.getSource()).getSelectedItem();
			for(int i=0;i<environmentChoices.length;i++){
				if(environmentChoices[i]==selection){
					environmentChoice = i;
				}
			}
		} else {
			System.exit(0);
		}
	}

}