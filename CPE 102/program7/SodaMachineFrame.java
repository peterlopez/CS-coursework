import java.awt.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SodaMachineFrame extends JFrame {

   private JPanel sodaPanel;
   private JPanel displayPanel;
   private JPanel coinPanel;
   
   private JTextField deposited, returned, total;
   private JButton coinReturn;
   
   private double amountDeposited;
   private double amountTotal;
   
   private ArrayList<JButton> sodaButtons;
   private SodaMachine sodaMachine;
   
   public SodaMachineFrame(SodaMachine sodaMachine) {
      this.sodaMachine = sodaMachine;
      sodaButtons = new ArrayList<JButton>(100);
      
      // Set frame preferences
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Create panels to go on pane
      createSodaPanel();
      createDisplayPanel();
      createCoinPanel();
      
      // Put panels on pane
      getContentPane().add(sodaPanel, BorderLayout.WEST);
      getContentPane().add(displayPanel, BorderLayout.CENTER);
      getContentPane().add(coinPanel, BorderLayout.EAST);
      
      pack();
   }
   
   public void createSodaPanel() {
      sodaPanel = new JPanel();
      sodaPanel.setPreferredSize(new Dimension(150, 300));
      sodaPanel.setBorder(new javax.swing.border.EtchedBorder());
      
      // Get count and setup grid layout
      int sodaCount = sodaMachine.getCount();
      sodaPanel.setLayout(new GridLayout(sodaCount, 1));
      
      class SodaListener implements ActionListener
      {
         private int index;
         
         public SodaListener(int index) {
            this.index = index;
         }
         public void actionPerformed(ActionEvent e) {
            // Dispense soda
            double change = sodaMachine.dispenseSoda(index);
            
            // Disable soda buttons
            disableSodas();
            coinReturn.setEnabled(false);
            
            // Update total sales text,
            // change returned, and deposited text
            deposited.setText(NumberFormat.getCurrencyInstance().format(0));
            returned.setText(NumberFormat.getCurrencyInstance().format(change / 100));
            double totalSales = sodaMachine.getSales();
            total.setText(NumberFormat.getCurrencyInstance().format(totalSales / 100));
         }
         public void disableSodas() {
            for(int i=0; i < sodaButtons.size(); i++) {
               sodaButtons.get(i).setEnabled(false);
            }
         }
      }
      
      // Loop through sodas creating buttons
      for(int i=0; i < sodaCount; i++) {
         sodaButtons.add(new JButton(sodaMachine.getSodaName(i)));
         sodaButtons.get(i).addActionListener(new SodaListener(i));
         sodaPanel.add(sodaButtons.get(i));
      }
      setSodaEnabled(false);
   }
   
   public void createDisplayPanel() {
      // Create CENTER panel
      displayPanel = new JPanel();
      displayPanel.setPreferredSize(new Dimension(150, 300));
      displayPanel.setLayout(new GridLayout(3, 1));
      displayPanel.setBorder(new javax.swing.border.EtchedBorder());
      
      // Create text fields for panels
      deposited = new JTextField("$0.00", 6);
      deposited.setEditable(false);
      returned = new JTextField("$0.00", 6);
      returned.setEditable(false);
      total = new JTextField("$0.00", 6);
      total.setEditable(false);
      
      // Create deposited panel
      JPanel depositedPanel = new JPanel();
      depositedPanel.setBorder(new javax.swing.border.EtchedBorder());
      depositedPanel.add(new JLabel("Amount Deposited", SwingConstants.RIGHT));
      depositedPanel.add(deposited);
      coinReturn = new JButton("Coin Return");
      coinReturn.setEnabled(false);
      depositedPanel.add(coinReturn);
      
      // Create returned panel
      JPanel returnedPanel = new JPanel();
      returnedPanel.setBorder(new javax.swing.border.EtchedBorder());
      returnedPanel.add(new JLabel("Change Returned", SwingConstants.RIGHT));
      returnedPanel.add(returned);
      
      // Write a listener classes for buttons
      class ReturnListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e) {
            double change = sodaMachine.returnDeposits();
            coinReturn.setEnabled(false);
            disableSodas();
            returned.setText(NumberFormat.getCurrencyInstance().format(change / 100));
            deposited.setText(NumberFormat.getCurrencyInstance().format(0));
         }

         public void disableSodas() {
            for(int i=0; i < sodaButtons.size(); i++) {
               sodaButtons.get(i).setEnabled(false);
            }
         }
      }
      coinReturn.addActionListener(new ReturnListener());
      
      // Create total sales panel
      JPanel totalPanel = new JPanel();
      totalPanel.setBorder(new javax.swing.border.EtchedBorder());
      totalPanel.add(new JLabel("Total Sales", SwingConstants.RIGHT));
      totalPanel.add(total);
      
      // Set textfields to right align
      deposited.setHorizontalAlignment(JTextField.RIGHT);
      returned.setHorizontalAlignment(JTextField.RIGHT);
      total.setHorizontalAlignment(JTextField.RIGHT);
      
      // Disable text fields
      deposited.setEnabled(false);
      returned.setEnabled(false);
      total.setEnabled(false);
      
      // Add panels to CENTER panel
      displayPanel.add(depositedPanel);
      displayPanel.add(returnedPanel);
      displayPanel.add(totalPanel);
   }
   
   public void createCoinPanel() {
      coinPanel = new JPanel();
      coinPanel.setBorder(new javax.swing.border.EtchedBorder());
      coinPanel.setLayout(new GridLayout(5, 1));
      coinPanel.setPreferredSize(new Dimension(150, 300));
      
      // Create buttons
      JButton nickelButton = new JButton("Nickel");
      JButton dimeButton = new JButton("Dime");
      JButton quarterButton = new JButton("Quarter");
      JButton halfDollarButton = new JButton("Half Dollar");
      JButton dollarButton = new JButton("Dollar");
      
      // Write a listener classes for buttons
      class CoinListener implements ActionListener
      {
         private int coinValue;
         
         public CoinListener(int coinValue) {
            this.coinValue = coinValue;
         }
         public void actionPerformed(ActionEvent e) {
            // Deposit coin
            sodaMachine.deposit(coinValue);
            
            // Enable coin return and sodas (if amount is accurate)
            coinReturn.setEnabled(true);
            if(sodaMachine.sufficientFunds()) {
               enableSodas();
            }
            
            // Change returned text and deposited text
            returned.setText(NumberFormat.getCurrencyInstance().format(0));
            update();
         }
         public void update() {
            double totalAmount = sodaMachine.getDeposits();
            deposited.setText(NumberFormat.getCurrencyInstance().format(totalAmount / 100));
         }
         public void enableSodas() {
            for(int i=0; i < sodaButtons.size(); i++) {
               if(sodaMachine.getSodaCount(i) > 0) {
                  sodaButtons.get(i).setEnabled(true);
               }
            }
         }
      }
      
      // Add listeners to buttons
      nickelButton.addActionListener(new CoinListener(5));
      dimeButton.addActionListener(new CoinListener(10));
      quarterButton.addActionListener(new CoinListener(25));
      halfDollarButton.addActionListener(new CoinListener(50));
      dollarButton.addActionListener(new CoinListener(100));
      
      // Add buttons to panel
      coinPanel.add(nickelButton);
      coinPanel.add(dimeButton);
      coinPanel.add(quarterButton);
      coinPanel.add(halfDollarButton);
      coinPanel.add(dollarButton);
   }
   
   private void setSodaEnabled(boolean enable) {
      for(int i=0; i < sodaButtons.size(); i++) {
         sodaButtons.get(i).setEnabled(enable);
      }
   }
}