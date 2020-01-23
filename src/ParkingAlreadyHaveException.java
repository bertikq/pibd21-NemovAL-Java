
public class ParkingAlreadyHaveException extends Exception 
{
	public ParkingAlreadyHaveException()
	{
		super("На парковке уже есть такая машина");
	}
}
