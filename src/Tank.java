package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Tank extends Terrain{
	private int xcoord, ycoord, Angle, Power, BigRect, SmallRectWidth,  SmallRectHeight, BigRadius, WheelRadius ;
	private Color kleur;
	
	public Tank(int xcoord, int ycoord, Color kleur, int Angle, int Power) {
		int[] Spawn = Terrain.Tank_spawn();
		this.xcoord = Spawn[0];
		this.ycoord = Spawn[1];
		this.Angle = Angle;
		this.Power = Power;
		this.BigRect = 50;
		this.SmallRectWidth = 30;
		this.SmallRectHeight = 10;
		this.BigRadius = 25;
		this.WheelRadius = 5;
		this.kleur = kleur;
	}
	
	public void drawTank(Graphics g){
		g.setColor(kleur);
		g.fillRect(xcoord-(BigRect/2), ycoord+BigRect+WheelRadius*2, BigRect, BigRect);
		g.fillOval(xcoord-(BigRect/2), ycoord+BigRect+WheelRadius*2, BigRadius, BigRadius);
		g.setColor(Color.BLACK);
		g.fillOval(xcoord-(BigRect/2), ycoord+WheelRadius*2, WheelRadius, WheelRadius);
		g.fillOval(xcoord+(BigRect/2), ycoord+WheelRadius*2, WheelRadius, WheelRadius);
		drawTankgun((Graphics2D) g);
	}
	
	public void drawTankgun(Graphics2D g){
		g.setColor(Color.BLACK);
		Rectangle Tankgun = new Rectangle(xcoord+(BigRect/2)-WheelRadius, ycoord+BigRect, SmallRectWidth, SmallRectHeight);
		g.rotate(Math.toRadians(Angle));
		g.draw(Tankgun);
		g.fill(Tankgun);
	}
	
	int getxcoord(){
		return xcoord;
	}
}
