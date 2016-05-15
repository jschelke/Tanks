package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	private JButton PlayButton, QuitButton;
	private Tanks mainscreen;
	
	final static String IMAGE_FOLDER = "images/";
	private Image TanksBackground;

	private JComboBox environment;//kies soort terrein
	private String[] environmentChoices = {"Plains","Desert", "Arctic"};// keuzes voor de ComboBox
	private String[] ColorChoices = {"Black","Blue","Dark Gray","Gray","Green","Magenta","Orange","Pink","Red","White","Yellow"};
	private int environmentChoice = 0;
	
	private Slider amountPlayersSlider;
	private JLabel amountPlayersLabel;
	private ArrayList<TankCostumization> CostumizationList= new ArrayList<TankCostumization>();
	
	private String[] nameList;
	private Color[] colorList;
	private boolean[] computerControlledList;
	
	private Timer timer;
	

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
		amountPlayersLabel.setForeground(Color.white);
		
		amountPlayersSlider = new Slider("Amount of Players", 1, 2, 2, 6, 2, 2);
		amountPlayersSlider.setBounds(375, 100, 250, 50);
		
		TanksBackground= new ImageIcon(getClass().getResource(IMAGE_FOLDER + "TanksBackground.jpg")).getImage();

		PlayButton = new JButton("Play");
		PlayButton.setBackground(Color.ORANGE);
		PlayButton.addActionListener(this);
		PlayButton.setBounds(435, 450, 130, 30);
		
		for(int i = 0;i<amountPlayersSlider.getValue();i++){
			CostumizationList.add(new TankCostumization(i));
			CostumizationList.get(i).setBounds(160*i+10,200,150,160);
			add(CostumizationList.get(i));
		}

		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		QuitButton.setBounds(850, 600, 130, 30);

		this.add(environment);
		this.add(PlayButton);
		this.add(amountPlayersLabel);
		this.add(amountPlayersSlider);
		this.add(QuitButton);
		
		timer = new Timer();
		timer.schedule(new MyTimerTask(), 0, 1000);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(TanksBackground, 0, 0, 1000, 700, this);
		System.out.println("repainting");
		if(amountPlayersSlider.getValue()>CostumizationList.size()){
			for(int i = CostumizationList.size();i<=amountPlayersSlider.getValue();i++){
				System.out.println("first loop started i:\t" +i);
				CostumizationList.add(new TankCostumization(i));
				CostumizationList.get(i).setBounds(160*i+10,200,150,160);
				add(CostumizationList.get(i));
				repaint();
			}
		}else if(amountPlayersSlider.getValue()<CostumizationList.size()){
			for(int i = CostumizationList.size()-1;i>=amountPlayersSlider.getValue();i--){
				System.out.println("second loop started i:\t" +i);
				remove(CostumizationList.get(i));
				CostumizationList.remove(i);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PlayButton) {
			nameList = new String[CostumizationList.size()-1];
			colorList = new Color[CostumizationList.size()-1];
			computerControlledList = new boolean[CostumizationList.size()-1];
			for(int i = 0;i<CostumizationList.size()-1;i++){
				 nameList[i] = CostumizationList.get(i).getName();
				 colorList[i] = CostumizationList.get(i).getColor();
				 computerControlledList[i] = CostumizationList.get(i).getComputerControlled();
			}
			if(environmentChoice == 0)
				mainscreen.switchPanel(new PlayPaneel(Color.GREEN,nameList,colorList,computerControlledList));
			if(environmentChoice == 1)
				mainscreen.switchPanel(new PlayPaneel(Color.YELLOW,nameList,colorList,computerControlledList));
			if(environmentChoice == 2)
				mainscreen.switchPanel(new PlayPaneel(Color.WHITE,nameList,colorList,computerControlledList));
		}else if (e.getSource() == environment) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection =  (String) ((JComboBox) e.getSource()).getSelectedItem();
			repaint();
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
	private class MyTimerTask extends TimerTask {
		public MyTimerTask(){
		}
		public void run() {
			repaint();
			}
		}
	}