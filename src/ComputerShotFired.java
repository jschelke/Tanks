package src;

//TODO see method set distancefromTerget

public class ComputerShotFired {
	private Tank Target;
	private int Angle,Power,xcoordTarget,xcoordHit,ycoordHit;
	private int distanceFromTarget;
	private boolean inPlayingField;
	private boolean AngleReevaluationNeeded = false;
	Terrain terrain;
	
	public ComputerShotFired(Tank Target, int Angle,int Power,Terrain terrain){
		this.terrain = terrain;
		this.Target = Target;
		this.Angle = Angle;
		this.Power = Power;
		this.xcoordTarget = Target.getxcoord();
	}
	public boolean inPlayingField(){
		return inPlayingField;
	}
	public Tank getTarget() {
		return Target;
	}
	public int getxcoordTarget(){
		return xcoordTarget;
	}
	public int getDistanceFromTarget(){
		return distanceFromTarget;
	}
	public int getPower() {
		return Power;
	}
	public int getAngle() {
		return Angle;
	}
	public int getxcoordHit(){
		return xcoordHit;
	}
	public int getycoordhit(){
		return ycoordHit;
	}
	public void ImpactOfShell(int xcoordShell,int ycoordShell, boolean isImpact){// changing hit method(xcoord, ycoord,inplayingfield) later calculating distance (tanks can move)
		this.xcoordHit = xcoordShell;
		this.ycoordHit = ycoordShell;
		inPlayingField = isImpact;
		if(inPlayingField)
			distanceFromTarget = Target.getxcoord()-xcoordHit;
		else
			distanceFromTarget = Target.getxcoord() - (700+ycoordShell - terrain.getyPoints(xcoordHit));
	}
	public void AngleReevaluationNeeded(){
		AngleReevaluationNeeded = true;
	}
	public boolean isAngleReevaluationNeeded(){
		return AngleReevaluationNeeded;
	}

}
