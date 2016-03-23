package src;

import java.awt.Color;
import java.awt.Graphics;

public class Terrain {
	public Terrain() {

	}
	
	public void drawme(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 4*TankPaneel.heigth/5, TankPaneel.width, (int)TankPaneel.heigth/5);
	}
	public void drawhit(Graphics g,int posx){
		g.setColor(Color.black);
		System.out.println(0.8*TankPaneel.heigth);
		g.fillOval(posx, 7*TankPaneel.heigth/10, 100, 100);
	}

}
