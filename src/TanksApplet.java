package src;

import javax.swing.JApplet;

public class TanksApplet extends JApplet {
	
	public void init(){
		Tanks hoofdpaneel = new Tanks();
		setContentPane(hoofdpaneel);
		}
}
