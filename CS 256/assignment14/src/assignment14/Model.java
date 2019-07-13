package assignment14;

import java.util.Vector;

public class Model {

	private int recLength;
	private int recWidth;
	private Vector<MyPropertyChangeListener> myPropertyChangeListeners = new Vector<MyPropertyChangeListener>();
	
	/**
	 * Add and remove event listeners from Vector
	 * @param listener to add/remove
	 */
	public void addMyPropertyChangeListener(MyPropertyChangeListener listener) {
		 if (!myPropertyChangeListeners.contains(listener)){
			 myPropertyChangeListeners.addElement(listener);
		 }
	}
	public void removeMyPropertyChangeListener(MyPropertyChangeListener listener) {
		if(myPropertyChangeListeners.contains(listener)) {
			myPropertyChangeListeners.removeElement(listener);
		}
	}
	
	/**
	 * Fires all event listeners in Vector
	 */
	public synchronized void fireMyPropertyChanged(MyPropertyChangeEvent propertyChangeEvent) {
		for (int i=0; i< myPropertyChangeListeners.size(); i++){
			( (MyPropertyChangeListener) myPropertyChangeListeners.elementAt(i)).myPropertyChange(propertyChangeEvent);
		}
	}
	
	/**
	 * Getters and setters
	 */
	public int getRecLength() {
		return recLength;
	}
	public void setRecLength(int newRecLength) {
		recLength = newRecLength;
		MyPropertyChangeEvent propertyChangeEvent = new MyPropertyChangeEvent("length", recLength, newRecLength);
		fireMyPropertyChanged(propertyChangeEvent);
	}
	public int getRecWidth() {
		return recWidth;
	}
	public void setRecWidth(int newRecWidth) {
		recWidth = newRecWidth;
	}
}
