import java.util.*;

/**
* Useful operations with ArrayLists
* @author Peter Lopez
* @version Lab 04
*/
public class Info {
   
   private ArrayList<String>  stringList  = new ArrayList<String>();
   private ArrayList<Integer> intList     = new ArrayList<Integer>();
   private ArrayList<Double>  dList       = new ArrayList<Double>();
   private ArrayList<Boolean> boolList    = new ArrayList<Boolean>();
   
   public ArrayList getStringList() {
      return stringList;
   }
   public ArrayList getIntList() {
      return intList;
   }
   public ArrayList getDList() {
      return dList;
   }
   public ArrayList getBoolList() {
      return boolList;
   }
   
   public void add(int addition) {
      intList.add(addition);
   }
   public void add(double addition) {
      dList.add(addition);
   }
   public void add(boolean addition) {
      boolList.add(addition);
   }
   public void add(String addition) {
      stringList.add(addition);
   }
   
   public int minimumInt() {
      // Return zero on empty list
      if(intList.size() == 0) {
         return 0;
      }
      
      int result = intList.get(0);
      
      // Run loop through integer list
      for(int i=1; i < intList.size(); i++) {
         
         if(intList.get(i) < result) {
            result = intList.get(i);
         }
      }
      
      return result;
   }
   
   public double averageDouble() {
      // Return zero on empty list
      if(dList.size() == 0) {
         return 0;
      }
      
      double result = dList.get(0);
      
      // Loop through doubles list
      for(int i=1; i < dList.size(); i++) {
         result += dList.get(i);
      }
      
      // Get average of total # of doubles
      result = result / dList.size();
      
      return result;
   }
   
   public int numberOfTrues() {
      int result = 0;
      
      for(int i=0; i < boolList.size(); i++) {
         // Add to result if current element is true
         if(boolList.get(i)) {
            result++;
         }
      }
      
      return result;
   }
   
   public int numberOfStrings() {
      return stringList.size();
   }
}