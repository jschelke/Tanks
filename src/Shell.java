package src;

public class Shell {
	private final int speed,Angle;
	private int xcoord,ycoord,time,interval;
	
	public Shell(int speed,int Angle,int xcoord,int ycoord) {
		this.speed = speed;
		this.Angle = Angle;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.time = 0;
		this.interval = 10;
		
		while(ycoord <){
			xcoord = speed*Math.cos(Angle)*time;
			ycoord = speed*Math.sin(Angle)*time-100*time*time/2;
			repaint();
			time = time + 1/interval;
			Thread.sleep(10);
		}
	}

}
