package src;

import java.awt.Color;
import java.awt.Graphics;

public class Terrain {
	public Terrain(int width, int height) {

	}
	
	public void drawme(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, TankPaneel.heigth-300, TankPaneel.width, TankPaneel.heigth);
	}

}
