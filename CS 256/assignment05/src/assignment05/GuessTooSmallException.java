//Assignment #: 5
//Student Name: Peter Lopez
//Class: CS-256

package assignment05;

@SuppressWarnings("serial")
public class GuessTooSmallException extends Exception {

	public GuessTooSmallException() {
		super("Guess too small");
	}
}
