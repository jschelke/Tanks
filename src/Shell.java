package src;

import java.awt.Color;
import java.awt.Graphics;

public class Shell {
	private final int speed,Angle;
	private final double gravity = 0.1;
	private int xcoord,ycoord,startx,starty;
	
	public Shell(int speed,int Angle,int xcoord) {
		this.speed = speed;
		this.Angle = Angle;
		this.xcoord = xcoord;
		this.ycoord = Terrain.getyPoints(xcoord);
		this.startx = xcoord;
		this.starty = ycoord;
		
		/*while(ycoord <0){
			xcoord = speed*Math.cos(Angle)*time;
			ycoord = speed*Math.sin(Angle)*time-100*time*time/2;
			repaint();
			time = time + 1/interval;
			Thread.sleep(10);
		}*/
	}
	public int updateme(int time){
		xcoord = startx + (int) (speed*Math.cos(Math.toRadians(Angle))*time);
		ycoord = starty + (int) ((gravity*time*time/2)-(speed*Math.sin(Math.toRadians(Angle))*time));
		if(ycoord >= Terrain.getyPoints(xcoord)&&time != 0&&xcoord<=700){
			return xcoord;
		}
		else
			return -1;
	}
	void drawme(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(xcoord,ycoord, 5, 5);
	}

}
