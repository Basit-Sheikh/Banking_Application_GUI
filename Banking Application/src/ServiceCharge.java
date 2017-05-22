public class ServiceCharge extends Transaction{
    
    private int associatedTransNumber;
    
    public ServiceCharge(int tCount, int tId, double tAmt, int assocTransNum){
        super(tCount, tId, tAmt);
        associatedTransNumber = assocTransNum;
    }
    
    public int getAssociatedTransNumber(){
        return associatedTransNumber;
    }
    
}
