/**
* InputParser class that prompts user for
* values and returns the types of those
* entered values
*
* @author Peter Lopez
* @version Lab 2
*/
import java.util.*;

public class InputParser
{  
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Enter the number of values to input: ");
      
      int num_values = scanner.nextInt();
      
      for(int i=0; i < num_values; i++) {
         int value_number = i+1;
         System.out.print("Enter value "+value_number+": ");
         scanner.nextLine();
         
         if(scanner.hasNextInt()) {
            System.out.println("You entered an int");
         }
         else if(scanner.hasNextDouble()) {
            System.out.println("You entered a double");
         }
         else {
            System.out.println("You entered a string");
         }
      }
   }
}