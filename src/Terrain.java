package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Terrain extends JPanel implements ActionListener {
	private Tanks mainscreen;
	private JButton PauseButton, ShootButton;
	
	

	public Terrain(Tanks mainscreen) {
		this.mainscreen = mainscreen;
		this.setBackground(Color.BLUE);
		mainscreen.setBounds(0, 0, 700, 700);
		
		//JPanel TankPaneel = new TankPaneel();
		//mainscreen.add(TankPaneel);
		
		//System.out.println(WIDTH + " ; " + HEIGHT);
		//System.out.println(xPoints.length + " ; " + yPoints.length);
		
		this.PauseButton = new JButton("Pause");
		PauseButton.addActionListener(this);
		
		//this.ShootButton = new JButton("Shoot");
		//ShootButton.addActionListener(this);
		
		PauseButton.setPreferredSize(new Dimension(130, 30));
		//ShootButton.setPreferredSize(new Dimension(130, 30));
		
		
		this.add(PauseButton);
		//this.add(ShootButton);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(5, 5, WIDTH, HEIGHT);
		drawme(g);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
		//g.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void drawhit(Graphics g, int posx) {
		g.setColor(Color.black);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PauseButton) {
			System.out.println("Pause");
		}//else if (e.getSource() == ShootButton){
			//mainscreen.add(new TankPaneel(mainscreen));
		//}
		
	}
}
