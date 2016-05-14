package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;

import javafx.scene.transform.Transform;

public class Tank extends Transform implements ActionListener{
	static int xcoord;
	static int ycoord;
	private int HP;
	private int BigRectWidth;
	private int BigRectHeight;
	private int SmallRectWidth;
	private int SmallRectHeight;
	private int BigRadius;
	private int WheelRadius;
	private int TANKID;
	private Color kleur;
	private Terrain terrain;
	private int Angle = 45, Power = 5, Fuel = 100;
	static double vx = 0;
	static double vy = 0;
	
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
		AffineTransform at = g.getTransform();
			g.rotate(-Angle, xcoord, ycoord-BigRectHeight-(WheelRadius*1/2) -1);
			g.draw(Tankgun);
			g.fill(Tankgun);
			g.setTransform(at);
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
	
	public static void Right(int Fuel){
		if(Fuel>0){
			vx = 0.5;
			vy = 0;
			Fuel -= 5; 
		}
	}
	
	public static void Left(int Fuel){
		if(Fuel>0){
			vx = -0.5;
			vy = 0;
			Fuel -= 5;
		}
	}
	
	public int updateme(int time){
		if(xcoord<700 && xcoord > 0 &&ycoord >= terrain.getyPoints(xcoord)&&time != 0){
			return xcoord;
		}
		else if(xcoord>=700 || xcoord <= 0) {
			return -2;
		}
		else
			return -1;
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
	public int getFuel() {
		return Fuel;
	}
	public void setAngle(int Angle){
		this.Angle = Angle;
	}
	public void setPower(int Power){
		this.Power = Power;
	}
	public void setFuel(int Fuel){
		this.Fuel = Fuel;
	}

	@Override
	public void impl_apply(Affine3D arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseTransform impl_derive(BaseTransform arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}