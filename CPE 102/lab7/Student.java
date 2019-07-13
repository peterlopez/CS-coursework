/**
* Simple class inheritance exercise
* @author Peter Lopez
* @version Lab 07
*/
public class Student extends Person {
   
   private double gpa;
   private int legs;
   private String name;
   
   public Student(String name, int legs, double gpa) {
      super(name, legs);
      this.gpa = gpa;
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
      if(this.legs != ((Student) other).legs) {
         return false;
      }
      // Compare name
      if(!this.name.equals(((Student) other).name)) {
         return false;
      }
      // Compare gpa
      if(this.gpa != ((Student) other).gpa) {
         return false;
      }
      
      return true;
   }
   
   public String toString() {
      String result = "I am an Animal object with "+
         this.legs+" legs and a Person object whose name is "+this.name +
            " and a Student Object with a "+this.gpa+" gpa";
      

      return result;
   }
}