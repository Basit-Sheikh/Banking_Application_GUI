import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JFrameListener extends JFrame implements WindowListener {
    
    public JFrameListener(String title) 
    {
        super(title);
        addWindowListener(this);
    }
    public void windowClosing(WindowEvent e) 
    {
        System.out.println("WindowListener method called: windowClosed.");
        
                
        if(!BankingApp.saved)
        {
            int status, confirm;     
            String  message = "Would you like to save the file?";
            confirm = JOptionPane.showConfirmDialog (null, message);
            if (confirm == JOptionPane.YES_OPTION)
                BankingApp.writeFile();
        }
      
        this.setVisible(false);
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {    }

    public void windowOpened(WindowEvent e) {    }

    public void windowIconified(WindowEvent e) {    }

    public void windowDeiconified(WindowEvent e) {    }

    public void windowActivated(WindowEvent e) {    }

    public void windowDeactivated(WindowEvent e) {    }
    
} 
