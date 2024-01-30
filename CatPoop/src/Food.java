import java.awt.Color;
import java.awt.Graphics;

public class Food extends Obstacle {

	public Food (int x, int y, int size) {
		this.x = x;
		this.y = y;
		l = size;
		h = size;
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, l, h);

		int collision = collide(main.catChar.getX(), main.catChar.getY(), main.catChar.getSize(), main.catChar.getSize());
		collision(collision);
	}
	
	public void collision(int collision) {
		if(collision != -1) {
			main.listOfFood.remove(this);

			Food food = newFood();

			main.listOfFood.add(food);
			main.catChar.incrementHP(1);
			main.listOfPoop.add(newPoop());
			main.score += 1;
			main.catChar.stopMoving();

		}
	}
	
	public Poop newPoop() {
		int x = main.catChar.getX(), y = main.catChar.getY();

		if(main.catChar.getDirection() == 1)
			y += 55;
		else if(main.catChar.getDirection() == 2)
			y -= 55;
		else if(main.catChar.getDirection() == 3)
			x += 55;
		else
			x -= 55;
		
		return new Poop(x, y);
	}
	
	public Food newFood() {
		int x = (int)((main.W - 100) * Math.random());
		int y = (int)((main.H - 100) * Math.random());
		Food food = new Food(x, y, 30);
		boolean collidingWithWall = true;

		while(collidingWithWall) {
			collidingWithWall = false;
			for(Obstacle o: main.listOfWalls) {
				if(food.isInside(o)){//make sure food doesn't appear in walls
					collidingWithWall = true;
					break;
				}
			}
			if(collidingWithWall) {
				x = (int)(main.W * Math.random());
				y = (int)(main.H * Math.random());
				food = new Food(x, y, 30);
			}
		}		
		return food;
	}
}
