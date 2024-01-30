import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Wall extends Obstacle{

	public Wall(int x, int y, int l, int h) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.h = h;
	}
	
	public void draw(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(x, y, l, h);
		
		int collision = collide(main.catChar.getX(), main.catChar.getY(), main.catChar.getSize(), main.catChar.getSize());
		collision(collision);
	}
	
	public void collision(int collision) {
		if(collision != -1) {
			if(collision == 1) { // left
				main.catChar.setX(newPos(main.catChar.getSize(), main.catChar.getSize(), collision));
			} else if(collision == 2) { // right
				main.catChar.setX(newPos(main.catChar.getSize(), main.catChar.getSize(), collision));
			} else if(collision == 3) { // top
				main.catChar.setY(newPos(main.catChar.getSize(), main.catChar.getSize(), collision));
			} else { // bottom
				main.catChar.setY(newPos(main.catChar.getSize(), main.catChar.getSize(), collision));
			}
		}
	}
}
