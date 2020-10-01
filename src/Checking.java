/**
 * Checking account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Checking extends Account{
    private final boolean directDeposit;

    public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {
        super(holder, balance, dateOpen);
        this.directDeposit = directDeposit;
    }

    @Override
    public String toString() {
        return directDeposit ?
                "*Checking*" + super.toString() + "*direct deposit account*" : "*Checking*" + super.toString();
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
