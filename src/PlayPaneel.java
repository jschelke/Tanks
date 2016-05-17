package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayPaneel extends JPanel {
	Tanks mainscreen;
	

	public PlayPaneel(Color terrainColor,String[] nameList,Color[] colorList,boolean[] computerControlledList, Image TerrainBackground){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Terrain Terrain = new Terrain(mainscreen, terrainColor,nameList,colorList,computerControlledList, TerrainBackground);
		Terrain.setMaximumSize(new Dimension(700,700));
		Terrain.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(Terrain);
		
		TankPaneel TankPaneel = new TankPaneel(Terrain, mainscreen);
		TankPaneel.setMaximumSize(new Dimension(300,700));
		TankPaneel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(TankPaneel);
	}
	
}
