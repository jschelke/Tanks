package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Terrain extends JPanel {
	private Tanks mainscreen;

	public Terrain(Tanks mainscreen) {
		this.mainscreen = mainscreen;

		mainscreen.setBackground(Color.cyan);
		mainscreen.setSize((int) (WIDTH * 0.7), HEIGHT);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
		// g.fillPolygon(xPoints, yPoints, nPoints);
	}

	public void drawhit(Graphics g, int posx) {
		g.setColor(Color.black);
		// System.out.println(0.8*TankPaneel.heigth);
		// g.fillOval(posx, 7*TankPaneel.heigth/10, 100, 100);
	}

}
