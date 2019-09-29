import java.awt.Color;
import java.awt.Graphics;

public class DrawDoors {
	
	private TypeDoors typeDoors;
	private Color colorDoor;
	
	public DrawDoors (TypeDoors typeDoors, Color colorDoor) {
		this.typeDoors = typeDoors;
		this.colorDoor = colorDoor;
	}
	
	public void Draw(Graphics g, int posX, int posY, int width, int height) {
		g.setColor(colorDoor);
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
}
