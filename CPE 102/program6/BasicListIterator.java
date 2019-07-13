/**
* @author Peter Lopez
* @version Program 6
*/
public interface BasicListIterator<E> extends java.util.Iterator<E> {
   
   public boolean hasPrevious();
   public E previous();
}