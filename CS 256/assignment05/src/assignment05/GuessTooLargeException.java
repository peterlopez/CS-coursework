//Assignment #: 5
//Student Name: Peter Lopez
//Class: CS-256

package assignment05;

@SuppressWarnings("serial")
public class GuessTooLargeException extends Exception {

	public GuessTooLargeException() {
		super("Guess too large");
	}
}
