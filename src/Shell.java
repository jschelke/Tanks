package src;

import java.awt.Color;
import java.awt.Graphics;

public class Shell {
	private final int speed,Angle;
	private final double gravity = 0.1;
	private int xcoord,ycoord,startx,starty;
	private int ConfigureY = -20; //zorgt ervoor dat kogel uit tank komt en niet van onder de tank
	private Terrain terrain;
	
	public Shell(int speed,int Angle,int xcoord,Terrain terrain) {
		this.terrain = terrain;
		this.speed = speed;
		this.Angle = Angle;
		this.xcoord = xcoord;
		this.ycoord = terrain.getyPoints(xcoord);
		this.startx = xcoord;
		this.starty = ycoord;
	}
	public int updateme(int time){
		xcoord = startx + (int) (speed/10*Math.cos(Math.toRadians(Angle))*time);
		ycoord = starty + ConfigureY  + (int) ((gravity*time*time/2)-(speed/10*Math.sin(Math.toRadians(Angle))*time));
		if(xcoord<700 && xcoord > 0 &&ycoord >= terrain.getyPoints(xcoord)&&time != 0){
			return xcoord;
		}
		else if(xcoord>=700 || xcoord <= 0) {
			return -2;
		}
		else
			return -1;
	}
	void drawme(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(xcoord,ycoord, 5, 5);
	}

}
