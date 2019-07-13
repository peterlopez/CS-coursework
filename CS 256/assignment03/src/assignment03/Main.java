//Assignment #: 3
//Student Name: Peter Lopez
//Class: CS-256

package assignment03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private static calcDroppable calc1 = new calcDroppable();
	private static calcDroppable calc2 = new calcDroppable();
	
	public static void main(String[] args) {
		// create frame for calculators
		JFrame frame = new JFrame();
		frame.setSize(500, 400);
		frame.setResizable(false);
		// Dimension minimumSize = new Dimension(500, 400);
		// frame.setMinimumSize(minimumSize);

		// create calculators and add them to the container
		JPanel calcContainer = new JPanel();
		calcContainer.setLayout(new GridLayout(1, 2));
		calc1.setVisible(true);
		calc2.setVisible(true);
		calcContainer.add(calc1);
		calcContainer.add(calc2);
		

		// Create buttons to control which calculator to use
		JPanel controlsContainer = new JPanel();
		controlsContainer.setLayout(new FlowLayout());
		JButton btnCalcFromLeft = new JButton("Calc Using Left");
		JButton btnCalcFromRight = new JButton("Calc Using Right");
		JButton btnClearAll = new JButton("Clear All");
		controlsContainer.add(btnCalcFromLeft);
		controlsContainer.add(btnCalcFromRight);
		controlsContainer.add(btnClearAll);
		
		btnCalcFromLeft.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(! ((calcDroppable) calc1).sanitizeInputs()) {
					return;
				}
				double num1 = calc1.getNum1();
				double num2 = calc1.getNum2();
				double result = 0;

				// caclulate result for calc1
				switch(calc1.getOperation()) {
				case "add":
					result = num1+num2;
					break;
				case "sub":
					result = num1-num2;
					break;
				case "mult":
					result = num1*num2;
					break;
				case "div":
					result = num1/num2;
					break;
				}

				calc1.setResult(result);
				// calculate result for calc2
				switch(calc2.getOperation()) {
				case "add":
					result = num1+num2;
					break;
				case "sub":
					result = num1-num2;
					break;
				case "mult":
					result = num1*num2;
					break;
				case "div":
					result = num1/num2;
					break;
				}
				calc2.setResult(result);
			}
		});
		btnCalcFromRight.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(! ((calcDroppable) calc2).sanitizeInputs()) {
					return;
				}
				double num1 = calc2.getNum1();
				double num2 = calc2.getNum2();
				double result = 0;

				// caclulate result for calc1
				switch(calc2.getOperation()) {
				case "add":
					result = num1+num2;
					break;
				case "sub":
					result = num1-num2;
					break;
				case "mult":
					result = num1*num2;
					break;
				case "div":
					result = num1/num2;
					break;
				}

				calc2.setResult(result);
				// calculate result for calc2
				switch(calc1.getOperation()) {
				case "add":
					result = num1+num2;
					break;
				case "sub":
					result = num1-num2;
					break;
				case "mult":
					result = num1*num2;
					break;
				case "div":
					result = num1/num2;
					break;
				}
				calc1.setResult(result);
			}
		});
		btnClearAll.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				calc1.setClear(true);
				calc2.setClear(true);
			}
		});
		
		frame.add(calcContainer, BorderLayout.CENTER);
		frame.add(controlsContainer, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}
