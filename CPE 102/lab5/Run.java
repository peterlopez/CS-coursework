import java.util.*;
import java.lang.*;

/**
* Driver for Info class to read user
* input values from Scanner and process
* into ArrayLists
* @author Peter Lopez
* @version Lab 04
*/
class Run {
   
   public static void main(String[] args) {
      System.out.println("----------");
      
      Info myInfo = new Info();
      
      Scanner scanner = new Scanner(System.in);
      Boolean stopLoop = false;
      
      while(! scanner.hasNext("quit")) {
         
         // Determine user input data type
         if(scanner.hasNextInt()) {
            myInfo.add(scanner.nextInt());
         }
         else if(scanner.hasNextBoolean()) {
            myInfo.add(scanner.nextBoolean());
         }
         else if(scanner.hasNextDouble()) {
            myInfo.add(scanner.nextDouble());
         }
         else {
            myInfo.add(scanner.next());
         }
      }
      
      // Print out minimum integer,
      // average double, and number of
      // true and string values entered
      System.out.print("\nMinimum integer value entered: ");
      System.out.print(myInfo.minimumInt() + "\n");
      
      System.out.print("Average double value entered: ");
      System.out.print(myInfo.averageDouble() + "\n");
      
      System.out.print("Number of true values entered: ");
      System.out.print(myInfo.numberOfTrues() + "\n");
      
      System.out.print("Number of string values entered: ");
      System.out.print(myInfo.numberOfStrings() + "\n");
   }
}