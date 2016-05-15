package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Terrain extends JPanel implements ActionListener {
	private int[][] Points;
	protected static int[] yPoints;
	Timer timer = new Timer();
	Shell firedShell;
	ArrayList<Tank> TankList;
	private int AmountOfTanks;
	private int CurrentTank = 0;
	private int ConfigureX = 10;
	private int SideEffect = 20;
	private Color terrainColor;
	
	private ArrayList<JLabel> HPLabels= new ArrayList<JLabel>();
	private ArrayList<JLabel> nameLabels= new ArrayList<JLabel>();
	
	public Terrain(Color terrainColor,String[] nameList,Color[] colorList,boolean[] computerControlledList){
		this.TankList = new ArrayList<Tank>(AmountOfTanks);
		this.AmountOfTanks = nameList.length;
		this.terrainColor = terrainColor;
		
		this.setBackground(Color.CYAN);
		Points = SplineFactory.TerrainGeneration();
		yPoints = Points[1];
		
		
		for(int i =0;i<AmountOfTanks;i++){
			if(computerControlledList[i])
				TankList.add(new Computer(colorList[i], this, i, nameList[i]));
			else
				TankList.add(new Tank(colorList[i],this,i,nameList[i]));
		}
		
		repaint();
		}
	
	public int getyPoints(int xcoord){
		if(xcoord<700 ){
			return(yPoints[xcoord]);
		} else{
			return -1;
		}
		
	}
	public void TankKilled(Tank tank){ //hiercontroleren hoeveel tanks overblijven en hieruit naar een nieuw scherm sturen
		TankList.remove(tank);
		CurrentTank--;
		AmountOfTanks--;
	}
	
	public int Tank_spawn(int TANKID){ 
		int Splitscreen = (yPoints.length-SideEffect)/AmountOfTanks;
		Random rand = new Random();
		return (rand.nextInt(Splitscreen) + Splitscreen*TANKID + ConfigureX);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		drawTerrain(g);
		if(firedShell!=null){
			firedShell.drawme(g);
		}
		if(HPLabels.size()==0){
			for(int i = 0; i<TankList.size();i++){
				if(TankList.get(i).getHP()>0){
					HPLabels.add(new JLabel(TankList.get(i).getHP()+"%",JLabel.CENTER));
					HPLabels.get(i).setBounds(TankList.get(i).getxcoord()-20,getyPoints(TankList.get(i).getxcoord())-30, 40, 20);
					add(HPLabels.get(i));
				
					nameLabels.add(new JLabel(TankList.get(i).getName(),JLabel.CENTER));
					nameLabels.get(i).setBounds(TankList.get(i).getxcoord()-20,getyPoints(TankList.get(i).getxcoord())-50, 40, 20);
					add(nameLabels.get(i));
				
				TankList.get(i).drawTank(g);
				}
			}
		}
		else{
			for(int i=0; i<TankList.size(); i++){//tekenen van Tanks en hun HP waarden
				remove(HPLabels.get(i));
				remove(nameLabels.get(i));
				if(TankList.get(i).getHP()>0){
					HPLabels.set(i, new JLabel(TankList.get(i).getHP()+"%",JLabel.CENTER));
					HPLabels.get(i).setBounds(TankList.get(i).getxcoord()-20,getyPoints(TankList.get(i).getxcoord())-30, 40, 20);
					add(HPLabels.get(i));
				
					nameLabels.set(i, new JLabel(TankList.get(i).getName(),JLabel.CENTER));
					nameLabels.get(i).setBounds(TankList.get(i).getxcoord()-20,getyPoints(TankList.get(i).getxcoord())-50, 40, 20);
					add(nameLabels.get(i));
				
					TankList.get(i).drawTank(g);
				}
			}
		}
	}

	public void drawTerrain(Graphics g) {
		g.setColor(terrainColor);
		g.fillPolygon(Points[0], yPoints, Points[0].length);
		}
	
	public void drawhit(int posx,int hitRadius,Tank attacker) {
		if(attacker.isComputer()){
			((Computer) attacker).hitPosition(posx);
		}
		for(int i =-hitRadius;i<=hitRadius;i++){
			if(posx+i>0 &&posx+i<700){
				yPoints[posx+i] = yPoints[posx+i]+(int)Math.sqrt(Math.abs(Math.pow(hitRadius,2)-Math.pow(i, 2)));
				if(yPoints[posx+i]>Tanks.SCHERM_HOOGTE){
					yPoints[posx+i] = Tanks.SCHERM_HOOGTE;
				}
			}
		}
		for(int i = 0;i<TankList.size();i++){
			TankList.get(i).Hit(hitRadius-Math.abs(TankList.get(i).getxcoord()-posx),attacker);
		}
		repaint();
	}

	public class MyTimerTask extends TimerTask {
		Shell shell;
		Tank tank;
		public MyTimerTask(Shell shell,Tank tank) {
			this.shell = shell;
			this.tank = tank;
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
				drawhit(returnValue,10,tank);
				firedShell = null;
				repaint();
				timer.cancel();
			}
		}
	}
	
	public void ShellFired(int speed,int Angle,Tank tank){
		firedShell = new Shell(speed,Angle,tank.getxcoord(),this);
		timer = new Timer();
		timer.schedule(new MyTimerTask(firedShell,tank), 0, 20);
	}
	public Tank fireTank(int speed,int Angle){
		ShellFired(speed,Angle,TankList.get(CurrentTank));
		TankList.get(CurrentTank).setAngle(Angle);
		TankList.get(CurrentTank).setPower(speed);
		CurrentTank++;
		if(CurrentTank >=AmountOfTanks){
			CurrentTank -= AmountOfTanks;
		}
		return TankList.get(CurrentTank);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
