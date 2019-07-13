/**
* A concrete implementation of an abstract data type Stack
* using LinkedLists
* @author Peter Lopez
* @version Lab11
*/
public class Stack<E>
{
   java.util.LinkedList<E> stack = new java.util.LinkedList<E>();
   
   public Stack() {
      
   }
   
   public void push(E item) {
      stack.add(item);
   }
   
   public E pop() {
      E result = stack.getLast();
      stack.removeLast();
      
      return result;
   }
}