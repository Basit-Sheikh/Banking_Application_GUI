import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OptionsFrame extends JFrameListener

{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JMenu fileMenu, accountMenu, transMenu; // TABS
    private JMenuItem readFile,writeFile,addAccount,listAllTrans, listChecks, listDeposits, listSC, findAcc, listAllAccts, addTransac; //DROPDOWN OPTIONS
    private JMenuBar bar;
    public OptionsFrame(String title)
    {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        fileMenu = new JMenu("File");
        
        MenuListener ml = new MenuListener();     

        readFile = new JMenuItem("Read from file");
        readFile.addActionListener(ml);
        fileMenu.add(readFile);

        writeFile = new JMenuItem("Write to file");
        writeFile.addActionListener(ml);
        fileMenu.add(writeFile);

        accountMenu = new JMenu("Account");
        
        addAccount = new JMenuItem("Add new Account");
        addAccount.addActionListener(ml);
        accountMenu.add(addAccount);

        listAllTrans = new JMenuItem("List account's transactions");
        listAllTrans.addActionListener(ml);
        accountMenu.add(listAllTrans);

        listChecks = new JMenuItem("List all checks");
        listChecks.addActionListener(ml);
        accountMenu.add(listChecks);
        
        listDeposits = new JMenuItem("List all deposits");
        listDeposits.addActionListener(ml);
        accountMenu.add(listDeposits);
        
        listSC = new JMenuItem("List all service charges");
        listSC.addActionListener(ml);
        accountMenu.add(listSC);
        
        findAcc = new JMenuItem("Find an account");
        findAcc.addActionListener(ml);
        accountMenu.add(findAcc);
        
        listAllAccts = new JMenuItem("List all Accounts");
        listAllAccts.addActionListener(ml);
        accountMenu.add(listAllAccts);
        
        transMenu = new JMenu("Transactions");
        
        addTransac = new JMenuItem("Add Transactions");
        addTransac.addActionListener(ml);
        transMenu.add(addTransac);

        bar = new JMenuBar( );
        bar.add(fileMenu);
        bar.add(accountMenu);
        bar.add(transMenu);
        setJMenuBar(bar);


    }
    private class MenuListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            String source = event.getActionCommand();
            
            if (source.equals("Read from file"))
                BankingApp.readFile();
            else if (source.equals("Write to file"))
                BankingApp.writeFile();
            else if (source.equals("Add new Account"))
                BankingApp.addAccount();
            else if (source.equals("List account's transactions"))
                BankingApp.listTransactions();
            else if (source.equals("List all checks"))
                BankingApp.listChecks();
            else if (source.equals("List all deposits"))
                BankingApp.listDeposits();
            else if (source.equals("List all service charges"))
                BankingApp.listServiceCharges();
            else if (source.equals("Find an account"))
                BankingApp.findAccount();
            else if (source.equals("List all Accounts"))
                BankingApp.listAllAccts();
            else if (source.equals("Add Transactions"))
                BankingApp.inputTrans();
            else;
        }
      /*public void actionPerformed (ActionEvent event) 
      {
         String source = event.getActionCommand();

         if (source.equals("Add Element"))
           MyElements4.addElements();
         else
            if (source.equals("List Elements"))
               MyElements4.listElements();
            else
                if (source.equals("Find Element"))
                   MyElements4.findElement();
                else
                   if (source.equals("Read Elements"))
                      MyElements4.readElements();
                  else
                     if (source.equals("Save Elements"))
                        MyElements4.writeElements();
      }*/
    } //End of inner class
}
