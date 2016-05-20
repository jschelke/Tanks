package src;

import javax.swing.*;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	private JButton PlayButton, QuitButton;
	private Tanks mainscreen;
	
	final static String IMAGE_FOLDER = "images/";
	private Image TanksBackground, PlainsBackground, DesertBackground, ArcticBackground;

	private JComboBox<String> environment;//kies soort terrein
	private String[] environmentChoices = {"Plains","Desert", "Arctic"};// keuzes voor de ComboBox
	private int environmentChoice = 0;
	
	private Slider amountPlayersSlider;
	private JLabel amountPlayersLabel;
	private ArrayList<TankCostumization> CostumizationList= new ArrayList<TankCostumization>();
	
	private String[] nameList;
	private Color[] colorList;
	private boolean[] computerControlledList;
	
	Color Sand = new Color(194, 178, 128);
	Color Hill = new Color(148,214,49);
	Color Snow = new Color(235,245,245);
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;
		this.setLayout(null);
		
		environment = new JComboBox(environmentChoices);
		environment.setSelectedIndex(0);
		environment.addActionListener(this);
		environment.setBounds(435, 10, 130, 30);
		
		amountPlayersLabel = new JLabel("Amount of Tanks",JLabel.CENTER);
		amountPlayersLabel.setBounds(375, 80, 250, 20);
		amountPlayersLabel.setForeground(Color.WHITE);
		
		amountPlayersSlider = new Slider("Amount of Players", 1, 2, 2, 6, 2, 2);
		amountPlayersSlider.setBounds(375, 100, 250, 50);
		amountPlayersSlider.setOpaque(false);
		
		TanksBackground= new ImageIcon(getClass().getResource(IMAGE_FOLDER + "TanksBackground.jpg")).getImage();
		PlainsBackground= new ImageIcon(getClass().getResource(IMAGE_FOLDER + "Plains.jpg")).getImage();
		DesertBackground= new ImageIcon(getClass().getResource(IMAGE_FOLDER + "Desert.jpg")).getImage();
		ArcticBackground= new ImageIcon(getClass().getResource(IMAGE_FOLDER + "Arctic.jpg")).getImage();
		
		PlayButton = new JButton("Play");
		PlayButton.setForeground(Color.ORANGE);
		PlayButton.addActionListener(this);
		PlayButton.setBounds(435, 450, 130, 30);
		PlayButton.setContentAreaFilled(false);
		PlayButton.setOpaque(false);
		
		for(int i = 0;i<amountPlayersSlider.getValue();i++){
			CostumizationList.add(new TankCostumization(i));
			CostumizationList.get(i).setBounds(160*i+10,200,150,160);
			add(CostumizationList.get(i));
		}
//		System.out.println(CostumizationList.size());

		QuitButton = new JButton("Quit");
		QuitButton.setForeground(Color.RED);
		QuitButton.addActionListener(this);
		QuitButton.setBounds(850, 600, 130, 30);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setOpaque(false);

		this.add(environment);
		this.add(PlayButton);
		this.add(amountPlayersLabel);
		this.add(amountPlayersSlider);
		this.add(QuitButton);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(amountPlayersSlider.getValue()>CostumizationList.size()){
			for(int i = CostumizationList.size();i<=amountPlayersSlider.getValue();i++){
//				System.out.println("first loop started i:\t" +i);
				CostumizationList.add(new TankCostumization(i));
				CostumizationList.get(i).setBounds(160*i+10,200,150,160);
				add(CostumizationList.get(i));
				repaint();
			}
		}else if(amountPlayersSlider.getValue()<CostumizationList.size()){
			for(int i = CostumizationList.size()-1;i>=amountPlayersSlider.getValue();i--){
//				System.out.println("second loop started i:\t" +i);
				remove(CostumizationList.get(i));
				CostumizationList.remove(i);
			}
		}
		g.drawImage(TanksBackground, 0, 0, 1000, 700, this);
		repaint();
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PlayButton) {
			nameList = new String[CostumizationList.size()];
			colorList = new Color[CostumizationList.size()];
			computerControlledList = new boolean[CostumizationList.size()];
			for(int i = 0;i<CostumizationList.size();i++){
				nameList[i] = CostumizationList.get(i).getName();
				colorList[i] = CostumizationList.get(i).getColor();
				computerControlledList[i] = CostumizationList.get(i).getComputerControlled();
			}
			if(environmentChoice == 0)
				mainscreen.switchPanel(new PlayPaneel(mainscreen, Hill,nameList,colorList,computerControlledList, PlainsBackground));
			if(environmentChoice == 1)
				mainscreen.switchPanel(new PlayPaneel(mainscreen, Sand,nameList,colorList,computerControlledList, DesertBackground));
			if(environmentChoice == 2)
				mainscreen.switchPanel(new PlayPaneel(mainscreen, Snow,nameList,colorList,computerControlledList, ArcticBackground));
		}else if (e.getSource() == environment) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection =  (String) ((JComboBox) e.getSource()).getSelectedItem();
			for(int i=0;i<environmentChoices.length;i++){
				if(environmentChoices[i]==selection){
					environmentChoice = i;
				}
			}
		}
		else if(e.getSource() == amountPlayersSlider){
			repaint();
			}
		else {
			System.exit(0);
		}
	}
}
