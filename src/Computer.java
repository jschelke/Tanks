package src;

import java.awt.Color;

public class Computer extends Tank{

	public Computer(Color kleur, Terrain terrain, int TANKID, String name) {
		super(kleur, terrain, TANKID, name);
		System.out.println(getxcoord());
	}
	public void fire(){
		
	}
}
