import java.util.*;

/**
* Tests for Info class working with ArrayLists
* @author Peter Lopez
* @version Lab 04
*/
public class InfoTest {
   
   public static void main(String[] args) {
      testAddInt();
      testAddDouble();
      testAddBool();
      testAddString();
      testMinimumInt();
      testNumberOfTrues();
   }
   
   public static void testAddInt() {
      Info myInfo = new Info();
      myInfo.add(5);
      myInfo.add(7);
      
      ArrayList<Integer> intList = new ArrayList<Integer>();
      intList.add(5);
      intList.add(7);
      
      if(! myInfo.getIntList().equals(intList)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Integer, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
      
      Info myInfo2 = new Info();
      myInfo2.add(542);
      myInfo2.add(-42);
      
      ArrayList<Integer> intList2 = new ArrayList<Integer>();
      intList2.add(542);
      myInfo2.add(-42);
      
      if(! myInfo.getIntList().equals(intList)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Integer, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
   }
      
   public static void testAddDouble() {
      Info myInfo = new Info();
      myInfo.add(3.2);
      myInfo.add(22.001);
   
      ArrayList<Double> dList = new ArrayList<Double>();
      dList.add(3.2);
      dList.add(22.001);
   
      if(! myInfo.getDList().equals(dList)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Double, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
   
      Info myInfo2 = new Info();
      myInfo2.add(-1.1);
      myInfo2.add(22.333333333333);
   
      ArrayList<Double> dList2 = new ArrayList<Double>();
      dList2.add(-1.1);
      dList2.add(22.333333333333);
   
      if(! myInfo2.getDList().equals(dList2)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Double, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
   }
   
   public static void testAddBool() {
      Info myInfo = new Info();
      myInfo.add(true);
      myInfo.add(false);
      myInfo.add(true);
      
      ArrayList<Boolean> boolList = new ArrayList<Boolean>();
      boolList.add(true);
      boolList.add(false);
      boolList.add(true);
      
      if(! myInfo.getBoolList().equals(boolList)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Boolean, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
      
      Info myInfo2 = new Info();
      myInfo2.add(false);
      myInfo2.add(false);
      myInfo2.add(false);
      
      ArrayList<Boolean> boolList2 = new ArrayList<Boolean>();
      boolList2.add(false);
      boolList2.add(false);
      boolList2.add(false);
      
      if(! myInfo2.getBoolList().equals(boolList2)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of type Boolean, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
   }
   
   public static void testAddString() {
      Info myInfo = new Info();
      myInfo.add("Hello");
      myInfo.add("World!");
      
      ArrayList<String> sList = new ArrayList<String>();
      sList.add("Hello");
      sList.add("World!");
      
      if(! myInfo.getStringList().equals(sList)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of Strings, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
      
      Info myInfo2 = new Info();
      myInfo2.add("2092jfoijdsaj~@#");
      myInfo2.add("     ");
      
      ArrayList<String> sList2 = new ArrayList<String>();
      sList2.add("2092jfoijdsaj~@#");
      sList2.add("     ");
      
      if(! myInfo2.getStringList().equals(sList2)) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected ArrayList of Strings, found ArrayList with incorrect values");
         exception.printStackTrace();
      }
   }
   
   public static void testMinimumInt() {
      Info myInfo = new Info();
      
      if(myInfo.minimumInt() != 0) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value 0, found X");
         exception.printStackTrace();
      }
      
      myInfo.add(3);
      myInfo.add(100);
      
      if(myInfo.minimumInt() != 3) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value X, found Y");
         exception.printStackTrace();
      }
   }
   
   public static void testAverageDouble() {
      Info myInfo = new Info();
      
      if(myInfo.averageDouble() != 0) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value 0, found X");
         exception.printStackTrace();
      }
      
      myInfo.add(1.1);
      myInfo.add(2.2);
      myInfo.add(5.31);
      
      if(myInfo.averageDouble() != 2.87) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value X, found Y");
         exception.printStackTrace();
      }
   }
   
   public static void testNumberOfTrues() {
      Info myInfo = new Info();
      
      if(myInfo.numberOfTrues() != 0) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value 0, found Y");
         exception.printStackTrace();
      }
      
      myInfo.add(true);
      myInfo.add(false);
      myInfo.add(true);
      
      if(myInfo.numberOfTrues() != 2) {
         java.lang.Throwable exception = new Throwable(
            "Error: Expected return value X, found Y");
         exception.printStackTrace();
      }
   }
}