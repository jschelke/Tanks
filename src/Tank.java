package src;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.javafx.geom.Rectangle;

public class Tank {
	private int xcoord,ycoord,AngleBarrel, AngleTank ,Power;
	private Color kleur;
	
	public Tank(int xcoord,Color kleur) {
		this.xcoord = xcoord;
		this.kleur = kleur;
		this.ycoord = Terrain.getyPoints(xcoord);
		this.AngleBarrel = 0;
		this.Power = 10;
	}
	public void drawme(Graphics g){
		g.setColor(kleur);
		g.fillRect(xcoord-5, 15, 60, 20);
		g.fillOval(xcoord+20, 5, 20, 20);
		//g.fill
		g.setColor(Color.gray);
		g.fillRoundRect(xcoord, 30, 50, 10, 5, 5);
		
	}
	private int getxcoord(){
		return xcoord;
	}
}
