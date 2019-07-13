/**
* Virtual Drawing Program
* @author Peter Lopez
* @version Program 3
*/
public class Canvas {
   
   private java.util.ArrayList<Shape> shapes = new java.util.ArrayList<Shape>(50);
   
   public Canvas() {
   }
   
   /**
   * Adds objects which implement the Shape interface to the end of the
   * Canvas's java.util.ArrayList<Shape> instance variable.
   */
   public void add(Shape shape) {
      
   }
   /**
   * Removes the Shape at the specified index and returns a
   * reference to it or null if the index is out-of-bounds.
   */
   public Shape remove(int index) {
      if(index > shapes.length || index < 0) {
         
      }
   }
   /**
   * Return the ith Shape object from Canvas.
   */
   public Shape get(int index) {
      return shapes[index];
   }
   /**
   * Returns the number of Shapes contained by the Canvas.
   */
   public int size() {
      int result = 0;
      
      for(int i=0; i < shapes.length; i++) {
         if(shapes[i] != null) {
            result++;
         }
      }
      return result;
   }
   /**
   * Returns a java.util.ArrayList of all of the Circle objects contained in the Canvas.
   */
   public java.util.ArrayList<Circle> getCircles() {
      java.util.ArrayList<Circle> result = new java.util.ArrayList<Circle>(list.length);
      
      for(int i=0; i < size(); i++) {
         if(list[i].getClass() == "Circle") {
            result.add(list[i]);
         }
      }
      return result;
   }
   public java.util.ArrayList<Rectangle> getRectangles() {
      java.util.ArrayList<Rectangle> result = new java.util.ArrayList<Rectangle>(shapes.length);
      
      return result;
   }
   public java.util.ArrayList<Triangle> getTriangles() {
      java.util.ArrayList<Triangle> result = new java.util.ArrayList<Triangle>(shapes.length);
      
      return result;
   }
   public java.util.ArrayList<ConvexPolygon> getConvexPolygons() {
      java.util.ArrayList<ConvexPolygon> result = new java.util.ArrayList<ConvexPolygon>(shapes.length);
      
      return result;
   }
   public java.util.ArrayList<Shape> getShapesByColor(java.awt.Color color) {
      java.util.ArrayList<Shape> result = new java.util.ArrayList<Shape>(shapes.length);
      
      return result;
   }
   public double getAreaOfAllShapes() {
      double result = 0;
      
      for(int i=0; i<shapes.length; i++) {
         if(shapes[i] != null) {
            result += shapes[i].getArea();
         }
      }
      return result;
   }
}