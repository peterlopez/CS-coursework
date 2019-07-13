//Assignment #: 5
//Student Name: Peter Lopez
//Class: CS-256

package assignment05;

@SuppressWarnings("serial")
public class GuessOutOfBoundsException extends Exception {
	
	public GuessOutOfBoundsException() {
		super("Guess out of range");
	}
}
