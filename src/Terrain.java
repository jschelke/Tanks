package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Terrain extends JPanel implements ActionListener, KeyListener {
	private PlayPaneel Play;
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
	private Image TerrainBackground;
	private boolean isStartupDrawComplete = false;
	
	private ArrayList<JLabel> HPLabels= new ArrayList<JLabel>();
	private ArrayList<JLabel> nameLabels= new ArrayList<JLabel>();
	
	private double vx, vy;
	
	public Terrain(PlayPaneel Play, Color terrainColor,String[] nameList,Color[] colorList,boolean[] computerControlledList, Image TerrainBackground){
		this.Play = Play;
		this.TankList = new ArrayList<Tank>(AmountOfTanks);
		this.AmountOfTanks = nameList.length;
		this.terrainColor = terrainColor;
		this.TerrainBackground = TerrainBackground; 
		
		Points = SplineFactory.TerrainGeneration();
		yPoints = Points[1];
		
		
		for(int i =0;i<AmountOfTanks;i++){
			if(computerControlledList[i])
				TankList.add(new Computer(colorList[i], this, i, nameList[i]));
			else
				TankList.add(new Tank(colorList[i],this,i,nameList[i]));
		}
		timer = new Timer();
		timer.schedule(new TankTimerTask(), 0, 1000);
		setFocusable(true);
		addKeyListener(this);
		repaint();
		}
	
	public int getyPoints(int xcoord){
		if(xcoord<700&&xcoord>0 ){
			return(yPoints[xcoord]);
		} else{
			return -1;
		}
		
	}
	public int getAngleTerrain(int beginPoint,int endPoint){
		System.out.println("Angle of terrain between "+beginPoint + " and " + endPoint + " is : "+ (int)-Math.toDegrees(Math.atan((Points[1][beginPoint]-Points[1][endPoint])/(double)(endPoint-beginPoint))));
		return(int)Math.toDegrees(Math.atan((Points[1][beginPoint]-Points[1][endPoint])/(double)(endPoint-beginPoint)));
	}
	public void TankKilled(Tank tank){ //hier controleren hoeveel tanks overblijven en hieruit naar een nieuw scherm sturen
		if(CurrentTank>tank.getTANKID())
			CurrentTank--;
			AmountOfTanks--;
			TankList.remove(tank);
				JOptionPane.showMessageDialog(this, tank.getName() + " his tank was destroyed", "Destroy", JOptionPane.WARNING_MESSAGE);
		if(AmountOfTanks == 1){
			int GameOver = JOptionPane.showConfirmDialog(this,"Do you want to restart Tanks", "GAME OVER", JOptionPane.YES_NO_OPTION);
	        if (GameOver == JOptionPane.YES_OPTION) {
	        	Play.GoToMenu();
	        }else {
	           System.exit(0);
	        }
		}
	}
	
	public int Tank_spawn(int TANKID){ //geeft x coordinaten in het begin van het spel voor de Tanks
		int Splitscreen = (yPoints.length-SideEffect)/AmountOfTanks;
		Random rand = new Random();
		return (rand.nextInt(Splitscreen) + Splitscreen*TANKID + ConfigureX);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(TerrainBackground, 0, 0, this);
		drawTerrain(g);
		if(firedShell!=null){
			firedShell.drawme(g);
		}
		if(HPLabels.size()==0){//tekenen van HP points labels en namen boven de Tanks
			for(int i = 0; i<TankList.size();i++){
				if(TankList.get(i).getHP()>0){
					HPLabels.add(new JLabel(TankList.get(i).getHP()+"%",JLabel.CENTER));
					HPLabels.get(i).setBounds(TankList.get(i).getxcoord()-20,getyPoints(TankList.get(i).getxcoord())-30, 40, 20);
					add(HPLabels.get(i));
				
					nameLabels.add(new JLabel(TankList.get(i).getName(),JLabel.CENTER));
					nameLabels.get(i).setBounds(TankList.get(i).getxcoord()-30,getyPoints(TankList.get(i).getxcoord())-50, 60, 20);
					add(nameLabels.get(i));
				
				TankList.get(i).drawTank(g);
				}
			}
		}
		else{
			for(int i=0; i<TankList.size(); i++){//tekenen van Tanks en hun HP waarden
				remove(HPLabels.get(i));
				remove(nameLabels.get(i));
				if(TankList.get(i).getHP()>=0){
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

	public void drawTerrain(Graphics g) {// tekent het terrein
		g.setColor(terrainColor);
		g.fillPolygon(Points[0], yPoints, Points[0].length);
		if(!isStartupDrawComplete){
			this.isStartupDrawComplete = true;
			if(TankList.get(0).isComputer()){
				((Computer) TankList.get(0)).fire();
			}
		}
	}
	public Tank getCurrentTank() {// geeft de huidige actieve Tank weer
		return TankList.get(CurrentTank);
	}
	
	public void fireTank(int speed,int Angle){//veranderen van geslecteerde Tank en doorsturen van commando om shell af te vuren
		System.out.println("Shot fired Angle:\t" + Angle + "\tPower:\t"+speed );
		ShellFired(speed,Angle,getCurrentTank());
		TankList.get(CurrentTank).setAngle(Angle);
		TankList.get(CurrentTank).setPower(speed);
	}
	public void ShellFired(int speed,int Angle,Tank tank){//starten timer en nieuwe Shell aanmaken
		firedShell = new Shell(speed,Angle,tank.getxcoord(),this);
		timer = new Timer();
		timer.schedule(new MyTimerTask(firedShell,tank), 0, 20);
	}

	public class MyTimerTask extends TimerTask {// wordt gebruikt om de osistie van de shell te upadten en zo ook te tekenen
		Shell shell;
		Tank tank;
		public MyTimerTask(Shell shell,Tank tank) {
			this.shell = shell;
			this.tank = tank;
		}
		private int time = 0;
		public void run() {
			int returnValue = shell.updateme(time);//hieruit kan worden gezien of er een inslag om het terrein is of de shell nog steeds vliegt
			shell.updateme(time);
			if(returnValue == -1){//Shell heeft nog niets geraaktt en wordt verder geupdate
				time++;
				repaint();
			}else if(returnValue == -2){//Shell is uit het paneel gevlogen en heeft niets geraakt
				if(tank.isComputer()){
					((Computer) tank).hitPosition(shell.getxPoint(),shell.getyPoint(),false);//geeft een waarde terug die groter is dan de breedte van het scherm
				}
				repaint();
				checkNextTank();
				firedShell = null;
				timer.purge();
			}
			else{
				repaint();
				drawhit(returnValue,10,tank);//Shell heeft het terrein geraakt
				timer.purge();
			}
		}
	}
	
	public void drawhit(int posx,int hitRadius,Tank attacker) {// als de shell het terrein raakt zal hiermee een krater getekent worden en wordt gecontroleerd of een tank geraakt is
		if(attacker.isComputer()){
			((Computer) attacker).hitPosition(posx,this.getyPoints(posx),true);
		}
		for(int i =-hitRadius;i<=hitRadius;i++){
			if(posx+i>0 &&posx+i<700){
				yPoints[posx+i] = yPoints[posx+i]+(int)Math.sqrt(Math.abs(Math.pow(hitRadius,2)-Math.pow(i, 2)));
				if(yPoints[posx+i]>700){
					yPoints[posx+i] = 700;
				}
			}
		}
		for(int i = 0;i<TankList.size();i++){
			TankList.get(i).Hit((hitRadius-Math.abs(TankList.get(i).getxcoord()-posx))*3,attacker);
		}
		firedShell = null;
		repaint();
		checkNextTank();
	}
	
	private void checkNextTank(){ //controleert of de volgende Tank een computer is en indien dit het geval is dan begint het proces om een shell af te vuren
		CurrentTank++;
		if(CurrentTank >=AmountOfTanks){
			CurrentTank -= AmountOfTanks;
		}
		System.out.println("next tank is "+ CurrentTank);
		if(TankList.get(CurrentTank).isComputer())
			((Computer) TankList.get(CurrentTank)).fire();
	}
	
	private class TankTimerTask extends TimerTask {
		public TankTimerTask(){
		}
		public void run() {
			repaint();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Tank tank = getCurrentTank();
		tank.xcoord += vx;
		tank.ycoord += vy;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			Tank.Right(100, vx, vy);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			Tank.Left(100, vx, vy);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
