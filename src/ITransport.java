import java.awt.Graphics;

public interface ITransport {
	
	void SetPosition(int posX, int posY, int widthWindow, int heightWindow);
	
	void Move(Direction direction);
	
	void Draw(Graphics g);
	
}
