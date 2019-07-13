/**
* Complex number
*
* @author Peter Lopez
* @version Program 1B
*/

public class ComplexNumberTest {
   
   public static void main(String[] args) {
      testConstructor();
      testAdd();
      testSub();
      testMul();
      //testDiv();
      testGetReal();
      testGetImaginary();
      testEquals();
   }
   
   public static void testConstructor() {
      ComplexNumber testObject = new ComplexNumber();
      SUnit sUnit = new SUnit();
      
      sUnit.assertEquals(0, testObject.getReal(), 0.00001);
      sUnit.assertEquals(0, testObject.getImaginary(), 0.00001);
      
      testObject = new ComplexNumber(2,5);
      sUnit.assertEquals(2, testObject.getReal(), 0.00001);
      sUnit.assertEquals(5, testObject.getImaginary(), 0.00001);
   }
   
   public static void testAdd() {
      ComplexNumber test1 = new ComplexNumber(1,2);
      ComplexNumber test2 = new ComplexNumber(5,4);
      ComplexNumber result = test2.add(test1);
      
      SUnit sUnit = new SUnit();
      sUnit.assertEquals(6, result.getReal(), 0.00001);
      sUnit.assertEquals(6, result.getImaginary(), 0.00001);
      
      test1 = new ComplexNumber(2.134,42.1);
      test2 = new ComplexNumber(1.00001,4.56);
      result = test2.add(test1);
      
      sUnit.assertEquals(3.13401, result.getReal(), 0.00001);
      sUnit.assertEquals(46.66, result.getImaginary(), 0.00001);
      
   }
   public static void testSub() {
      ComplexNumber test1 = new ComplexNumber(1,2);
      ComplexNumber test2 = new ComplexNumber(5,4);
      
      ComplexNumber result = test1.sub(test2);
      SUnit sUnit = new SUnit();
      
      sUnit.assertEquals(-4, result.getReal(), 0.00001);
      sUnit.assertEquals(-2, result.getImaginary(), 0.00001);
      
      test1 = new ComplexNumber(4.000321, 31.111111);
      test2 = new ComplexNumber(-3.1, 31);
      
      result = test1.sub(test2);
      sUnit.assertEquals(7.100321, result.getReal(), 0.00001);
      sUnit.assertEquals(0.111111, result.getImaginary(), 0.00001);
   }
   public static void testMul() {
      ComplexNumber test1 = new ComplexNumber(1,4);
      ComplexNumber test2 = new ComplexNumber(2,8);
      
      ComplexNumber result = test1.mul(test2);
      SUnit sUnit = new SUnit();
      
      sUnit.assertEquals(-30, result.getReal(), 0.00001);
      sUnit.assertEquals(16, result.getImaginary(), 0.00001);
      
      test1 = new ComplexNumber(4.000321, 31.111111);
      test2 = new ComplexNumber(-3.1, 31);
      
      result = test1.mul(test2);
      sUnit.assertEquals(-976.8454361, result.getReal(), 0.00001);
      sUnit.assertEquals(27.5655069, result.getImaginary(), 0.00001);
   }
   public static void testDiv() {
      ComplexNumber test1 = new ComplexNumber(988,12.2310);
      ComplexNumber test2 = new ComplexNumber(2.2222,888);
      
      ComplexNumber result = test1.mul(test2);
      SUnit sUnit = new SUnit();
      
      sUnit.assertEquals(792983.568310744, result.getReal(), 0.000000001);
      sUnit.assertEquals(607301.958238597, result.getImaginary(), 0.000000001);
      
      test1 = new ComplexNumber(5, 2);
      test2 = new ComplexNumber(7, 4);
      
      result = test1.mul(test2);
      sUnit.assertEquals(34.876923077, result.getReal(), 0.00001);
      sUnit.assertEquals(0.415384615, result.getImaginary(), 0.00001);
   }
   
   public static void testGetReal() {
      SUnit sUnit = new SUnit();
      
      ComplexNumber testObject = new ComplexNumber(303, 1);
      sUnit.assertEquals(303, testObject.getReal(), 0.00001);
      
      testObject = new ComplexNumber(-212.2, 9);
      sUnit.assertEquals(-212.2, testObject.getReal(), 0.00001);
   }
   public static void testGetImaginary() {
      SUnit sUnit = new SUnit();
      
      ComplexNumber testObject = new ComplexNumber(2.123, 0);
      sUnit.assertEquals(0, testObject.getImaginary(), 0.00001);
      
      testObject = new ComplexNumber(0, 4449);
      sUnit.assertEquals(4449, testObject.getImaginary(), 0.00001);
   }
   public static void testToString() {
      SUnit sUnit = new SUnit();
      
      ComplexNumber testObject = new ComplexNumber(912.2, 330);
      String expectedStr = "(912.2+330i)";
      
      if(! expectedStr.equals(testObject.toString()) ) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected String X, found String Y");
         exception.printStackTrace();
      }
      
      testObject = new ComplexNumber(3.3333, -1.1);
      expectedStr = "(3.3333-1.1)";
      
      if(! expectedStr.equals(testObject.toString()) ) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected String X, found String Y");
         exception.printStackTrace();
      }
   }
   
   public static void testEquals() {
      ComplexNumber test1 = new ComplexNumber(4.4, 3.3);
      ComplexNumber test2 = new ComplexNumber(4.4, 3.3);
      
      if(! test1.equals(test2)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected object equality, found inequality");
         exception.printStackTrace();
      }
      
      
   }
}