interface Payment{
    void pay(double amount);
}
class UPI implements Payment{
    @Override
    public void pay(double amount){
        System.out.println("paid "+amount+" using UPI");
    }
}
class Creditcard implements Payment{
    @Override
    public void pay(double amount){
    System.out.println("paid "+amount+" using CreditCard");
    }
}
class NetBanking implements Payment{
    @Override
    public void pay (double amount){
        System.out.println("paid "+amount+" using NetBanking");
    }
}
public class OnlinePayments {
    public static void main(String[] args) {
        Payment p1=new UPI();
        Payment p2=new Creditcard();
        Payment p3=new NetBanking();

        p1.pay(3000);
        p2.pay(4900);
        p3.pay(5400);
    }
    
}
