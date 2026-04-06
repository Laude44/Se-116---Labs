import java.sql.SQLOutput;

/* QUESTİON 1 :
Constructor is a method that we create in order to create some objects. With parameters, we can arrange the initial
values or features of this object.
QUESTİON 2 :
Static methods/attributes are belong to the class itself. Non-Static objects and attributes are belong to the object
*/
public class Bank {
    private static BankAccount[] allAccounts = new BankAccount[100];

    public static BankAccount findAccount(double IBAN) {
        for (int i =0;i<allAccounts.length;i++) {
            if (allAccounts[i] != null && allAccounts[i].getIBAN() == IBAN) {
                return allAccounts[i];
            }
        }return null;
    }
    public static void addAccount(BankAccount acc){
        for(int i=0;i<allAccounts.length;i++){
            if(allAccounts[i]==null){
                allAccounts[i]=acc;
                break;
            }
        }
    }

    public static void main(String[] args) {
        BankAccount bankAccount1 = new BankAccount("Ahmet Demir",1234567890);
        BankAccount bankAccount2 = new BankAccount("Aleyna Kılınç",1234567891);
        bankAccount2.deposit(1000);
        bankAccount1.deposit(2000);
        bankAccount1.displayAccountInfo();
        bankAccount2.displayAccountInfo();
        System.out.println("*//////////////////////*");
        bankAccount1.sendMoney(1500,1234567891);
        bankAccount1.displayAccountInfo();
        bankAccount2.displayAccountInfo();
    }
}