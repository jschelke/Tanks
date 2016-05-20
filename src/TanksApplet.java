package src;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class TanksApplet extends JApplet {
	
	public void init(){
		Tanks hoofdpaneel = new Tanks();
		setContentPane(hoofdpaneel);
		}
}
