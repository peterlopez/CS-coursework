/**
* @author Peter Lopez
* @version Lab Quiz 1
*/
public class ConeTest {
   
   private int numberOfTests;
   private int failedTests;
   
   public static void main(String[] args) {
      //testConstructor();
      testEquals();
      
      //System.out.println("Total number of tests: " + getNumberOfTests());
      //System.out.println("Total failed tests: " + getFailedTests());
   }
   
   public int getNumberOfTests() {
      return numberOfTests;
   }
   public int getFailedTests() {
      return 0; //failedTests
   }
   
   public static void testEquals() {
      Cone c = new Cone();
      Cone m = new Cone();
      
      if(c.equals(m)) {
         System.out.println("TRUE");
      }
   }
   
   public static void testConstructor() {
      Cone c = new Cone();
      Cone m = new Cone();
      
      SUnit sUnit = new SUnit();
      
      sUnit.assertEquals(1.0, c.getRadius());
      sUnit.assertEquals(1.5, c.getHeight());
   }
}