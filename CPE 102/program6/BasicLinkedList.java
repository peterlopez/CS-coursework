/**
* @author Peter Lopez
* @version Program 6
*/
public class BasicLinkedList<E> implements BasicList {
   
   private int size;
   private Node head;
   
   public void add(E data) {
      Node temp = new Node();
      
      // Assign data to temp
      temp.data = data;
      
      // Update temp to point to new node
      temp.prev = head.prev; 
      temp.next = head;
      
      // Update head to point to temp
      head.prev.next = temp;
      head.prev = temp;
      
      size++;
   }
   public void add(int index, E element) {
      
   }
   public void clear() {
      
   }
   public boolean contains(E element) {
      return false;
   }
   public E get(int index) {
      return null;
   }
   public int indexOf(E element) {
      return 0;
   }
   public E remove(int index) {
      return null;
   }
   public E set(int index, E element) {
      return null;
   }
   public int size() {
      return 0;
   }
   public java.util.Iterator<E> basicListIterator() {
      return new BasicListIterator<E>();
   }
   
   private class Node {
      public E data;
      public Node next, prev;
   }
   private class basicIterator implements BasicListIterator {
      // Stuff
      private Node cursor = head;
      
      public boolean hasNext() {
         return !(cursor.next == head);
      }
      
      public E next() {
         cursor = cursor.next;
         return cursor.data;
      }
   }
}