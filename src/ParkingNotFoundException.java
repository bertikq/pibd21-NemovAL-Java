
public class ParkingNotFoundException extends NullPointerException
{
	public ParkingNotFoundException(int i) 
	{
		super("�� ������ ���������� �� ����� " + i);
	}
}
