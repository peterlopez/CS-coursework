//Assignment #: 7c
//Student Name: Peter Lopez
//Class: CS-256

package assignment10;

import java.util.List;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergeRunnable implements Runnable {
	private String file1;
	private String file2;
	private String filem;
	private Object obj1;
	private Object obj2;
	private List<String> file1Contents;
	private List<String> file2Contents;
	private String fileAB;
	private String fileBA;
	
	public MergeRunnable(String file1, String file2, String filem, Object obj1, Object obj2) {
		this.file1 = file1;
		this.file2 = file2;
		this.filem = filem;
		this.obj1 = obj1;
		this.obj2 = obj2;
		
		// read contents of file1 and file 2 into memory
		Scanner scanner1 = null;
		Scanner scanner2 = null;
		try {
			scanner1 = new Scanner(new File(file1));
			scanner2 = new Scanner(new File(file2));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		while(scanner1.hasNextLine()) {
			file1Contents.add(0, scanner1.nextLine());
		}
		while(scanner2.hasNextLine()) {
			file2Contents.add(0, scanner2.nextLine());
		}
		// create iterators for getting each list item
		ListIterator<String> lt1 = file1Contents.listIterator();
		ListIterator<String> lt2 = file2Contents.listIterator();
		
		// determine which list is bigger so we can iterate through all items
		int count = (file1Contents.size() > file2Contents.size()) ? file1Contents.size() : file2Contents.size();
		
		// create fileAB contents
		for(int i=0; i <= count; i++) {
			if (lt1.hasNext()) {
				
			}
		}
		
		// create fileBA contents
	}
	
	@Override
	public void run() {
        
		synchronized (obj1) {
	        try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	        synchronized (obj2) {
	                try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	                // create FileAB and FileBA with contents from File1 and File2
	                PrintWriter writer = null;
	        		try {
	        			writer = new PrintWriter(new FileWriter(filem, true), true);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	        		
	        }
		}
	}

}
