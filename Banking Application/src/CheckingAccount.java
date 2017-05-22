import java.util.*;
/**
 *
 * @author basit_sheikh
 */
public class CheckingAccount extends Account implements Comparable<CheckingAccount>{
    
      private int transCount;
      private boolean under500;
      private double totalServiceCharge;
      private ArrayList<Transaction> transList = new ArrayList();
      
      public CheckingAccount(double initialBalance, String name)
      {
          super(name, initialBalance); 
          totalServiceCharge = 0.00;
          under500 = false;
      }
      
      public int compareTo(CheckingAccount arg)
      {
          return name.compareTo(arg.name);       
      }
      
      /*public static Comparable binarySearch (Comparable<Vector> list, Comparable target)
      {
          int min=0, max=list.size(), mid=0;
          boolean found = false;
          while (!found && min <= max){
              mid = (min+max) / 2;
              if (list.equals(target))
                  found = true;
              if (list[mid].equals(target))
                  found = true;
              else
                  if (target.compareTo(list[mid]) < 0)
                      max = mid-1;
                  else
                      min = mid+1;
          }
          if (found)
              return list[mid];
          else return null;
      }*/
      
      public void addTrans(Transaction newTrans)
      {
          transList.add(newTrans);
          transCount++;
      }
      
      public int gettransCount()
      {
          return transCount;
      }
      
      public Transaction getTrans(int i)
      {
          return transList.get(i);
      }     
 
      public double getBalance()
      {
            return balance;
      }
 
      public double getServiceCharge()
      {
            return totalServiceCharge;
      }
      
      public boolean getUnder500()
      {
          return under500;
      }
      
      public void setUnder500(boolean x)
      {
          under500 = x;
      }
      
      public void setBalance(double transAmt, int tCode)
      {
            if(tCode == 1){
                balance = balance - transAmt;
                //Transaction t = new Transaction(tCode, transCount ,transAmt); 
                //addTrans(t);
            }
            else {//if(tCode == 2)
                balance = balance + transAmt;
                //Transaction t = new Transaction(tCode, transCount ,transAmt); 
                //addTrans(t);
            }
      }
 
      public void setServiceCharge(double currentServiceCharge, int assocTransNum)
      {
            totalServiceCharge = totalServiceCharge + currentServiceCharge;
            ServiceCharge sc = new ServiceCharge (3, gettransCount(), currentServiceCharge, assocTransNum);
            addTrans(sc);
            //Transaction t = new Transaction(3, transCount, currentServiceCharge); 
            //addTrans(t);
      }
    
}