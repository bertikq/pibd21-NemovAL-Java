import java.io.BufferedReader;
import java.io.File;
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
    
    public boolean SaveData(String filename) throws IOException
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
        return true;
    }
    
    public boolean SaveLvl(String filename, int numLvl) throws IOException
    {
    	File file = new File(filename);
        if (file.exists())
        {
            file.delete();
        }
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
        return true;
    }
    
    public boolean LoadLvl(String filename, int numLvl) throws NumberFormatException, IOException
    {
    	File file = new File(filename);
        if (!file.exists())
        {
            return false;
        }
        terminalStages.set(numLvl, new BusTerminal<ITransport, IExtraFunc>(countPlaces, pictureWidth, pictureHeight));
        try (BufferedReader fs = new BufferedReader(new FileReader(file)))
        {
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
        return true;
    }
    
    public boolean LoadData(String filename) throws NumberFormatException, IOException
    {
    	File file = new File(filename);
        if (!file.exists())
        {
            return false;
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
                return false;
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
                String[] mas = line.split(":");
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
        return true;
    }
    
}
