import java.awt.Graphics;

import javax.swing.JPanel;

public class BusParkingPanel extends JPanel {
	
	BusTerminal<ITransport, IExtraFunc> busTerminal;
	
	public void setBusTerminal(BusTerminal<ITransport, IExtraFunc> busTerminal) {
		this.busTerminal = busTerminal;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (busTerminal != null)
		busTerminal.Draw(g);
	}

}
