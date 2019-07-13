import java.util.*;
import java.io.*;

class SelectionSorterTest {
   
   private static SUnit sUnit = new SUnit();
   
   public static void main(String[] args) {
      try {
         ArrayList<String> randomWords = readRandomWords();
         testSort(randomWords);
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
   
   public static void testSort(ArrayList<String> randomWords) {
      SelectionSorter sorter = new SelectionSorter(randomWords);
      
      // Test words are randomized
      sUnit.assertEqualsObject("MOTHERLY", randomWords.get(0));
      sUnit.assertEqualsObject("PACIFICAL", randomWords.get(1));
      
      // Sort words
      sorter.sort();
      randomWords = sorter.getList();
      
      // Test words have been sorted
      sUnit.assertEqualsObject("ABATTOIR", randomWords.get(0));
      sUnit.assertEqualsObject("YAYS", randomWords.get(1233));
   }
}