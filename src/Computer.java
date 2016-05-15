package src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Computer extends Tank{
	private Tank Target;
	private Tank ClosestTarget;
	private int ClosestTargetDistance = 700;
	private int hitCount = 0;//aantal keren geraakt door een shell
	List <ComputerShotFired> ShotsFired = new ArrayList<ComputerShotFired>();
	Random rand = new Random();
	
	public Computer(Color kleur, Terrain terrain, int TANKID, String name) {
		super(kleur, terrain, TANKID, name);
		System.out.println(getxcoord());
	}
	public void Hit(int Damage,Tank Attacker){
		updateHeight();
		LastAttacker = Attacker;
		hitCount++;
		if (Damage>0){
			HP -=Damage;
		}
		if(HP <= 0||ycoord<=0){
			HP=0;
			terrain.TankKilled(this);
		}
	}
	
	public void fire(){ // main methode voor het evalueren en afvuren van een aanval
		reevaluateTargetsDistance();
		chooseTarget();
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
			if(Math.abs(i.getxcoord()-this.getxcoord())<ClosestTargetDistance){
				ClosestTargetDistance = Math.abs(i.getxcoord()-this.getxcoord());
				ClosestTarget = i;
			}
		}
	}
	private void chooseTarget(){ // kiest aan de hand van vorige aanvallen,afstand en laatste aanvallen het doelwit van de volgende aanval
		if(ShotsFired.size() == 0){
			if(LastAttacker == null){
				Target = ClosestTarget;
			} else{
				Target = LastAttacker;
			}
		}else{
			if(LastAttacker == null){
				return;
			}else if(!(LastAttacker == null)&& isTankAlive(LastAttacker)){
				Target = LastAttacker;
			} else{
				Target = ClosestTarget;
			}
		}
	}
	private void engageTank(){//kiest een geschikte hoek en snelheid voor te schieten
		ComputerShotFired ClosestShot = null;
		int ShotAccuracy;
		int Power,Angle;
		if(ShotsFired.size() == 0){ //toevoegen is hier nog nodig
			System.out.println("nu nog niets");
		} else{
			for(ComputerShotFired Shot : ShotsFired){//kijkt naar vorige pogingen en haalt er een uit dat dichtbij het doelwit ligt
				ShotAccuracy = 700;
				if(Math.abs(Target.getxcoord()-Shot.getxcoordTarget())+Math.abs(Shot.getDistanceFromTarget())<ShotAccuracy){
					if((Shot.getxcoordTarget()<getxcoord()&&Target.getxcoord()<getxcoord())||(Shot.getxcoordTarget()>getxcoord()&&Target.getxcoord()>getxcoord())){//controleerd of het Target en Shot aan dezelfde kan van de Computer liggen
						ShotAccuracy = Math.abs(Target.getxcoord()-Shot.getxcoordTarget())+Math.abs(Shot.getDistanceFromTarget());
						ClosestShot = Shot;
					}
				}
			}
			if(ClosestShot == null){
				
			} else{//Marker 1
				if(ClosestShot.getxcoordTarget()<getxcoord()){// controleer of target links ligt van de computer
					System.out.println("Debugging Computer: /t Running: Marker 1");
					Power = (ClosestShot.getPower()-(ClosestShot.getDistanceFromTarget()/(4+rand.nextInt(8))));
					Angle = ClosestShot.getAngle();
					ShotsFired.add(new ComputerShotFired(Target,Angle,Power));
					terrain.fireTank(Power, Angle);
				}else{ //Marker 2
					System.out.println("Debugging Computer: /t Running: Marker 2");
					Power = (ClosestShot.getPower()+(ClosestShot.getDistanceFromTarget()/(4+rand.nextInt(8))));
					Angle = ClosestShot.getAngle();
					ShotsFired.add(new ComputerShotFired(Target,Angle,Power));
					terrain.fireTank(Power, Angle);
				}
			}
		}
	}
	private boolean checkShot(int Angle,int Power){//controleerd of het schot binnen bepaalde waarden valt(niet te ver weg)
		
	}
	
	public void hitPosition(int posx){ //stuurt positie van de laatste hit door naar Shotsfired
		ShotsFired.get(ShotsFired.size()-1).setDistanceFromTarget(posx);;
	}
	public boolean isComputer(){//controle of deze Tank een compûter is
		return true;
	}
}
