package src;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PlayPaneel extends JPanel {

	public PlayPaneel(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 

		Terrain Terrain = new Terrain();
		this.add(Terrain);
		this.add(new TankPaneel());
	}
	
}
