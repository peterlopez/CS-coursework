import javax.swing.*;

public class Frame {
   
   public static void main(String[] args) {
      // Make a frame and set closing behavior
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // In general, do this last..
      frame.pack();
      frame.setVisible(true);
   }
}