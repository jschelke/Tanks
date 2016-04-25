package src;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SingleplayerPaneel extends JPanel implements ActionListener {
	private static TankPaneel mainscreen;
	private JButton DesertButton, MountainButton, PlainButton;
	
	public SingleplayerPaneel(TankPaneel mainscreen){
		
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
	
	
	public static void main(String[] args) {
		JFrame SPframe = new JFrame("Tanks.Singleplayermode");
		SPframe.setSize(700, 700);
		SPframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		SPframe.setLocation(330,10);

		JPanel hoofdpaneel = new SingleplayerPaneel(mainscreen);
		SPframe.add(hoofdpaneel);

		SPframe.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == DesertButton){
			System.out.println("Desert");
			
		}else if(e.getSource() == MountainButton){
			System.out.println("Mountain");
		}else{
			System.out.println("Plain");
		}
		
	}
	

}
