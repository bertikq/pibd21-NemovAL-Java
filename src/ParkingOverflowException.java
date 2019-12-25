
public class ParkingOverflowException extends IndexOutOfBoundsException 
{
	public ParkingOverflowException() {
		super("На парковке нет свободных мест");
	}
}
