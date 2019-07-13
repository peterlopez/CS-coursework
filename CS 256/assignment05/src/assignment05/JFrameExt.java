//Assignment #: 5
//Student Name: Peter Lopez
//Class: CS-256

package assignment05;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameExt extends JFrame {

	private JPanel contentPane;
	private JTextField textGuess;
	public static Guess guess;
	// stats for summary report
	public int gameCount = 0;
	public double guessAverage;
	
	/**
	 * create the UI elements and event handlers
	 */
	public JFrameExt() {
		// create new guess on startup
		guess = new Guess();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel bg1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bg1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		bg1.setBackground(Color.GREEN);
		contentPane.add(bg1, BorderLayout.NORTH);
		
		JLabel lblGuess = new JLabel("Guess");
		bg1.add(lblGuess);
		
		JPanel bg2 = new JPanel();
		bg2.setBackground(Color.YELLOW);
		contentPane.add(bg2, BorderLayout.CENTER);
		bg2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		bg2.add(scrollPane);
		
		JTextArea txtrNewGameStarted = new JTextArea();
		txtrNewGameStarted.setText("New game started, enter guess..\n");
		txtrNewGameStarted.setRows(10);
		txtrNewGameStarted.setColumns(40);
		scrollPane.setViewportView(txtrNewGameStarted);

		textGuess = new JTextField();
		textGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				
				try {
					guess.checkGuess(textGuess.getText().trim());
		            message = "Correct!!\n";
		            
		            // increment gameCount
		            gameCount++;
		 
		            //show summary report in message.
		            if(gameCount < 2) {
		            	guessAverage = guess.guessCount;
		            }
		            else {
		            	guessAverage = (guessAverage + guess.guessCount) / 2;
		            }
		            message += "Guesses: "+guess.guessCount+" - Guess Average: "+guessAverage+" - Games: "+gameCount+"\n";
		            
		            // start a new game
		            guess = new Guess();
				}
				catch (GuessNotWholeNumberException ex1) {
					message = ex1.getMessage();
				}
				catch (GuessTooSmallException ex2) {
					message = ex2.getMessage();
				}
				catch (GuessTooLargeException ex3) {
					message = ex3.getMessage();
				}
				catch (GuessOutOfBoundsException ex4) {
					message = ex4.getMessage();
				}
				finally {
					//display message and prompt for next guess
					txtrNewGameStarted.append(textGuess.getText().trim()+": "+message + "\n");
					textGuess.setText("");
					txtrNewGameStarted.append("Enter your next guess..\n");
				}
			}
		});
		bg1.add(textGuess);
		textGuess.setColumns(10);
	}
}
