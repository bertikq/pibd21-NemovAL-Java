import java.awt.Graphics;

import javax.swing.JPanel;

public class BusPanel extends JPanel {
	
	private ITransport bus;


	public void setBus(ITransport bus) {
		this.bus = bus;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bus != null)
			bus.Draw(g);
	}
	
}
