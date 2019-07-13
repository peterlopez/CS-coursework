import java.io.*;
import java.util.*;

public class BoxTest {
   
   public static void main(String[] args) {
      
      Box a = new Box(1,2,3,4);
      
      System.out.println(a.getLength());
      System.out.println(a.getWidth());
      System.out.println(a.getHeight());
      System.out.println(a.getWeight());
      
      System.out.println(a.getMaxDimension());
      System.out.println(a.getVolume());

      Box b = new Box(5,4,2,1);
      System.out.println("\nBox 2");
      System.out.println(b.getMaxDimension());
      System.out.println(b.getVolume());
      
      ArrayList<Box> boxes = new ArrayList<Box>(10);
      boxes.add(a);
      boxes.add(b);
      
      try {
         a.write("test.bin", boxes);
      }
      catch(IOException e) {
         
      }
      ArrayList<Box> result = null;
      try {
         result = a.read("test.bin");
      }
      catch(FileNotFoundException e) {
         
      }
      catch(IOException e) {
         
      }
      System.out.println("\nFrom file:");
      for(int i=0; i<result.size(); i++) {
         System.out.println(result.get(i).getLength());
         System.out.println(result.get(i).getWidth());
         System.out.println(result.get(i).getHeight());
      }
   }
}