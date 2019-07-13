/**
* Tests for class BasicArrayList
* that realizes interface BasicList
*
* @author Peter Lopez
* @version Program 02
*/
public interface BasicList {
   
   void add(int index, java.lang.Object o);
   void add(java.lang.Object o);

   void clear();

   boolean contains(java.lang.Object o);

   java.lang.Object get(int index);

   int indexOf(java.lang.Object element);

   java.lang.Object remove(int index);

   java.lang.Object set(int index, java.lang.Object o);
   
   int size();
}