/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
import java.awt.*;

public interface Shape {
   
   /** 
   * Calculates and returns the area of the shape.
   * @return area 
   */
   public double getArea();
   
   /**
   * Returns the color of the shape.
   * @return color of shape
   */
   public java.awt.Color getColor();
   
   /**
   * Sets the color of the shape.
   * @param color
   * @return void
   */
   public void setColor(java.awt.Color color);
   
   /**
   * @return Returns true if the shape is filled with color,
   *         otherwise false (shape is wire-framed).
   */
   public boolean getFilled();
   
   /**
   * Sets the filled state of the shape to the specified value.
   * @param filled state
   * @return void
   */
   public void setFilled(boolean filled);
   
   /**
   * Returns the currrent position of the shape.
   * @return current position
   */
   public java.awt.Point getPosition();
   
   /**
   * Changes the position of the shape to the specfied Point.
   * @param position (point) to move to
   * @return void
   */
   public void setPosition(java.awt.Point position);
   
   /**
   * Moves the shape by the x and y amounts specified in the Point delta.
   * @param point used as delta to move from
   */
   public void move(java.awt.Point delta);
}