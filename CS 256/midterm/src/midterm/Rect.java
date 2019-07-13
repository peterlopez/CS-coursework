package midterm;

import java.awt.Color;
import java.awt.Graphics;
import java.text.Format.Field;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rect extends JPanel {

	private int xStart = 20;
	private int yStart = 20;
	private int width = 50;
	private int height = 50;
	private String foreColor = "Green";
	private String backColor = "Blue";
	private boolean filled = true;
	
	public Rect() {
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Set color
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
		
		// Draw rectangle, filled or not
		if(filled) {
			g.fillRect(xStart, yStart, width, height);
		}
		else {
			g.drawRect(xStart, yStart, width, height);
		}
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
	public void setWidth(int newWidth) {
		width = newWidth;
		repaint();
	}
	public int getWidth() {
		return width;
	}
	public void setHeight(int newHeight) {
		height = newHeight;
		repaint();
	}
	public int getHeight() {
		return height;
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
	public void setFilled(Boolean newFilled) {
		filled = newFilled;
		repaint();
	}
	public Boolean getFilled() {
		return filled;
	}
}
