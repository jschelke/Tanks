package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;

import javafx.scene.transform.Transform;

public class Tank extends Transform implements ActionListener{
	int xcoord;
	int ycoord;
	protected int HP;
	protected int BigRectWidth;
	protected int BigRectHeight;
	protected int SmallRectWidth;
	protected int SmallRectHeight;
	protected int BigRadius;
	protected int WheelRadius;
	protected int TANKID;
	protected Color kleur;
	protected Terrain terrain;
	protected int Angle=0, Power=0, Fuel=100;
	protected String name;
	protected Tank LastAttacker;
	
	public Tank(Color kleur, Terrain terrain, int TANKID,String name) {
		this.TANKID = TANKID;
		this.terrain = terrain;
		this.name = name;
		if(name == null){
			this.name = "Player "+(TANKID+1);
		}
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
		g.setColor(kleur);
		g.fillOval(xcoord-(BigRectWidth/4), ycoord-BigRectHeight-(BigRadius/2), BigRadius, BigRadius);
		g.setColor(kleur);
		g.fillRoundRect(xcoord-(BigRectWidth/2), ycoord-BigRectHeight, BigRectWidth, BigRectHeight, BigRadius, BigRadius);
		g.setColor(Color.gray);
		g.fillRoundRect(xcoord-(BigRectWidth/2)+2, ycoord-WheelRadius, BigRectWidth-4, WheelRadius, WheelRadius, WheelRadius);
	}
	
	public void drawTankgun(Graphics2D g){
		g.setColor(kleur); //Bgtan van vector tot de muis voor hoek aan te passen + power = distance van dezelfde vector
		Shape Tankgun = new Rectangle(xcoord, ycoord-BigRectHeight-(WheelRadius*1/2), SmallRectWidth, SmallRectHeight);
		AffineTransform at = g.getTransform();
		g.rotate(180-terrain.getCurrentTank().getAngle(), xcoord, ycoord-BigRectHeight-(WheelRadius*1/2) -1);
		g.draw(Tankgun);
		g.fill(Tankgun);
		g.setTransform(at);
	}
	
	public void Hit(int Damage,Tank Attacker){
		updateHeight();
		if (Damage>0){
			HP -=Damage;
			LastAttacker = Attacker;
		}
		if(HP <= 0||ycoord<=0){
			HP=0;
			terrain.TankKilled(this);
		}
	}
	public void update_right(int vx){
		xcoord += vx;
		Fuel -= 5;
		updateHeight();
	}
	public void update_left(int vx){
		xcoord -= vx;
		Fuel -= 5;
		updateHeight();
	}
	public boolean isComputer(){
		return false;
	}
	
	public void updateHeight(){
		if(terrain.getyPoints(xcoord)-ycoord>5)
			HP-=(terrain.getyPoints(xcoord)-ycoord)*2;
		ycoord = terrain.getyPoints(xcoord);
		if(ycoord>700)
			HP=0;
		return;
	}
	public String getName() {
		return name;
	}
	public Color getColor(){
		return kleur;
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