import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JOptionPane; 
import java.awt.JobAttributes.DialogType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.DisplayMode;

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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
public class BusTerminalApp {

	private JFrame frame;
	private JTextField inputText;
	BusParkingPanel drawPanel;
	BusPanel getBusPanel;
	
	JList list;
	
	private JPanel panel;
	private JButton createBus;
	MultiLevelParking levelTerminal;
	private final int countLevel = 5;
	private HashSet<ITransport> hashSetBus = new HashSet<ITransport>();
	private JMenuBar menuBar;
	private JMenu menuSelect;
	private JMenuItem btnSave;
	private JMenuItem btnLoad;
	private JMenuItem btnSaveLvl;
	private JMenuItem btnLoadLvl;
	private Logger loggerInfo;
	private Logger loggerError;

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
		loggerInfo = Logger.getLogger("Info");
		loggerError = Logger.getLogger("Errors");
		try {
			FileHandler fhInfo = new FileHandler("infoLogs.txt");
			FileHandler fhError = new FileHandler("errorLogs.txt");
			loggerInfo.addHandler(fhInfo);
			loggerError.addHandler(fhError);
			loggerInfo.setUseParentHandlers(false);
			loggerError.setUseParentHandlers(false);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fhInfo.setFormatter(simpleFormatter);//on up
			fhError.setFormatter(simpleFormatter);//on up
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		levelTerminal = new MultiLevelParking(countLevel, drawPanel.getWidth(), drawPanel.getHeight());
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(drawPanel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(createBus);
		
		list = new JList();
		DefaultListModel dlm = new DefaultListModel();
		for (int i = 0; i < countLevel; i++)
			dlm.addElement("Уровень " + i);
		list.setBounds(569, 13, 309, 154);
		frame.getContentPane().add(list);
		list.setModel(dlm);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuSelect = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menuSelect);
		
		btnSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveToolStripMenuItem_Click();
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnSave);
		
		btnLoad = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C");
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoadToolStripMenuItem_Click();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnLoad);
		
		btnSaveLvl = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		btnSaveLvl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveLvl();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnSaveLvl);
		
		btnLoadLvl = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		btnLoadLvl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoadLvl();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnLoadLvl);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	ListBoxLevels_SelectedIndexChanged();
		        }
			}
		});
	}
	
	private void SaveToolStripMenuItem_Click() throws HeadlessException, IOException
    {
        try {
            FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
            fileDialog.setVisible(true);
            levelTerminal.SaveData(fileDialog.getDirectory() + fileDialog.getFile());
			loggerInfo.info("Сохранено в файл " + fileDialog.getName());
			JOptionPane.showMessageDialog(null,"Сохранение прошло успешно");
        }
        catch (Exception e) {
			loggerError.warning(e.getMessage());
			JOptionPane.showMessageDialog(null,"Не сохранилось");
		}
    }
	
	private void SaveLvl() throws HeadlessException, IOException {
		try {
			FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
			fileDialog.setVisible(true);
			levelTerminal.SaveLvl(fileDialog.getDirectory() + fileDialog.getFile(), list.getSelectedIndex());
			loggerInfo.info("Сохранено в файл " + fileDialog.getName());
			JOptionPane.showMessageDialog(null,"Сохранение прошло успешно");
		}
		catch (Exception e) {
			loggerError.warning(e.getMessage());
			JOptionPane.showMessageDialog(null,"Не сохранилось");
		}
	}
	
	private void LoadLvl() throws HeadlessException, IOException {
        try {
    		FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.LOAD);
            fileDialog.setVisible(true);
            levelTerminal.LoadLvl(fileDialog.getDirectory() + fileDialog.getFile(), list.getSelectedIndex());
			JOptionPane.showMessageDialog(null,"Загрузили");
			loggerInfo.info("Загружено из файла" + fileDialog.getName());
            Draw();
        }
        catch (ParkingOccupiedPlaceException ex) {
        	loggerError.warning(ex.getMessage().toString());
			JOptionPane.showMessageDialog(null,"Занятое место");
        }
        catch (Exception ex) {
        	loggerError.warning(ex.getMessage());
			JOptionPane.showMessageDialog(null,"Неизвестная ошибка при сохранении");
		}
	}
	
	private void LoadToolStripMenuItem_Click() throws NumberFormatException, HeadlessException, IOException
    {
        try {
            FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.LOAD);
            fileDialog.setVisible(true);
        	levelTerminal.LoadData(fileDialog.getDirectory() + fileDialog.getFile());
			JOptionPane.showMessageDialog(null,"Загрузили");
			loggerInfo.info("Загружено из файла" + fileDialog.getName());
            Draw();
        }
        catch (ParkingOccupiedPlaceException ex) {
        	loggerError.warning(ex.getMessage().toString());
			JOptionPane.showMessageDialog(null,"Занятое место");
        }
        catch (Exception ex) {
        	loggerError.warning(ex.getMessage());
			JOptionPane.showMessageDialog(null,"Неизвестная ошибка при сохранении");
		}
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
	
	private void GetCar() {
		if (list.getSelectedIndex() > -1)        
		{
			if (inputText.getText() != "")
	        {
				try 
				{
		            ITransport car = levelTerminal.getBusTerminal(list.getSelectedIndex()).Remove(Integer.parseInt(inputText.getText()));
	                car.SetPosition(getBusPanel.getWidth() / 2 - 30, getBusPanel.getHeight() / 2, getBusPanel.getWidth(),
	                		getBusPanel.getHeight());
	                getBusPanel.setBus(car);
	                hashSetBus.add(car);
		            getBusPanel.validate();
		            getBusPanel.repaint();
		            Draw();
				}
				catch (ParkingNotFoundException ex)
                {
	            	getBusPanel.setBus(null);
		            getBusPanel.validate();
		            getBusPanel.repaint();
		            Draw();
        			JOptionPane.showMessageDialog(null,"Не найдено");
                	loggerError.warning(ex.getMessage().toString());
                }
                catch (Exception ex)
                {
        			JOptionPane.showMessageDialog(null,"Не известная ошибка");
                	loggerError.warning(ex.getMessage().toString());
                }
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
		
		createBus = new JButton("\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C");
		createBus.setBounds(564, 249, 314, 62);
		
		createBus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FormBusConfig config = new FormBusConfig(new CarDelegate() {
					@Override
					public void Invoke(ITransport transport) {
						if (transport != null && list.getSelectedIndex() > -1) {
							try {
								int place = levelTerminal.getBusTerminal(list.getSelectedIndex()).Add(transport);
								loggerInfo.info("Добавлен автомобиль " + transport.toString() + " на место " + place);
								Draw();
							}
							catch (ParkingOverflowException ex)
			                 {
			                     loggerError.warning(ex.getMessage());
			                     JOptionPane.showMessageDialog(null,"Переполнение");
			                 }
			                 catch (Exception ex)
			                 {
			                     loggerError.warning(ex.getMessage());
			                     JOptionPane.showMessageDialog(null,"Неизвестная ошибка");
			                 }
						}
					}
				});
				config.getFrame().setVisible(true);
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
