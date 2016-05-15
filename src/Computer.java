package src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Computer extends Tank{
	private Tank Target;
	private Tank ClosestTarget;
	private int ClosestTargetDistance = 700;
	private int hitcount = 0;
	List <ComputerShotFired> ShotsFired = new ArrayList<ComputerShotFired>();
	Random rand = new Random();
	
	public Computer(Color kleur, Terrain terrain, int TANKID, String name) {
		super(kleur, terrain, TANKID, name);
		System.out.println(getxcoord());
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
				Target = Target;
			}else if(!(LastAttacker == null)&& isTankAlive(LastAttacker)){
				Target = LastAttacker;
			} else{
				Target = ClosestTarget;
			}
		}
	}
	private void engageTank(){
		
	}
	public void hitPosition(int posx){ //stuurt positie van de laatste hit door naar Shotsfired
		ShotsFired.get(ShotsFired.size()-1).setDistanceFromTarget(posx);;
	}
	public boolean isComputer(){//controle of deze Tank een compûter is
		return true;
	}
}
