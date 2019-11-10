import java.awt.Color;
import java.awt.Graphics;

public class DrawDoorsHeight implements IExtraFunc {

	@Override
	public void Draw(TypeDoors typeDoors, Graphics g, Color color, int posX, int posY, int width, int height) {
		g.setColor(color);
		switch (typeDoors) {
		case One:
			g.drawRect(posX - width / 3, posY, width / 7, height - height / 2);
			break;
		case Two:
			g.drawRect(posX - width / 3, posY, width / 7, height - height / 2);
			g.drawRect(posX + width / 3, posY, width / 7, height - height / 2);
			break;
		case Three:
			g.drawRect((posX - width / 2) + width / 10, posY - height / 2, width / 7, height - height / 2);
			g.drawRect(posX + width / 8, posY, width / 7, height - height / 2);
			g.drawRect(posX + width / 3, posY, width / 7, height - height / 2);
			break;

		default:
			break;
		}
	}
	
}
