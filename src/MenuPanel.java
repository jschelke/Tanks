package src;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	private JButton PlayButton, QuitButton;
	private Tanks mainscreen;
	@SuppressWarnings("rawtypes")
	private JComboBox environment;//kies kleur terrein
	private String[] environmentChoices = {"Plains","Desert", "Arctic"};// keuzes voor de ComboBox
	@SuppressWarnings("unused")
	private int environmentChoice = 0;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPanel(Tanks mainscreen){	
		this.mainscreen = mainscreen;

		this.setLayout(null);

		PlayButton = new JButton("Play");
		PlayButton.addActionListener(this);
		PlayButton.setBounds(400, 250, 130, 30);


		QuitButton = new JButton("Quit");
		QuitButton.addActionListener(this);
		QuitButton.setBounds(850, 600, 130, 30);
		
		environment = new JComboBox(environmentChoices);
		environment.setSelectedIndex(0);
		environment.addActionListener(this);
		environment.setBounds(400, 10, 130, 30);

		this.add(PlayButton);
		this.add(QuitButton);
		this.add(environment);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(Images.Tanksbackground, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PlayButton) {
			mainscreen.switchPanel(new PlayPaneel());
		} else if (e.getSource() == environment) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection =  (String) ((JComboBox) e.getSource()).getSelectedItem();
			for(int i=0;i<environmentChoices.length;i++){
				if(environmentChoices[i]==selection){
					environmentChoice = i;
				}
			}
		} else {
			System.exit(0);
		}
	}

}