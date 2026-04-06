public class BankAccount {
    private String ownerName;
    private double IBAN;
    private double balance;

    public BankAccount(String ownerName,double IBAN){
        this.ownerName=ownerName;
        this.balance=0;
        this.IBAN=IBAN;
        Bank.addAccount(this);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getIBAN() {
        return IBAN;
    }

    public void setIBAN(double IBAN) {
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void deposit(double amount){
        this.balance+=amount;
    }
    public void withdraw (int amount){
        if(this.balance>=amount){
            balance-=amount;
        }
    }
    public void sendMoney(int amount, double IBAN){
        if(this.balance >= amount){

            BankAccount target = Bank.findAccount(IBAN);

            if(target == null){
                System.out.println("Target account not found!");
                return;
            }

            balance -= amount;
            target.deposit(amount);
        }
    }
    public void displayAccountInfo(){
        System.out.println("The name of the owner is :"+ownerName);
        System.out.println("The amount of money is : "+balance);
        System.out.println("IBAN of the account is : "+IBAN);
    }


}
