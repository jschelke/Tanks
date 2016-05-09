package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TankPaneel extends JPanel implements ActionListener{
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 1/2, PowerMajor = 1;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 10;
	private int AngleBegin = 90, AngleBetween = 45, PowerBegin = 0, PowerBetween = 1;
	private JButton ShootButton;
	private Slider AngleSlider,PowerSlider;
	private int AngleValue = 90,PowerValue = 5;
	private Tank tank;
	Terrain terrain;
	
	public TankPaneel(Terrain terrain) {
	this.terrain = terrain;
	ShootButton = new JButton("Shoot!");
	ShootButton.addActionListener(this);
	ShootButton.setBounds(400, 250, 130, 30);
	
	AngleSlider = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	PowerSlider = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);
	
	this.add(ShootButton);
	this.add(AngleSlider);
	this.add(PowerSlider);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ShootButton){
			terrain.ShellFired(PowerSlider.getValue(), AngleSlider.getValue(),tank);
		}
		
	}



}
