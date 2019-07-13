/**
* Greeter class for Lab 2
*
* @author Peter Lopez
* @version Lab 2
*/
import java.util.*;

public class Greeter
{  
   public String name = "";
   
   public Greeter(String name) {
      this.name = name;
   }
   
   public boolean set_name(String name) {
      this.name = name;
      return true;
   }
   
   public String greet() {
      String string = "Hello "+name;
      return string;
   }
}