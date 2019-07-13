//Assignment #: 9
//Student Name: Peter Lopez
//Class: CS-256

package assignment09;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CompSyncRunnable implements Runnable {
	private String message;
	private int count;
	private String fileName;
	private Object obj;
	
	public CompSyncRunnable(String message, int count, String fileName, Object obj) {
		this.message = message;
		this.count = count;
		this.fileName = fileName;
		this.obj = obj;
	}
	
	@Override
	public void run() {
		// Create output stream to append to file
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName, true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		while(true) {
			// Use print writer to write to file with autoflush on
			PrintWriter writer = new PrintWriter(out, true);
			try {
				Thread.sleep(100);
			}
			 catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj) {
				for(int i=0; i<count; i++) {
					writer.println(message);
					try {
						Thread.sleep(100);
					}
					 catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				writer.close();
			}
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
