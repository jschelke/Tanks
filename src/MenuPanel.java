package src;

import javax.swing.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	private JButton PlayButton, QuitButton;
	private Tanks mainscreen;
	@SuppressWarnings("rawtypes")
	private JComboBox environment;//kies soort terrein
	private String[] environmentChoices = {"Plains","Desert", "Arctic"};// keuzes voor de ComboBox
	private String[] ColorChoices = {"Black","Blue","Dark Gray","Gray","Green","Magenta","Orange","Pink","Red","White","Yellow"};
	@SuppressWarnings("unused")
	private int environmentChoice = 0;
	private Slider amountPlayersSlider;
	private JLabel amountPlayersLabel;
	private int amountPlayers;
	private ArrayList<JComboBox> PlayerColorSelection= new ArrayList<JComboBox>();
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;
		this.setLayout(null);
		
		PlayerColorSelection.add(CreateJComboBoxcolor());
		PlayerColorSelection.add(CreateJComboBoxcolor());
		
		environment = new JComboBox(environmentChoices);
		environment.setSelectedIndex(0);
		environment.addActionListener(this);
		environment.setBounds(400, 10, 130, 30);
		
		amountPlayersLabel = new JLabel("Amount of Tanks",JLabel.CENTER);
		amountPlayersLabel.setBounds(350, 80, 250, 20);;
		
		amountPlayersSlider = new Slider("Amount of Players", 1, 2, 2, 8, 2, 2);
		amountPlayersSlider.setBounds(350, 100, 250, 50);

		PlayButton = new JButton("Play");
		PlayButton.addActionListener(this);
		PlayButton.setBounds(400, 250, 130, 30);


		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		QuitButton.setBounds(850, 600, 130, 30);

		this.add(environment);
		this.add(PlayButton);
		this.add(amountPlayersLabel);
		this.add(amountPlayersSlider);
		this.add(QuitButton);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int ElementAmount = PlayerColorSelection.size();
		for(int i = 0; i<PlayerColorSelection.size();i++){
//			this.add(PlayerColorSelection.get(0).setBounds(200, 200, 200, 50));
		}
	}
	public JComboBox CreateJComboBoxcolor(){
		JComboBox ComboBox= new JComboBox(ColorChoices);
		ComboBox.setSelectedIndex(0);
		ComboBox.addActionListener(this);
		return ComboBox;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PlayButton) {
			mainscreen.switchPanel(new PlayPaneel((int) amountPlayersSlider.getValue()));
		} else if (e.getSource() == environment) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection =  (String) ((JComboBox) e.getSource()).getSelectedItem();
			for(int i=0;i<environmentChoices.length;i++){
				if(environmentChoices[i]==selection){
					environmentChoice = i;
				}
			}
		}
		else if(e.getSource() == amountPlayersSlider){
			if(!amountPlayersSlider.getValueIsAdjusting()&&amountPlayers!=(int)amountPlayersSlider.getValue()){
				amountPlayers = (int)amountPlayersSlider.getValue();
				repaint();
			}
				
			
		}
		else {
			System.exit(0);
		}
	}

}