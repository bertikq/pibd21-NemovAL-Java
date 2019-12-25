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
	
	public BaseBus(String save)
    {
        String[] mas = save.split(";");
        if (mas.length == 6)
        {
            speed = Integer.parseInt(mas[0]);
            mainColor = toColor(mas[1]);
            radWheel = Float.parseFloat(mas[2]);
            colorWheels = toColor(mas[3]);
            typeDoors = toTypeDoors(mas[4]);
            extraFunc = toExtraFunc(mas[5]);
            extraColor = Color.BLACK;
        }
    }
	
	public Color toColor(String info) {
		
		if (info.contains("BLACK")) return Color.BLACK;
		if (info.contains("WHITE")) return Color.WHITE;
		if (info.contains("ORANGE")) return Color.ORANGE;
		if (info.contains("YELLOW")) return Color.YELLOW;
		if (info.contains("RED")) return Color.RED;
		if (info.contains("BLUE")) return Color.BLUE;
		if (info.contains("GREEN")) return Color.GREEN;
		if (info.contains("GRAY")) return Color.GRAY;
		
		return Color.BLACK;
	}
	
	public String getNameColor(Color color) {
		if (color.equals(Color.BLACK)) return "BLACK";
		if (color.equals(Color.WHITE)) return "WHITE";
		if (color.equals(Color.ORANGE)) return "ORANGE";
		if (color.equals(Color.YELLOW)) return "YELLOW";
		if (color.equals(Color.RED)) return "RED";
		if (color.equals(Color.BLUE)) return "BLUE";
		if (color.equals(Color.GREEN)) return "GREEN";
		if (color.equals(Color.GRAY)) return "GRAY";

		return "WHITE";
	}
	
	public IExtraFunc toExtraFunc(String info) {
		
		if (info.contains("DrawBaseExtraFunc")) return new DrawBaseExtraFunc();
		if (info.contains("DrawDoorsOval")) return new DrawDoorsOval();
		if (info.contains("DrawDoorsHeight")) return new DrawDoorsHeight();
		
		return new DrawDoorsHeight();
	}
	
	public String parseTypeDoors(TypeDoors type) {
		if (type.equals(TypeDoors.One)) return "One";
		if (type.equals(TypeDoors.Two)) return "Two";
		if (type.equals(TypeDoors.Three)) return "Three";
		return "One";
	}
	
	public TypeDoors toTypeDoors(String info) {
		
		if (info.contains("One")) return TypeDoors.One;
		if (info.contains("Two")) return TypeDoors.Two;
		if (info.contains("Three")) return TypeDoors.Three;
		return TypeDoors.One;
		
	}
	
	public void setExtraFunc(IExtraFunc extraFunc) {
		this.extraFunc = extraFunc;
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
	
	@Override
	public String toString()
    {
        return speed + ";" + getNameColor(mainColor) + ";" + radWheel + ";" + getNameColor(colorWheels) + ";" + parseTypeDoors(typeDoors) + ";" + extraFunc.toString();
    }
}
