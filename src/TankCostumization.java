package src;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TankCostumization extends JPanel{
	private int tankID;
	private String[] ColorChoices = {"Black","Blue","Dark Gray","Gray","Green","Magenta","Orange","Pink","Red","White","Yellow"};
	private JComboBox<String> ColorChoice;
	private JLabel ColorLabel,NameLabel,IDLabel,ComputerLabel;
	private JTextField NameField;
	private JCheckBox ComputerControlled;
	
	private String Name;
	private Color color;
	
	public TankCostumization(int tankID) {
		this.tankID = tankID;
		this.setLayout(null);
		this.setBackground(null);
		
		IDLabel = new JLabel("Tank " + (tankID+1),JLabel.CENTER);
		IDLabel.setBounds(0,0,150,20);
		
		NameLabel = new JLabel("Choose name:");
		NameLabel.setBounds(0,20,150,20);
		
		
		NameField = new JTextField(150);
		NameField.setBounds(0, 40, 150, 20);
		
		
		ColorLabel = new JLabel("Choose color:");
		ColorLabel.setBounds(0,60,150,20);
		
		
		ColorChoice = new JComboBox<String>(ColorChoices);
		ColorChoice.setBounds(0, 80, 150, 30);
		//ColorChoice.setVisible(true);
		
		ComputerLabel = new JLabel("Computer controlled:");
		ComputerLabel.setBounds(0,110,150,20);
		
		ComputerControlled = new JCheckBox();
		ComputerControlled.setBounds(0, 130, 30, 30);
		
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
		return color;
	}
	public String getName(){
		return Name;
	}
	public boolean getComputerControlled(){
		return ComputerControlled.isSelected();
	}
	public void ComboBoxColor(String Choice){
		switch(Choice){
		case "Black":
			color = Color.BLACK;
		case "Blue":
			color = Color.BLUE;
		case "Dark Gray":
			color = Color.DARK_GRAY;
		case "Gray":
			color = Color.GRAY;
		case "Green":
			color = Color.GREEN;
		case "Magenta":
			color = Color.MAGENTA;
		case "Orange":
			color = Color.ORANGE;
		case "Pink":
			color = Color.PINK;
		case "Red":
			color = Color.RED;
		case "White":
			color = Color.WHITE;
		case "Yellow":
			color = Color.GREEN;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == NameField){
			Name = NameField.getText();
		}
		else if(e.getSource() == ColorChoice){
			String Choice = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
			ComboBoxColor(Choice);
		}
	}

}
