//Assignment #: 7c
//Student Name: Peter Lopez
//Class: CS-256

package assignment10;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameExt extends JFrame {

	private JPanel contentPane;
	private JTextField textFileA;
	private JTextField textFileB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameExt frame = new JFrameExt();
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
	public JFrameExt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 326);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelInputs = new JPanel();
		panelInputs.setBackground(Color.YELLOW);
		contentPane.add(panelInputs, BorderLayout.NORTH);
		
		JLabel lblFileA = new JLabel("File A");
		panelInputs.add(lblFileA);
		
		textFileA = new JTextField();
		panelInputs.add(textFileA);
		textFileA.setColumns(10);
		
		JLabel lblFileB = new JLabel("File B");
		panelInputs.add(lblFileB);
		
		textFileB = new JTextField();
		panelInputs.add(textFileB);
		textFileB.setColumns(10);
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setBackground(Color.GREEN);
		contentPane.add(panelDisplay, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelDisplay.add(scrollPane);
		
		JTextArea textAreaDisplay = new JTextArea();
		scrollPane.setViewportView(textAreaDisplay);
		textAreaDisplay.setRows(10);
		textAreaDisplay.setColumns(40);
		
		JPanel panelControls = new JPanel();
		panelControls.setBackground(Color.RED);
		contentPane.add(panelControls, BorderLayout.SOUTH);
		
		JButton btnMergeDeadlock = new JButton("Merge Deadlock");
		btnMergeDeadlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validateInputs()) {
					textAreaDisplay.setText("Error: invalid input");
					return;
				}
				String fileA = textFileA.getText().trim();
				String fileB = textFileB.getText().trim();
				String fileAB = "fileAB.txt";
				String fileBA = "fileBA.txt";
				Object objA = new Object();
				Object objB = new Object();
				
				MergeRunnable mr1 = new MergeRunnable(fileA, fileB, fileAB, objA, objB);
				MergeRunnable mr2 = new MergeRunnable(fileA, fileB, fileBA, objA, objB);
				Thread t1 = new Thread(mr1);
				Thread t2 = new Thread(mr2);
				t1.start();
				t2.start();
			}
		});
		panelControls.add(btnMergeDeadlock);
		
		JButton btnMergenodeadlock = new JButton("MergeNoDeadlock");
		panelControls.add(btnMergenodeadlock);
	}
	
	public boolean validateInputs() {
		if(textFileA.getText().trim().equals("") || textFileB.getText().trim().equals("")) {
			return false;
		}
		return true;
	}
}
