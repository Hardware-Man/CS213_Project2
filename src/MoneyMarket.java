/**
 * This is an abstract class that defines the common features of all account types.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class MoneyMarket extends Account{
    private int withdrawals;

    public MoneyMarket(Profile holder, double balance, Date dateOpen) {
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
