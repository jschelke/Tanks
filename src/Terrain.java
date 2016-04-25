package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Terrain extends JPanel{
	public Terrain() {
		PolynomalSplineFunction();
	}
	
	public void drawme(Graphics g){
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	public void drawhit(Graphics g,int posx){
		g.setColor(Color.black);
		System.out.println(0.8*TankPaneel.heigth);
		g.fillOval(posx, 7*TankPaneel.heigth/10, 100, 100);
	}

}
