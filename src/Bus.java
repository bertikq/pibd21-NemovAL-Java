import java.awt.Color;
import java.awt.Graphics;

public class Bus {
	private int posX;
	private int posY;
    final private int width = 150;
    final private int height = 40;
    private int speed;
    private Color colorBody;
    private Color colorWings;
    private Color colorAccord = Color.BLUE;
    private float radWheel;
    private int widthWindow;
    private int heightWindow;
    private int widthAccord = 40;
    private DrawDoors drawDoors;

    public int countPeople;
    public String NameDriver;
    
    public Bus(int posX, int posY, Color colorBody, Color colorWings, int speed, DrawDoors drawDoors)
    {
        this.posX = posX;
        this.posY = posY;
        this.colorBody = colorBody;
        this.colorWings = colorWings;
        this.speed = speed;
        this.drawDoors = drawDoors;
        radWheel = 15;
    }
    public void Move(Direction dir)
    {
        switch (dir)
        {
            case Right:
                if (posX + speed < widthWindow - width / 2)
                    posX += speed;
                break;
            case Left:
                if (posX - speed > width / 2)
                	posX -= speed;
                break;
            case Up:
                if (posY - speed > height / 2)
                	posY -= speed;
                break;
            case Down:
                if (posY + speed < heightWindow - height / 2)
                	posY += speed;
                break;
        }
    }

    public void SetSizeWindow(int w, int h)
    {
        widthWindow = w;
        heightWindow = h;
    }

    public void DrawBus(Graphics g)
    {
    	g.setColor(colorBody);
        g.drawRect(posX - (width / 2), posY - (height / 2), width, height);
        g.setColor(colorAccord);
        g.drawRect(posX - (widthAccord / 2), posY - (height / 2), width / 6, height);

    	g.setColor(colorWings);
        g.drawOval((int)(posX - (width / 3) - (radWheel / 2)), (int)(posY + (height / 2) - (radWheel / 2)), (int)radWheel, (int)radWheel);
        g.drawOval((int)(posX + (width / 3) - (radWheel / 2)), (int)(posY + (height / 2) - (radWheel / 2)), (int)radWheel, (int)radWheel);
        drawDoors.Draw(g, posX, posY, width, height);
    }
    
	public int getCountPeople() {
		return countPeople;
	}
	public void setCountPeople(int countPeople) {
		this.countPeople = countPeople;
	}
	public String getNameDriver() {
		return NameDriver;
	}
	public void setNameDriver(String nameDriver) {
		NameDriver = nameDriver;
	}
}
