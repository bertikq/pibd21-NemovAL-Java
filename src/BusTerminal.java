import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;

public class BusTerminal<T extends ITransport, U extends IExtraFunc> {
	private T[] places;
    
    private int WidthWindow;
    private int HeightWindow;

    private final int widthSizePlace = 200;
    private final int heightSizePlace = 50;
    
	public BusTerminal(int size, int widthWindow, int heightWindow)
    {
        places = (T[])new ITransport[size];
        WidthWindow = widthWindow;
        HeightWindow = heightWindow;
        for (int i = 0; i < places.length; i++) {
            places[i] = null;
        }
    }

    public int Add(T bus, U extraFunc)
    {
        for (int i = 0; i < places.length; i++)
        {
            if (CheckFreePlace(i))
            {
                bus.SetPosition(widthSizePlace / 2 + i / 5 * widthSizePlace + 5 - 50, i % 5 * heightSizePlace + heightSizePlace / 2,
                    WidthWindow, HeightWindow);
                ((BaseBus)bus).setExtraFunc(extraFunc);
                places[i] = bus;
                return i;
            }
        }
        return -1;
    }
    
    public boolean BolsheRavno(BusTerminal<ITransport, IExtraFunc> term) {
    	if (places.length >= term.places.length)
    		return true;
    	return false;
    }
    
    public boolean MensheRavno(BusTerminal<ITransport, IExtraFunc> term) {
    	if(places.length <= term.places.length)
    		return true;
    	return false;
    }

    public T Remove(int index)
    {
        if (index < 0 || index > places.length)
            return null;
        if (!CheckFreePlace(index))
        {
            T bus = places[index];
            places[index] = null;
            return bus;
        }
        return null;
    }
    
    //create two methods

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < places.length; i++)
        {
            if (!CheckFreePlace(i))
            {
                places[i].Draw(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, (places.length / 5) * widthSizePlace, 480);
        for (int i = 0; i < places.length / 5; i++)
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
        return places[indexPlace] == null;
    } 
}
