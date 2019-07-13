package cs256;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class RoomGrid extends JPanel {

	private String roomName;
	
	private final int rows = 31;
	private final int cols = 7;
	private final int startTime = 8;
	private final int endTime = 22;
	private final String[] daysOfWeek = new String[]{"", "M", "T", "W", "Th", "F", "Sa"};
	
	/**
	 * Create the panel.
	 */
	public RoomGrid(String roomName) {
		this.roomName = roomName;
		setLayout(new GridLayout(0, cols, 0, 0));
		
		// Loop to create labels on top for days of week
		JLabel[] dayLabels = new JLabel[7];
		for(int i=0; i<7; i++) {
			dayLabels[i] = new JLabel( daysOfWeek[i] );
			add(dayLabels[i]);
		}
		
		// Loop to create labels on side for time slots
		JLabel[] timeSlotLabel = new JLabel[31];
		timeSlotLabel[0] = new JLabel("");
		int labelIndex = 1;
		for(int i = startTime; i <= endTime; i++) {
			timeSlotLabel[labelIndex] = new JLabel(Integer.toString(i) + ":00");
			timeSlotLabel[labelIndex + 1] = new JLabel(Integer.toString(i) + ":30");
			labelIndex += 2;
		}
		
		// Loop to create buttons
		JButton[] timeSlotBtn = new JButton[5000];
		for(int i=0; i < (28*6); i++) {
			timeSlotBtn[i] = new JButton();
		}
		
		// Add each row to the grid
		int totalCells =  timeSlotBtn.length;
		int colIndex = 0;
		for(int i=0; i < 100; i++) { //totalCells - 1
			System.out.println("cell "+i);
			if(colIndex > 7) {
				colIndex = 0;
			}
			
			// Add times in first column
			if(colIndex == 0) {
				add(timeSlotLabel[i/7]);
			}
			// Add buttons to other columns
			else {
				add(timeSlotBtn[i]);
			}
			
			colIndex++;
		}
	}

}
