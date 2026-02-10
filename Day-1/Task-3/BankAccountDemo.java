class BankAccount{
    private double balance;
    BankAccount(double init_balance){
        balance=init_balance;
    }
    public void deposit(double amount){
        if (amount>0){
            balance+=amount;
            System.out.println("Deposited: ₹"+amount);
        }
        else {System.out.println("Invalid amount");}
    }
    public void withdraw(double amount){
        if (amount>balance){
            System.out.println("Your balance is less");
        }
        else if(amount>0){
            balance-=amount;
            System.out.println("successfully withdrawn");
        }
        else{System.out.println("Invalid amount entered");}
    }
    public double getBalance(){
        return balance;
    }
}



public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount acc=new BankAccount(6000);
        acc.deposit(2000);
        acc.withdraw(3500);
        System.out.println("Balance "+ acc.getBalance());
    }
}
