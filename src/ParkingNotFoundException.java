
public class ParkingNotFoundException extends NullPointerException
{
	public ParkingNotFoundException(int i) 
	{
		super("Не найден автомобиль по месту " + i);
	}
}
