package src;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JSlider;


@SuppressWarnings("serial")
public class Slider extends JPanel implements MouseListener{
	JSlider slider;
	
	public Slider(String Name, int Minor, int Major, int Min, int Max, int Begin, int Between){
		super(true);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		slider = new JSlider(JSlider.HORIZONTAL, Min, Max, Begin);
		slider.addMouseListener(this);
		
		slider.setMinorTickSpacing(Minor);
		slider.setMajorTickSpacing(Major);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(Between));
		
		add(slider);
	}
	public int getValue(){
		return (int) slider.getValue();
	}
	public void setValue(int Value){
		slider.setValue(Value);
	}
	public boolean getValueIsAdjusting() {
		return slider.getValueIsAdjusting();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
