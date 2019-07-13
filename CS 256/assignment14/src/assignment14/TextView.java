package assignment14;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextView extends JPanel {
	private JTextField textFieldLength;
	private JTextField textFieldWidth;

	/**
	 * Create the panel.
	 */
	public TextView() {
		
		JLabel lblLength = new JLabel("Length: ");
		add(lblLength);
		
		textFieldLength = new JTextField();
		textFieldLength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(textFieldLength);
		textFieldLength.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width: ");
		add(lblWidth);
		
		textFieldWidth = new JTextField();
		textFieldWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(textFieldWidth);
		textFieldWidth.setColumns(10);

	}

}
