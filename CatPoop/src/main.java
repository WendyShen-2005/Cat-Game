import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class main extends UI implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener{
	final static int W = 1200, H = 700;
	static cat catChar = new cat(700, 225);
	static int score = 0;

	static List<Food> listOfFood = new ArrayList<>();
	static List<Wall> listOfStartingWalls = new ArrayList<>();
	static List<Wall> listOfOtherWalls = new ArrayList<>();
	static List<Wall> listOfWalls = new ArrayList<>();
	static List<Poop> listOfPoop = new ArrayList<>();

	static Image[] backgrounds = new Image[10];

	static Image bed = Toolkit.getDefaultToolkit().getImage("bed.png");
	//TODO bed, closet, door, etc.
	
	static int mouseXC, mouseYC, mouseXM, mouseYM;

	static String scene = "startMenu";

	public main () throws FontFormatException, IOException {

		listOfFood.add(new Food(300, 300, 30));
		listOfStartingWalls.add(new Wall(0, 0, W, 20));//top
		listOfStartingWalls.add(new Wall(W - 34, 0, 20, H));//left
		listOfStartingWalls.add(new Wall(0, H - 200, W, 200));//bottom
		listOfStartingWalls.add(new Wall(0, 0, 200, H));//right

		backgrounds[0] = Toolkit.getDefaultToolkit().getImage("catPoopTitle.png");
		backgrounds[1] = Toolkit.getDefaultToolkit().getImage("woodBackground.png");

//		housePictures[0] = Toolkit.getDefaultToolkit().getImage("bed.png");
		listOfStartingWalls.add(new furniture(200, 20, 150, 250, bed));//right

//		generateWalls();

		listOfWalls.addAll(listOfOtherWalls);
		listOfWalls.addAll(listOfStartingWalls);

		thread = new Thread(this);
		thread.start();

		addMouseListener (this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		addMouseWheelListener(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if (scene.equals("startMenu")) {
			int firstButton = 300, buttonOffset = 85;
			g.drawImage(backgrounds[0], 0, 0, null);
			button("Start", 100, firstButton, "play", g);
			button("Options", 100, firstButton + buttonOffset, "play", g);
			button("Exit", 100, firstButton + buttonOffset * 2, "play", g);

			catChar.resetPosition();
		} else if (scene.equals("play")) {
			g.drawImage(backgrounds[1], 150, 0, null);
			drawWalls(g);
			drawScore(g);
			catChar.drawCat(g);
			drawFood(g);
			drawPoop(g);

			button("Home", 20, 500, "startMenu", g);
			button("Restart", 20, 580, "restart", g);
			

		} else if(scene.equals("restart")) {
			resetWalls();
			scene = "play";
			score = 0;
			mouseXC = -1;
			mouseYC = -1;
			catChar.resetPosition();
		}

		//maybe make new variable for collision detection with cat
	}

	public void drawFood(Graphics g) {
		for(Food i: listOfFood) 
			i.draw(g);
	}

	public void drawWalls(Graphics g) {
		for(Wall i: listOfWalls) 
			i.draw(g, Color.black);
	}

	public void resetWalls() {
		listOfWalls.removeAll(listOfOtherWalls);
		listOfOtherWalls.removeAll(listOfOtherWalls);
		generateWalls();
	}

	public void generateWalls() {
		int numOfWalls = (int)(Math.random() * 10);
		for(int i = 0; i < numOfWalls; i++) {

			int height, width, x, y;
			Wall newWall, placeHolderWall;
			boolean intersecting = false;

			do {//TODO make it so that it randomly chooses from a list of furniture rather than completely random dimensions
				//scale thing
				//generate a wall
				intersecting = false;
				height = (int)(Math.random() * H/30) + 50;
				width = (int)(Math.random() * W/30) + 50;
				x = 20 + (int)(Math.random() * (W - 40 - width));
				y = 20 + (int)(Math.random() * (H - 40 - height));

				newWall = new Wall(x, y, height, width);

				//see if the wall intersects with other walls
				placeHolderWall = new Wall(x - 75, y - 75, height + 150, width + 150);

				if(placeHolderWall.isInside(catChar))
					intersecting = true;
				else
					for(Wall wall: listOfOtherWalls) 
						if(placeHolderWall.isInside(wall)) 
							intersecting = true;

			} while(intersecting == true);

			listOfOtherWalls.add(newWall);
		}
	}

	public void drawPoop(Graphics g) {
		for(Poop i: listOfPoop) 
			i.draw(g);
	}

	public void drawScore (Graphics g) {
		g.setColor(Color.black);
		g.fillRect(10, 30, 100, 50);
		g.setColor(Color.white);
		g.drawString("Score: " + score, 1000, 525);
	}

	public static void main(String[] args) throws FontFormatException, IOException {
		JFrame frame = new JFrame("Cat Game");
		main panel = new main();
		panel.setPreferredSize (new Dimension(W, H));
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(W, H);


		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		catChar.move(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseXM = e.getX();
		mouseYM = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseXC = e.getX();
		mouseYC = e.getY();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
