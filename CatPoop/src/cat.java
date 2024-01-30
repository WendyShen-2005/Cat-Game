import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class cat extends Obstacle{

	private int hp = (int)(Math.random() * 10), maxHP = 10;
	private final int speed = 7;
	boolean up = false, down = false, left = false, right = true;
	
	public cat(int x, int y) {
		this.x = x;
		this.y = y;
		l = 50;
		h = l;
	}
	
	public void drawCat(Graphics g) {
		drawHPBar(g);
		
		g.setColor(Color.red);
		g.drawRect(x, y, h, h);
		updateCoordinates();
	}
	
	public void drawHPBar(Graphics g) {
		int hpPercentSize = (int)((h - 2) * ( 1.0 * hp/maxHP)); 

		g.setColor(Color.black);
		g.fillRect(x - 1, y - 25, h + 2, 15);
		g.setColor(Color.red);
		g.fillRect(x + 1, y - 23, h - 2, 11);
		g.setColor(Color.green);
		g.fillRect(x + 1, y - 23, hpPercentSize, 11);
	}
	
	private void updateCoordinates() {
		if(up) {
			y -= speed;
		} else if(down) {
			y += speed;
		} else if(left) {
			x -= speed;
		} else if(right) {
			x += speed;
		}
	}
	
	private void resetDirection() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void move(KeyEvent e) {
		int key = e.getKeyCode();
		 if(key == KeyEvent.VK_UP) {
			 resetDirection();
			 up = true;
		 } else if(key == KeyEvent.VK_DOWN) {
			 resetDirection();
			 down = true;
		 } else if(key == KeyEvent.VK_LEFT) {
			 resetDirection();
			 left = true;
		 } else if(key == KeyEvent.VK_RIGHT) {
			 resetDirection();
			 right = true;
		 }
		
	}
	
	public void stopMoving() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void resetPosition() {
		x = 700;
		y = 225;
		stopMoving();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return h;
	}
	
	public int getHP() {
		return hp;
	}
	
	public int getDirection() {
		if(up) {
			return 1;
		} else if(down) {
			return 2;
		} else if(left) {
			return 3;
		} else if(right) {
			return 4;
		}
		return -1;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incrementHP(int hp) {
		if(this.hp + hp >= maxHP)
			this.hp = maxHP;
		else
			this.hp += hp;
		
	}

}
