package cs256;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel controlsPanel;
	private JPanel roomsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Add course controls to top of view
		controlsPanel = new JPanel();
		controlsPanel.setLayout(new GridLayout(0, 7, 0, 2));
		addControlsComponents(controlsPanel);
		contentPane.add(controlsPanel, BorderLayout.NORTH);
		
		// Add container for room grids to center
		roomsPanel = new JPanel();
		contentPane.add(roomsPanel, BorderLayout.CENTER);
		addRoomGrids(roomsPanel);
		
		// Add save/restore buttons to bottom
		
	}
	
	/**
	 * Add radio buttons for reserve/unreserve
	 * Add drop downs for course options
	 */
	public void addControlsComponents(JPanel controlsPanel) {
		// First column
		JPanel firstColumn = new JPanel();
		ButtonGroup radioGroup = new ButtonGroup();
		JCheckBox showMatch = new JCheckBox("Show Match"); 
		JRadioButton radioReserve = new JRadioButton("Reserve");
		JRadioButton radioUnreserve = new JRadioButton("Unreserve");
		radioGroup.add(radioReserve);
		radioGroup.add(radioUnreserve);
		firstColumn.add(showMatch);
		firstColumn.add(radioReserve);
		firstColumn.add(radioUnreserve);
		controlsPanel.add(firstColumn, 0, 0);
		
		// Second column
		JPanel secondColumn = new JPanel();
		JLabel labelCourse = new JLabel("Course");
		JComboBox<String> courseOptions = new JComboBox<String>();
		courseOptions.addItem("110");
		courseOptions.addItem("165");
		courseOptions.addItem("210");
		secondColumn.add(labelCourse);
		secondColumn.add(courseOptions);
		controlsPanel.add(secondColumn, 0, 1);
		
		// Third column
		JPanel thirdColumn = new JPanel();
		JLabel labelSection = new JLabel("Section");
		JComboBox<String> courseSection = new JComboBox<String>();
		courseSection.addItem("1");
		courseSection.addItem("2");
		courseSection.addItem("3");
		thirdColumn.add(labelSection);
		thirdColumn.add(courseSection);
		controlsPanel.add(thirdColumn, 0, 2);
		
		// Fourth column
		JPanel fourthColumn = new JPanel();
		JLabel labelType = new JLabel("Type");
		JComboBox<String> courseType = new JComboBox<String>();
		courseType.addItem("lab");
		courseType.addItem("lec");
		courseType.addItem("hybrid");
		fourthColumn.add(labelType);
		fourthColumn.add(courseType);
		controlsPanel.add(fourthColumn, 0, 3);

		// Fifth column
		JPanel fifthColumn = new JPanel();
		JLabel labelProf = new JLabel("Prof");
		JComboBox<String> courseProf = new JComboBox<String>();
		courseProf.addItem("CP");
		courseProf.addItem("CM");
		courseProf.addItem("RB");
		fifthColumn.add(labelProf);
		fifthColumn.add(courseProf);
		controlsPanel.add(fifthColumn, 0, 4);

		// Sixth column
		JPanel sixthColumn = new JPanel();
		JLabel labelColor = new JLabel("Color");
		JComboBox<String> courseColor = new JComboBox<String>();
		courseColor.addItem("red");
		courseColor.addItem("blue");
		courseColor.addItem("green");
		courseColor.addItem("yellow");
		sixthColumn.add(labelColor);
		sixthColumn.add(courseColor);
		controlsPanel.add(sixthColumn, 0, 5);

		// Seventh column
		JPanel seventhColumn = new JPanel();
		JLabel labelStatus = new JLabel("Status");
		JTextField textStatus = new JTextField();
		seventhColumn.add(labelStatus);
		seventhColumn.add(textStatus);
		controlsPanel.add(seventhColumn, 0, 6);
	}

	/**
	 * Create RoomGrid objects and add them to view
	 */
	public void addRoomGrids(JPanel roomsPanel) {
		RoomGrid room1 = new RoomGrid("ATC-109");
		RoomGrid room2 = new RoomGrid("ATC-110");
		RoomGrid room3 = new RoomGrid("ATC-111");
		
		roomsPanel.add(room1);
		roomsPanel.add(room2);
		roomsPanel.add(room3);
	}
}
