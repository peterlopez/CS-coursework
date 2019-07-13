/**
* Advanced operations with ArrayLists
* @author Peter Lopez
* @version Lab 05
*/
import java.util.*;

public class Info {
   
   private ArrayList<Object> myList = new ArrayList<Object>();
   
   public ArrayList getMyList() {
      return myList;
   }
   
   public void add(int addition) {
      Integer i = Integer.valueOf(addition);
      myList.add(i);
   }
   public void add(double addition) {
      Double d = Double.valueOf(addition);
      myList.add(d);
   }
   public void add(boolean addition) {
      Boolean b = Boolean.valueOf(addition);
      myList.add(b);
   }
   public void add(String addition) {
      myList.add(addition);
   }
   
   public Integer minimumInt() {
      // Return zero on empty list
      if(myList.size() == 0) {
         return 0;
      }
      
      Integer result = 0;
      
      // Run loop through integer list
      for(int i=0; i < myList.size(); i++) {
         
         Object obj = myList.get(i);
         
         if(obj instanceof Integer) {
            if(result == 0) {
               result = (Integer) obj;
            }
            
            if(((Integer) obj).compareTo(result) < 0) {
               result = (Integer) obj;
            }
         }
      }
      
      return result;
   }
   
   public Double averageDouble() {
      Double result = 0.0;
      
      // Return zero on empty list
      if(myList.size() == 0) {
         return result;
      }
      
      int numberOfDoubles = 0;
      
      // Loop through doubles list
      for(int i=0; i < myList.size(); i++) {
         Object obj = myList.get(i);
         
         // Add to result if current element
         // is a boolean has has value "true"
         if(obj instanceof Double) {
            result += (Double) obj;
            numberOfDoubles++;
         }
      }
      
      if(result > 0) {
         // Get average of total # of doubles
         result = result / numberOfDoubles;
      }

      return result;
   }
   
   public int numberOfTrues() {
      int result = 0;
      
      for(int i=0; i < myList.size(); i++) {
         Object obj = myList.get(i);
         
         // Add to result if current element
         // is a boolean has has value "true"
         if(obj instanceof Boolean) {
            boolean value = ((Boolean) obj).booleanValue();

            if(value) {
               result++;
            }
         }
      }
      
      return result;
   }
   
   public int numberOfStrings() {      
      int result = 0;
      
      for(int i=0; i < myList.size(); i++) {
         // Add to result if current element is
         // instance of String
         if(myList.get(i) instanceof String) {
            result++;
         }
      }
      
      return result;
   }
}