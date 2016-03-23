package src;

import java.util.ArrayList;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TankPaneel extends JPanel{
	public static final int width = 1500;
	public static final int heigth = 1000;
	private static final long serialVersionUID = 1L;
	ArrayList<Terrain> terrain  = new ArrayList<Terrain>();
	
	public TankPaneel() {
		terrain.add(new Terrain(1000,1000));
		repaint();
	}
	public void paint(Graphics g){
		terrain.get(0).drawme(g);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(width, heigth);

		f.add(new TankPaneel());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
