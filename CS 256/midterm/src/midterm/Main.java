package midterm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel leftView;
	
    private JComboBox<String> classNameComboBox = new JComboBox<String>();
	private JLabel[] propNames = new JLabel[10];
	private JTextField[] propValues = new JTextField[10];
	
	private PropertyDescriptor[] currentObjectPropertyDescriptor = null;
	private JPanel targetObject = null;
	private Class targetClass = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Event handler for text fields
	 */
	public void actionPerformed(ActionEvent e) {
		// Find index of text field that has changed
		int indexOfProperty;
		for(indexOfProperty = 0; indexOfProperty < propValues.length; indexOfProperty++) {
			if(e.getSource() == propValues[indexOfProperty]) {
				break;
			}
		}
		
		// Get property name and value from text field
		String propertyName = propNames[indexOfProperty].getText();
		String propertyValue = propValues[indexOfProperty].getText();
		System.out.println("Setting property: "+propertyName+" to new value: "+propertyValue);
		
		// Get type of property in object
		Class propertyType = currentObjectPropertyDescriptor[indexOfProperty].getPropertyType();
		String propertyTypeStr = propertyType.getName();
		
		// Construct object of correct type containing new value for property
		Object newPropValue = null;
		System.out.println("Property type: "+propertyTypeStr);
		if(propertyTypeStr.equals("int")) {
			newPropValue = new Integer(Integer.parseInt(propertyValue));
		}
		else if(propertyTypeStr.equals("java.lang.Boolean")) {
			newPropValue = new Boolean(propertyValue);
		}
		else if(propertyTypeStr.equals("java.lang.String")) {
			newPropValue = propertyValue;
		}
		else {
			System.out.println("ERROR: Cannot handle property type '"+propertyTypeStr+"'");
			return;
		}
		
		// Invoke set method
		Method setMethod = currentObjectPropertyDescriptor[indexOfProperty].getWriteMethod();
		try {
			setMethod.invoke(targetObject, newPropValue);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		leftView = new JPanel();
		contentPane.add(leftView);
		leftView.setLayout(new BorderLayout(0, 0));
		
		// Create panel on right containing controller and inspector
		JPanel rightView = new JPanel();
		rightView.setBackground(Color.RED);
		rightView.setLayout(new BorderLayout());
		contentPane.add(rightView);
		
		JPanel controllerPanel = new JPanel();
		controllerPanel.setBackground(Color.MAGENTA);
		JPanel inspectorPanel = new JPanel();
		inspectorPanel.setBackground(Color.ORANGE);
		inspectorPanel.setLayout(new GridLayout(10, 2, 2, 2));
		rightView.add(controllerPanel, BorderLayout.NORTH);
		rightView.add(inspectorPanel, BorderLayout.CENTER);

		// Add components to controller and inspector panels
		// inspector panel must be added before controller
		addComponentsToInspectorPanel(inspectorPanel);
		addComponentsToControllerPanel(controllerPanel);
	}

	/**
	 * Controller panel contains combo box for selecting
	 * which class to display and inspect
	 */
	public void addComponentsToControllerPanel(JPanel controllerPanel) {
		//
		// Combo Box event handler - reads and displays selected object properties
		//
		classNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get selected object name from combo box
				String selectedObject = (String) classNameComboBox.getSelectedItem();
				
				// If selecting blank option,
				// set all labels and text fields blank
				if(selectedObject.equals("")) {
					for(int i=0; i<10; i++) {
						propNames[i].setText("");
						propValues[i].setText("");
					}
					return;
				}
				
				Class newObject = null;
				BeanInfo beanInfo = null;
				try {
					// Create selected object
					System.out.println("Creating new object with name: "+selectedObject);
					newObject = Class.forName(selectedObject);
					targetClass = newObject;
					targetObject = (JPanel) Beans.instantiate(null, selectedObject);
					
					// Create BeanInfo object to inspect selected object
					beanInfo = Introspector.getBeanInfo(newObject, JPanel.class);
				}
				catch (ClassNotFoundException | IntrospectionException | IOException e1) {
					e1.printStackTrace();
				}
				
				// Loop through each property descriptor
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				currentObjectPropertyDescriptor = propertyDescriptors;
				for(int i=0; i<propertyDescriptors.length; i++) {

					// Get property name and set label
					String propertyName = propertyDescriptors[i].getName();
					propNames[i].setText(propertyName);
					
					// Read property and set value text box
					Method getMethod = propertyDescriptors[i].getReadMethod();
					System.out.println("Property: "+propertyName+" -- Read Method: "+getMethod);

					Object methodResult = null;
					try {
						methodResult = getMethod.invoke(newObject.newInstance());
					}
					catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e2) {
						e2.printStackTrace();
					}
					String propertyValue = methodResult.toString();
					propValues[i].setText(propertyValue);
				}
				
				// Finally, display new JPanel in left
				if(targetObject instanceof JPanel) {
					contentPane.remove(0);
					//targetObject.setLayout(new FlowLayout());
					contentPane.add(targetObject, 0);
					contentPane.validate();
				}
			}
		});
		classNameComboBox.setEditable(true);
		classNameComboBox.addItem("");
		classNameComboBox.addItem("midterm.Rect");
		classNameComboBox.addItem("midterm.Circ");
		classNameComboBox.addItem("midterm.Ticker");
		
		controllerPanel.add(classNameComboBox);
	}
	
	/**
	 * Inspector panel contains list of labels and text fields
	 * for displaying class property names and values
	 */
	public void addComponentsToInspectorPanel(JPanel inspectorPanel) {
		// Create 10 labels and text fields
		// and add them to inspector panel
		
		for(int i=0; i<10; i++) {
			propNames[i] = new JLabel("");
			propValues[i] = new JTextField();
			propValues[i].addActionListener(this);
			
			inspectorPanel.add(propNames[i]);
			inspectorPanel.add(propValues[i]);
		}
	}
}
