/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
import java.awt.*;

public class Circle implements Shape {
   
   double radius;
   java.awt.Point position;
   java.awt.Color color;
   boolean filled;
   
   public Circle(double radius, java.awt.Point position, java.awt.Color color, boolean filled) {
      this.radius    = radius;
      this.position  = position;
      this.color     = color;
      this.filled    = filled;
   }
   
   public double getRadius() {
      return radius;
   }
   public void setRadius(double radius) {
      this.radius = radius;
   }
   public boolean equals(Object other) {
      // Compare reference is null
      if(other == null) {
         return false;
      }
      // Compare object types
      if(this.getClass() != other.getClass()) {
         return false;
      }
      // Compare simple 
      if(this.radius != ((Circle) other).radius ||
         this.filled != ((Circle) other).filled)
      {
         return false;
      }
      if(!this.position.equals(((Circle)other).position)) {
         return false;
      }
      if(!this.color.equals(((Circle)other).color)) {
         return false;
      }
      
      return true;
   }
   
   /**
   * Inherited from Shape interface
   */
   public double getArea() {
      double area =  Math.PI * (this.radius * this.radius);
      return area;
   }
   public java.awt.Color getColor() {
      return this.color;
   }
   public void setColor(java.awt.Color color) {
      this.color = color;
   }
   public boolean getFilled() {
      return this.filled;
   }
   public void setFilled(boolean filled) {
      this.filled = filled;
   }
   /**
   * @return position defined as center of circle
   */
   public java.awt.Point getPosition() {
      return this.position;
   }
   public void setPosition(java.awt.Point position) {
      this.position = position;
   }
   public void move(java.awt.Point delta) {
      int x = delta.x + position.x;
      int y = delta.y + position.y;
      
      this.position = new java.awt.Point(x,y);
   }
}