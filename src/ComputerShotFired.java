package src;

//TODO see method set distancefromTerget

public class ComputerShotFired {
	private Tank Target;
	private int Angle,Power,xcoordTarget,xcoordHit,ycoordHit;
	private int distanceFromTarget;
	private boolean inPlayingField;
	private boolean AngleReevaluationNeeded = false;
	public ComputerShotFired(Tank Target, int Angle,int Power){
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
	public void setDistanceFromTarget(int xcoordHit){// changing hit method(xcoord, ycoord,inplayingfield) later calculating distance (tanks can move)
		if(xcoordHit != 1000){
			inPlayingField = true;
			this.xcoordHit = xcoordHit;
			distanceFromTarget = Target.getxcoord()-xcoordHit;
		}
		else{
			inPlayingField = false;
			if(Angle<90){
				this.xcoordHit = 1000;
				distanceFromTarget = -300;
				System.out.println("shell exited playingfield on the right:\t"+getxcoordHit() + " Distance:\t"+getDistanceFromTarget());
				
			}
			else{
				inPlayingField = false;
				this.xcoordHit = -300;
				distanceFromTarget = 700-Target.getxcoord()+300;
				System.out.println("shell exited playingfield on the left:\t"+getxcoordHit() + " Distance:\t"+getDistanceFromTarget());
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
