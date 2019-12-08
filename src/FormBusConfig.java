import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FormBusConfig {

	private JFrame frame;
	private Color curColor;
	private ITransport curBus;
	private ITransport getBus;
	private BusPanel drawPanel;
	private IExtraFunc curExtraFunc;
    private CarDelegate eventAddCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBusConfig window = new FormBusConfig();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public FormBusConfig() {
		initialize();
	}
	
	public FormBusConfig(CarDelegate eventAddCar) {
		initialize();
		this.eventAddCar = eventAddCar;
	}
	

	private void Draw() {
		drawPanel.validate();
		drawPanel.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel BtnPanel = new JPanel();
		BtnPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		BtnPanel.setBounds(27, 53, 206, 201);
		frame.getContentPane().add(BtnPanel);
		BtnPanel.setLayout(null);
		
		JButton btnBus = new JButton("\u0410\u0432\u0442\u043E\u0431\u0443\u0441");
		btnBus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
		        getBus = new BaseBus(15, Color.BLACK, 15, Color.RED, new DrawBaseExtraFunc(), TypeDoors.Three, Color.BLACK);
		        getBus.SetPosition(drawPanel.getWidth() / 2, drawPanel.getHeight() / 2, drawPanel.getWidth(), drawPanel.getHeight());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				getBus = null;
			}
		});
		btnBus.setBounds(12, 13, 182, 74);
		BtnPanel.add(btnBus);
		
		JButton btnBusAccord = new JButton("\u0410\u0432\u0442\u043E\u0431\u0443\u0441 \u0441 \u0413\u0430\u0440\u043C\u043E\u0448\u043A\u043E\u0439");
		btnBusAccord.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				getBus = new BusWithAccord(15, Color.BLACK, 15, Color.RED, Color.GREEN, 40, 2, 50, new DrawDoorsOval(), TypeDoors.Two, Color.BLACK);
				getBus.SetPosition(drawPanel.getWidth() / 3, drawPanel.getHeight() / 2, drawPanel.getWidth(), drawPanel.getHeight());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				getBus = null;
			}
		});
		btnBusAccord.setBounds(12, 114, 182, 74);
		BtnPanel.add(btnBusAccord);
		
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventAddCar.Invoke(curBus);
				frame.dispose();
			}
		});
		btnAdd.setBounds(68, 298, 120, 46);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(68, 359, 120, 46);
		frame.getContentPane().add(btnCancel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		mainPanel.setBounds(246, 26, 312, 379);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		drawPanel = new BusPanel();
		drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (getBus != null) {
					curBus = getBus;
					drawPanel.setBus(curBus);
					Draw();
				}
			}
		});
		drawPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawPanel.setBounds(12, 27, 288, 113);
		mainPanel.add(drawPanel);
		
		JLabel mainColorLabel = new JLabel("\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
		mainColorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curColor != null && curBus != null) {
					curBus.SetMainColor(curColor);
					Draw();
				}
			}
		});
		mainColorLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainColorLabel.setBounds(12, 153, 288, 62);
		mainPanel.add(mainColorLabel);
		
		JLabel dopColorLabel = new JLabel("\u0414\u043E\u043F. \u0446\u0432\u0435\u0442");
		dopColorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curColor != null && curBus != null && curBus instanceof BusWithAccord) {
					((BusWithAccord)curBus).setAccordColor(curColor);
					Draw();
				}
			}
		});
		dopColorLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		dopColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dopColorLabel.setBounds(12, 228, 288, 62);
		mainPanel.add(dopColorLabel);
		
		JLabel labelExtraFunc = new JLabel("\u0414\u043E\u043F. \u0444\u0443\u043D\u043A\u0446\u0438\u043E\u043D\u0430\u043B");
		labelExtraFunc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curBus != null && curExtraFunc != null) {
					((BaseBus)curBus).setExtraFunc(curExtraFunc);
					Draw();
				}
			}
		});
		labelExtraFunc.setHorizontalAlignment(SwingConstants.CENTER);
		labelExtraFunc.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelExtraFunc.setBounds(12, 304, 288, 62);
		mainPanel.add(labelExtraFunc);
		
		JPanel panelColors = new JPanel();
		panelColors.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelColors.setBounds(570, 26, 156, 250);
		frame.getContentPane().add(panelColors);
		panelColors.setLayout(null);
		
		JPanel panelWhite = new JPanel();
		panelWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.WHITE;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(94, 10, 50, 50);
		panelColors.add(panelWhite);
		
		JPanel panelBlue = new JPanel();
		panelBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.BLUE;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(94, 70, 50, 50);
		panelColors.add(panelBlue);
		
		JPanel paneYellow = new JPanel();
		paneYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.YELLOW;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		paneYellow.setBackground(Color.YELLOW);
		paneYellow.setBounds(94, 130, 50, 50);
		panelColors.add(paneYellow);
		
		JPanel panelOrange = new JPanel();
		panelOrange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.ORANGE;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(94, 190, 50, 50);
		panelColors.add(panelOrange);
		
		JPanel panelBlack = new JPanel();
		panelBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.black;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(12, 10, 50, 50);
		panelColors.add(panelBlack);
		
		JPanel panelGreen = new JPanel();
		panelGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.GREEN;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(12, 70, 50, 50);
		panelColors.add(panelGreen);
		
		JPanel panelRed = new JPanel();
		panelRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.RED;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(12, 130, 50, 50);
		panelColors.add(panelRed);
		
		JPanel panelGray = new JPanel();
		panelGray.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.GRAY;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(12, 190, 50, 50);
		panelColors.add(panelGray);
		
		JPanel panelDopFunc = new JPanel();
		panelDopFunc.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDopFunc.setBounds(570, 298, 156, 131);
		frame.getContentPane().add(panelDopFunc);
		panelDopFunc.setLayout(null);
		
		JButton btnDrawBaseExtraFunc = new JButton("DrawBaseExtraFunc");
		btnDrawBaseExtraFunc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curExtraFunc = new DrawBaseExtraFunc();			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curExtraFunc = null;			}
		});
		btnDrawBaseExtraFunc.setBounds(12, 10, 132, 30);
		panelDopFunc.add(btnDrawBaseExtraFunc);
		
		JButton btnDrawDoorsHeight = new JButton("DrawDoorsHeight");
		btnDrawDoorsHeight.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curExtraFunc = new DrawDoorsHeight();			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curExtraFunc = null;			}
		});
		btnDrawDoorsHeight.setBounds(12, 50, 132, 30);
		panelDopFunc.add(btnDrawDoorsHeight);
		
		JButton btnDrawDoorsOval = new JButton("DrawDoorsOval");
		btnDrawDoorsOval.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curExtraFunc = new DrawDoorsOval();			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curExtraFunc = null;			}
		});
		btnDrawDoorsOval.setBounds(12, 90, 132, 30);
		panelDopFunc.add(btnDrawDoorsOval);
	}
}
