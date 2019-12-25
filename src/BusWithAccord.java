import java.awt.Color;
import java.awt.Graphics;

public class BusWithAccord extends BaseBus {

	public Color accordColor;
	public int widthAccord;
	public int paddingAccord;
	public int widthHalfBus;
	

	public BusWithAccord(int speed, Color mainColor, float radWheel, Color colorWheels,
            Color accordColor, int widthAccord, int paddingAccord, int widthHalfBus, IExtraFunc extraFunc, TypeDoors typeDoors, Color extraColor) {
		super(speed, mainColor, radWheel, colorWheels, extraFunc, typeDoors, extraColor);
		this.accordColor = accordColor;
        this.widthAccord = widthAccord;
        this.paddingAccord = paddingAccord;
        this.widthHalfBus = widthHalfBus;
	}
	
	public BusWithAccord(String save)
    {
		super(save);
		String[] mas = save.split(";");
        if (mas.length == 10)
        {
            speed = Integer.parseInt(mas[0]);
            mainColor = toColor(mas[1]);
            radWheel = Float.parseFloat(mas[2]);
            colorWheels = toColor(mas[3]);
            typeDoors = toTypeDoors(mas[4]);
            extraFunc = toExtraFunc(mas[5]);
            accordColor = toColor(mas[6]);
            widthAccord = Integer.parseInt(mas[7]);
            paddingAccord = Integer.parseInt(mas[8]);
            widthHalfBus = Integer.parseInt(mas[9]);
            extraColor = Color.BLACK;
        }
    }

	@Override
	public void Draw(Graphics g) {
		super.Draw(g);
		g.setColor(accordColor);
        g.drawRect(posX + (width / 2), posY - (height / 2) - paddingAccord, widthAccord, height + paddingAccord * 2);
        for (int i = posX + (width / 2); i < posX + width / 2 + widthAccord; i += 3)
        {
            g.drawLine(i, posY - (height / 2) - paddingAccord, i, posY + (height / 2) + paddingAccord);
        }
        g.setColor(mainColor);
        g.drawRect(posX + width / 2 + widthAccord, posY - (height / 2), widthHalfBus, height);
        g.setColor(colorWheels);
        g.drawOval((int)(posX + width / 2 + widthAccord + (widthHalfBus / 2 - radWheel / 2)), (int)(posY + (height / 2) - (radWheel / 2)), (int)radWheel, (int)radWheel);
	}

	public Color getAccordColor() {
		return accordColor;
	}

	public void setAccordColor(Color accordColor) {
		this.accordColor = accordColor;
	}

	public int getWidthAccord() {
		return widthAccord;
	}

	public void setWidthAccord(int widthAccord) {
		this.widthAccord = widthAccord;
	}

	public int getPaddingAccord() {
		return paddingAccord;
	}

	public void setPaddingAccord(int paddingAccord) {
		this.paddingAccord = paddingAccord;
	}

	public int getWidthHalfBus() {
		return widthHalfBus;
	}

	public void setWidthHalfBus(int widthHalfBus) {
		this.widthHalfBus = widthHalfBus;
	}
	
	@Override
	public String toString()
    {
        return super.toString() + ";" + getNameColor(accordColor)+ ";" + widthAccord + ";" + paddingAccord + ";" + widthHalfBus;
    }
}
