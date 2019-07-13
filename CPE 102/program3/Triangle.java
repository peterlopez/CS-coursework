/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
public class Triangle implements Shape {
   
   java.awt.Point a;
   java.awt.Point b;
   java.awt.Point c;
   java.awt.Color color;
   boolean filled;
   
   public Triangle(java.awt.Point a, java.awt.Point b, java.awt.Point c,
      java.awt.Color color, boolean filled) 
   {
      this.a = a;
      this.b = b;
      this.c = c;
      this.color = color;
      this.filled = filled;
   }
   public java.awt.Point getVertexA() {
      return a;
   }
   public java.awt.Point getVertexB() {
      return b;
   }
   public java.awt.Point getVertexC() {
      return c;
   }
   public void setVertexA(java.awt.Point a) {
      this.a = a;
   }
   public void setVertexB(java.awt.Point b) {
      this.b = b;
   }
   public void setVertexC(java.awt.Point c) {
      this.c = c;
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
      // Compare verticies, color, and filled
      if(this.a != ((Triangle) other).a ||
         this.b != ((Triangle) other).b ||
         this.c != ((Triangle) other).c ||
         this.color != ((Triangle) other).color ||
         this.filled != ((Triangle) other).filled)
      {
         return false;
      }
      
      return true;
   }
   
   /**
   * Inherited from Shape interface
   */
   public double getArea() {
      return 0.0;
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
   * @return vertex A of triangle is defined as position
   */
   public java.awt.Point getPosition() {
      return this.a;
   }
   public void setPosition(java.awt.Point position) {
      this.a = position;
   }
   public void move(java.awt.Point delta) {
      // Move pointA
      int x = delta.x + a.x;
      int y = delta.y + a.y;
      this.a = new java.awt.Point(x, y);
      
      // Move pointB
      x = delta.x + b.x;
      y = delta.y + b.y;
      this.b = new java.awt.Point(x, y);
      
      // Move pointC
      x = delta.x + c.x;
      y = delta.y + c.y;
      this.c = new java.awt.Point(x, y);
   }
}