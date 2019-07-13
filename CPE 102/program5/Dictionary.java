/**
* Dictionary Exception used when throwing Dictionary 
* class specific exceptions
* @author Peter Lopez
* @version Program 5
*/
import java.util.*;
import java.io.*;

public class Dictionary implements java.lang.Iterable<java.lang.String> {
   
   /**
   * TODO Trim this to size when filled.
   */
   private java.util.ArrayList<String> words = new java.util.ArrayList<String>(1000000);
   
   /**
   * TODO Create another constructor with size variable.
   */
   public Dictionary(java.lang.String fileName, boolean sort) throws DictionaryException {
      try {
         File file = new File(fileName);
         Scanner scanner = new Scanner(file);
      
         while(scanner.hasNext()) {
            words.add(scanner.nextLine());
         }
         if(sort) {
            sort();
         }
      }
      catch(FileNotFoundException e) {
         throw new DictionaryException();
      }
   }
   
   public java.util.Iterator<java.lang.String> iterator() {
      return words.iterator();
   }
   
   /**
   * Sorts words in Dictionary then looks up word in Dictionary
   * @param word to search for in ArrayList words
   * @return true if param value is in words. False otherwise
   */
   public boolean lookUp(java.lang.String word) {
      int lo = 0;
      int hi = words.size();

      while (lo <= hi)
      {
         int mid = (lo + hi) / 2;

         if (word.compareTo(words.get(mid)) < 0)
         {
            hi = mid - 1;
         }
         else if (word.compareTo(words.get(mid)) > 0)
         {
            lo = mid + 1;
         }
         else
         {
            return true;
         }
      }
      return false;
   }
   
   /**
   * Sorts words in Dictionary then writes each word
   * to specified filename separated with a newline character
   * @param filename to write words to
   */
   public void write(java.lang.String fileName) throws DictionaryException {
      // Catch file not found then throw Dictionary
      try {
         File file = new File(fileName);
         PrintWriter writer = new PrintWriter(file);
         sort();
      
         for(int i=0; i < words.size(); i++) {
            writer.printf("%s%n", words.get(i));
         }
         writer.close();
      }
      catch(FileNotFoundException e) {
         throw new DictionaryException();
      }
   }
   
   /**
   * Sorts words in Dictionary using SelectionSort algorithm
   */
   private void sort() {
      for (int i = 0; i < words.size(); i++)
      {
        int minPos = minimumPosition(i);
        swap(minPos, i);
      }
   }
   private int minimumPosition(int from)
   {
      int minPos = from;

      for (int i = from + 1; i < words.size(); i++)
      {
        if (words.get(i).compareTo(words.get(minPos)) < 0) {
           minPos = i;
        }
      }

      return minPos;
   }
   private void swap(int i, int j)
   {
      String temp = words.get(i);
      words.set(i, words.get(j));
      words.set(j, temp);
   }
}