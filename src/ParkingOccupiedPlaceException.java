
public class ParkingOccupiedPlaceException extends IllegalArgumentException
{
	public ParkingOccupiedPlaceException(int i) {
		super("На месте " + i + " уже стоит автомобиль");
	}
}
