import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Panel;

public class JWindow {

	private JFrame frmBus;
	Bus bus;
	BusPanel busPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JWindow window = new JWindow();
					window.frmBus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBus = new JFrame();
		frmBus.setTitle("Bus");
		frmBus.setBounds(100, 100, 546, 436);
		frmBus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBus.getContentPane().setLayout(null);
		
		JButton buttonCreateBus = new JButton("Create Bus\r\n");
		buttonCreateBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBus();
			}
		});
		buttonCreateBus.setBounds(44, 40, 111, 41);
		frmBus.getContentPane().add(buttonCreateBus);
		
		JButton btnRight = new JButton("btnRight");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Move(btnRight);
			}
		});
		btnRight.setMargin(new Insets(2, 30, 2, 14));
		btnRight.setIcon(new ImageIcon("C:\\Users\\berti\\Downloads\\arrow.png"));
		btnRight.setBounds(466, 326, 50, 50);
		frmBus.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("btnDown");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Move(btnDown);
			}
		});
		btnDown.setMargin(new Insets(2, 30, 2, 14));
		btnDown.setIcon(new ImageIcon("C:\\Users\\berti\\Downloads\\cropped-2925-illustration-of-a-bright-green-down-arrow-pv.png"));
		btnDown.setBounds(414, 326, 50, 50);
		frmBus.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("btnLeft");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Move(btnLeft);
			}
		});
		btnLeft.setMargin(new Insets(0, 25, 2, 14));
		btnLeft.setIcon(new ImageIcon("C:\\Users\\berti\\Downloads\\pfeillinks.png"));
		btnLeft.setBounds(360, 326, 50, 50);
		frmBus.getContentPane().add(btnLeft);
		
		JButton btnUp = new JButton("btnUp");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Move(btnUp);
			}
		});
		btnUp.setMargin(new Insets(2, 30, 2, 14));
		btnUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUp.setIcon(new ImageIcon("C:\\Users\\berti\\Downloads\\27849.png"));
		btnUp.setBounds(414, 270, 50, 50);
		frmBus.getContentPane().add(btnUp);
		
				busPanel = new BusPanel();
				busPanel.setBounds(new Rectangle(0, 0, 528, 389));
				frmBus.getContentPane().add(busPanel);
				busPanel.setLayout(null);
		
	}
	
	private void Move(JButton button) {
		try {
			switch (button.getText())
	        {
	            case "btnRight":
	                bus.Move(Direction.Right);
	                break;
	            case "btnLeft":
	                bus.Move(Direction.Left);
	                break;
	            case "btnUp":
	                bus.Move(Direction.Up);
	                break;
	            case "btnDown":
	                bus.Move(Direction.Down);
	                break;
	        }
	        Draw();
		}
		catch (Exception e) {
		}
	}
	
	private void Draw() {
		busPanel.validate();
		busPanel.repaint();
	}
	
	private void CreateBus() {
		bus = new Bus(busPanel.getWidth() / 2, busPanel.getHeight() / 2,Color.BLACK, Color.GREEN, 15, TypeDoors.One, Color.RED);
		bus.SetSizeWindow(busPanel.getWidth(), busPanel.getHeight());
		busPanel.setBus(bus);
	}
}
