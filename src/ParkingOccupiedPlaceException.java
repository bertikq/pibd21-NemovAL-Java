
public class ParkingOccupiedPlaceException extends Exception
{
	public ParkingOccupiedPlaceException(int i) {
		super("�� ����� " + i + " ��� ����� ����������");
	}
}
