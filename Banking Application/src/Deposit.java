public class Deposit extends Transaction
{
    private double cashAmount;
    private double checkAmount;
 
    public Deposit(int tCount, int tId, double tAmt, double cashAmt, double checkAmt) {
        super(tCount, tId, tAmt);
        this.cashAmount = cashAmt;
        this.checkAmount = checkAmt;
    }
 
    public double getCheckAmount() {
        return checkAmount;
    }
    
    public double getCashAmount(){
        return cashAmount;
    }
 
    public void setCashAmount(double cashAmt) {
        this.cashAmount = cashAmt;
    }
    
    public void setCheckAmount (double checkAmt) {
        this.checkAmount = checkAmt;
    }
}