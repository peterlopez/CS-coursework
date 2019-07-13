/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
public class Rectangle implements Shape {
   
   int width;
   int height;
   java.awt.Point position;
   java.awt.Color color;
   boolean filled;
   
   public Rectangle(int width, int height, java.awt.Point position, java.awt.Color color, boolean filled) {
      this.width = width;
      this.height = height;
      this.position = position;
      this.color = color;
      this.filled = filled;
   }
   public int getWidth() {
      return this.width;
   }
   public void setWidth(int width) {
      this.width = width;
   }
   public int getHeight() {
      return this.height;
   }
   public void setHeight(int height) {
      this.height = height;
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
      // Compare width, height, position, color, and filled
      if(this.width != ((Rectangle) other).width ||
         this.height != ((Rectangle) other).height ||
         this.position != ((Rectangle) other).position ||
         this.color != ((Rectangle) other).color ||
         this.filled != ((Rectangle) other).filled)
      {
         return false;
      }
      
      return true;
   }
   
   /**
   * Inherited from Shape interface
   */
   public double getArea() {
      double area = this.width * this.height;
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
   * @return position defined as left corner point
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