import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MultiLevelParking {
	
	ArrayList<BusTerminal<ITransport, IExtraFunc>> terminalStages;

	private int pictureWidth;
    private int pictureHeight;
	
    private final int countPlaces = 10;

    public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
    {
    	this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        terminalStages = new ArrayList<BusTerminal<ITransport,IExtraFunc>>();
        for (int i = 0; i < countStages; i++)
        {
            terminalStages.add(new BusTerminal<ITransport, IExtraFunc>(countPlaces, pictureWidth, pictureHeight));
        }
    }
    
    public BusTerminal<ITransport, IExtraFunc> getBusTerminal(int ind)
    {
    	if (ind > -1 && ind < terminalStages.size())
        {
            return terminalStages.get(ind);
        }
        return null;
    }
    
    public ITransport getBus(int indTerminal, int indVehicle){
    	
    	if (indTerminal > -1 && indTerminal < terminalStages.size())
        {
			return terminalStages.get(indTerminal).getTransport(indVehicle);
        }
        return null;
        
    }
    
    public void SaveData(String filename) throws IOException, ParkingNotFoundException
    {
    	File file = new File(filename);
        if (file.exists())
        {
            file.delete();
        }
        try (FileWriter fs = new FileWriter(filename, false)) {
		    fs.write("CountLeveles:" + terminalStages.size());
		    fs.write("\n");
		    for (BusTerminal<ITransport, IExtraFunc> level : terminalStages) {
		        fs.write("Level\n");
		        for (int i = 0; i < countPlaces; i++)
		        {
		            ITransport bus = level.getPlace(i);
		            if (bus != null)
		            {
		                if (bus.getClass().getName() == "BaseBus")
		                {
		                    fs.write(i + ":BaseBus:");
		                }
		                if (bus.getClass().getName() == "BusWithAccord")
		                {
		                    fs.write(i + ":BusWithAccord:");
		                }
		                fs.write(bus.toString() + "\n");   
		            }
		        }
		    }
        }
    }
    
    public void SaveLvl(String filename, int numLvl) throws Exception
    {
        try (FileWriter fs = new FileWriter(filename, false)) {
        	BusTerminal<ITransport, IExtraFunc> level = terminalStages.get(numLvl);
        	 for (int i = 0; i < countPlaces; i++)
		        {
		            ITransport bus = level.getPlace(i);
		            if (bus != null)
		            {
		                if (bus.getClass().getName() == "BaseBus")
		                {
		                    fs.write(i + ":BaseBus:");
		                }
		                if (bus.getClass().getName() == "BusWithAccord")
		                {
		                    fs.write(i + ":BusWithAccord:");
		                }
		                fs.write(bus.toString() + "\n");   
		            }
		        }
        }
        catch (Exception ex) {
			throw ex;
		}
    }
    
    public void LoadLvl(String filename, int numLvl) throws Exception
    {
    	File file = new File(filename);
        if (!file.exists())
        {
            throw new FileNotFoundException();
        }
        try (BufferedReader fs = new BufferedReader(new FileReader(file)))
        {
            terminalStages.set(numLvl, new BusTerminal<ITransport, IExtraFunc>(countPlaces, pictureWidth, pictureHeight));
        	String line = "";
            ITransport bus = null;
        	while ((line = fs.readLine()) != null) {
        		if (line == null || line == "")
		        {
		            continue;
		        }
	            String[] mas = line.split(":");
	            if (line.split(":")[1].contains("BaseBus"))
	            {
	                bus = new BaseBus(line.split(":")[2]);
	            }
	            else if (line.split(":")[1].contains("BusWithAccord"))
	            {
	                bus = new BusWithAccord(line.split(":")[2]);
	            }
	            terminalStages.get(numLvl).setPlace(Integer.parseInt(line.split(":")[0]), bus);
	        	}
        }
        catch (ParkingOccupiedPlaceException ex) {
			throw ex;
		} 
        catch (Exception ex) {
			throw ex;
		}
    }
    
    public void LoadData(String filename) throws Exception
    {
    	File file = new File(filename);
        if (!file.exists())
        {
            throw new FileNotFoundException();
        }
        try (BufferedReader fs = new BufferedReader(new FileReader(file)))
        {
            String line = fs.readLine();
            if (line.contains("CountLeveles"))
            {
                int count = Integer.parseInt(line.split(":")[1]);
                if (terminalStages != null)
                {
                    terminalStages.clear();
                }
                terminalStages = new ArrayList<BusTerminal<ITransport, IExtraFunc>>(count);
            }
            else
            {
                throw new Exception("Неверный формат файла");
            }
            int counter = -1;
            ITransport bus = null;
            while ((line = fs.readLine()) != null)
            {
                if (line.contains("Level"))
                {
                    counter++;
                    terminalStages.add(new BusTerminal<ITransport, IExtraFunc>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }
                if (line == null || line == "")
                {
                    continue;
                }
                if (line.split(":")[1].contains("BaseBus"))
                {
                    bus = new BaseBus(line.split(":")[2]);
                }
                else if (line.split(":")[1].contains("BusWithAccord"))
                {
                    bus = new BusWithAccord(line.split(":")[2]);
                }
                terminalStages.get(counter).setPlace(Integer.parseInt(line.split(":")[0]), bus);
            }
        }
    }
    
}
