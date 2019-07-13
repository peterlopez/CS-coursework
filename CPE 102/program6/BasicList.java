/**
* @author Peter Lopez
* @version Program 06
*/
public interface BasicList<E> {
   
   void add(int index, E o);
   void add(E o);

   void clear();

   boolean contains(E o);

   E get(int index);

   int indexOf(E element);

   E remove(int index);

   E set(int index, E o);
   
   int size();
}