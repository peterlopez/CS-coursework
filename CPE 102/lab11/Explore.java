/**
* Exploring the world of iterators with LinkedLists
* @author Peter Lopez
* @version Lab11
*/
public class Explore {

   public static void main(String[] args) {
      java.util.LinkedList<String> myStrList = new java.util.LinkedList<String>();
      java.util.LinkedList<Integer> myIntList = new java.util.LinkedList<Integer>();
      
      myStrList.add("Hello");
      myStrList.add("World");
      myStrList.add("My");
      myStrList.add("Name");
      myStrList.add("is");
      myStrList.add("Peter");
      myStrList.add("Lopez");
      
      myIntList.add(new Integer(3));
      myIntList.add(new Integer(2));
      myIntList.add(new Integer(1));
      myIntList.add(new Integer(9));
      myIntList.add(new Integer(7));
      myIntList.add(new Integer(8));
      myIntList.add(new Integer(4));
      myIntList.add(new Integer(5));
      myIntList.add(new Integer(6));
      
      // Get iterators for LinkedLists
      java.util.ListIterator strIterator = myStrList.listIterator();
      java.util.ListIterator intIterator = myIntList.listIterator();
      
      // Loop through values using iterators
      System.out.println("String list while loop");
      while (strIterator.hasNext()) {
         System.out.println(strIterator.next());
      }
      System.out.println("Integer list while loop");
      while (intIterator.hasNext()) {
         System.out.println(intIterator.next());
      }

      System.out.println("String list foreach loop");
      for (String value : myStrList) {
         System.out.println(value);
      }

      System.out.println("Integer list foreach loop");
      for (Integer value : myIntList) {
         System.out.println(value);
      }
   }
}