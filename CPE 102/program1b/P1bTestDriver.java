/**
 * Unit test driver for the ComplexNumber Class.
 *
 * @author Kurt Mammen
 * @version 01/04/2014 - Renamed P1bTestDriver and modified success message
 *                       to mention the new testing requirment.
 * @version 12/22/2010 - Added function name verifications
 * @version 10/01/2009
 */
import java.util.Scanner;
import java.util.Arrays;
import java.lang.reflect.*;

public class P1bTestDriver 
{
   private static final String RESULTS_FOR = "Results for Program 1b";
   
   public static void main(String[] args)
   {
      boolean pass = true;
      printHeader(args);
      pass &= testArchitecture();
      
      if (pass)
      {
         // Constructor tests use the getImaginary and getReal methods to verify
         // results so any reported error could be because the constructor
         // is wrong or because the accessor method(s) are wrong.
         pass &= testDefaultConstructor();
         pass &= testSecondConstructor();
         
         pass &= testToString();
         pass &= testEquals();
         
         // These tests all use the getImaginary and getReal methods to verify
         // results so any reported error could be because the method being
         // tested is wrong or because the accessor method(s) are wrong.
         pass &= testAdd();
         pass &= testSub();
         pass &= testMul();
         pass &= testDiv();    
      }
      
      printResults(pass);
      
       // Added for to support robust script checking
      if (!pass)
      {
         System.exit(-1);
      }
   }

   private static boolean testArchitecture()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      Class cl;
      Class[] temp;
      
      System.out.println("   Testing ComplexNumber architecture...");
       
      cl = ComplexNumber.class;

      int cnt = cl.getConstructors().length;     
      pass &= test(cnt == 2, msg + cnt + " constructors, expected 2");
      
      String[] names = {"add", "sub", "mul", "div", "getReal", "getImaginary",
                        "equals", "toString"};

      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);
      pass &= test(cnt == names.length,
                   msg + cnt + " public non-constructor methods found, expected " + names.length);

      pass &= test(verifyNames(cl.getDeclaredMethods(), Modifier.PUBLIC, names), "");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 0, msg + cnt + " public fields found, expected 0");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, msg + cnt + " protected fields found, expected 0");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 2, msg + cnt + " private fields found, expected 2");
         
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);
      pass &= test(cnt == 0, msg + cnt + " package fields, expected 0");
 
      return pass;
   }
      
   // Test default constructor
   private static boolean testDefaultConstructor()
   {
      boolean pass = true;
      System.out.println("   Testing default constructor - ComplexNumber()...");
      
      pass &= test(new ComplexNumber(), 0, 0);
     
      return pass;
   }

   // Test second constructor
   private static boolean testSecondConstructor()
   {
      boolean pass = true;
      System.out.println("   Testing constructor - ComplexNumber(double, double)...");
      String msg = "      FAILED: ";
            
      pass &= test(new ComplexNumber(-3.7531, 9.9876), -3.7531, 9.9876);
     
      return pass;
   }
   
   // Test toString
   private static boolean testToString()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing toString()...");
      
      ComplexNumber cn = new ComplexNumber(5.37465, 8.910238);
      pass &= test(cn.toString().equals("(5.37465+8.910238i)"), msg +
         "toString() is " + cn.toString() + ", expected (5.37465+8.910238i)");

      cn = new ComplexNumber(-5.37465, 8.910238);
      pass &= test(cn.toString().equals("(-5.37465+8.910238i)"), msg +
         "toString() is " + cn.toString() + ", expected (-5.37465+8.910238i)");

      cn = new ComplexNumber(5.37465, -8.910238);
      pass &= test(cn.toString().equals("(5.37465-8.910238i)"), msg +
         "toString() is " + cn.toString() + ", expected (5.37465-8.910238i)");

      cn = new ComplexNumber(-5.37465, -8.910238);
      pass &= test(cn.toString().equals("(-5.37465-8.910238i)"), msg +
         "toString() is " + cn.toString() + ", expected (-5.37465-8.910238i)");
               
      return pass;
   }

   // Test equals
   private static boolean testEquals()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing equals(ComplexNumber)...");
      
      ComplexNumber cn1 = new ComplexNumber(5.5, 6.6);
      ComplexNumber cn2 = new ComplexNumber(1.1, -2.2);

      pass &= test(cn1.equals(cn1), msg + "equals() is false, expected true");
      pass &= test(!cn1.equals(cn2), msg + "equals() is true, expected false");
         
      return pass;
   }

   // Test add
   private static boolean testAdd()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing add(ComplexNumber)...");
      
      ComplexNumber cn1 = new ComplexNumber();
      ComplexNumber cn2 = new ComplexNumber(2.6, 3.7);
      ComplexNumber cn3 = new ComplexNumber(4.7, 5.5);
      ComplexNumber cn4 = new ComplexNumber(-4.7, -5.5);

      pass &= test(cn1.add(cn1), 0, 0);
      pass &= test(cn1, 0, 0);
     
      pass &= test(cn2.add(cn2), 5.2, 7.4);
      pass &= test(cn2, 2.6, 3.7);

      pass &= test(cn1.add(cn2), 2.6, 3.7);
      pass &= test(cn1, 0, 0);
      pass &= test(cn2, 2.6, 3.7);

      // Remember, just like C, floats and doubles are sometime slightly imprecise,
      // the price we pay for the floating decimal and and expanded range!
      pass &= test(cn2.add(cn3), 7.300000000000001, 9.2);
      pass &= test(cn2, 2.6, 3.7);
      pass &= test(cn3, 4.7, 5.5);

      pass &= test(cn3.add(cn4), 0, 0);
      pass &= test(cn3, 4.7, 5.5);
      pass &= test(cn4, -4.7, -5.5);

      // Remember, just like C, floats and doubles are sometime slightly imprecise,
      // the price we pay for the floating decimal and and expanded range!
      pass &= test(cn2.add(cn4), -2.1, -1.7999999999999998);
      pass &= test(cn2, 2.6, 3.7);
      pass &= test(cn4, -4.7, -5.5);

      return pass;
   }
  
   // Test sub
   private static boolean testSub()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing sub(ComplexNumber)...");
      
      ComplexNumber cn1 = new ComplexNumber();
      ComplexNumber cn2 = new ComplexNumber(1.123, 2.234);
      ComplexNumber cn3 = new ComplexNumber(3.345, 4.456);
      ComplexNumber cn4 = new ComplexNumber(-3.345, -4.456);

      pass &= test(cn1.sub(cn1), 0, 0);
      pass &= test(cn1, 0, 0);
      
      pass &= test(cn1.sub(cn2), -1.123, -2.234);
      pass &= test(cn1, 0, 0);
      pass &= test(cn2, 1.123, 2.234);
      
      // Remember, just like C, floats and doubles are sometime slightly imprecise,
      // the price we pay for the floating decimal and and expanded range!
      pass &= test(cn2.sub(cn3), -2.2220000000000004, -2.2220000000000004);
      pass &= test(cn2, 1.123, 2.234);
      pass &= test(cn3, 3.345, 4.456);

      // Remember, just like C, floats and doubles are sometime slightly imprecise,
      // the price we pay for the floating decimal and and expanded range!
      pass &= test(cn3.sub(cn2), 2.2220000000000004, 2.2220000000000004);
      pass &= test(cn2, 1.123, 2.234);
      pass &= test(cn3, 3.345, 4.456);

      pass &= test(cn2.sub(cn4), 4.468, 6.69);
      pass &= test(cn2, 1.123, 2.234);
      pass &= test(cn4, -3.345, -4.456);

      return pass;
   }
   
   // Test mul
   private static boolean testMul()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing mul(ComplexNumber)...");
      
      ComplexNumber cn0 = new ComplexNumber();
      ComplexNumber cn1 = new ComplexNumber(0, 1);
      ComplexNumber cn2 = new ComplexNumber(1.1, 9.9);
      ComplexNumber cn3 = new ComplexNumber(4.4, 7.7);

      pass &= test(cn0.mul(cn3), 0, 0);
      pass &= test(cn0, 0, 0);
      pass &= test(cn3, 4.4, 7.7);

      pass &= test(cn1.mul(cn1), -1, 0);
      pass &= test(cn1, 0, 1);

      pass &= test(cn2.mul(cn3), -71.39, 52.03);
      pass &= test(cn2, 1.1, 9.9);
      pass &= test(cn3, 4.4, 7.7);

      return pass;
   }

   // Test div
   private static boolean testDiv()
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      System.out.println("   Testing div(ComplexNumber)...");
      
      ComplexNumber cn1 = new ComplexNumber(1.1, 9.9);
      ComplexNumber cn2 = new ComplexNumber(4.4, 7.7);
      ComplexNumber result = cn1.div(cn2);

      // Remember, just like C, floats and doubles are sometimes slightly
      // imprecise, the price we pay for the expanded range!
      pass &= test(cn1.div(cn2), 1.0307692307692309, 0.4461538461538462); 
      
      return pass;
   }
   
   private static void printHeader(String[] args)
   {
      if (args.length == 1)
      {
         System.out.println(args[0]);
      }
      
      System.out.println(RESULTS_FOR + "\n");
   }
   
   private static void printResults(boolean pass)
   {
      String msg;
      
      if(pass)
      {
         msg = "\nCongratulations, you passed all the tests!\n\n"
            + "Your grade will be based on when you turn in your functionally\n"
            + "correct solution and any deductions for the quality of your\n"
            + "implementation.  Quality is based on, but not limited to,\n"
            + "any required tests, coding style, documentation requirements,\n"
            + "compiler warnings, and the efficiency and elegance of your code.\n";
      }
      else
      {
         msg = "\nNot done yet - you failed one or more tests!\n";
      }
      
      System.out.print(msg);       
   }
   
   private static boolean test(boolean pass, String msg)
   {
      if(!pass)
      {
         System.out.println(msg);
      }
      
      return pass;
   }
   
   private static boolean test(ComplexNumber cn, double r, double i)
   {
      boolean pass = true;
      String msg = "      FAILED: ";
      
      pass &= test(cn.getReal() == r, msg +
         "Real part is " + cn.getReal() + ", expected " + r);
         
      pass &= test(cn.getImaginary() == i, msg + 
         "Imaginary part is " + cn.getImaginary() + ", expected " + i);
         
      return pass;     
   }
   
   private static int countModifiers(Field[] fields, int modifier)
   {
      int count = 0;
      
      for (Field f : fields)
      {
         if (f.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }
   
   private static int countModifiers(Method[] methods, int modifier)
   {
      int count = 0;
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }

   private static boolean verifyNames(Method[] methods, int modifier, String[] names)
   {
      Arrays.sort(names);
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            if (Arrays.binarySearch(names, m.getName()) < 0)
            {
               System.out.println("      FAILED: Missing method " + m.getName() + "()");
               return false;
            }
         }
      }
      
      return true;
   }
}
