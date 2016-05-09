package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TankPaneel extends JPanel implements ActionListener{
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 1/2, PowerMajor = 1;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 10;
	private int AngleBegin = 90, AngleBetween = 45, PowerBegin = 0, PowerBetween = 1;
	private JButton ShootButton;
	private Slider AngleSlider,PowerSlider;
	private JLabel AngleLabel, PowerLabel;
	private int AngleValue = 90,PowerValue = 5;
	private Tank tank;
	Terrain terrain;
	
	public TankPaneel(Terrain terrain) {
	this.terrain = terrain;
	
	this.setLayout(null);
	
	AngleLabel = new JLabel("ANGLE",JLabel.CENTER);
	AngleLabel.setBounds(10, 80, 250, 20);
	
	AngleSlider = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	AngleSlider.setBounds(10, 100, 250, 50);
	
	PowerLabel = new JLabel("POWER", JLabel.CENTER);
	PowerLabel.setBounds(10, 280, 250, 20);
	PowerSlider = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);
	PowerSlider.setBounds(10, 300, 250, 50);
	
	ShootButton = new JButton("Shoot!");
	ShootButton.addActionListener(this);
	ShootButton.setBounds(80, 600, 130, 30);
	
	this.add(AngleLabel);
	this.add(AngleSlider);
	this.add(PowerLabel);
	this.add(PowerSlider);
	this.add(ShootButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ShootButton){
			terrain.ShellFired(PowerSlider.getValue(), AngleSlider.getValue(),tank);
		}
		
	}



}
