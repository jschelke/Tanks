package src;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TankPaneel extends JPanel{
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 50, PowerMajor = 100;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 500;
	private int AngleBegin = 90, AngleBetween = 45, PowerBegin = 0, PowerBetween = 100;
	
	public TankPaneel() {
	super(true);
	this.setBackground(Color.BLACK);
	
	Slider Angle = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	Slider Power = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);

	this.add(Angle);
	this.add(Power);

	}



}
