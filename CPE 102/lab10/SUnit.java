/**
* Simple unit testing class for Java
* 
* @author Peter Lopez
* @version Program 1A
*/

class SUnit {
   
   private static int failed;
   private static int total;
   
   public static void assertEquals(double expect, double actual, double epsilon) {
      total++;
      
      if(Math.abs(actual-expect) > epsilon) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected X +/-E, found Y");
         exception.printStackTrace();
      }
   }
   
   public static void assertEquals(long expect, long actual) {
      total++;
      
      if(expect != actual) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected X, found Y");
         exception.printStackTrace();
      }
   }
   
   public static void assertEqualsObject(java.lang.Object expect, java.lang.Object actual) {
      total++;
      
      if(! expect.equals(actual)) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected object equality, found inequality");
         exception.printStackTrace(); 
      }
   }
   
   public static void assertEqualsReference(java.lang.Object expect, java.lang.Object actual) {
      total++;
      
      if(expect != actual) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected reference equality, found inequality");
         exception.printStackTrace(); 
      }
   }
   
   public static void assertFalse(boolean actual) {
      total++;
      
      if(actual != false) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
               "Error: Expected false, found true");
         exception.printStackTrace();

      }
   }
   
   public static void assertNotNull(java.lang.Object actual) {
      total++;
      
      if(actual == null) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
               "Error: Expected non-null, found null");
         exception.printStackTrace();
      }
   }
   
   public static void assertNull(java.lang.Object actual) {
      total++;
      
      if(actual != null) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected null, found non-null");
         exception.printStackTrace();
      }
   }
   
   public static void assertTrue(boolean actual) {
      total++;
      
      if(!actual) {
         failed++;
         
         java.lang.Throwable exception = new Throwable(
            "Error: Expected true, found false");
         exception.printStackTrace(); 
      }
   }
   
   public static int testsFailed() {
      return failed;
   }
   
   public static int testsRun() {
      return total;
   }
}