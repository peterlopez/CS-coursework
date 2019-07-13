import javax.swing.*;

public class Button {
   
   public static void main(String[] args) {
      // Make a frame and set closing behavior
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Make stuff to add to the frame
      JButton button = new JButton("Push Me!");
      
      // Add the stuff to the frame's content pane
      JPanel contentPane = (JPanel)frame.getContentPane();
      contentPane.add(button);
      
      // In general, do this last..
      frame.pack();
      frame.setVisible(true);
   }
}