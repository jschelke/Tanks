package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.math.*;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Terrain extends JPanel implements ActionListener {
	private int[][] Points;
	private int[] yPoints;
	
	public Terrain(){
		this.setBackground(Color.BLUE);
		Points = SplineFactory.TerrainGeneration();
		yPoints = Points[1];
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawTerrain(g);
	}

	public void drawTerrain(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(Points[0], yPoints, Points[0].length);
		}

	public void drawhit(int posx,int hitRadius) {
		for(int i =-hitRadius;i<=hitRadius;i++){
			yPoints[posx+i] = yPoints[posx+i]+(int)Math.sqrt(Math.abs(Math.pow(hitRadius,2)-Math.pow(i, 2)));
			if(yPoints[posx+i]>Tanks.SCHERM_HOOGTE){
				yPoints[posx+i] = Tanks.SCHERM_HOOGTE;
			}
			
		}
		System.out.println(HEIGHT);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
