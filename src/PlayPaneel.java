package src;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayPaneel extends JPanel {
	

	public PlayPaneel(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Terrain Terrain = new Terrain();
		Terrain.setMaximumSize(new Dimension(700,700));
		Terrain.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(Terrain);
		TankPaneel TankPaneel = new TankPaneel();
		TankPaneel.setMaximumSize(new Dimension(300,700));
		TankPaneel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(TankPaneel);
	}
	
}
