import java.io.*;
import java.util.*;

public class BinaryIO {

   public static ArrayList<Object> read(String fileName)
   {
      ArrayList<Object> result = new ArrayList<Object>(100000);
      FileInputStream file = null;
      DataInputStream reader = null;
      
      // Open file for reading
      try {
        file = new FileInputStream(fileName);
      reader = new DataInputStream(file);
      }
      catch(FileNotFoundException e) {
         
      }
      
      // Set flag for end of file
      boolean finished = false;
      
      while(! finished) {
         
         try {
            Integer numDoubles = reader.readInt();
            result.add(numDoubles);
            
            for(int i=0; i < numDoubles; i++) {
               result.add(reader.readDouble());
            }
         }
         catch (IOException e) {
            finished = true;
         }
      }
      
      return result;
   }
   
   public static void write(String fileName, ArrayList<Object> list) {
      DataOutputStream writer = null;
      FileInputStream file = null;
      
      try {
         file = new FileInputStream(fileName);
         writer = new DataOutputStream(file);
      }
      catch(FileNotFoundException e) {
         
      }
      
      for(int i=0; i < list.size(); i++) {
         
         if(list.get(i).getClass() != Integer || list.get(i).getClass() != Double) {
            throw new IllegalArgumentException();
         }
         
      }
   }
}