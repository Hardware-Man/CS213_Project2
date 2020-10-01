/**
 * Savings account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Savings extends Account{
    private final boolean isLoyal;

    public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
        super(holder, balance, dateOpen);
        this.isLoyal = isLoyal;
    }

    @Override
    public String toString() {
        return isLoyal ?
                "*Savings*" + super.toString() + "*special Savings account*" : "*Savings*" + super.toString();
    }

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}
