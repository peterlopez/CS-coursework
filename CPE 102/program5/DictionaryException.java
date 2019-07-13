/**
* Dictionary Exception used when throwing Dictionary 
* class specific exceptions
* @author Peter Lopez
* @version Program 5
*/
public class DictionaryException extends java.lang.Exception {
   
   public DictionaryException() {
      super();
   }
   public DictionaryException(String message) {
      super(message);
   }
}