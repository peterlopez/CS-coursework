/**
 * Tests for SUnit, a simple unit testing framework.
 *
 * @author Kurt Mammen
 * @version 01/14/2014 - Initial revision developed for CPE 102 Program 1a
 */
import java.util.Scanner;
import java.io.*;

public class SUnitTest
{
   private static final String RESULTS_FOR = "Results for Program 1a";

   // Keeps track of any failure during all testing. This is updated
   // by all tests.
   private static boolean allPass = true;

   // Counts used in tests methods - declared here for readability
   // instead of making local. These are updated in updateCounts().
   private static int testsRun;
   private static int testsFailed;

   // Used to verify the number tests run. This is updated in updateCounts();
   private static int expectedTestsRun;
   
   // Used to verify the number of tests that fail AND to calculate the
   // number of lines that should be written to System.err. It is updated
   // in verifyCounts();
   private static int expectedTestsFailed;

   // File to redirect System.printStackTrace() output to for testing.
   private static final String ERR_FILE = "SUnitTest.err";

   public static void main(String[] args) throws IOException
   {
      printHeader(args);

      // Save all System.printStackTrace() output to a file for checking
      PrintStream saveErr = System.err;
      System.setErr(new PrintStream(new File(ERR_FILE)));

      testAssertTrue();
      testAssertFalse();
      testAssertEqualsLong();
      testAssertEqualsDouble();
      testAssertEqualsObject();
      testAssertEqualsReference();
      testAssertNull();
      testAssertNotNull();

      // Close redirected err and restore original
      System.err.close();
      System.setErr(saveErr);

      testErrOutput();

      printResults();

      // Added for robust script checking
      if (!allPass)
      {
         System.exit(-1);
      }
   }

   private static void testAssertTrue()
   {
      updateCounts();
      SUnit.assertTrue(true);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertTrue(false);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertFalse()
   {
      updateCounts();
      SUnit.assertFalse(false);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertFalse(true);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertEqualsLong()
   {
      updateCounts();
      SUnit.assertEquals(9876543210L, 9876543210L);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertEquals(9876543210L, 9876543211L);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertEqualsDouble()
   {
      updateCounts();
      SUnit.assertEquals(0.123456, 0.123456, 0.000001);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertEquals(0.123456, 0.1234569, 0.000001);
      verifyCounts(testsRun + 1, testsFailed + 0);

      updateCounts();
      SUnit.assertEquals(0.123456, 0.1234571, 0.000001);
      verifyCounts(testsRun + 1, testsFailed + 1);

      updateCounts();
      SUnit.assertEquals(-100.0, -100.0, 10);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertEquals(-100.0, -110.0, 10);
      verifyCounts(testsRun + 1, testsFailed + 0);

      updateCounts();
      SUnit.assertEquals(-100.0, -110.1, 10);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }
      
   private static void testAssertEqualsObject()
   {
      String a1 = new String("apple");
      String a2 = new String("apple");
      String o = new String("orange");

      updateCounts();
      SUnit.assertEqualsObject(a1, a2);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertEqualsObject(a1, o);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertEqualsReference()
   {
      String a1 = new String("apple");
      String a2 = new String("apple");
      String a3 = a2;

      updateCounts();
      SUnit.assertEqualsReference(a2, a3);
      verifyCounts(testsRun + 1, testsFailed + 0);
      
      updateCounts();
      SUnit.assertEqualsReference(a1, a2);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertNull()
   {
      Object o = null;

      updateCounts();
      SUnit.assertNull(o);
      verifyCounts(testsRun + 1, testsFailed + 0);

      o = new Object();

      updateCounts();
      SUnit.assertNull(o);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testAssertNotNull()
   {
      Object o = new Object();

      updateCounts();
      SUnit.assertNotNull(o);
      verifyCounts(testsRun + 1, testsFailed + 0);

      o = null;

      updateCounts();
      SUnit.assertNotNull(o);
      verifyCounts(testsRun + 1, testsFailed + 1);
   }

   private static void testErrOutput() throws FileNotFoundException
   {
      int lines = 0;
      Scanner scan = new Scanner(new File(ERR_FILE));

      while(scan.hasNextLine())
      {
         lines++;
         scan.nextLine();
      }

      if (lines != expectedTestsFailed * 4)
      {
         allPass = false;
         new Throwable("Expected " + (expectedTestsFailed * 4)
                     + " lines to System.err, found " + lines
                     + " (see SUnitTest.err for actual output)").printStackTrace();
      }
   }

   // Testing helper functions...
   private static void updateCounts()
   {
      expectedTestsRun++;
      testsRun = SUnit.testsRun();
      testsFailed = SUnit.testsFailed();
   }

   private static void verifyCounts(int expectedRun, int expectedFailed)
   {
      int actualRun = SUnit.testsRun();
      int actualFailed = SUnit.testsFailed();

      if (expectedRun != actualRun)
      {
         allPass = false;
         new Throwable("Expected " + expectedRun
                     + " tests run, found " + actualRun).printStackTrace(System.out);
      }

      if (expectedFailed != actualFailed)
      {
         allPass = false;
         new Throwable("Expected " + expectedFailed
                     + " failed tests, found " + actualFailed).printStackTrace(System.out);
      }
      
      // Count expected failures so number of lines printed
      // to System.err can be calculated.
      if (expectedFailed > testsFailed)
      {
         expectedTestsFailed++;
      }
   }

   private static void printHeader(String[] args)
   {
      if (args.length == 1)
      {
         System.out.println(args[0]);
      }
      
      System.out.println(RESULTS_FOR + "\n");
   }

   private static void printResults()
   {
      String msg;
      
      if(allPass)
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
}
