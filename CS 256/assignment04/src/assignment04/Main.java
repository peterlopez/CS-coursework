//Assignment #: 4
//Student Name: Peter Lopez
//Class: CS-256

package assignment04;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Integer studentCount = 0;
		String studentList = "";
		
		System.out.println("Enter student count");
		while(scanner.hasNext()) {
			try {
				String input = scanner.nextLine();
				studentCount = Integer.parseInt(input);
				break;
			}
			catch (NumberFormatException e) {
				String errorMsg = e.getMessage();
				System.out.println(errorMsg+"\n");
			}
		}
		
		// Get each student's information
		for(int i = 0; i < studentCount; i++) {

			// Gather student ID
			String studentId = "";
			System.out.println("Enter student ID");
			while(scanner.hasNext()) {
				try {
					studentId = scanner.nextLine();
					validateId(studentId);
					break;
				}
				catch(RuntimeException e) {
					String errorMsg = e.getMessage();
					System.out.println(errorMsg+"\n");
				}
			}
			
			// Gather student name
			String studentName = "";
			System.out.println("Enter student name");
			while(scanner.hasNext()) {
				try {
					studentName = scanner.nextLine();
					validateName(studentName);
					break;
				}
				catch(RuntimeException e) {
					String errorMsg = e.getMessage();
					System.out.println(errorMsg+"\n");
				}
			}
			
			// Add student to list
			studentList = studentList + studentId+" "+studentName+"\n";
		}
		
		System.out.println("\n"+studentList);
	}
	
	// Check student ID
	public static boolean validateId(String ID) {
		Integer IDInt = null;
		try {
			IDInt = Integer.parseInt(ID);
		}
		catch (NumberFormatException ex) {
			throw new IdNotAWholeNumberException();
		}
		if(IDInt < 1 || IDInt > 999) {
			throw new IdOutOfRangeException();
		}
		return true;
	}
	
	// Check student Name
	public static boolean validateName(String name) {
		if (name.equals("")){
			throw new NotSpecifiedException();  
	    }
		else if(!name.matches("[a-zA-Z]+")){
			throw new NotAlphabeticException();
	    }
		return true;
	}
}
