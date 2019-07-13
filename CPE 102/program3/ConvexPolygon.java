/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
public class ConvexPolygon implements Shape {
   
   java.awt.Point[] vertices;
   java.awt.Color color;
   boolean filled;
   
   public ConvexPolygon(java.awt.Point[] vertices, java.awt.Color color, boolean filled) {
      this.vertices = vertices;
      this.color = color;
      this.filled = filled;
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
      // Compare color and filled
      if(this.color != ((ConvexPolygon) other).color ||
         this.filled != ((ConvexPolygon) other).filled)
      {
         return false;
      }
      // Compare all vertices
      for(int i=0; i < ((ConvexPolygon) other).vertices.length(); i++) {
         if(vertices[i] != ((ConvexPolygon) other).vertices[i]) {
            return false;
         }
      }
      
      return true;
   }
   public java.awt.Point getVertex(int index) {
      return vertices[index];
   }
   public void setVertex(int index, java.awt.Point vertex) {
      vertices[index] = vertex;
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
      return this.vertices[0];
   }
   public void setPosition(java.awt.Point position) {
      this.vertices[0] = position;
   }
   public void move(java.awt.Point delta) {
      int x, y;
      
      // Loop through verticies and move them
      for(int i=0; i < vertices.length(); i++) {
         x = delta.x + vertices[i].x;
         y = delta.y + vertices[i].y;
         
         vertices[i] = new java.awt.Point(x, y);
      }
   }
}