import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public abstract class UI extends JPanel implements Runnable {

	private long startTime, timeElapsed;
	private int frameCount = 0;
	private int FPS = 60;
	
	static Font buttonFont, biggerButtonFont, titleFont;
	
	static Thread thread;
	static Color lightPink = new Color(242, 107, 161), 
			lightCyan = new Color(174, 230, 235), 
			darkPink = new Color(127, 69, 133), 
			darkCyan = new Color(82, 119, 122);

	public UI() throws FontFormatException, IOException {
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("Starborn.ttf")).deriveFont(30f);
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Starborn.ttf")));
		biggerButtonFont = Font.createFont(Font.TRUETYPE_FONT, new File("Starborn.ttf")).deriveFont(35f);
	}
	
	public void button(String text, int x, int y, String newScene, Graphics g) {
		int h = 70, round = 50, outline = 5;
		int w;
		g.setFont(buttonFont);
		w = g.getFontMetrics().stringWidth(text) + 50;
		
		if(main.mouseXM >= x && main.mouseXM <= x + w && main.mouseYM >= y && main.mouseYM <= y + h) { //if hovering over button
			g.setFont(biggerButtonFont);
			w = g.getFontMetrics().stringWidth(text) + 50;

			g.setColor(Color.black);
			g.fillRoundRect(x + outline - 10, y + outline - 10, w - 2 * outline + 10, h - 2 * outline + 20, round - outline * 2 + 20, round - outline * 2 + 20);
			g.setColor(lightPink);
			
			g.drawString(text, x + 20, y + 45);

		} else {//if not
			g.setFont(buttonFont);
			w = g.getFontMetrics().stringWidth(text) + 50;

			g.setColor(Color.black);
			g.fillRoundRect(x, y, w, h, round, round);
			g.setColor(lightPink);
			g.fillRoundRect(x + outline, y + outline, w - 2 * outline, h - 2 * outline, round - outline * 2, round - outline * 2);
			g.setColor(Color.black);
			
			g.drawString(text, x + 25, y + 45);

		}
		
		if(main.mouseXC >= x && main.mouseXC <= x + w && main.mouseYC >= y && main.mouseYC <= y + h)
			main.scene = newScene;
	}
	
	public void run() {
		System.out.println("Thread: Starting thread");
		initialize();
		while(true) {
			update();
			this.repaint();
			try {
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void update() {
		timeElapsed = System.currentTimeMillis() - startTime;
		frameCount++;
	}

	public void initialize() {
		System.out.println("Thread: Initializing game");
		startTime = System.currentTimeMillis();
		timeElapsed = 0;
		FPS = 60;
		for(int i = 0; i < 100000; i++) {
			String s = "set up stuff blah blah blah";
			s.toUpperCase();
		}
		System.out.println("Thread: Done initializing game");
	}
}
