import java.awt.Color;
import java.awt.Graphics;

public class BaseBus extends Vehicle {

	protected final int width = 100;
	protected final int height = 40;
	protected float radWheel;
	
	protected IExtraFunc extraFunc;
	protected TypeDoors typeDoors;
	protected Color extraColor;
	
	protected Color colorWheels;
	
	public BaseBus(int speed, Color mainColor, float radWheel, Color colorWheels, IExtraFunc extraFunc, TypeDoors typeDoors, Color extraColor) {
		this.radWheel = radWheel;
		this.colorWheels = colorWheels;
		this.speed = speed;
		this.mainColor = mainColor;
		this.extraFunc = extraFunc;
		this.typeDoors = typeDoors;
		this.extraColor = extraColor;
	}

	@Override
	public void Move(Direction direction) {
		switch (direction)
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

	@Override
	public void Draw(Graphics g) {
		g.setColor(mainColor);
	    //body
		g.drawRect((int)(posX - (width / 2)), (int)(posY - (height / 2)), width, height);
	    //window
	    g.drawRect(posX - (width / 2), posY - (height / 2), width / 6, height / 4);
	    //wheels
	    g.setColor(colorWheels);
	    g.drawOval((int)(posX - (width / 3) - (radWheel / 2)), (int)(posY + (height / 2) - (radWheel / 2)), (int)radWheel, (int)radWheel);
	    g.drawOval((int)(posX + (width / 3) - (radWheel / 2)), (int)(posY + (height / 2) - (radWheel / 2)), (int)radWheel, (int)radWheel);
	    extraFunc.Draw(typeDoors, g, extraColor, posX, posY, width, height);
	}
}
