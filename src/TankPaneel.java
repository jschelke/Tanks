package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TankPaneel extends JPanel implements ActionListener{
	
	public TankPaneel() {
	this.setBackground(Color.red);
	this.setBounds(0, 0, 300, 700);
	//this.setSize(300, 700);

	}

	public void drawme(Graphics g) {	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
