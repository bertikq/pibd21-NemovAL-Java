
public class ParkingNotFoundException extends Exception
{
	public ParkingNotFoundException(int i) 
	{
		super("�� ������ ���������� �� ����� " + i);
	}
}
