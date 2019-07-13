import java.util.*;

public class BinaryIOTest {
   
   public static void main(String[] args) {
      testRead();
   }
   
   public static void testRead() {
      BinaryIO io = new BinaryIO();
      
      ArrayList<Object> expected = new ArrayList<Object>(100000);
      ArrayList<Object> actual = io.read("file.bin");
      
      for(int i=0; i < actual.size(); i++) {
         System.out.println(actual.get(i));
      }
   }
}