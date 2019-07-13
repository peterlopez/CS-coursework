//Assignment #: 2
//Student Name: Peter Lopez
//Class: CS-256

package assignment02;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		// create frame for calculators
		JFrame frame = new JFrame();
		frame.setSize(500, 400);
		frame.setResizable(false);
		// Dimension minimumSize = new Dimension(500, 400);
		// frame.setMinimumSize(minimumSize);
		
		// create calculators and add them to the frame
		JPanel calc1 = new calcDroppable();
		calc1.setVisible(true);
		
		JPanel calc2 = new calcDroppable();
		calc2.setVisible(true);
		
		frame.add(calc1, BorderLayout.EAST);
		frame.add(calc2, BorderLayout.WEST);
		frame.setVisible(true);
	}

}
