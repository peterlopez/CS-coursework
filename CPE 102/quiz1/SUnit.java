/**
* @author Peter Lopez
* @version Lab Quiz 1
*/
public class SUnit {
   
   public static void assertEquals(double a, double b) {
      if(a != b) {
         java.lang.Throwable exception = new Exception(
            "Error: Expecting X, Found Y");
         exception.printStackTrace();
      }
   }
}