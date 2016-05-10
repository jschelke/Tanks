package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tank {
	private int xcoord, ycoord, BigRectWidth, BigRectHeight, SmallRectWidth,  SmallRectHeight, BigRadius, WheelRadius, TANKID;
	private Color kleur;
	private Terrain terrain;
	private int Angle = 45,Power = 5;
	
	public Tank(Color kleur, Terrain terrain, int TANKID) {
		this.TANKID = TANKID;
		this.terrain = terrain;
		this.xcoord = terrain.Tank_spawn(TANKID);
		this.ycoord = terrain.getyPoints(xcoord);
		this.BigRectWidth = 18;
		this.BigRectHeight = 10;
		this.SmallRectWidth = 20;
		this.SmallRectHeight = 1;
		this.BigRadius = 9;
		this.WheelRadius = 5;
		this.kleur = kleur;
	}
	
	public void drawTank(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(xcoord-(BigRectWidth/4), ycoord-BigRectHeight-(BigRadius/2), BigRadius, BigRadius);
		g.setColor(kleur);
		g.fillRoundRect(xcoord-(BigRectWidth/2), ycoord-BigRectHeight, BigRectWidth, BigRectHeight, BigRadius, BigRadius);
		g.setColor(Color.gray);
		g.fillRoundRect(xcoord-(BigRectWidth/2)+2, ycoord-WheelRadius, BigRectWidth-4, WheelRadius, WheelRadius, WheelRadius);
		//drawTankgun((Graphics2D) g);
	}
	
	public void drawTankgun(Graphics2D g){
		g.setColor(kleur); //Bgtan van vector tot de muis voor hoek aan te passen + power = distance van dezelfde vector
		Rectangle Tankgun = new Rectangle(xcoord, ycoord-BigRectHeight-(WheelRadius*1/2), SmallRectWidth, SmallRectHeight);
		g.rotate(Math.toRadians(Angle));
		g.draw(Tankgun);
		g.fill(Tankgun);
	}
	
	int getxcoord(){
		return xcoord;
	}
	public void updateHeight(){
		ycoord = terrain.getyPoints(xcoord);
		return;
	}
	public int getAngle(){
		return Angle;
	}
	public int getPower(){
		return Power;
	}
	public void setAngle(int Angle){
		this.Angle = Angle;
	}
	public void setPower(int Power){
		this.Power = Power;
	}

	public int getTANKID() {
		return TANKID;
	}
}