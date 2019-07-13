/**
* Simple class inheritance exercise
* @author Peter Lopez
* @version Lab 07
*/
public class Person extends Animal {
   
   private String name;
   private int legs;
   
   public Person(String name, int legs) {
      super(legs);
      this.legs = legs;
      this.name = name;
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
      if(this.legs != ((Person) other).legs) {
         return false;
      }
      // Compare name
      if(!this.name.equals(((Person)other).name)) {
         return false;
      }
      
      return true;
   }
   
   public String toString() {
      String result = "I am an Animal object with "+
         this.legs+" legs and a Person object whose name is "+this.name;
      

      return result;
   }
}