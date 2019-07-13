/**
* Simple class inheritance exercise
* @author Peter Lopez
* @version Lab 07
*/
public class Animal {
   
   private int legs;
   
   public Animal(int legs) {
      this.legs = legs;
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
      // Compare number of legs
      if(this.legs != ((Animal) other).legs) {
         return false;
      }
      
      return true;
   }
   
   public String toString() {
      String result = "I am an Animal object with "+this.legs+" legs";

      return result;
   }
}