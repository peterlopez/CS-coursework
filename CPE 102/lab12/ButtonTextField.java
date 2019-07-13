import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ButtonTextField {
   
   public static void main(String[] args) {
      // Make a frame and set closing behavior
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Make stuff to add to the frame
      JButton button = new JButton("Push Me!");
      JTextField text = new JTextField(15);
      text.setText("Hello World!");
      
      // Add the stuff to the frame's content pane
      JPanel contentPane = (JPanel)frame.getContentPane();
      contentPane.add(button);
      contentPane.add(text);
      
      // Change the layout manager of the content pane
      contentPane.setLayout(new FlowLayout());
      
      // In general, do this last..
      frame.pack();
      frame.setVisible(true);
   }
}