
public class ParkingOccupiedPlaceException extends IllegalArgumentException
{
	public ParkingOccupiedPlaceException(int i) {
		super("�� ����� " + i + " ��� ����� ����������");
	}
}
