import java.awt.Color;
import java.awt.Graphics;

public abstract class Obstacle {

	protected int x, y, l, h;
	
	protected void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, l, h);
	}
	
	protected void drawHitBox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x, y, l, h);
	}
	
	protected int collide(int oX, int oY, int oL, int oH) {
		int cRight = oX + oL, cLeft = oX, cTop = oY, cBottom = oY + oH;
		int wRight = x + l, wLeft = x, wTop = y, wBottom = y + h;
		if(cRight > wLeft && cLeft < wLeft &&
				cRight - wLeft < cBottom - wTop && 
				cRight - wLeft < wBottom - cTop){//collide with left side of wall
			return 1;
		} else if(cLeft < wRight && cRight > wRight &&
				wRight - cLeft < cBottom - wTop && 
				wRight - cLeft < wBottom - cTop){//collide with right side of wall
			return 2;
		}
		else if(cBottom > wTop && cTop < wTop &&
				(cRight > wLeft && cLeft < wRight)){//rect collides from top side of the wall
			return 3;
		}else if(cTop < wBottom && cBottom > wBottom &&
				(cRight > wLeft && cLeft < wRight)){//rect collides from bottom side of the wall
			return 4;
		}
		
		return -1;
	}
	
	protected boolean isInside(Obstacle o) {
		int left = o.getX(), right = o.getX() + o.getL(), up = o.getY(), down = o.getY() + o.getH();
		
		if(x + l >= left && x <= right && y + h >= up && y <= down) {
			return true;
		}
		
		return false;
	}
	
	protected int newPos(int oL, int oH, int opt) {
		int wLeft = x, wTop = y;
		if(opt == 1) {
			return wLeft - oL;
		} else if(opt == 2) {
			return wLeft + l;
		} else if(opt == 3) {
			return wTop - oH;
		} else if(opt == 4) {
			return wTop + h;
		}
		return -1;
	}
	/*
	 * hit box function - generic, not specific to cat cause might wanna use this to see if fish is colliding with anything
	 * maybe certain walls we can't die from
	 * could also be used for fish and power ups cause they also use hit box
	 * maybe cat is a child of this as well to see if cat collides with anything, but if everything else is checking cat then??? 
	 maybe cat is not chlid of this then, kinda redundant
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getL() {
		return l;
	}
	
	public int getH() {
		return h;
	}
}
