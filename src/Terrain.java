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
	
	public Terrain() {
		
		this.setBackground(Color.BLUE);
		
	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(5, 5, WIDTH, HEIGHT);
		drawme(g);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
		//g.fillPolygon(xPoints, yPoints, nPoints);
		}

	public void drawhit(Graphics g, int posx) {
		g.setColor(Color.black);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
