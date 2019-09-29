import java.awt.Graphics;

import javax.swing.JPanel;

public class BusPanel extends JPanel {
	
	private Bus bus;


	public void setBus(Bus bus) {
		this.bus = bus;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bus != null)
			bus.DrawBus(g);
	}
	
}
