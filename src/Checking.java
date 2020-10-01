/**
 * Checking account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Checking extends Account{
    private boolean directDeposit;

    public Checking(Profile holder, double balance, Date dateOpen) {
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
