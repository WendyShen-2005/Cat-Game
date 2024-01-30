import java.awt.Color;
import java.awt.Graphics;

public class Poop extends Wall{

	private static int size = 30;
	private static Color brown = new Color(105, 79, 77);
	private boolean currentlyInsideThisPoop = false;

	public Poop(int x, int y) {
		super(x, y, size, size);

	}

	public void draw(Graphics g) {
		super.draw(g, brown);
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
	}

	public void collision(int collision) {
		if(collision != -1) {
			main.catChar.stopMoving();

			if(!currentlyInsideThisPoop)
				main.catChar.incrementHP(-1);

			if(!currentlyInsideThisPoop) 
				currentlyInsideThisPoop = true;
		} else 
			currentlyInsideThisPoop = false;
	}
}
