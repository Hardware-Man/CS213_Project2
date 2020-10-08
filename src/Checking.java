/**
 * Checking account class.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Checking extends Account {
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
        return this.getBalance() * 0.0005 / 12;
    }

    @Override
    public double monthlyFee() {
        return this.getBalance() >= 1500 || this.directDeposit ? 0 : 25;
    }
}
