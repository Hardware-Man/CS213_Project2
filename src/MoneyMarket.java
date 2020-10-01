/**
 * This is an abstract class that defines the common features of all account types.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class MoneyMarket extends Account{
    private int withdrawals;

    public double monthlyInterest() {
        return 0;
    }

    public double monthlyFee() {
        return 0;
    }
}
