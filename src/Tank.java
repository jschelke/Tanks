package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Tank {
	private int xcoord, ycoord,HP, BigRectWidth, BigRectHeight, SmallRectWidth,  SmallRectHeight, BigRadius, WheelRadius, TANKID;
	private Color kleur;
	private Terrain terrain;
	private int Angle = 45, Power = 5;
	
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
		this.HP = 100;
	}
	
	public void drawTank(Graphics g){
		drawTankgun((Graphics2D) g);
		g.setColor(Color.BLACK);
		g.fillOval(xcoord-(BigRectWidth/4), ycoord-BigRectHeight-(BigRadius/2), BigRadius, BigRadius);
		g.setColor(kleur);
		g.fillRoundRect(xcoord-(BigRectWidth/2), ycoord-BigRectHeight, BigRectWidth, BigRectHeight, BigRadius, BigRadius);
		g.setColor(Color.gray);
		g.fillRoundRect(xcoord-(BigRectWidth/2)+2, ycoord-WheelRadius, BigRectWidth-4, WheelRadius, WheelRadius, WheelRadius);
	}
	
	public void drawTankgun(Graphics2D g){
		g.setColor(kleur); //Bgtan van vector tot de muis voor hoek aan te passen + power = distance van dezelfde vector
		Rectangle Tankgun = new Rectangle(xcoord, ycoord-BigRectHeight-(WheelRadius*1/2), SmallRectWidth, SmallRectHeight);
		AffineTransform at = AffineTransform.getRotateInstance(Angle*(Math.PI/180), xcoord, ycoord-BigRectHeight-(WheelRadius*1/2)-1);
		g.draw(Tankgun);
		g.fill(Tankgun);
		Shape rotatedRect = at.createTransformedShape(Tankgun);
		//g.setTransform(at);
	}
	
	public boolean Hit(int Damage){
		updateHeight();
		if (Damage>0){
			HP -=Damage;
		}
		if(HP <= 0||ycoord<=0)
			return true;
		else
			return false;
	}
	public void updateHeight(){
		ycoord = terrain.getyPoints(xcoord);
		return;
	}
	public int getTANKID() {
		return TANKID;
	}
	public int getHP(){
		return HP;
	}
	int getxcoord(){
		return xcoord;
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
}