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
	
	
}
