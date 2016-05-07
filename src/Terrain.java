package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
//maxPower: +- 10

@SuppressWarnings("serial")
public class Terrain extends JPanel implements ActionListener {
	private int[][] Points;
	protected static int[] yPoints;
	Timer timer = new Timer();
	Shell firedShell;
	Tank tank;
	
	public Terrain(){
		this.setBackground(Color.CYAN);
		Points = SplineFactory.TerrainGeneration();
		yPoints = Points[1];
		repaint();
		ShellFired(7,30,4);
		}
	public static int getyPoints(int xcoord){
		return(yPoints[xcoord]);
	}
	
	public static int[] Tank_spawn(){ // Kies random extrema en spawn daar
		int[] Extremum = Tank.Extrema();
		Random rand = new Random();
		int [] SpawnPlace = new int[2];
		int Spawn = 0;
		int ycoord = Extremum[rand.nextInt()];
		for(int i=0; i<yPoints.length; i++){
			if(yPoints.equals(ycoord)){ 
				Spawn = yPoints[i]; // Neem x coordinaat
			}
			SpawnPlace[0] = Spawn;
			SpawnPlace[1] = ycoord;
		}
		return SpawnPlace;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawTerrain(g);
		if(firedShell!=null){
			firedShell.drawme(g);
		}
		/*if(tank == null){
			tank.drawTank(g);
		}*/ //Hier moet iets veranderd worden? 
		
		
	}

	public void drawTerrain(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillPolygon(Points[0], yPoints, Points[0].length);
		}
	
	public void drawhit(int posx,int hitRadius) {
		for(int i =-hitRadius;i<=hitRadius;i++){
			yPoints[posx+i] = yPoints[posx+i]+(int)Math.sqrt(Math.abs(Math.pow(hitRadius,2)-Math.pow(i, 2)));
			if(yPoints[posx+i]>Tanks.SCHERM_HOOGTE){
				yPoints[posx+i] = Tanks.SCHERM_HOOGTE;
			}
		}
		repaint();
	}

	private class MyTimerTask extends TimerTask {
		Shell shell;
		public MyTimerTask(Shell shell) {
			this.shell = shell;
		}
		private int time = 0;
		public void run() {
			int returnValue = shell.updateme(time);
			shell.updateme(time);
			if(returnValue == -1){
				time++;
				repaint();
			}
			else{
				drawhit(returnValue,10);
				firedShell = null;
				repaint();
				timer.cancel();
			}
		}
	}
	
	public void ShellFired(int speed,int Angle,int xcoord){
		firedShell = new Shell(speed,Angle,xcoord);
		timer.schedule(new MyTimerTask(firedShell), 0, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
