package src;

public class ComputerShotFired {
	private Tank Target;
	private int Angle,Power,xcoordTarget,xcoordHit,ycoordHit;
	private int distanceFromTarget;
	private boolean inPlayField;
	private boolean AngleReevaluationNeeded = false;
	public ComputerShotFired(Tank Target, int Angle,int Power){
		this.Target = Target;
		this.Angle = Angle;
		System.out.println("Angleobject: " + Angle);
		this.Power = Power;
		this.xcoordTarget = Target.getxcoord();
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
	public void setDistanceFromTarget(int xcoordHit){
		if(xcoordHit != 1000){
			inPlayField = true;
			this.xcoordHit = xcoordHit;
			distanceFromTarget = Target.getxcoord()-xcoordHit;
		}
		else{
			inPlayField = false;
			if(Angle<90){
				this.xcoordHit = 1000;
				distanceFromTarget = Target.getxcoord()+300;
				System.out.println("shell exited playingfield on the right");
			}
			else{
				this.xcoordHit = -300;
				distanceFromTarget = Target.getxcoord()-300;
				System.out.println("shell exited playingfield on the left");
			}
			
		}
	}
	public void setycoordHit(int ycoord){
		this.ycoordHit= ycoord;
	}
	public void AngleReevaluationNeeded(){
		AngleReevaluationNeeded = true;
	}
	public boolean isAngleReevaluationNeeded(){
		return AngleReevaluationNeeded;
	}

}
