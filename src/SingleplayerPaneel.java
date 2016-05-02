package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SingleplayerPaneel extends JPanel implements ActionListener {
	private static Tanks mainscreen;
	private JButton DesertButton, MountainButton, PlainButton;
	
	public SingleplayerPaneel(Tanks mainscreen){
		
		this.mainscreen = mainscreen;
		
		DesertButton = new JButton("Desert");
		DesertButton.addActionListener(this);
		
		MountainButton = new JButton("Mountains");
		MountainButton.addActionListener(this);
		
		PlainButton = new JButton("Plain");
		PlainButton.addActionListener(this);
		
		DesertButton.setPreferredSize(new Dimension(130,30));
		MountainButton.setPreferredSize(new Dimension(130,30));
		PlainButton.setPreferredSize(new Dimension(130,30));
		
		this.add(DesertButton);
		this.add(MountainButton);
		this.add(PlainButton);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == DesertButton){
			mainscreen.switchPanel(new Terrain(mainscreen));			
		}else if(e.getSource() == MountainButton){
			mainscreen.switchPanel(new Terrain(mainscreen));
		}else{
			mainscreen.switchPanel(new Terrain(mainscreen));
		}
		
	}
	

}
