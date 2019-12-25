
public class ParkingOccupiedPlaceException extends Exception
{
	public ParkingOccupiedPlaceException(int i) {
		super("На месте " + i + " уже стоит автомобиль");
	}
}
