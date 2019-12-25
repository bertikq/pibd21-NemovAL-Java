import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {
	
	public int posX;
	public int posY;
	
	protected int widthWindow;
	protected int heightWindow;
	
	public int speed;
	
	public Color mainColor;
	
	@Override
	public void SetPosition(int posX, int posY, int widthWindow, int heightWindow) {
		this.posX = posX;
		this.posY = posY;
		this.widthWindow = widthWindow;
		this.heightWindow = heightWindow;
	}
	
	public abstract void Move(Direction direction);
	
	public abstract void Draw(Graphics g);
	
	public int getSpeed() {
		return speed;
	}
	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	public Color getMainColor() {
		return mainColor;
	}
	protected void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
    public void SetMainColor(Color color)
    {
        mainColor = color;
    }
	
}
