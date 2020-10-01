/**
 * Savings account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Savings extends Account{
    private boolean isLoyal;

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}
