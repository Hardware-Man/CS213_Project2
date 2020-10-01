/**
 * This is an abstract class that defines the common features of all account types.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;

    public void debit(double amount) {

    }
    public void credit(double amount) {

    }
    public String toString() {
        return "";
    }
    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}
