package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private ArrayList<Tank> TankList;
	private static int AmountOfTanks;
	private int CurrentTank = 0;
	
	public Terrain(int AmountPlayers){
		this.setBackground(Color.CYAN);
		Points = SplineFactory.TerrainGeneration();
		yPoints = Points[1];
		this.TankList = new ArrayList<Tank>(AmountOfTanks);
		this.AmountOfTanks = AmountPlayers;
		for(int i =0;i<AmountOfTanks;i++){
			TankList.add(new Tank(Color.RED,this));
		}
		repaint();
		}
	
	public static int getyPoints(int xcoord){
		if(xcoord<700 ){
			return(yPoints[xcoord]);
		} else{
			return -1;
		}
		
	}
	
	public static int[] Tank_spawn(){ 
		int [] SpawnPlace = new int [AmountOfTanks];
		int Splitscreen = yPoints.length/AmountOfTanks; //Deel op in amountoftanks delen en kies random
		Random rand = new Random();
		for(int i=0; i<AmountOfTanks; i++){
			SpawnPlace[i] = rand.nextInt(Splitscreen) + Splitscreen*i;
		}
		return SpawnPlace;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawTerrain(g);
		if(firedShell!=null){
			firedShell.drawme(g);
		}
		for(int i=0; i<TankList.size(); i++){
			TankList.get(i).drawTank(g);
		}
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
			}else if(returnValue == -2){
				firedShell = null;
				repaint();
				timer.cancel();
			}
			else{
				drawhit(returnValue,10);
				firedShell = null;
				repaint();
				timer.cancel();
			}
		}
	}
	
	public void ShellFired(int speed,int Angle,Tank tank){
		firedShell = new Shell(speed,Angle,tank.getxcoord(),this);
		timer = new Timer();
		timer.schedule(new MyTimerTask(firedShell), 0, 20);
	}
	public void fireTank(int speed,int Angle){
		ShellFired(speed,Angle,TankList.get(CurrentTank));
		CurrentTank++;
		if(CurrentTank >=AmountOfTanks){
			CurrentTank -= AmountOfTanks;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
