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
	
	private int AngleStop = 15; //zorgt dat de tank niet recht naar boven kan shieten als AngleStop = 10 kan de tank niet shieten tussen 80° en 100°
	private int AccuracyFirstShot = 20;//als de tnak voor het eerst naar een doelwitschiet dan bepaals dit hoe nauwkeuerig dat schot is
	private int MinimumPower = 15;
	private int GuessAngleVariation = 20;
	
	public Computer(Color kleur, Terrain terrain, int TANKID, String name) {
		super(kleur, terrain, TANKID, name);
		System.out.println(getxcoord());
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
			if(LastAttacker == null){
				Target = ClosestTarget;
				System.out.println("ClosestTarget Chosen");
			} else{
				Target = LastAttacker;
				System.out.println("Last Attacker Chosen");
			}
		}else{
			if(!(LastAttacker == null)&& isTankAlive(LastAttacker)){
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
			if(Math.abs(Target.getxcoord()-Shot.getxcoordTarget())+Math.abs(Shot.getDistanceFromTarget())<ShotAccuracy){
				if((Shot.getxcoordTarget()<getxcoord()&&Target.getxcoord()<getxcoord())||(Shot.getxcoordTarget()>getxcoord()&&Target.getxcoord()>getxcoord())){//controleerd of het Target en Shot aan dezelfde kan van de Computer liggen
					ShotAccuracy = Math.abs(Target.getxcoord()-Shot.getxcoordTarget())+Math.abs(Shot.getDistanceFromTarget());
					ClosestShot = Shot;
				}
			}
		}
		return ClosestShot;
	}
	
	private void engageTank(){//kiest een geschikte hoek en snelheid voor te schieten
		int Power,Angle;
		if(ShotsFired.size() == 0){ //toevoegen is hier nog nodig
			System.out.println("Take Guess Shot is running");
			TakeShotGuess();
		} else{
			ComputerShotFired ClosestShot = closestShotFinder();
			if(ClosestShot.getxcoordTarget()<ClosestShot.getxcoordHit()){// controleer de poging links schoot van het target
				System.out.println("Debugging Computer: \t Running: Marker 1");
				Power = (ClosestShot.getPower()-(ClosestShot.getDistanceFromTarget()/(4+rand.nextInt(8))));
				Angle = ClosestShot.getAngle();
				ShotsFired.add(new ComputerShotFired(Target,Angle,Power));
				terrain.fireTank(Power, Angle);
			}else{ //Marker 2
				
				int AngleShot;
				System.out.println("Debugging Computer: \t Running: Marker 2");
				if(ClosestShot.isAngleReevaluationNeeded()&&ClosestShot.getTarget()==Target&&Math.abs(terrain.getAngleTerrain(ClosestShot.getxcoordHit()-20, ClosestShot.getxcoordHit()+20))>30){
						AngleShot = ClosestShot.getAngle()+10+rand.nextInt(GuessAngleVariation-10);
				}else if(getTopMountaininWayShootingRight(ClosestShot)!=0){
					if(90-AngleStop-ClosestShot.getAngle()>GuessAngleVariation)
						AngleShot = ClosestShot.getAngle() + rand.nextInt(GuessAngleVariation);
					else
						AngleShot = ClosestShot.getAngle() + rand.nextInt(90-AngleStop-ClosestShot.getAngle());
				}
				else{
					AngleShot = ClosestShot.getAngle();
				}
				if(ClosestShot.getDistanceFromTarget()>0)
					Power = (ClosestShot.getPower()+Math.abs(ClosestShot.getDistanceFromTarget()/(4+rand.nextInt(8))));
				else if(ClosestShot.getDistanceFromTarget()<0)
					Power = (ClosestShot.getPower()-Math.abs(ClosestShot.getDistanceFromTarget()/(4+rand.nextInt(8))));
				else
					Power = ClosestShot.getPower();
				if(Power>100)
					Power = 100; 
				if(Power<MinimumPower)
					Power = MinimumPower;
				
				ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power));
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
			System.out.println("AngleShot: "+ AngleShot);
			Power = (int)((Target.getxcoord()-this.getxcoord()+rand.nextInt(AccuracyFirstShot))/Math.cos(Math.toRadians(AngleShot)));
			if(Power>100)
				Power = 100; 
			if(Power<MinimumPower)
				Power = MinimumPower;
			System.out.println("AngleShot: "+ AngleShot);
			ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power));
			terrain.fireTank(Power, AngleShot);
		} else{
			AngleTerrain = 90+(terrain.getAngleTerrain(this.getxcoord()-50,this.getxcoord()));//omdat het target links staat wordt bergaf, bergop en omgekeerd
			System.out.println("AngleTerrain: "+AngleTerrain);
			if (AngleTerrain<0)
				AngleTerrain = 0;
			AngleShot = AngleTerrain + rand.nextInt(90-AngleStop-AngleTerrain);
			Power = (int)((this.getxcoord()-Target.getxcoord()+rand.nextInt(AccuracyFirstShot))/Math.cos(AngleShot));
			AngleShot = 180-AngleShot;
			if(Power>100)
				Power = 100; 
			if(Power<MinimumPower)
				Power = MinimumPower;
			ShotsFired.add(new ComputerShotFired(Target,AngleShot,Power));
			System.out.println("Shot fired Angle:\t" + AngleShot + "\tPower:\t"+Power );
			terrain.fireTank(Power, AngleShot);
		}
	}
	
	public void hitPosition(int posx,int posy){ //stuurt positie van de laatste hit door naar Shotsfired
		ShotsFired.get(ShotsFired.size()-1).setDistanceFromTarget(posx);
		ShotsFired.get(ShotsFired.size()-1).setycoordHit(posy);
		evaluateAngle();
	}
	private void evaluateAngle(){
		if(ShotsFired.get(ShotsFired.size()-1).getPower()>40&&Math.abs(ShotsFired.get(ShotsFired.size()-1).getxcoordHit()-this.getxcoord())<1.5*Math.abs(ShotsFired.get(ShotsFired.size()-1).getDistanceFromTarget())){
			System.out.println("Hill is detected in the way");
			ShotsFired.get(ShotsFired.size()-1).AngleReevaluationNeeded();
		}
	}
	public int getTopMountaininWayShootingRight(ComputerShotFired Shot){
		int topMountain =terrain.getyPoints(this.getxcoord());
		for(int i = this.getxcoord();i<Target.getxcoord();i++){// start van positie tank en controleerd of er een berg tussen jou en het doelwit staat
			if(terrain.getyPoints(i)>topMountain){
				topMountain = i;
			}
		}
		if(topMountain<Shot.getycoordhit()){
			return topMountain;
		} else{
			return 0;
		}
	}
	
	public boolean isComputer(ComputerShotFired  shot){//controle of deze Tank een computer is
		return true;
	}
}
