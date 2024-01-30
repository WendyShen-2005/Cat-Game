import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class furniture extends Wall{

	private Image image;
	
	public furniture(int x, int y, int l, int h, Image image) {
		super(x, y, l, h);
		System.out.println(l + " " + h);
		this.image = image;
	}

	public void draw(Graphics g, Color color) {
		g.drawImage(image, x, y, null);
		
		int collision = collide(main.catChar.getX(), main.catChar.getY(), main.catChar.getSize(), main.catChar.getSize());
		collision(collision);
	}
	
}
