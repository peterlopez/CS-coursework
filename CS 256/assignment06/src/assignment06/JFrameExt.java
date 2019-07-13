//Assignment #: 6
//Student Name: Peter Lopez
//Class: CS-256

package assignment06;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameExt extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameExt frame = new JFrameExt();
					frame.setSize(400, 300);
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
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Ticker ticker = new Ticker(this);
		ticker.setLayout(new FlowLayout());
		ticker.setBackground(Color.YELLOW);
		contentPane.add(ticker, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticker.setMoving(true);
			}
		});
		panel.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		panel.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticker.setMoving(false);
			}
		});
	}

}
