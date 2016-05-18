package src;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.NumberFormatter;




@SuppressWarnings("serial")
public class TankPaneel extends JPanel implements ActionListener, KeyListener,MouseListener{
	private Tank CurrentTank;
	private Tanks mainscreen;
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 5, PowerMajor = 25;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 100;
	private int AngleBegin = 45, AngleBetween = 45, PowerBegin = 50, PowerBetween = 25;
	private JButton ShootButton, MenuButton;
	private Slider AngleSlider,PowerSlider;
	private JFormattedTextField AngleTextField,PowerTextField;
	private JLabel AngleLabel, PowerLabel, FuelLabel,CurrentTankLabel;
	private JProgressBar FuelBar;
	Terrain terrain;
	
	public TankPaneel(Terrain terrain, Tanks mainscreen) {
	this.mainscreen = mainscreen;
	addKeyListener(this);
	addMouseListener(this);
	this.terrain = terrain;
	
	this.setLayout(null);
	
	MenuButton = new JButton("Menu");
	MenuButton.addActionListener(this);
	MenuButton.setBounds(80, 20, 130, 30);
	
	AngleLabel = new JLabel("ANGLE",JLabel.CENTER);
	AngleLabel.setBounds(20, 80, 250, 20);
	AngleSlider = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	AngleSlider.setBounds(20, 100, 200, 50);
	
	java.text.NumberFormat numberFormat = java.text.NumberFormat.getIntegerInstance();
    NumberFormatter formatter = new NumberFormatter(numberFormat);
    formatter.setMinimum(new Integer(AngleMin));
    formatter.setMaximum(new Integer(AngleMax));
    AngleTextField = new JFormattedTextField(formatter);
    AngleTextField.setValue(new Integer(AngleSlider.getValue()));
    AngleTextField.setColumns(5); //get some space
    AngleTextField.setBounds(240, 100, 40, 20);
    AngleTextField.addActionListener(this);
    AngleTextField.addKeyListener(this);
	
	PowerLabel = new JLabel("POWER", JLabel.CENTER);
	PowerLabel.setBounds(20, 230, 250, 20);
	PowerSlider = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);
	PowerSlider.setBounds(20, 250, 200, 50);
	
	java.text.NumberFormat numberFormat2 = java.text.NumberFormat.getIntegerInstance();
    NumberFormatter formatter2 = new NumberFormatter(numberFormat2);
    formatter2.setMinimum(new Integer(PowerMin));
    formatter2.setMaximum(new Integer(PowerMax));
    PowerTextField = new JFormattedTextField(formatter);
    PowerTextField.setValue(new Integer(PowerSlider.getValue()));
    PowerTextField.setColumns(5); //get some space
    PowerTextField.setBounds(240, 250, 40, 20);
    PowerTextField.addActionListener(this);
    PowerTextField.addKeyListener(this);
	
	FuelLabel = new JLabel("FUEL", JLabel.CENTER);
	FuelLabel.setBounds(20, 380, 250, 20);
	FuelBar = new JProgressBar(0,100);
	FuelBar.setBounds(20, 400, 250, 30);
	FuelBar.setValue(100);
    FuelBar.setStringPainted(true);
	
	ShootButton = new JButton("Shoot!");
	ShootButton.addActionListener(this);
	ShootButton.setBounds(80, 550, 130, 30);
	
	this.add(MenuButton);
	this.add(AngleLabel);
	this.add(AngleSlider);
	this.add(AngleTextField);
	this.add(PowerLabel);
	this.add(PowerSlider);
	this.add(PowerTextField);
	this.add(FuelLabel);
	this.add(FuelBar);
	this.add(ShootButton);
	
	CurrentTankLabel = new JLabel("Current player: "+ terrain.getCurrentTank().getName(),JLabel.CENTER);
	CurrentTankLabel.setBounds(20, 40, 200, 30);
	add(CurrentTankLabel);
	}
	public void paintComponent(Graphics g){
		remove(CurrentTankLabel);
		CurrentTankLabel = new JLabel("Current player: "+ terrain.getCurrentTank().getName(),JLabel.CENTER);
		CurrentTankLabel.setBounds(20, 40, 200, 30);
		add(CurrentTankLabel);
	}
	
	@SuppressWarnings("static-access")
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyCode() == evt.VK_ENTER && evt.getSource() == AngleTextField){// als je op enter drukt dan wordt de hoek geupdate
			if(AngleTextField.isEditValid()){
				try {
					AngleTextField.commitEdit();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				AngleSlider.setValue((int)AngleTextField.getValue());
			}
		}else if(evt.getKeyCode() == evt.VK_ENTER && evt.getSource() == PowerTextField){// als je op enter drukt dan wordt de snelheid geupdate
			if(PowerTextField.isEditValid()){
				try {
					PowerTextField.commitEdit();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				PowerSlider.setValue((int)PowerTextField.getValue());
			}
		}else if(evt.getKeyCode() == KeyEvent.VK_SPACE){ // Als je op spatie drukt wordt er geschoten
			ShootButton.doClick();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ShootButton){
			repaint();
			if(terrain.firedShell == null&&!terrain.getCurrentTank().isComputer()){
				terrain.fireTank((int)PowerSlider.getValue(), 180-AngleSlider.getValue());
				this.CurrentTank = terrain.getCurrentTank();
				PowerSlider.setValue(CurrentTank.getPower());
				AngleSlider.setValue(180-CurrentTank.getAngle());
				FuelBar.setValue(CurrentTank.getFuel());
				}
			}if(e.getSource() == MenuButton){
				mainscreen.switchPanel(new MenuPanel(mainscreen));
			}
		}
			
		
	@Override
	public void mouseExited(MouseEvent e) {
		AngleTextField.setValue(AngleSlider.getValue());
		PowerTextField.setValue(PowerSlider.getValue());
		
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}