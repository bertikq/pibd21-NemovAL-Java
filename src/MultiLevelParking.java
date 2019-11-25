import java.util.ArrayList;

public class MultiLevelParking {
	
	ArrayList<BusTerminal<ITransport, IExtraFunc>> terminalStages;

    private final int countPlaces = 10;

    public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
    {
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
            return terminalStages.get(indTerminal).places.get(indVehicle);
        }
        return null;
    }
    
}
