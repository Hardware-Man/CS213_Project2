/**
 * Savings account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Savings extends Account{
    private boolean isLoyal;

    public Savings(Profile holder, double balance, Date dateOpen) {
        super(holder, balance, dateOpen);
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
