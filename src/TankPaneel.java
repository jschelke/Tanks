package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class TankPaneel extends JPanel implements ActionListener{
	private Tank CurrentTank;
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 5, PowerMajor = 25;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 100;
	private int AngleBegin = 0, AngleBetween = 45, PowerBegin = 0, PowerBetween = 25;
	private JButton ShootButton;
	private Slider AngleSlider,PowerSlider;
	private JLabel AngleLabel, PowerLabel,ErrorLabel, FuelLabel;
	private JProgressBar FuelBar;
	Terrain terrain;
	
	public TankPaneel(Terrain terrain) {
	this.terrain = terrain;
	
	this.setLayout(null);
	
	ErrorLabel = new JLabel("Wait till the shell hits the Terrain before firing again");
	
	AngleLabel = new JLabel("ANGLE",JLabel.CENTER);
	AngleLabel.setBounds(20, 80, 250, 20);
	AngleSlider = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	AngleSlider.setBounds(20, 100, 250, 50);
	
	PowerLabel = new JLabel("POWER", JLabel.CENTER);
	PowerLabel.setBounds(20, 230, 250, 20);
	PowerSlider = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);
	PowerSlider.setBounds(20, 250, 250, 50);
	
	FuelLabel = new JLabel("FUEL", JLabel.CENTER);
	FuelLabel.setBounds(20, 380, 250, 20);
	FuelBar = new JProgressBar(0,100);
	FuelBar.setBounds(20, 400, 250, 30);
	FuelBar.setValue(100);
    FuelBar.setStringPainted(true);
	
	ShootButton = new JButton("Shoot!");
	ShootButton.addActionListener(this);
	ShootButton.setBounds(80, 550, 130, 30);
	
	this.add(AngleLabel);
	this.add(AngleSlider);
	this.add(PowerLabel);
	this.add(PowerSlider);
	this.add(FuelLabel);
	this.add(FuelBar);
	this.add(ShootButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ShootButton){
			if(terrain.firedShell == null){
				this.CurrentTank = terrain.fireTank((int)PowerSlider.getValue()/10, 180-AngleSlider.getValue());
				PowerSlider.setValue(CurrentTank.getPower()*10);
				AngleSlider.setValue(180-CurrentTank.getAngle());
				ErrorLabel.setVisible(false);
				FuelBar.setValue(CurrentTank.getFuel());
				}
			}
			else{
				ErrorLabel.setBounds(0,20,300,10);
				add(ErrorLabel);
		}
			repaint();
		}
	}