/**
* Tests for class BasicArrayList
* that realizes interface BasicList
*
* @author Peter Lopez
* @version Program 1B
*/
public class BasicArrayListTest
{
   private static final double E = 0.000001;

   public static void testAll()
   {
      testConstructor();  
      testSize();
      testAdd();
      testClear();
      testContains();
      testGet();
      testIndexOf();
      testRemove();
   }

   public static void testConstructor() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      sUnit.assertEquals(10, test.capacity());
      
      BasicArrayList test2 = new BasicArrayList(25);
      sUnit.assertEquals(25, test2.capacity());
      
      BasicArrayList test3 = new BasicArrayList(102);
      sUnit.assertEquals(102, test3.capacity());
   }
   public static void testSize() {
      SUnit sUnit = new SUnit();
      
      // Start with empty list
      BasicArrayList test = new BasicArrayList();
      sUnit.assertEquals(0, test.size());
      
      // Add some stuff to it
      Integer i = new Integer(4);
      test.add(i);
      sUnit.assertEquals(1, test.size());
      
      // Add some more stuff to it
      String myString = "Hello, world!";
      Double d = new Double(1.00000001);
      BasicArrayList newList = new BasicArrayList();
      test.add(myString);
      test.add(d);
      test.add(newList);
      sUnit.assertEquals(4, test.size());
   }
   public static void testAdd() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      // Test simple add method
      test.add(new Boolean(true));
      test.add(new Boolean(false));
      
      sUnit.assertEqualsObject(new Boolean(true), test.get(0));
      sUnit.assertEqualsObject(new Boolean(false), test.get(1));
      
      // Test overloaded add method with index parameter
      test.add(1, new Integer(47));
      sUnit.assertEqualsObject(new Integer(47), test.get(1));
      
      test.add(1, new Double(55.01));
      sUnit.assertEqualsObject(new Double(55.01), test.get(1));
      
      test.add(1, new Double(11.1));
      test.add(1, new Double(11.2));
      test.add(1, new Double(11.3));
      test.add(1, new Double(11.4));
      test.add(1, new Double(11.5));
      test.add(1, new Double(11.6));
      
      // List should not have grown
      sUnit.assertEquals(10, test.capacity());
      
      test.add(10, new Double(11.7));
      
      // Make sure it grew
      sUnit.assertEquals(20, test.capacity());
      
      test.add(1, new Double(11.8));
      test.add(1, new Double(11.9));
      
      sUnit.assertEqualsObject(new Double(11.9), test.get(1));
      sUnit.assertEqualsObject(new Double(11.8), test.get(2));
      sUnit.assertEqualsObject(new Double(11.7), test.get(12));
      //System.out.println(((Object)test.get(10)).toString()+((Object)test.get(11)).toString()+((Object)test.get(12)).toString());
      
   }
   public static void testClear() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      String myString = "Foo bar";
      Double d = new Double(1.111112);
      test.add(myString);
      test.add(d);
      sUnit.assertEquals(2, test.size());
      
      test.clear();
      sUnit.assertEquals(0, test.size());
      
   }
   public static void testContains() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      String myString = "Foo bar";
      Double d = new Double(1.111112);
      test.add(myString);
      test.add(d);
      
      sUnit.assertTrue(test.contains(myString));
      sUnit.assertTrue(test.contains(d));
      
      sUnit.assertFalse(test.contains(new Integer(1)));
      sUnit.assertFalse(test.contains(new BasicArrayList()));
   }
   public static void testGet() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      String myString = "The quick brown fox jumped over the lazy dog";
      Float myFloat = new Float(0.0123456789);
      test.add(myString);
      test.add(myFloat);
      
      sUnit.assertEqualsObject(myString, (String) test.get(0));
      sUnit.assertEqualsObject(myFloat, (Float) test.get(1));
   }
   public static void testIndexOf() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      Boolean boolTrue = new Boolean(true);
      Boolean boolFalse = new Boolean(false);
      test.add(boolTrue);
      test.add(boolFalse);
      
      sUnit.assertEquals(0, test.indexOf(boolTrue));
      sUnit.assertEquals(1, test.indexOf(boolFalse));
      
      test.add(new String("Foo bar"));
      sUnit.assertEquals(2, test.indexOf(new String("Foo bar")));
   }
   public static void testRemove() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();

      test.add(new Boolean(true));
      test.add(new Boolean(false));
      test.add(new Double(1.111));
      test.remove(1);
      
      sUnit.assertEquals(2, test.size());
      sUnit.assertEqualsObject(test.get(0), new Boolean(true));
      sUnit.assertEqualsObject(test.get(1), new Double(1.111));
   }
   public static void testSet() {
      SUnit sUnit = new SUnit();
      BasicArrayList test = new BasicArrayList();
      
      test.add(new Boolean(true));
      test.add(new Double(1.83));
      test.set(0, new Boolean(false));
      
      sUnit.assertEqualsObject(new Boolean(false), test.get(0));
      sUnit.assertEquals(3, test.size());
   }
   
   /**
    * DO NOT MODIFY THIS METHOD! Calls testAll to run all tests and report
    * the number of tests that were run and how many failed. Not putting
    * the tests in main allows them to be run by another program if and
    * when desired.
    * 
    * @param args Any arguments specified when the program was run.
    */
   public static void main(String[] args)
   {
      // Run all the tests...
      testAll();

      // Display number of tests run and how many failed...
      System.out.println("SUnit: "
                       + SUnit.testsRun() + " tests run, "
                       + SUnit.testsFailed() + " tests failed");
   }
}
