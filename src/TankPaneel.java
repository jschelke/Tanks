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
	private PlayPaneel mainscreen;
	private String ANGLE, POWER;
	private int AngleMinor = 15, AngleMajor = 45, PowerMinor = 5, PowerMajor = 25;
	private int AngleMin = 0, AngleMax = 180, PowerMin = 0, PowerMax = 100;
	private int AngleBegin = 0, AngleBetween = 45, PowerBegin = 0, PowerBetween = 25;
	private JButton ShootButton, MenuButton, LeftButton, RightButton;
	private Slider AngleSlider,PowerSlider;
	private JFormattedTextField AngleTextField,PowerTextField;
	private JLabel AngleLabel, PowerLabel, FuelLabel,CurrentTankLabel;
	private JProgressBar FuelBar;
	Terrain terrain;
	
	public TankPaneel(Terrain terrain, PlayPaneel mainscreen) {
	this.setLayout(null);
	this.mainscreen = mainscreen;
	this.terrain = terrain;
	
	MenuButton = new JButton("Menu");
	MenuButton.addActionListener(this);
	MenuButton.addKeyListener(this);
	MenuButton.setBounds(80, 20, 130, 30);
	
	CurrentTankLabel = new JLabel(terrain.getCurrentTank().getName(),JLabel.CENTER);
	CurrentTankLabel.setBounds(45, 70, 200, 30);
	
	AngleLabel = new JLabel("ANGLE",JLabel.CENTER);
	AngleLabel.setBounds(20, 110, 250, 20);
	
	AngleSlider = new Slider(ANGLE, AngleMinor, AngleMajor, AngleMin, AngleMax, AngleBegin, AngleBetween);
	AngleSlider.setBounds(20, 130, 200, 50);
	AngleSlider.setValue(terrain.getCurrentTank().getAngle());
	
	java.text.NumberFormat numberFormat = java.text.NumberFormat.getIntegerInstance();
    NumberFormatter formatter = new NumberFormatter(numberFormat);
    formatter.setMinimum(new Integer(AngleMin));
    formatter.setMaximum(new Integer(AngleMax));
    AngleTextField = new JFormattedTextField(formatter);
    AngleTextField.setValue(new Integer(AngleSlider.getValue()));
    AngleTextField.setColumns(5); //get some space
    AngleTextField.setBounds(240, 130, 40, 20);
    AngleTextField.addActionListener(this);
    AngleTextField.addKeyListener(this);
	
	PowerLabel = new JLabel("POWER", JLabel.CENTER);
	PowerLabel.setBounds(20, 240, 250, 20);
	
	PowerSlider = new Slider(POWER, PowerMinor, PowerMajor, PowerMin, PowerMax, PowerBegin, PowerBetween);
	PowerSlider.setBounds(20, 260, 200, 50);
	PowerSlider.setValue(terrain.getCurrentTank().getPower());
	
	java.text.NumberFormat numberFormat2 = java.text.NumberFormat.getIntegerInstance();
    NumberFormatter formatter2 = new NumberFormatter(numberFormat2);
    formatter2.setMinimum(new Integer(PowerMin));
    formatter2.setMaximum(new Integer(PowerMax));
    PowerTextField = new JFormattedTextField(formatter);
    PowerTextField.setValue(new Integer(PowerSlider.getValue()));
    PowerTextField.setColumns(5); //get some space
    PowerTextField.setBounds(240, 260, 40, 20);
    PowerTextField.addActionListener(this);
    PowerTextField.addKeyListener(this);
    
    LeftButton = new JButton("Left");
    LeftButton.addActionListener(this);
    LeftButton.addKeyListener(this);
    LeftButton.setBounds(60, 370, 80, 30);
    
    RightButton = new JButton("Right");
    RightButton.addActionListener(this);
    RightButton.addKeyListener(this);
    RightButton.setBounds(150, 370, 80, 30);
	
	FuelLabel = new JLabel("FUEL", JLabel.CENTER);
	FuelLabel.setBounds(20, 430, 250, 20);
	FuelBar = new JProgressBar(0,100);
	FuelBar.setBounds(20, 450, 250, 30);
	FuelBar.setValue(100);
    FuelBar.setStringPainted(true);
	
	ShootButton = new JButton("Shoot!");
	ShootButton.addActionListener(this);
	ShootButton.setBounds(80, 550, 130, 30);
	ShootButton.addKeyListener(this);
	
	addKeyListener(this);
	addMouseListener(this);
	
	this.add(MenuButton);
	this.add(CurrentTankLabel);
	this.add(AngleLabel);
	this.add(AngleSlider);
	this.add(AngleTextField);
	this.add(PowerLabel);
	this.add(PowerSlider);
	this.add(PowerTextField);
	this.add(LeftButton);
	this.add(RightButton);
	this.add(FuelLabel);
	this.add(FuelBar);
	this.add(ShootButton);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		remove(CurrentTankLabel);
		CurrentTankLabel = new JLabel(terrain.getCurrentTank().getName(),JLabel.CENTER);
		CurrentTankLabel.setForeground(terrain.getCurrentTank().getColor());
		CurrentTankLabel.setBounds(45, 70, 200, 30);
		add(CurrentTankLabel);
		FuelBar.setValue(terrain.getCurrentTank().getFuel());
		repaint();
	}
	
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
		}else if(evt.getKeyCode() == KeyEvent.VK_S){ // Als je op spatie drukt wordt er geschoten
			System.out.println("S");
			ShootButton.doClick();
		}else if(evt.getKeyCode() == KeyEvent.VK_M){
			System.out.println("M");
			MenuButton.doClick();
		}else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
		terrain.getCurrentTank().update_right(1);
		terrain.repaint();
		System.out.println("Right");
			}
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ShootButton){
			if(terrain.firedShell == null&&!terrain.getCurrentTank().isComputer()){
				terrain.fireTank((int)PowerSlider.getValue(), 180-AngleSlider.getValue());
				this.CurrentTank = terrain.getCurrentTank();
				PowerSlider.setValue(CurrentTank.getPower());
				AngleSlider.setValue(180-CurrentTank.getAngle());
				repaint();
			}
		}else if(e.getSource() == MenuButton){
			mainscreen.GoToMenu();
		}else if(e.getSource() == LeftButton){
			if(terrain.getCurrentTank().getFuel()>0){
				terrain.getCurrentTank().update_left(2);
				terrain.repaint();
				FuelBar.setValue(terrain.getCurrentTank().getFuel());
			}	
		}else if(e.getSource() == RightButton){
			if(terrain.getCurrentTank().getFuel()>0){
				terrain.getCurrentTank().update_right(2);
				terrain.repaint();
				FuelBar.setValue(terrain.getCurrentTank().getFuel());
			}	
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