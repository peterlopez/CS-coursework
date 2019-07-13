import java.util.*;
import java.io.*;

class BinarySearcherTest {
   
   private static SUnit sUnit = new SUnit();
   
   public static void main(String[] args) {
      // Sort words
      try {
         ArrayList<String> randomWords = readRandomWords();
         SelectionSorter sorter = new SelectionSorter(randomWords);
         sorter.sort();

         ArrayList<String> orderedWords = sorter.getList();
         testSearch(orderedWords);
      }
      catch(FileNotFoundException e) {
         System.out.println("Fatal error: could not file source file randomWords.txt");
         System.exit(0);
      }
      
      System.out.println("Tests failed: "+sUnit.testsFailed());
      System.out.println("Tests run: "+sUnit.testsRun());
   }
   
   public static ArrayList<String> readRandomWords() throws FileNotFoundException {
      // Open file of random words
      File file = new File("randomWords.txt");
      if(!file.exists()) {
          System.out.println("File: randomWords.txt does not exist.");
          throw new FileNotFoundException();
      }
      
      // Scan over file, reading each line
      // into ArrayList
      ArrayList<String> myList = new ArrayList<String>(1235);
      Scanner fin = new Scanner(file);
      while(fin.hasNextLine()) {
          myList.add(fin.nextLine());
      }
      
      // Close file
      fin.close();
      return myList;
   }
   
   public static void testSearch(ArrayList<String> orderedWords) {
      BinarySearcher searcher = new BinarySearcher(orderedWords);
      
      sUnit.assertEquals(0, searcher.search("ABATTOIR"));
      sUnit.assertEquals(372, searcher.search("EXCRETE"));
      sUnit.assertEquals(896, searcher.search("REDIVIVUS"));
      sUnit.assertEquals(1233, searcher.search("YAYS"));
   }
}