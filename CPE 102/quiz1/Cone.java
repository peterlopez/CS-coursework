/**
* @author Peter Lopez
* @version Lab Quiz 1
*/
public class Cone {
   
   private double radius;
   private double height;
   
   public Cone() {
      radius = 1.0;
      height = 1.5;
   }
   public Cone(double r, double h) {
      radius = r;
      height = h;
   }
   
   public double getRadius() {
      return radius;
   }
   public double getHeight() {
      return height;
   }
   
   public double getVolume() {
      // Use volume formula: V = 1/3 pi r squared times height
      double calculation = (1/3) * Math.PI * (radius * radius) * height;

      return calculation;
   }
   
   public double getSurfaceArea() {
      double s = (1/2) * ( (height * height) + (radius * radius) );
      double calculation = (Math.PI * radius * s) + (Math.PI * (radius * radius));

      return calculation;
   }
   
   public boolean equals(Cone c) {
      if(c == this) {
         return true;
      }
      
      return false;
   }
   
   public String toString() {
      String result = "Cone with radius " +
         radius + " and height " + height;
      
      return result;
   }
}