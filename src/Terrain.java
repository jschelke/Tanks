package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Terrain extends JPanel{
	private Tanks mainscreen;
	private int WIDTH,HEIGHT;
	double[] ControlPoints = new double[(int)(WIDTH*0.7*3/50)];
	int[] xPoints = new int[ControlPoints.length/3+2];
	int[] yPoints = new int[xPoints.length];
	
	Random rand = new Random();

	public Terrain(Tanks mainscreen) {
		this.mainscreen = mainscreen;
		WIDTH = this.getWidth();
		HEIGHT = this.getHeight();
		
		mainscreen.setBackground(Color.cyan);
		mainscreen.setSize((int) (WIDTH * 0.7), HEIGHT);
		
		for(int i = 0;i<=(WIDTH*0.7);i+=50){
			ControlPoints[i*3] = i;
			ControlPoints[i*3+1] = 100+rand.nextInt(HEIGHT-100);
		}
		 
		double[] spline = SplineFactory.createCubic (ControlPoints,20);
		
		for(int i =0;i<(ControlPoints.length/3);i++){
			xPoints[i] = (int) ControlPoints[i*3];
		}
		for(int i =1;i<ControlPoints.length;i++){
			yPoints[i-1] = (int) ControlPoints[i*3+1];
		}
		xPoints[xPoints.length-3] = WIDTH;
		yPoints[yPoints.length-3] = HEIGHT;
		xPoints[xPoints.length-2] = 0;
		yPoints[yPoints.length-2] = HEIGHT;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawme(g);
	}

	public void drawme(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void drawhit(Graphics g, int posx) {
		g.setColor(Color.black);
		// System.out.println(0.8*TankPaneel.heigth);
		// g.fillOval(posx, 7*TankPaneel.heigth/10, 100, 100);
	}

}
