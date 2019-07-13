//Assignment #: 9
//Student Name: Peter Lopez
//Class: CS-256

package assignment09;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CoopSyncRunnable implements Runnable {
	private String message;
	private int count;
	private String fileName;
	private Object obj;
	
	public CoopSyncRunnable(String message, int count, String fileName, Object obj) {
		this.message = message;
		this.count = count;
		this.fileName = fileName;
		this.obj = obj;
	}
	
	@Override
	public void run() {
		// Open file for writing messages
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(fileName, true), true);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<count; i++) {
			synchronized (obj) {
				writer.println(message);
				obj.notify();
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Seems to work without closing the file handle
			//writer.close();
		}
		synchronized(obj) {
			obj.notify();
		}
	}
}
