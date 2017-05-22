/**
 * @author basit_sheikh
 * Java Concepts Utilized:
 * - GUI
 * - Methods
 * - Inheritance
 * - Polymorphism
 * - ArrayLists of objects
 * - Vectors of objects
 * - Binary Search
 * - Sorting
 * - Separate File Classes
 * - File I/O
 * - Searching (Linear, Binary)
 * - Sorting objects
 */

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Collections;
import java.util.Vector;

public class BankingApp {

    //public static JFrame frame;
    public static boolean flagr = false;
    public static CheckingAccount acct;
    public static Vector<CheckingAccount> accts;
    public static boolean saved = true;
    public static boolean flag_initiate = false;
    //public static boolean firstTimeCharge = false;
    public static String msg_check, msg_deposit, msg;
    public static String filename = "C:\\student\\elements.dat";
    public static NumberFormat fmt = NumberFormat.getCurrencyInstance();
    //public static JFrameListener frame;
    public static OptionsFrame frame;
    public static JTextArea ta;
    public static void main(String[] args) 
    {
      accts = new Vector<CheckingAccount>();
      frame = new OptionsFrame("Checking Account Menu");
      ta = new JTextArea (10,50);
      ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
      frame.getContentPane().add(ta);      
      frame.pack();
      frame.setVisible(true);
    }
    
   public static int getTransCode()
   {
       int transCode;
       String transCode_J;
       
       transCode_J = JOptionPane.showInputDialog("Enter trans code: (1) Check (2) Deposit (0) End Session  ");
       transCode = Integer.parseInt(transCode_J);
       
       return transCode;
   }
   public static double getTransAmt()
   {
       double transAmt;
       String transAmt_J;
       
       transAmt_J    = JOptionPane.showInputDialog("Enter trans amount: ");
       transAmt      = Double.parseDouble(transAmt_J);
       
       return transAmt;
   }
   
   public static void listAllAccts()
   {
       Vector<CheckingAccount> sortedList = new Vector<CheckingAccount>();
       sortedList = accts;
       Collections.sort(sortedList);
       
       JTextArea text = new JTextArea();
       String message = "";
       
       ta.setOpaque(false);
       ta.setFont(new Font("Monospaced", Font.PLAIN, 14) );
       ta.setBorder(null);
       
       if (sortedList.isEmpty())
           message = "No Accounts Found.";
       for(int i = 0; i < sortedList.size(); i++)
       {
           CheckingAccount ca;
           ca = sortedList.elementAt(i);
           message += (ca.getName() + " \n");
       }
       
       ta.setText(message);
       
   }
  
   public static void processCheck(double transAmt, int transCode, int assocTransNum, int checknumber)
   {
       
       acct.setBalance(transAmt, transCode);
       acct.setServiceCharge(0.15, assocTransNum);
       
       
       msg_check = acct.getName() + "'s Account:" + "\n" +
                   "Transaction: Check #" + checknumber + " in amount of " + fmt.format(transAmt) + "\n" +
                   "Current Balance: " + fmt.format(acct.getBalance()) + "\n" +
                   "Service Charge: Check --- charge $0.15 \n";
                 
       if (acct.getBalance() < 50.00)
       {
           msg_check += "Warning: Balance below $50 \n";
       }
       
       if (acct.getBalance() < 0)
       {
           acct.setServiceCharge(10.00, assocTransNum);
           msg_check += "Service Charge: Below $0 --- charge $10.00 \n";
       }

       if (acct.getBalance() < 500.00 && acct.getUnder500() == false)//firstTimeCharge == false)
       {
           acct.setUnder500(true);
           //firstTimeCharge = true;
           acct.setServiceCharge(5.00, assocTransNum);
           msg_check += "Service Charge: Below $500 --- charge $5.00 \n";
       }
              
       msg_check += "Total Service Charge: " + fmt.format(acct.getServiceCharge());
       
   }
  
   public static void processDeposit(double transAmt, int transCode, int assocTransNum)
   {
       
       acct.setBalance(transAmt, transCode);
       acct.setServiceCharge(0.10, assocTransNum);
       
       msg_deposit = acct.getName() + "'s Account:" + "\n" +
                     "Transaction: Deposit in amount of " + fmt.format(transAmt) + "\n" +
                     "Current Balance: " + fmt.format(acct.getBalance()) + "\n" +
                     "Service Charge: Deposit --- charge $0.10 \n";
        
       if (acct.getBalance() < 500 && acct.getUnder500() == false)//firstTimeCharge == false)
       {
           acct.setUnder500(true);
           //firstTimeCharge = true;
           acct.setServiceCharge(5.00, assocTransNum);
           msg_deposit += "Service Charge: Below $500 --- charge $5.00 \n";
       }
          
       msg_deposit += "Total Service Charge: " + fmt.format(acct.getServiceCharge());
   }
   
   public static void listTransactions(){
       
       
      JTextArea text = new JTextArea();
      String message = "";
      String transType = "";

      
      ta.setOpaque(false);
      ta.setFont(new Font("Monospaced", Font.PLAIN, 14) );
      ta.setBorder(null);
      message+="Account: " + acct.getName() + "\nBalance: " + fmt.format(acct.getBalance() - acct.getServiceCharge()) + "\nTotal Service Charges: " + fmt.format(acct.getServiceCharge()) + "\n\n"
              + "ID        Type                      Amount\n";
      
      
      for (int i = 0; i < acct.gettransCount();i++)
      {      
         
         if (acct.getTrans(i).getTransNumber() == 1)
             transType = "Check";
         else if (acct.getTrans(i).getTransNumber() == 2)
             transType = "Deposit";
         else if (acct.getTrans(i).getTransNumber() == 3)
             transType = "Service Charge";
         else transType = "Unknown";
         
         message += String.format("%-3d       %-15s  %15s \n", 
                 i,transType, fmt.format(acct.getTrans(i).getTransAmount()));
      }

      ta.setText(message);
       
   }
   
   public static void listChecks(){
       
      String message = "";
      
      ta.setOpaque(false);
      ta.setFont(new Font("Monospaced", Font.PLAIN, 14) );
      ta.setBorder(null);
      message+="Listing All Checks from " + acct.getName() + "\n\n"
              + "ID       Check                 Amount\n";
      
      
      for (int i = 0; i < acct.gettransCount();i++)
      {                       
         if (acct.getTrans(i).getTransNumber() == 1)
         {             
             Transaction t = acct.getTrans(i);
             Check c = (Check)t;
             message += String.format("%-3d      %-6d       %15s \n",  i, c.getCheckNumber() ,fmt.format(acct.getTrans(i).getTransAmount()));
         }
      }
      ta.setText(message);
   }
   
   public static void listDeposits(){
       
      String message = "";
      
      ta.setOpaque(false);
      ta.setFont(new Font("Monospaced", Font.PLAIN, 14) );
      ta.setBorder(null);
      message+="Listing all Deposits for " + acct.getName() + "\n\n"
              + "ID   Type       Checks      Cash         Amount\n";
      
      
      for (int i = 0; i < acct.gettransCount();i++)
      {              
         if (acct.getTrans(i).getTransNumber() == 2)
         {
             Transaction t = acct.getTrans(i);
             Deposit d = (Deposit)t;
             message += String.format("%-3d  %7s    %-6s      %-6s  %11s \n", i, "Deposit", fmt.format(d.getCheckAmount()), fmt.format(d.getCashAmount()), fmt.format(acct.getTrans(i).getTransAmount()));
         }        
      }
      ta.setText(message);       
   }
   
   public static void listServiceCharges(){
      String message = "";
      
      ta.setOpaque(false);
      ta.setFont(new Font("Monospaced", Font.PLAIN, 14) );
      ta.setBorder(null);
      message+="Listing Service Charges for " + acct.getName() + "\n\n"
              + "ID       Assoc. Trans             Amount\n            Number\n";
      
      
      for (int i = 0; i < acct.gettransCount();i++)
      {              
         if (acct.getTrans(i).getTransNumber() == 3)
         {
             Transaction t = acct.getTrans(i);
             ServiceCharge sc = (ServiceCharge)t;
             message += String.format("%-3d            %-8d    %12s\n", 
                 i, sc.getAssociatedTransNumber() ,fmt.format(acct.getTrans(i).getTransAmount()));
         }        
      }
      ta.setText(message);       
   }
   
   public static void addAccount(){

       int transCode;
       double initBalance, transAmt;
       String initBalance_J, name_J;
       saved = false;
       
       name_J = JOptionPane.showInputDialog("Enter the account name: ");
       initBalance_J = JOptionPane.showInputDialog("Enter your initial balance: ");
       initBalance   = Double.parseDouble(initBalance_J);
       flag_initiate = true;
       acct = new CheckingAccount (initBalance, name_J);
       accts.addElement(acct);       
   }
   
   public static void findAccount(){     
       
       String name;
       name = JOptionPane.showInputDialog("Enter the Account name: ");
       
       JTextArea text = new JTextArea();
       String message = "";
       
       ta.setOpaque(false);
       ta.setFont(new Font("Monospaced", Font.PLAIN, 14));
       ta.setBorder(null);
       
       if (accts.isEmpty())
       {
           message = "No accounts currently registered. Please add a new account.";
           ta.setText(message);
           return;           
       }
       
       //binary search
       int index;
       CheckingAccount testAcct = new CheckingAccount(50, name);
       Vector<CheckingAccount> v = new Vector<CheckingAccount>();
       v = accts;
       Collections.sort(v);
       index = Collections.binarySearch(v, testAcct);
       if (index < 0)
       {
           message = "Account not found.";
           ta.setText(message);
           return;
       }
       name = v.elementAt(index).getName();
       message = name + "'s Account Found";
       acct = v.elementAt(index);
       
       
       //linear search: YOU CAN REPLACE THE BINARY SEARCH WITH THE FOLLOWING CODE:
       
       /*for (int i = 0; i != accts.size(); i++){
           CheckingAccount arg1 = accts.elementAt(i);
           
           if (name.equals(arg1.name))
           {
               acct = accts.elementAt(i);
               message = name + "'s Account Found";
           }
       }
       if (accts.isEmpty())
           message = "No accounts currently registered. Please add a new account.";*/
       
       ta.setText(message);
   }
   
   public static void inputTrans(){
       int transCode;
       double initBalance, transAmt;
       String initBalance_J, name_J;
       saved = false;
       //first time only: also ask for initial balance
       //--------------------------------------------------
       if (flag_initiate == false)
       {
           name_J = JOptionPane.showInputDialog("Enter the account name: ");
           initBalance_J = JOptionPane.showInputDialog("Enter your initial balance: ");
           initBalance   = Double.parseDouble(initBalance_J);
           flag_initiate = true;
           acct = new CheckingAccount (initBalance, name_J);
           transCode = getTransCode();
           if (transCode < 1 || transCode > 2)
           {
               msg = acct.getName() + "'s Account:" +
                     "Transaction: End \n" +
                     "Current Balance: " + fmt.format(acct.getBalance())+ "\n" +
                     "Total Service Charge: " + fmt.format(acct.getServiceCharge()) + "\n" +
                     "Final Balance: " + fmt.format(acct.getBalance() - acct.getServiceCharge());
                     JOptionPane.showMessageDialog(null,msg); 
           }
           else 
           {
               switch (transCode) 
               {
                   case 1: 
                       int checknumber;
                       String checknumber_J;
                       checknumber_J = JOptionPane.showInputDialog("Enter Check Number: ");
                       checknumber = Integer.parseInt(checknumber_J);
                       transAmt  = getTransAmt();
                       Check c = new Check (transCode, acct.gettransCount(), transAmt, checknumber);
                       int assocTransNumCheck = acct.gettransCount();
                       acct.addTrans(c);
                       processCheck(transAmt, transCode, assocTransNumCheck, checknumber);
                       JOptionPane.showMessageDialog (null, msg_check);
                       break;
                   case 2:        
                       //double window here
                       String cash_J = "", check_J = "";
                       double cash, check;
                       JTextField field1 = new JTextField("");
                       JTextField field2 = new JTextField("");
                       
                       JPanel panel = new JPanel(new GridLayout(0,1));
                       
                       panel.add(new JLabel("Cash"));
                       panel.add(field1);
                       panel.add(new JLabel("Check"));
                       panel.add(field2);
                       
                       field1.addAncestorListener(new SetFocus());                       
                       int result = JOptionPane.showConfirmDialog(null, panel, "Deposit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                       
                       if (result == JOptionPane.OK_OPTION)
                       {
                           cash_J = field1.getText();
                           cash = Double.parseDouble(cash_J);
                           
                           check_J = field2.getText();
                           check = Double.parseDouble(check_J);
                           
                           int assocTransNumDeposit = acct.gettransCount();
                           
                           Deposit d = new Deposit(transCode, acct.gettransCount(), (cash + check), cash, check);
                           acct.addTrans(d);
                           processDeposit((check+cash), transCode, assocTransNumDeposit);            
                           JOptionPane.showMessageDialog (null, msg_deposit);
                       }
                       else
                       {
                           System.exit(0);
                       }
                       
                       break;               
                   default: break;
               }           
           }
       }
//------------------------------------------------------------------------------------------------------     
       //every other time besides first
       else 
       {
           transCode = getTransCode();
           if (transCode < 1 || transCode > 2)
           {
               msg = acct.getName() + "'s Account:" + "\n" +
                     "Transaction: End \n" +
                     "Current Balance: " + fmt.format(acct.getBalance())+ "\n" +
                     "Total Service Charge: " + fmt.format(acct.getServiceCharge()) + "\n" +
                     "Final Balance: " + fmt.format(acct.getBalance() - acct.getServiceCharge());
                     JOptionPane.showMessageDialog(null,msg);                    
           }
           else
           {
               saved = false;
               switch (transCode) 
               {
                   case 1: 
                       int checknumber;
                       String checknumber_J;
                       checknumber_J = JOptionPane.showInputDialog("Enter Check Number: ");
                       checknumber = Integer.parseInt(checknumber_J);
                       transAmt  = getTransAmt();
                       int assocTransNumCheck = acct.gettransCount();

                       Check c = new Check (transCode, acct.gettransCount(), transAmt, checknumber);
                       acct.addTrans(c);
                       processCheck(transAmt, transCode, assocTransNumCheck, checknumber);
                       JOptionPane.showMessageDialog (null, msg_check);
                       break;
                   case 2:        
                       
                       String cash_J = "", check_J = "";
                       double cash, check;
                       JTextField field1 = new JTextField("");
                       JTextField field2 = new JTextField("");
                       
                       JPanel panel = new JPanel(new GridLayout(0,1));
                       
                       panel.add(new JLabel("Cash"));
                       panel.add(field1);
                       panel.add(new JLabel("Check"));
                       panel.add(field2);
                       field1.addAncestorListener(new SetFocus());                       
                       int result = JOptionPane.showConfirmDialog(null, panel, "Deposit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                       
                       if (result == JOptionPane.OK_OPTION)
                       {
                           cash_J = field1.getText();
                           cash = Double.parseDouble(cash_J);
                           
                           check_J = field2.getText();
                           check = Double.parseDouble(check_J);
                           int assocTransNumDeposit = acct.gettransCount();                          
                           Deposit d = new Deposit(transCode, acct.gettransCount(), (cash + check), cash, check);
                           acct.addTrans(d);
                           processDeposit((check+cash), transCode, assocTransNumDeposit);            
                           JOptionPane.showMessageDialog (null, msg_deposit);
                       } else {
                           System.exit(0);
                       }                       
                       break;               
                   default: break;          
               }
           }
       }
       for (int i = 0; i < accts.size(); i++)
       {
           CheckingAccount a = accts.elementAt(i);
           if ((a.getName()).equals(acct.name)){
               accts.setElementAt(acct, i);
           }
       }
       //accts.addElement(acct);
       //System.out.println(accts.get(0).getBalance());
   } //closes inputTrans
   
   
 public static void readFile() 
   {  
        chooseFile(1);	
	try
	{
            FileInputStream fis = new FileInputStream(filename);
	    ObjectInputStream in = new ObjectInputStream(fis);
            accts = (Vector<CheckingAccount>)in.readObject();
            //acct = (CheckingAccount)in.readObject();
	    in.close();
            flag_initiate = true;
            saved = true;
            testFirstCharge();
            acct = accts.elementAt(0);
        }	
	catch(ClassNotFoundException e)	
        { 
            System.out.println(e);
        }

        catch (IOException e) 
        { 
            System.out.println(e);
        }
        
        
   }
   public static void writeFile() 
   {  
        chooseFile(2);
      	try
            {
                FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(accts);
                //out.writeObject(acct);
                out.close();
                saved = true;
	    }	
	catch(IOException e)	
            { 
                System.out.println(e);
            }
 
   }
   public static void chooseFile(int ioOption) 
   {  
      int status, confirm;       
                
      String  message = "Would you like to use the current default file: \n" +
                          filename;
      confirm = JOptionPane.showConfirmDialog (null, message);
      if (confirm == JOptionPane.YES_OPTION)
          return;
      JFileChooser chooser = new JFileChooser();
      if (ioOption == 1)
          status = chooser.showOpenDialog (null);
      else
          status = chooser.showSaveDialog (null);
      if (status == JFileChooser.APPROVE_OPTION)
      {
          File file = chooser.getSelectedFile();
          filename = file.getPath();
      }
   }  
   public static void testFirstCharge()
   {
       for (int i = 0; i < accts.size(); i++)
            {
                CheckingAccount arg;
                arg = accts.elementAt(i);
                if (arg.getTrans(i).getTransAmount() == 5 && arg.getTrans(i).getTransNumber() == 3)
                    accts.elementAt(i).setUnder500(true);
                //if (acct.getTrans(i).getTransAmount() == 5 && acct.getTrans(i).getTransNumber() == 3)
                //    acct.setUnder500(true);
                   
            }
   }
   
   private static class SetFocus implements AncestorListener {
     
     public void ancestorAdded (AncestorEvent e)
     {
         JComponent component = e.getComponent();
         component.requestFocusInWindow();
     }
     
     public void ancestorMoved (AncestorEvent e)
     {
         
     }
     public void ancestorRemoved (AncestorEvent e)
     {
         
     }
 }
}
       

       
   
   
   