package assignment13;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class TextListener extends JPanel {

	private TextSource source;
	private JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public TextListener() {
		setBackground(Color.GREEN);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(10);
		textArea.setColumns(20);
	}

	public void newTextEntered(NewTextEvent nte) {
		String newText = nte.getNewText();
		textArea.append(newText+"\n");
	}
	
	public void setSource(TextSource s) {
		source = s;
		source.addNewTextListener(this);
	}
}
