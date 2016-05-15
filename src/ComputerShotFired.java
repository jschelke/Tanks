package src;

public class ComputerShotFired {
	private Tank Target;
	private int Angle,Power,xcoordTarget;
	private int distanceFromTarget;
	public ComputerShotFired(Tank Target, int Angle,int Power){
		this.Target = Target;
		this.Angle = Angle;
		this.Power = Power;
		this.xcoordTarget = Target.getxcoord();
		
	}
	public Tank getTarget() {
		return Target;
	}
	public int getPower() {
		return Power;
	}
	public int getAngle() {
		return Angle;
	}
	public void setDistanceFromTarget(int xcoordHit){
		distanceFromTarget = Target.getxcoord()-xcoordHit;
	}

}
