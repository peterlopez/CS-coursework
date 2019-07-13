/**
* Implementation of Selection Sort algorithm to sort
* an ArrayList of Strings
* @author Peter Lopez
* @version Lab10
*/
import java.util.*;

public class SelectionSorter
{
   private ArrayList<String> a;

   public SelectionSorter(ArrayList<String> array)
   {
      this.a = array;
   }
   public void sort()
   {
      for (int i = 0; i < a.size(); i++)
      {
        int minPos = minimumPosition(i);
        swap(minPos, i);
      }
   }
   public ArrayList<String> getList() {
      return a;
   }
   private int minimumPosition(int from)
   {
      int minPos = from;

      for (int i = from + 1; i < a.size(); i++)
      {
        if (a.get(i).compareTo(a.get(minPos)) < 0) {
           minPos = i;
        }
      }

      return minPos;
   }
   private void swap(int i, int j)
   {
      String temp = a.get(i);
      a.set(i, a.get(j));
      a.set(j, temp);
   }
} // End of class