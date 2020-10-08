import java.text.DecimalFormat;

/**
 * This is an abstract class that defines the common features of all account types.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public abstract class Account {
    private final Profile holder;
    private double balance;
    private final Date dateOpen;

    public Account(Profile holder, double balance, Date dateOpen) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public String toString() {
        DecimalFormat balFormat = new DecimalFormat("0.00");
        return holder.toString() + "* $" + balFormat.format(balance) + "*" + dateOpen.toString();
    }

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    public double getBalance() {
        return balance;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public Profile getHolder() {
        return holder;
    }
}
