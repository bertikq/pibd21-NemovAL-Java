import java.awt.Color;
import java.awt.Graphics;

public class DrawBaseExtraFunc implements IExtraFunc {
	
	@Override
	public void Draw(TypeDoors typeDoors, Graphics g, Color color, int posX, int posY, int width, int height) {
		g.setColor(color);
		switch (typeDoors) {
		case One:
			g.drawRect(posX - width / 3, posY - height / 2, width / 7, height);
			break;
		case Two:
			g.drawRect(posX - width / 3, posY - height / 2, width / 7, height);
			g.drawRect(posX + width / 3, posY - height / 2, width / 7, height);
			break;
		case Three:
			g.drawRect((posX - width / 2) + width / 10, posY - height / 2, width / 7, height);
			g.drawRect(posX + width / 8, posY - height / 2, width / 7, height);
			g.drawRect(posX + width / 3, posY - height / 2, width / 7, height);
			break;

		default:
			break;
		}
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
