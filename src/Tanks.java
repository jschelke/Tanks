package src;

import java.awt.*;
import javax.swing.*;

public class Tanks extends JPanel {
	final static int SCHERM_BREEDTE = 1000, SCHERM_HOOGTE = 700;

	/**
	 * De applicatie
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Tanks");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setSize(SCHERM_BREEDTE, SCHERM_HOOGTE);

		// toont window in het midden van het scherm
		Dimension frameSize = window.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // geeft
																			// grootte
																			// van
																			// het
																			// huidige
																			// scherm!!!
		window.setLocation(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2);

		Tanks hoofdpaneel = new Tanks();
		window.setContentPane(hoofdpaneel);

		window.setVisible(true); // hier wordt de applicatie effectief gestart

	}

	private JPanel activePanel; // dit is het paneel dat getoond wordt

	public Tanks() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // is nodig zodat
															// paneel heel de
															// ruimte bedekt

		activePanel = new MenuPanel(this); // het eerste paneel dat getoond
											// wordt
		add(activePanel);
		validate(); // het plaatsen van de componenten (de layout) wordt
					// berekend
		}

	public void switchPanel(JPanel toActivate) {
		remove(activePanel);
		activePanel = toActivate;
		add(activePanel);

		validate();
		repaint(); // we laten het geheel hertekenen
	}

	public void switchPanel() {
		switchPanel(new MenuPanel(this)); // terug naar het begin
	}

}
