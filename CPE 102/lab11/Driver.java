/**
* A concrete implementation of an abstract data type Stack
* using LinkedLists
* @author Peter Lopez
* @version Lab11
*/
public class Driver {
   
   public static void main(String[] args) {
      Stack<String> stack = new Stack<String>();
      stack.push("A");
      stack.push("B");
      stack.push("C");
      System.out.println(stack.pop());
      stack.push("D");
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop()); 
   }
}