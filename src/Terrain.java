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
	private int[][] Points;
	
	public Terrain() {
		
		this.setBackground(Color.BLUE);
		//System.out.println(this.getWidth());
		Points = SplineFactory.TerrainGeneration();
	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawme(g);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(Points[0], Points[1], Points[0].length);
		}

	public void drawhit(Graphics g, int posx) {
		g.setColor(Color.black);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
