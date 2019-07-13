/**
* @author Peter Lopez
* @version Program 6
*/
public class BasicLinkedListTest {

   private static final double E = 0.000001;

   public static void testAll() {
      testAdd();
      testAddAtIndex();
      testBasicListIterator();
      testClear();
      testContains();
      testGet();
      testIndexOf();
      testIterator();
      testRemove();
      testSet();
      testSize();
      testConstructor();
   }

   public static void testAdd() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertEqualsObject(myList.get(0), "First");
      SUnit.assertEqualsObject(myList.get(1), "Second");
   }
   
   public static void testAddAtIndex() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      myList.add(1, "Third");
      myList.add(0, "Fourth");
      
      SUnit.assertEqualsObject(myList.get(1), "Third");
      SUnit.assertEqualsObject(myList.get(0), "Fourth");
   }
   
   public static void testBasicListIterator() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      
      // Add stuff to list
      for(int j=0; j < 10; j++) {
         Integer myInt = j;
         myList.add(myInt.toString());
      }
      BasicListIterator<String> iterator = myList.basicListIterator();
      
      // Test iterator iterates through list
      // Forwards
      int i = 0;
      while(iterator.hasNext()) {
         Integer myInt = i;
         SUnit.assertEqualsObject(myInt.toString(), iterator.next());
         i++;
      }
      
      // Test iterator iterates through list
      // Backwards
      i = 10;
      while(iterator.hasPrevious()) {
         Integer myInt = i;
         SUnit.assertEqualsObject(myInt.toString(), iterator.previous());
         i--;
      }
      
      // Test iterator throws UnsupportedOperationException
      try {
         iterator.remove();
      }
      catch(UnsupportedOperationException e) {
         SUnit.assertTrue(true);
      }
   }
   
   public static void testClear() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      // Assert empty list
      myList.clear();
      SUnit.assertEquals(myList.size(), 0);
      
      // Assert no odd behaviour when list is empty
      myList.clear();
      SUnit.assertEquals(myList.size(), 0);
   }
   
   public static void testContains() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertTrue(myList.contains("First"));
      SUnit.assertTrue(myList.contains("Second"));
   }
   
   public static void testGet() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertEqualsObject(myList.get(0), "First");
      SUnit.assertEqualsObject(myList.get(1), "Second");
   }
   
   public static void testIndexOf() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertEquals(myList.indexOf("First"), 0);
      SUnit.assertEquals(myList.indexOf("Second"), 0);
   }
   
   public static void testIterator() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      
      // Add stuff to list
      for(int j=0; j < 10; j++) {
         Integer myInt = j;
         myList.add(myInt.toString());
      }
      java.util.Iterator iterator = myList.basicListIterator();
      
      // Test iterator iterates through list
      int i = 0;
      while(iterator.hasNext()) {
         Integer myInt = i;
         SUnit.assertEqualsObject(myInt.toString(), iterator.next());
         i++;
      }
      
      // Test iterator throws UnsupportedOperationException
      try {
         iterator.remove();
      }
      catch(UnsupportedOperationException e) {
         SUnit.assertTrue(true);
      }
   }
   
   public static void testRemove() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertEqualsObject(myList.remove(0), "First");
      SUnit.assertEqualsObject(myList.remove(0), "Second");
   }
   
   public static void testSet() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      myList.add("First");
      myList.add("Second");
      
      SUnit.assertEqualsObject(myList.set(0, "Second"), "First");
      SUnit.assertEqualsObject(myList.set(0, "Third"), "Second");
      SUnit.assertEqualsObject(myList.get(0), "Third");
   }
   
   public static void testSize() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      
      myList.add("First");
      myList.add("Second");
      SUnit.assertEquals(2, myList.size());
      
      myList.add("Third");
      SUnit.assertEquals(3, myList.size());
   }
   
   public static void testConstructor() {
      BasicLinkedList<String> myList = new BasicLinkedList<String>();
      
      SUnit.assertEquals(0, myList.size());
   }
   
   public static void main(String[] args) {
      // Run all the tests...
      testAll();

      // Display number of tests run and how many failed...
      System.out.println("SUnit: "
                       + SUnit.testsRun() + " tests run, "
                       + SUnit.testsFailed() + " tests failed");
   }
}