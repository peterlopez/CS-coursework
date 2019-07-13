package assignment13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class TextSource extends JPanel {
	private JTextField textField;
	private TextListener newTextListener;
	
	/**
	 * Create the panel.
	 */
	public TextSource() {
		setBackground(Color.YELLOW);
		JLabel lblSourceText = new JLabel("Source Text: ");
		add(lblSourceText);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(20);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sourceTextContents = textField.getText();
				fireNewTextEntered(sourceTextContents);
			}
		});
	}

	/**
	 * 
	 */
	public void fireNewTextEntered(String sourceText) {
		NewTextEvent nte = new NewTextEvent(sourceText);
		this.newTextListener.newTextEntered(nte);
	}
	
	/**
	 * 
	 */
	public void addNewTextListener(TextListener textListener) {
		newTextListener = textListener;
	}
	
	/**
	 * 
	 */
	public void removeNewTextListener() {
		newTextListener = null;
	}
}
