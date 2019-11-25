import java.awt.EventQueue;
import java.awt.JobAttributes.DialogType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.print.attribute.standard.SheetCollate;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
public class BusTerminalApp {

	private JFrame frame;
	private JTextField inputText;
	BusParkingPanel drawPanel;
	BusPanel getBusPanel;
	
	public BusTerminal<ITransport, IExtraFunc> busTerminal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusTerminalApp window = new BusTerminalApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BusTerminalApp() {
		initialize();
		busTerminal = new BusTerminal<ITransport, IExtraFunc>(10, drawPanel.getWidth(), drawPanel.getHeight());
		drawPanel.setBusTerminal(busTerminal);
	}
	
	private void Draw() {
		drawPanel.validate();
		drawPanel.repaint();
	}
	
	private void ParkingBaseBus() {
		JColorChooser colorChooser = new JColorChooser();
		Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
		BaseBus bus = new BaseBus(15, mainColor, 15, Color.RED, new DrawBaseExtraFunc(), TypeDoors.Three, Color.BLACK);
		busTerminal.Add(bus, new DrawDoorsOval());
		Draw();
	}
	private void ParkingBusAccord() {
		JColorChooser colorChooser = new JColorChooser();
		Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
		Color extraColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
		BusWithAccord bus = new BusWithAccord(15, Color.BLACK, 15, Color.RED, Color.GREEN, 40, 2, 50, new DrawDoorsOval(), TypeDoors.Two, extraColor);
		busTerminal.Add(bus, new DrawBaseExtraFunc());
		Draw();
	}
	
	private void GetCar() {
		if (inputText.getText() != "")
        {
            ITransport car = busTerminal.Remove(Integer.parseInt(inputText.getText()));
            if (car != null)
            {
                car.SetPosition(getBusPanel.getWidth() / 2 - 30, getBusPanel.getHeight() / 2, getBusPanel.getWidth(),
                		getBusPanel.getHeight());
                getBusPanel.setBus(car);
            }
            else
            {
            	getBusPanel.setBus(null);
            }
            getBusPanel.validate();
            getBusPanel.repaint();
            Draw();
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		drawPanel = new BusParkingPanel();
		
		JButton parkingBaseBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		
		JButton pakingBusAccord = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441\r\n \u0441 \u0433\u0430\u0440\u043C\u043E\u0448\u043A\u043E\u0439");
		
		parkingBaseBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ParkingBaseBus();
			}
		});
		
		pakingBusAccord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ParkingBusAccord();
			}
		});
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(drawPanel, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
						.addComponent(parkingBaseBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
						.addComponent(pakingBusAccord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(drawPanel, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(parkingBaseBus, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pakingBusAccord, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("\u041C\u0435\u0441\u0442\u043E :");
		textPane.setBounds(43, 33, 66, 22);
		panel.add(textPane);
		
		inputText = new JTextField();
		inputText.setBounds(121, 33, 81, 22);
		panel.add(inputText);
		inputText.setColumns(10);
		
		getBusPanel = new BusPanel();
		getBusPanel.setBounds(12, 122, 290, 127);
		panel.add(getBusPanel);
		
		JButton getBus = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		getBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GetCar();
			}
		});
		getBus.setBounds(106, 68, 117, 25);
		panel.add(getBus);
		frame.getContentPane().setLayout(groupLayout);
	}
}
