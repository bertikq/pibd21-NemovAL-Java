import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class BusTerminal<T extends ITransport, U extends IExtraFunc> {
	
	private Map<Integer, T> places;
    
    private int WidthWindow;
    private int HeightWindow;
    
    private int maxCount;

    private final int widthSizePlace = 200;
    private final int heightSizePlace = 50;
    
	public BusTerminal(int size, int widthWindow, int heightWindow)
    {
        places = new HashMap<Integer, T>();
        WidthWindow = widthWindow;
        HeightWindow = heightWindow;
        maxCount = size;
    }
	
	public T getPlace(int ind) throws ParkingNotFoundException {
		if (places.containsKey(ind))
        {
            return places.get(ind);
        }
		return null;
	}
	
	/**
	 * @param bus
	 * @return
	 * @throws ParkingOccupiedPlaceException 
	 */
	public void setPlace(int ind, T value) throws ParkingOccupiedPlaceException {
		if (CheckFreePlace(ind))
        {
            places.put(ind, value);
            places.get(ind).SetPosition(widthSizePlace / 2 + ind / 5 * widthSizePlace + 5 - 50, 
                ind % 5 * heightSizePlace + heightSizePlace / 2, WidthWindow, HeightWindow);
        }
        else throw new ParkingOccupiedPlaceException(ind);
	}
	
	public int Add(T bus) throws ParkingOverflowException
    {
		if (places.size() == maxCount)
		{
            throw new ParkingOverflowException();
	    }
        for (int i = 0; i < maxCount; i++)
        {
            if (CheckFreePlace(i))
            {
            	places.put(i, bus);
                bus.SetPosition(widthSizePlace / 2 + i / 5 * widthSizePlace + 5 - 50, i % 5 * heightSizePlace + heightSizePlace / 2,
                    WidthWindow, HeightWindow);
                places.replace(i, bus);
                return i;
            }
        }
        throw new ParkingOverflowException();
    }

    public int Add(T bus, U extraFunc) throws ParkingOverflowException
    {
		if (places.size() == maxCount)
		{
            throw new ParkingOverflowException();
	    }
        for (int i = 0; i < maxCount; i++)
        {
            if (CheckFreePlace(i))
            {
            	places.put(i, bus);
                bus.SetPosition(widthSizePlace / 2 + i / 5 * widthSizePlace + 5 - 50, i % 5 * heightSizePlace + heightSizePlace / 2,
                    WidthWindow, HeightWindow);
                ((BaseBus)bus).setExtraFunc(extraFunc);
                places.replace(i, bus);
                return i;
            }
        }
        throw new ParkingOverflowException();
    }
    
    public boolean BolsheRavno(BusTerminal<ITransport, IExtraFunc> term) {
    	if (places.size() >= term.places.size())
    		return true;
    	return false;
    }
    
    public boolean MensheRavno(BusTerminal<ITransport, IExtraFunc> term) {
    	if(places.size() <= term.places.size())
    		return true;
    	return false;
    }

    public T Remove(int index) throws ParkingNotFoundException
    {
    	if (index < 0 || index > maxCount)
            throw new ParkingNotFoundException(index);
        if (!CheckFreePlace(index))
        {
            T bus = places.get(index);
            places.replace(index, null);
            return bus;
        }
        throw new ParkingNotFoundException(index);
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < places.size(); i++)
        {
            if (!CheckFreePlace(i))
            {
                places.get(i).Draw(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, (maxCount / 5) * widthSizePlace, 480);
        for (int i = 0; i < maxCount / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * widthSizePlace, j * heightSizePlace,
                i * widthSizePlace + 110, j * heightSizePlace);
            }
            g.drawLine(i * widthSizePlace, 0, i * widthSizePlace, 400);
        }
    }

    private boolean CheckFreePlace(int indexPlace)
    {
        return places.get(indexPlace) == null;
    }
    
    public ITransport getTransport(int ind) {
		return places.get(ind);
    }
}
