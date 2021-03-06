/**
* Implementation of Binary Search algorithm to search
* ArrayLists of Strings
* @author Peter Lopez
* @version Lab10
*/
import java.util.*;

public class BinarySearcher
{
   private ArrayList<String> a;

   public BinarySearcher(ArrayList<String> array)
   {
      this.a = array;
   }

   public int search(String value)
   {
      int lo = 0;
      int hi = a.size();

      while (lo <= hi)
      {
         int mid = (lo + hi) / 2;

         if (value.compareTo(a.get(mid)) < 0)
         {
            hi = mid - 1;
         }
         else if (value.compareTo(a.get(mid)) > 0)
         {
            lo = mid + 1;
         }
         else
         {
            return mid;
         }
      }
      return -1;
   }
} // End of class