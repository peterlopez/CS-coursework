package midterm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Circ extends JPanel {

	private int xStart = 20;
	private int yStart = 20;
	private int myWidth = 50;
	private int myHeight = 50;
	private String color = "Blue";
	private String backColor = "Yellow";
	private Boolean filled = true;
	
	public Circ() {
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
			
			field = Class.forName("java.awt.Color").getField(color.toLowerCase());
			color2 = (Color) field.get(null);
		}
		catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.setBackground(color1);
		g.setColor(color2);
		
		// Draw circle, filled or not
		if(filled) {
			g.fillOval(xStart, yStart, myWidth, myHeight);
		}
		else {
			g.drawOval(xStart, yStart, myWidth, myHeight);
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
	public void setMyWidth(int newWidth) {
		myWidth = newWidth;
		repaint();
	}
	public int getMyWidth() {
		return myWidth;
	}
	public void setMyHeight(int newHeight) {
		myHeight = newHeight;
		repaint();
	}
	public int getMyHeight() {
		return myHeight;
	}
	public void setColor(String newColor) {
		color = newColor;
		repaint();
	}
	public String getColor() {
		return color;
	}
	public void setbackColor(String newBackColor) {
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
