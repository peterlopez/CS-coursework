/**
* Advanced ArrayList operations using interface
*
* @author Peter Lopez
* @version Program 02
*/
public class BasicArrayList implements BasicList {

   private int size = 0;
   private Object[] myList;
   public static final int DEFAULT_CAPACITY = 10;
   
   public BasicArrayList() {
      myList = new Object[DEFAULT_CAPACITY];
   }
   public BasicArrayList(int capacity) {
      myList = new Object[capacity];
   }
   
   public void add(java.lang.Object o) {
      // Throw exception when adding item
      // exceed capacity
      if(size + 1 > myList.length) { 
         Object[] result = new Object[myList.length * 2];
      
         for(int i=0; i < myList.length; i++) {
            result[i] = myList[i];
         }
         myList = result;
      }
      
      // Add element to end of array
      myList[size] = o;
      size++;
   }
   public void add(int index, java.lang.Object o) {
      // Throw exception when index
      // is out of bounds of the Arraylist
      if(index < 0) {
         throw new java.lang.IndexOutOfBoundsException();
      }
      if(index > size || index != 0 || index > myList.length) {
         throw new java.lang.IndexOutOfBoundsException();
      }
      
      // There is a large enough capacity
      // to hold a new element
      if(size + 1 <= myList.length) {
         for(int i = myList.length - 1; i >= 0; i--) {
            // Move items up to allow room
            // for new element
            if(i > index) {
               myList[i] = myList[i-1];
            }
            if(i == index) {
               myList[i] = o;
            }
         }
         this.size++;
         return;
      }
      
      // The additional element will take list
      // over capacity, so double the capacity
      // and copy elements into new list
      Object result[] = new Object[2 * myList.length];
      
      for(int i = size; i > 0; i--) {
         // Move item in destination position
         // and add new element into list
         if(i == index) {
            Object tempItem = myList[i];
            result[i] = o;
            result[i+1] = tempItem;
         }
         // Move items passed index
         // to new higher position
         // in destination list
         else if(i > index) {
            result[i+1] = myList[i];
         }
         else {
            result[i] = myList[i];
         }
      }

      this.size++;
      myList = result;
   }
   
   public void clear() {
      size = 0;
      
      for(int i=0; i < myList.length; i++) {
         myList[i] = null;
      }
   }

   public boolean contains(java.lang.Object o) {
      for(int i=0; i < myList.length; i++) {
         
         if(o.equals(myList[i])) {
            return true;
         }
      }
      return false;
   }
   
   public int capacity() {
      return myList.length;
   }
   
   public java.lang.Object get(int index) {
      if(index >= myList.length || index < 0 || index == size) {
         throw new java.lang.IndexOutOfBoundsException();
      }
      
      return myList[index];
   }

   public int indexOf(java.lang.Object element) {
      for(int i=0; i < myList.length; i++) {
         // Use equals method to compare objects in list
         if(element.equals(myList[i])) {
            return i;
         }
      }
      // Did not find element
      throw new java.util.NoSuchElementException();
   }

   public java.lang.Object remove(int index) {
      if(index > myList.length || index < 0) {
         throw new java.lang.IndexOutOfBoundsException();
      }
      
      // Fetch element from array
      Object result = myList[index];
      
      // Create new list
      for(int i=0; i < myList.length; i++) {
         // Remove element
         if(i == index) {
            myList[i] = null;
         }
         // Shift all elements
         // passed index down
         if(i > index) {
            myList[i-1] = myList[i];
         }
      }
      size--;
      
      return result;
   }

   public java.lang.Object set(int index, java.lang.Object o) {
      // Fetch element at index
      Object result = myList[index];
      
      // Replace old element with new one
      myList[index] = o;
      
      return result;
   }
   
   public int size() {
      return size;
   }
   
   public void trimToSize() {
      // Never trim list to less than default capacity
      if(size < DEFAULT_CAPACITY) {
         return;
      }
      
      myList = new Object[size];
   }
}