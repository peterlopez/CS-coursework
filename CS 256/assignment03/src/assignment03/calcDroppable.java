//Assignment #: 3
//Student Name: Peter Lopez
//Class: CS-256

package assignment03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class calcDroppable extends JPanel {

	private JTextField textNum1;
	private JTextField textNum2;
	private JTextField textResult;
	
	public double num1;
	public double num2;
	public double result;
	public String operation;
	public boolean clear;
	
	private JRadioButton rdbtnAdd = new JRadioButton("add");	
	private JRadioButton rdbtnSub = new JRadioButton("sub");
	private JRadioButton rdbtnMult = new JRadioButton("mult");
	private JRadioButton rdbtnDiv = new JRadioButton("div");
	private ButtonGroup radioGroup = new ButtonGroup();
	
	/**
	 * returns a calculation between two numbers
	 * depending on the selected operation
	 * @param num1 user input number 1
	 * @param num2 user input number 2
	 * @return
	 */
	public double calculateResult(double num1, double num2) {
		// perform selected operation
		if(rdbtnAdd.isSelected()) {
			return num1+num2;
		}
		if(rdbtnSub.isSelected()) {
			return num1-num2;
		}
		if(rdbtnMult.isSelected()) {
			return num1*num2;
		}
		if(rdbtnDiv.isSelected()) {
			return num1/num2;
		}
		return 0;
	}
	
	/**
	 * checks user has entered valid numbers for calculation
	 * @return whether or not inputs are good
	 */
	public boolean sanitizeInputs() {
		// check we have numbers to calculate
		if(textNum1.getText().equals("") || textNum2.getText().equals("")) {
			textResult.setText("Invalid input");
			return false;
		}
		// check no divide by 0
		if(Double.parseDouble(textNum2.getText()) == 0) {
			textResult.setText("Cannot divide by 0");
			return false;
		}
		// check operation selected
		if(radioGroup.getSelection() == null) {
			textResult.setText("Select operation to calculate");
			return false;
		}
		return true;
	}
	
	/** 
	 * Initalize UI elements and event listeners
	 */
	public calcDroppable() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNum = new JLabel("Num 1");
		panel.add(lblNum);
		
		textNum1 = new JTextField();
		panel.add(textNum1);
		textNum1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		add(panel_1);
		
		JLabel lblNum_1 = new JLabel("Num 2");
		panel_1.add(lblNum_1);

		textNum2 = new JTextField();
		panel_1.add(textNum2);
		textNum2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		add(panel_2);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setForeground(Color.WHITE);
		panel_2.add(lblResult);
		
		textResult = new JTextField();
		panel_2.add(textResult);
		textResult.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.YELLOW);
		add(panel_3);

		panel_3.add(rdbtnAdd);
		panel_3.add(rdbtnSub);
		panel_3.add(rdbtnMult);
		panel_3.add(rdbtnDiv);
		
		radioGroup.add(rdbtnAdd);
		radioGroup.add(rdbtnSub);
		radioGroup.add(rdbtnMult);
		radioGroup.add(rdbtnDiv);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GREEN);
		add(panel_4);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(!sanitizeInputs()) {
					return;
				}
				// calculate result and write to screen
				double num1 = getNum1();
				double num2 = getNum2();
				textResult.setText(Double.toString(calculateResult(num1, num2)));
			}
		});
		panel_4.add(btnCalculate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNum1.setText("");
				textNum2.setText("");
				textResult.setText("");
			}
		});
		panel_4.add(btnClear);
	}
	
	/**
	 * Getters and setters for properties
	 */
	public double getNum1() {
		num1 = Double.parseDouble(textNum1.getText().trim());
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		num2 = Double.parseDouble(textNum2.getText());
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public String getOperation() {
		if(rdbtnAdd.isSelected()) {
			this.operation = "add";
		}
		if(rdbtnSub.isSelected()) {
			this.operation = "sub";
		}
		if(rdbtnMult.isSelected()) {
			this.operation = "mult";
		}
		if(rdbtnDiv.isSelected()) {
			this.operation = "div";
		}
		return this.operation;
	}
	public void setOperation(String operation) {
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
		textResult.setText(Double.toString(result));
	}
	public boolean getClear() {
		return clear;
	}
	public void setClear(boolean clear) {
		this.clear = clear;
		if(this.clear) {
			textNum1.setText("");
			textNum2.setText("");
			textResult.setText("");
		}
	}
}
