import java.awt.Color;
import java.awt.Graphics;

public class DrawDoorsOval implements IExtraFunc {

	@Override
	public void Draw(TypeDoors typeDoors, Graphics g, Color color, int posX, int posY, int width, int height) {
		g.setColor(color);
		switch (typeDoors) {
		case One:
			g.drawOval(posX - width / 3, posY - height / 2, width / 7, height);
			break;
		case Two:
			g.drawOval(posX - width / 3, posY - height / 2, width / 7, height);
			g.drawOval(posX + width / 3, posY - height / 2, width / 7, height);
			break;
		case Three:
			g.drawOval((posX - width / 2) + width / 10, posY - height / 2, width / 7, height);
			g.drawOval(posX + width / 8, posY - height / 2, width / 7, height);
			g.drawOval(posX + width / 3, posY - height / 2, width / 7, height);
			break;
		default:
			break;
		}
	}
}
