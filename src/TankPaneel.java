package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TankPaneel extends JPanel implements ActionListener {
	private Tanks mainscreen;
	private JButton PauseButton;
	
	public TankPaneel(Tanks mainscreen) {
		this.mainscreen = mainscreen;
		
		this.PauseButton = new JButton("Pause");
		PauseButton.addActionListener(this);
		
		PauseButton.setPreferredSize(new Dimension(130, 30));
		
		this.add(PauseButton);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PauseButton) {
			System.out.println("Pause");
		}
	}
	
}
