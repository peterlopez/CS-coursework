//Assignment #: 7
//Student Name: Peter Lopez
//Class: CS-256

package assignment07;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

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
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		Ticker ticker1 = new Ticker(this);
		ticker1.setStockName("WFB");
		ticker1.setStockPrice(55);
		ticker1.setDirection(true);
		ticker1.setSleepTime(200);
		ticker1.setLayout(new FlowLayout());
		ticker1.setBackground(Color.RED);
		contentPane.add(ticker1);
		
		Ticker ticker2 = new Ticker(this);
		ticker2.setStockName("DVC");
		ticker2.setStockPrice(32.1);
		ticker2.setDirection(false);
		ticker2.setSleepTime(100);
		ticker2.setLayout(new FlowLayout());
		ticker2.setBackground(Color.YELLOW);
		contentPane.add(ticker2);
		
		Ticker ticker3 = new Ticker(this);
		ticker3.setStockName("BAC");
		ticker3.setStockPrice(21);
		ticker3.setDirection(true);
		ticker3.setSleepTime(150);
		ticker3.setBackground(Color.GREEN);
		ticker3.setLayout(new FlowLayout());
		contentPane.add(ticker3);
		
		JPanel panelControls = new JPanel();
		panelControls.setBackground(Color.RED);
		contentPane.add(panelControls);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticker1.setMoving(true);
				ticker2.setMoving(true);
				ticker3.setMoving(true);
			}
		});
		panelControls.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticker1.setMoving(false);
				ticker2.setMoving(false);
				ticker3.setMoving(false);
			}
		});
		panelControls.add(btnStop);
	}
}
