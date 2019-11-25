import java.awt.EventQueue;
import java.awt.JobAttributes.DialogType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;

import javax.print.attribute.standard.SheetCollate;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
public class BusTerminalApp {

	private JFrame frame;
	private JTextField inputText;
	BusParkingPanel drawPanel;
	BusPanel getBusPanel;
	
	JList list;
	
	private JPanel panel;
	private JButton pakingBusAccord;
	private JButton parkingBaseBus;
	MultiLevelParking levelTerminal;
	private final int countLevel = 5;
	private HashSet<ITransport> hashSetBus = new HashSet<ITransport>();

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
		levelTerminal = new MultiLevelParking(countLevel, drawPanel.getWidth(), drawPanel.getHeight());
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(drawPanel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(pakingBusAccord);
		frame.getContentPane().add(parkingBaseBus);
		
		list = new JList();
		DefaultListModel dlm = new DefaultListModel();
		for (int i = 0; i < countLevel; i++)
			dlm.addElement("Уровень " + i);
		list.setBounds(569, 13, 309, 154);
		frame.getContentPane().add(list);
		list.setModel(dlm);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	ListBoxLevels_SelectedIndexChanged();
		        }
			}
		});
	}
	
	private void ListBoxLevels_SelectedIndexChanged()
    {
        Draw();
    }
	
	private void Draw() {
		drawPanel.setBusTerminal(levelTerminal.getBusTerminal(list.getSelectedIndex()));
		drawPanel.validate();
		drawPanel.repaint();
	}
	
	private void ParkingBaseBus() {
		if (list.getSelectedIndex() > -1)
        {
			JColorChooser colorChooser = new JColorChooser();
			Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
			BaseBus bus = new BaseBus(15, mainColor, 15, Color.RED, new DrawBaseExtraFunc(), TypeDoors.Three, Color.BLACK);
			int place = levelTerminal.getBusTerminal(list.getSelectedIndex()).Add(bus, new DrawBaseExtraFunc());
			Draw();
        }
	}
	private void ParkingBusAccord() {
		if (list.getSelectedIndex() > -1)
        {
			JColorChooser colorChooser = new JColorChooser();
			Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
			Color extraColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
			BusWithAccord bus = new BusWithAccord(15, Color.BLACK, 15, Color.RED, Color.GREEN, 40, 2, 50, new DrawDoorsOval(), TypeDoors.Two, extraColor);
			int place = levelTerminal.getBusTerminal(list.getSelectedIndex()).Add(bus, new DrawDoorsOval());
			Draw();
        }
	}
	
	private void GetCar() {
		
		if (list.getSelectedIndex() > -1)        
		{
			if (inputText.getText() != "")
	        {
	            ITransport car = levelTerminal.getBusTerminal(list.getSelectedIndex()).Remove(Integer.parseInt(inputText.getText()));
	            if (car != null)
	            {
	                car.SetPosition(getBusPanel.getWidth() / 2 - 30, getBusPanel.getHeight() / 2, getBusPanel.getWidth(),
	                		getBusPanel.getHeight());
	                getBusPanel.setBus(car);
	                hashSetBus.add(car); // HashSet
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		drawPanel = new BusParkingPanel();
		drawPanel.setBounds(0, 0, 557, 599);
		
		parkingBaseBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		parkingBaseBus.setBounds(564, 180, 314, 62);
		
		pakingBusAccord = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441\r\n \u0441 \u0433\u0430\u0440\u043C\u043E\u0448\u043A\u043E\u0439");
		pakingBusAccord.setBounds(564, 249, 314, 68);
		
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
		
		panel = new JPanel();
		panel.setBounds(564, 324, 314, 262);
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
	}
}
