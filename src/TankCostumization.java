package src;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TankCostumization extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2303426341972587919L;
	private int tankID;
	private String[] ColorChoices = {"Blue","Green","Magenta","Orange","Pink","Red","White","Yellow"};
	private JComboBox<String> ColorChoice;
	private JLabel ColorLabel,NameLabel,IDLabel,ComputerLabel;
	private JTextField NameField;
	private JCheckBox ComputerControlled;
	
	private Color color = Color.BLACK;
	
	public TankCostumization(int tankID) {
		this.tankID = tankID;
		this.setLayout(null);
		this.setOpaque(false);
		
		IDLabel = new JLabel("Tank " + (tankID+1),JLabel.CENTER);
		IDLabel.setBounds(0,0,150,20);
		IDLabel.setForeground(Color.WHITE);
		
		NameLabel = new JLabel("Choose name:");
		NameLabel.setBounds(0,20,150,20);
		NameLabel.setForeground(Color.WHITE);
		
		
		NameField = new JTextField(150);
		NameField.setBounds(0, 40, 150, 20);
		NameField.setText("Player "+(tankID+1));
		
		
		ColorLabel = new JLabel("Choose color:");
		ColorLabel.setBounds(0,60,150,20);
		ColorLabel.setForeground(Color.WHITE);
		
		
		ColorChoice = new JComboBox<String>(ColorChoices);
		ColorChoice.setBounds(0, 80, 150, 30);
		
		ComputerLabel = new JLabel("Computer controlled:");
		ComputerLabel.setBounds(0,110,150,20);
		ComputerLabel.setForeground(Color.WHITE);
		
		ComputerControlled = new JCheckBox();
		ComputerControlled.setBounds(0, 130, 30, 30);
		ComputerControlled.setOpaque(false);
		
		add(IDLabel);
		add(NameLabel);
		add(NameField);
		add(ColorLabel);
		add(ColorChoice);
		add(ComputerLabel);
		add(ComputerControlled);
	}
	public int getID(){
		return tankID;
	}
	public Color getColor(){
		ComboBoxColor((String) ColorChoice.getSelectedItem());
		return color;
	}
	public String getName(){
		return NameField.getText();
	}
	public boolean getComputerControlled(){
		return ComputerControlled.isSelected();
	}
	public void ComboBoxColor(String Choice){
		if("Blue"==Choice)
			color = Color.BLUE;
		if("Green"==Choice)	
			color = Color.GREEN;
		if("Magenta"==Choice)
			color = Color.MAGENTA;
		if("Orange"==Choice)
			color = Color.ORANGE;
		if("Pink"==Choice)
			color = Color.PINK;
		if("Red"==Choice)
			color = Color.RED;
		if("White"==Choice)
			color = Color.WHITE;
		if("Yellow"==Choice)
			color = Color.YELLOW;
	}

}
