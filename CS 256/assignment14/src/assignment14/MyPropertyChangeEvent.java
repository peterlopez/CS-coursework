package assignment14;

public class MyPropertyChangeEvent implements MyPropertyChangeListener {
	
	private String propertyName;
	private int propertyValue;
	private int newPropertyValue;
	
	public MyPropertyChangeEvent(String propertyName, int propertyValue, int newPropertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
		this.newPropertyValue = newPropertyValue;
	}
	
	public void myPropertyChange(MyPropertyChangeEvent propertyChangeEvent) {
		
		
	}
	
}
