//Assignment #: 5
//Student Name: Peter Lopez
//Class: CS-256

package assignment05;

public class Guess {
	public int answer;
	public int guessCount;
	
	public Guess() {
		answer = ((int) (Math.random() * 7)) + 1;
	}
	
	/**
	 * Verifies user input is a valid guess and throws exceptions otherwise
	 * @param inputString
	 */
	public void checkGuess(String inputString) throws GuessNotWholeNumberException, GuessOutOfBoundsException, GuessTooLargeException, GuessTooSmallException {
		Integer input;
		guessCount++;
		
		try {
			// check guess is a whole number
			input = Integer.parseInt(inputString);
			
			// check guess is in range
			if(input > 7 || input < 1) {
				throw new GuessOutOfBoundsException();
			}
			// check guess too large
			if(input > answer) {
				throw new GuessTooLargeException();
			}
			// check guess too small
			if(input < answer) {
				throw new GuessTooSmallException();
			}
		}
		catch(NumberFormatException e) {
			throw new GuessNotWholeNumberException();
		}
	}
}
