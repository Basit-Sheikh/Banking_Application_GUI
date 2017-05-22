import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionsPanel extends JPanel{
    
   private JLabel prompt;
   private JRadioButton one, two, three, four, five, six, seven;
 
   //-----------------------------------------------------------------
   //  Sets up a panel with a label and a set of radio buttons
   //  that present options to the user.
   //-----------------------------------------------------------------
   public OptionsPanel()
   {
      prompt = new JLabel ("Choose action:");
      prompt.setFont (new Font ("Helvetica", Font.BOLD, 37));
 
      // menu options
      
      one = new JRadioButton ("enter transaction");
      one.setBackground (Color.yellow);
      two = new JRadioButton ("list all transactions");
      two.setBackground (Color.yellow);
      three = new JRadioButton ("list all checks");
      three.setBackground (Color.yellow);
      four = new JRadioButton("list all deposits");
      four.setBackground(Color.yellow);
      five = new JRadioButton("list all service charges");
      five.setBackground(Color.yellow);
      six = new JRadioButton("read from file");
      six.setBackground(Color.yellow);
      seven = new JRadioButton("write to file");
      seven.setBackground(Color.yellow);
   
      ButtonGroup group = new ButtonGroup();
      group.add (one);
      group.add (two);
      group.add (three);
      group.add (four);
      group.add (five);
      group.add (six);
      group.add(seven);
      
      OptionListener listener = new OptionListener();
      one.addActionListener (listener);
      two.addActionListener (listener);
      three.addActionListener (listener);
      four.addActionListener(listener);
      five.addActionListener(listener);
      six.addActionListener(listener);
      seven.addActionListener(listener);
      // add the components to the JPanel
      add (prompt);
      add (one);
      add (two);
      add (three);
      add (four);
      add (five);
      add (six);
      add (seven);
     
      setBackground (Color.yellow);
      setPreferredSize (new Dimension(400, 150));
   }
 
   //*****************************************************************
   //  Represents the listener for the radio buttons
   //*****************************************************************
   private class OptionListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Calls the method to process the option for which radio
      //  button was pressed.
      //--------------------------------------------------------------

      public void actionPerformed (ActionEvent event)
      {
         Object source = event.getSource();
         if (source == one)
             BankingApp.inputTrans();
         else if (source == two)
             BankingApp.listTransactions();
         else if (source == three)
             BankingApp.listChecks();
         else if (source == four)
             BankingApp.listDeposits();
         else if (source == five)
             BankingApp.listServiceCharges();
         else if (source == six)
             BankingApp.readFile();
         else if (source == seven)
             BankingApp.writeFile();
         else;
      }
   }
    
}
