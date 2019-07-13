//Assignment #: 9
//Student Name: Peter Lopez
//Class: CS-256

package assignment09;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class JFrameExt extends JFrame {

	private JPanel contentPane;
	private JTextField textMsg1;
	private JTextField textMsg2;
	private JTextField textCount;
	private JTextField textFileName;

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
		setBounds(100, 100, 750, 350);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInputs = new JPanel();
		panelInputs.setBackground(Color.GREEN);
		contentPane.add(panelInputs, BorderLayout.NORTH);
		
		JLabel lblMsg = new JLabel("Msg 1");
		panelInputs.add(lblMsg);
		
		textMsg1 = new JTextField();
		panelInputs.add(textMsg1);
		textMsg1.setColumns(10);
		
		JLabel lblMsg_1 = new JLabel("Msg 2");
		panelInputs.add(lblMsg_1);
		
		textMsg2 = new JTextField();
		panelInputs.add(textMsg2);
		textMsg2.setColumns(10);
		
		JLabel lblCount = new JLabel("Count");
		panelInputs.add(lblCount);
		
		textCount = new JTextField();
		panelInputs.add(textCount);
		textCount.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name");
		panelInputs.add(lblFileName);
		
		textFileName = new JTextField();
		panelInputs.add(textFileName);
		textFileName.setColumns(10);
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setBackground(Color.YELLOW);
		contentPane.add(panelDisplay, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelDisplay.add(scrollPane);
		
		JTextArea textAreaDisplay = new JTextArea();
		textAreaDisplay.setColumns(40);
		textAreaDisplay.setRows(12);
		scrollPane.setViewportView(textAreaDisplay);
		
		JPanel panelControls = new JPanel();
		panelControls.setBackground(Color.RED);
		contentPane.add(panelControls, BorderLayout.SOUTH);
		
		JButton btnWrite = new JButton("Write");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !validateInputs() ) {
					textAreaDisplay.setText("Error: Invalid input");
					return;
				}
				String message1 = textMsg1.getText().trim();
				String message2 = textMsg2.getText().trim();
				int count = Integer.parseInt(textCount.getText().trim());
				String fileName = textFileName.getText().trim();
				emptyTextFile(fileName);
				
				NoSyncRunnable nsr1 = new NoSyncRunnable(message1, count, fileName);
				NoSyncRunnable nsr2 = new NoSyncRunnable(message2, count, fileName);
				Thread t1 = new Thread(nsr1);
				Thread t2 = new Thread(nsr2);
				t1.start();
				t2.start();
			}
		});
		panelControls.add(btnWrite);
		
		JButton btnSynchWrite = new JButton("Synch Write");
		panelControls.add(btnSynchWrite);
		btnSynchWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !validateInputs() ) {
					textAreaDisplay.setText("Error: Invalid input");
					return;
				}
				String message1 = textMsg1.getText().trim();
				String message2 = textMsg2.getText().trim();
				int count = Integer.parseInt(textCount.getText().trim());
				String fileName = textFileName.getText().trim();
				Object obj = new Object();
				emptyTextFile(fileName);
				
				CompSyncRunnable csr1 = new CompSyncRunnable(message1, count, fileName, obj);
				CompSyncRunnable csr2 = new CompSyncRunnable(message2, count, fileName, obj);
				Thread t1 = new Thread(csr1);
				Thread t2 = new Thread(csr2);
				t1.start();
				t2.start();
			}
		});
		
		JButton btnCoopWrite = new JButton("Coop Write");
		panelControls.add(btnCoopWrite);
		btnCoopWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !validateInputs() ) {
					textAreaDisplay.setText("Error: Invalid input");
					return;
				}
				String message1 = textMsg1.getText().trim();
				String message2 = textMsg2.getText().trim();
				int count = Integer.parseInt(textCount.getText().trim());
				String fileName = textFileName.getText().trim();
				Object obj = new Object();
				emptyTextFile(fileName);
				
				CoopSyncRunnable csr1 = new CoopSyncRunnable(message1, count, fileName, obj);
				CoopSyncRunnable csr2 = new CoopSyncRunnable(message2, count, fileName, obj);
				Thread t1 = new Thread(csr1);
				Thread t2 = new Thread(csr2);
				t1.start();
				t2.start();
			}
		});
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !validateInputs() ) {
					textAreaDisplay.setText("Error: Invalid input");
					return;
				}
				String content;
				try {
					content = String.join("\n", Files.readAllLines(Paths.get(textFileName.getText().trim())));
					textAreaDisplay.setText(content);
				} catch (IOException e1) {
					e1.printStackTrace();
					textAreaDisplay.setText("ERROR");
				}
			}
		});
		panelControls.add(btnDisplay);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMsg1.setText("");
				textMsg2.setText("");
				textCount.setText("");
				textFileName.setText("");
				textAreaDisplay.setText("");
			}
		});
		panelControls.add(btnClear);
	}
	/**
	 * Verifies user has not left required fields blank
	 * @return
	 */
	public boolean validateInputs() {
		if( textMsg1.getText().trim().equals("") ||
			textMsg2.getText().trim().equals("") ||
			textCount.getText().trim().equals("") ||
			textFileName.getText().trim().equals(""))
		{
			return false;
		}
		return true;
	}
	/**
	 * Erases content of given file
	 * @param fileName
	 */
	public void emptyTextFile(String fileName) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
