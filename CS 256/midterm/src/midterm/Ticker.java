package midterm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ticker extends JPanel implements Runnable {

	private int xStart = 20;
	private int yStart = 20;
	private String message = "Hello";
	private Boolean moving = false;
	private String foreColor = "Blue";
	private String backColor = "Yellow";
	
	public Ticker() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			if(getMoving()) {
				// reset x position of string if outside frame
				xStart += 10;
				int frameWidth = (int) this.getWidth();
				if((xStart + 20) >= frameWidth) {
					xStart = 0;
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Set colors
		// Create Color object from String properties
		Color color1 = Color.RED;
		Color color2 = Color.RED;
		java.lang.reflect.Field field;
		try {
			field = Class.forName("java.awt.Color").getField(backColor.toLowerCase());
			color1 = (Color) field.get(null);
				
			field = Class.forName("java.awt.Color").getField(foreColor.toLowerCase());
			color2 = (Color) field.get(null);
		}
		catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.setBackground(color1);
		g.setColor(color2);
		
		// Create message text
		g.drawString(message, xStart, yStart);
	}
	
	/**
	 * Getters and setters
	 */
	public void setXStart(int newXStart) {
		xStart = newXStart;
		repaint();
	}
	public int getXStart() {
		return xStart;
	}
	public void setYStart(int newYStart) {
		yStart = newYStart;
		repaint();
	}
	public int getYStart() {
		return yStart;
	}
	public void setMessage(String newMessage) {
		message = newMessage;
		repaint();
	}
	public String getMessage() {
		return message;
	}
	public void setMoving(Boolean newMoving) {
		moving = newMoving;
		repaint();
	}
	public Boolean getMoving() {
		return moving;
	}
	public void setForeColor(String newForeColor) {
		foreColor = newForeColor;
		repaint();
	}
	public String getForeColor() {
		return foreColor;
	}
	public void setBackColor(String newBackColor) {
		backColor = newBackColor;
		repaint();
	}
	public String getBackColor() {
		return backColor;
	}
}
