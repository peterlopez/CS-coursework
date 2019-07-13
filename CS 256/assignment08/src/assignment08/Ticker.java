//Assignment #: 8
//Student Name: Peter Lopez
//Class: CS-256

package assignment08;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ticker extends JPanel implements Runnable {
	private String stockName;
	private double stockPrice;
	private boolean direction;
	private long sleepTime;
	
	private int symbolWidth;
	private int x = 10;
	private int y = 60;
	private JFrameExt frame;
	
	public Ticker(JFrameExt frame) {
		this.frame = frame;
		Thread thread = new Thread(this);
		thread.start();
		this.repaint();
	}
	
	/**
	 * Draws Symbol string on screen and updates symbolWidth property
	 * @param graphics object
	 */
	public void paintComponent(Graphics g) {
		String symbol = stockName+" "+Double.toString(stockPrice);
		
		// clear drawing surface.
		super.paintComponent(g);
		
		// determine the width of the string
		FontMetrics fm = g.getFontMetrics();
		symbolWidth = fm.stringWidth(symbol);
		
		// draw the string
		g.setColor(Color.black);
		g.drawString(symbol, x, y);
	}
	
	public synchronized void myRun() {
		if(!frame.moving) {
			try {
				wait();
			}
			catch(InterruptedException e) {
			}
		}
		else {
			// move left
			if(direction) {
				if(x >= this.getWidth() - (symbolWidth+10)) {
					x = 10;
				} else {
					x += 10;
				}
			}
			// move right
			else {
				if(x <= 10) {
					x = this.getWidth() - symbolWidth;
				} else {
					x -= 10;
				}
			}
			
			this.repaint();
		}
	}
	@Override
	public void run() {
		while(true) {
			myRun();
			try {
				Thread.sleep(sleepTime);
			}
			catch(InterruptedException e) {

			}
		}
	}

	/**
	 * Getters and setters for properties
	 */
	public synchronized void setMoving(boolean moving) {
		frame.moving = moving;
		notify();
	}
	public boolean getMoving() {
		return frame.moving;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	public boolean getDirection() {
		return direction;
	}
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
}
