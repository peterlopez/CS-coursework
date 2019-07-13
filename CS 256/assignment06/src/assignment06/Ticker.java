//Assignment #: 6
//Student Name: Peter Lopez
//Class: CS-256

package assignment06;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ticker extends JPanel implements Runnable {
	private boolean moving = false;
	private String symbol = "DVC 32.0";
	private int symbolWidth;
	private int x = 30;
	private int y = 100;
	private JFrameExt frame;
	
	public Ticker(JFrameExt frame) {
		this.frame = frame;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Draws Symbol string on screen and updates symbolWidth property
	 * @param graphics object
	 */
	public void paintComponent(Graphics g) {
		// clear drawing surface.
		super.paintComponent(g);
		
		// determine the width of the string
		FontMetrics fm = g.getFontMetrics();
		symbolWidth = fm.stringWidth(symbol);
		
		// draw the string
		g.drawString(symbol, x, y);
	}
	
	@Override
	public void run() {
		while(true) {
			if(getMoving()) {
				// reset x position of string if outside frame
				x += 10;
				int frameWidth = (int) frame.getContentPane().getSize().getWidth();
				if((symbolWidth + x) >= frameWidth) {
					x = 0;
				}
				this.repaint();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Getters and setters for moving property
	 */
	public void setMoving(boolean newValue) {
		moving = newValue;
	}
	public boolean getMoving() {
		return moving;
	}
}
