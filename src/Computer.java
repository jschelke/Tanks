package src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//TODO when attacking a target and multiple shots fired change Angle sligthly to hit him at bottom of well

public class Computer extends Tank{
	private Tank Target;
	private Tank ClosestTarget;
	private int ClosestTargetDistance = 700;
	List <ComputerShotFired> ShotsFired = new ArrayList<ComputerShotFired>();
	Random rand = new Random();
	
	private int AngleStop = 15; //zorgt dat de tank niet recht naar boven kan shieten als AngleStop = 10 kan de tank niet shieten tussen 80� en 100�
	private int AccuracyFirstShot = 20;//als de tnak voor het eerst naar een doelwitschiet dan bepaals dit hoe nauwkeuerig dat schot is
	private int MinimumPower = 15;
	private int GuessAngleVariation = 30;
	
	public Computer(Color kleur, Terrain terrain, int TANKID, String name) {
		super(kleur, terrain, TANKID, name);
	}
	
	public void fire(){ // main methode voor het evalueren en afvuren van een aanval
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		   Thread.currentThread().interrupt();
		}
		reevaluateTargetsDistance();
		chooseTarget();
		System.out.println(this.getName() + ": Target = "+ Target.getName());
		engageTank();
	}
	private boolean isTankAlive(Tank tank){ //controleerd of de tank nog niet vernietigd is
		for(Tank i:terrain.TankList){
			if(i == tank){
				return true;
			}
		}
		return false;
	}
	private void reevaluateTargetsDistance(){//bekijkt alle tank en kijkt welke tanks het dichtstebij zijn
		for(Tank i:terrain.TankList){
			if(Math.abs(i.getxcoord()-this.getxcoord())<ClosestTargetDistance&&i.getTANKID()!=this.getTANKID()){
				ClosestTargetDistance = Math.abs(i.getxcoord()-this.getxcoord());
				ClosestTarget = i;
			}
		}
	}
	private void chooseTarget(){ // kiest aan de hand van vorige aanvallen,afstand en laatste aanvallen het doelwit van de volgende aanval
		if(ShotsFired.size() == 0){
			if(LastAttacker == null&&LastAttacker!=this){
				Target = ClosestTarget;
				System.out.println("ClosestTarget Chosen");
			} else{
				Target = LastAttacker;
				System.out.println("Last Attacker Chosen");
			}
		}else{
			if(!(LastAttacker == null)&& isTankAlive(LastAttacker)&&LastAttacker!=this){
				Target = LastAttacker;
				System.out.println("Last Attacker Chosen");
			} else{
				Target = ClosestTarget;
				System.out.println("ClosestTarget Chosen");
			}
		}
	}
	private ComputerShotFired closestShotFinder(){
		ComputerShotFired ClosestShot = null;
		int ShotAccuracy = 700;
		for(ComputerShotFired Shot : ShotsFired){//kijkt naar vorige pogingen en haalt er een uit dat dichtbij het doelwit ligt
			if(Math.abs(Target.getxcoord()-Shot.getxcoordTarget())<=ShotAccuracy){
				if((Shot.getxcoordTarget()<getxcoord()&&Target.getxcoord()<getxcoord())||(Shot.getxcoordTarget()>getxcoord()&&Target.getxcoord()>getxcoord())){//controleerd of het Target en Shot aan dezelfde kan van de Computer liggen
					ShotAccuracy = Math.abs(Target.getxcoord()-Shot.getxcoordTarget())+Math.abs(Shot.getDistanceFromTarget());
					ClosestShot = Shot;
				}
			}
		}
		return ClosestShot;
	}
	
	private void engageTank(){//kiest een geschikte hoek en snelheid voor te schieten
		int Power;
		double Powerchange;
		if(ShotsFired.size() == 0){ //waneer er nog geen schoten zijn afgevuurd runt dit
			System.out.println("Take Guess Shot is running");
			TakeShotGuess();
		} else{
			ComputerShotFired ClosestShot = closestShotFinder();
			if(Target.getxcoord()<this.getxcoord()){//controleer of Target links ligt van Tank
				int AngleShot = ClosestShot.getAngle();
				System.out.println("Debugging Computer: \t Running: Marker 1");
				if(getTopMountaininWayShootingLeft(ClosestShot)!=0&&ClosestShot.getDistanceFromTarget()>3&&ClosestShot.getPower()>90){
					if(ClosestShot.getPower() == 100 && ClosestShot.getycoordhit()>terrain.getyPoints(ClosestShot.getxcoordTarget())){
						if(180-AngleStop-ClosestShot.getAngle()>GuessAngleVariation+90)
							AngleShot = ClosestShot.getAngle() - rand.nextInt(GuessAngleVariation);
						else
							AngleShot = ClosestShot.getAngle() - rand.nextInt(180-AngleStop-ClosestShot.getAngle());
					}
				}
				Powerchange = ClosestShot.getDistanceFromTarget()/(double)(8+rand.nextInt(6));
				if(Powerchange>-1&&Powerchange<1)//wanneer het schot heel dicht is bij het doelwit
					if(Powerchange>0)
						Powerchange = 1;
					else
						Powerchange = -1;
				System.	out.println("Power adjustment: " + (int)Powerchange);
				System.out.println("total Power: "+(int)(ClosestShot.getPower()+Powerchange));
				Power = (int) (ClosestShot.getPower()+Powerchange);
				if(Power>100)
					Power = 100; 
				if(Power<MinimumPower)
					Power = MinimumPower;
				ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power,terrain));
				terrain.fireTank(Power, AngleShot);
			}else{ 													//Marker 2, Target ligt rechts van Tank
				int AngleShot = ClosestShot.getAngle();
				System.out.println("Debugging Computer: \t Running: Marker 2");
				if(getTopMountaininWayShootingRight(ClosestShot)!=0&&ClosestShot.getDistanceFromTarget()>3&&ClosestShot.getPower()>90){
					if(ClosestShot.getPower() == 100 && ClosestShot.getycoordhit()>terrain.getyPoints(ClosestShot.getxcoordTarget())){
						if(90-AngleStop-ClosestShot.getAngle()>GuessAngleVariation)
							AngleShot = ClosestShot.getAngle() + rand.nextInt(GuessAngleVariation);
						else
							AngleShot = ClosestShot.getAngle() + rand.nextInt(90-AngleStop-ClosestShot.getAngle());
					}
				}
				Powerchange = ClosestShot.getDistanceFromTarget()/(double)(6+rand.nextInt(6));
				if(Powerchange>-3&&Powerchange<3)//wanneer het schot heel dicht is bij het doelwit
					if(Powerchange>0)
						Powerchange = 1;
					else
						Powerchange = -1;
				System.	out.println("Power adjustment: " + (int)Powerchange);
				System.out.println("total Power: "+(int)(ClosestShot.getPower()+Powerchange));
				Power = (int) (ClosestShot.getPower()+Powerchange);
				if(Power>100)
					Power = 100; 
				if(Power<MinimumPower)
					Power = MinimumPower;
				ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power,terrain));
				terrain.fireTank(Power, AngleShot);
			}
		}
	}
	private void TakeShotGuess(){//Wanneer er nog geen data beschikbaar is voor een tank aan te vallen wordt deze code gerund
		int AngleTerrain,AngleShot,Power;
		if(Target.getxcoord()>this.getxcoord()){//Target ligt rechts van Computer
			AngleTerrain = terrain.getAngleTerrain(this.getxcoord(),this.getxcoord()+50);
			if (AngleTerrain<0)
				AngleTerrain = 0;
			if(90-AngleStop-AngleTerrain>GuessAngleVariation)
				AngleShot = AngleTerrain + rand.nextInt(GuessAngleVariation);
			else
				AngleShot = AngleTerrain + rand.nextInt(90-AngleStop-AngleTerrain);
			Power = (int)((Target.getxcoord()-this.getxcoord()+rand.nextInt(AccuracyFirstShot))/Math.cos(Math.toRadians(AngleShot)));
			if(Power>100)
				Power = 100; 
			if(Power<MinimumPower)
				Power = MinimumPower;
			ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power,terrain));
			terrain.fireTank(Power, AngleShot);
		} else{
			AngleTerrain = (terrain.getAngleTerrain(this.getxcoord()-50,this.getxcoord()));//omdat het target links staat wordt bergaf, bergop en omgekeerd
			System.out.println("Angle terrain: " + AngleTerrain);
			if (AngleTerrain>0)
				AngleTerrain = 0;
			System.out.println(GuessAngleVariation + " - " + AngleStop +" + " + AngleTerrain + " = "+(GuessAngleVariation-AngleStop+AngleTerrain));
			AngleShot = AngleTerrain + 90+AngleStop+rand.nextInt(GuessAngleVariation-AngleStop+AngleTerrain);
			Power = (int)((this.getxcoord()-Target.getxcoord()+rand.nextInt(AccuracyFirstShot))/Math.cos(AngleShot));
			if(Power>100)
				Power = 100; 
			if(Power<MinimumPower)
				Power = MinimumPower;
			ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power,terrain));
			System.out.println("Shot fired Angle:\t" + AngleShot + "\tPower:\t"+Power );
			terrain.fireTank(Power, AngleShot);
		}
	}
	
	public void hitPosition(int posx,int posy,boolean isImpact){ //stuurt positie van de laatste hit door naar Shotsfired
		if(posx<0)
			posx = 0;
		ShotsFired.get(ShotsFired.size()-1).ImpactOfShell(posx,posy,isImpact);
	}

	public int getTopMountaininWayShootingRight(ComputerShotFired Shot){
		int topMountain =terrain.getyPoints(this.getxcoord());
		for(int i = Shot.getxcoordHit();i<Target.getxcoord();i++){// start van positie tank en controleerd of er een berg tussen jou en het doelwit staat
			if(terrain.getyPoints(i)>topMountain){
				topMountain = i;
			}
		}
		if(terrain.getyPoints(topMountain)<Shot.getycoordhit()){
			System.out.println("Hill is detected in the way");
			return topMountain;
		} else{
			return 0;
		}
	}
	public int getTopMountaininWayShootingLeft(ComputerShotFired Shot){
		int topMountain =terrain.getyPoints(this.getxcoord());
		if(Shot.getxcoordHit()<Target.getxcoord()){
			for(int i = Shot.getxcoordHit();i>Target.getxcoord();i--){// start van positie tank en controleerd of er een berg tussen jou en het doelwit staat
				if(terrain.getyPoints(i)<topMountain){
					topMountain = i;
				}
			}
		}
		if(topMountain<Shot.getycoordhit()){
			System.out.println("Hill is detected in the way");
			return topMountain;
		} else{
			return 0;
		}
	}
	
	public boolean isComputer(){//controle of deze Tank een computer is
		return true;
	}
}
