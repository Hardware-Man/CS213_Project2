/**
 * This is an array-based container class with an initial capacity of 5. It will automatically grow the capacity by 5
 * if the database is full. The array shall hold different account instances in Checking, Savings or MoneyMarket.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;

    public AccountDatabase() {
        accounts = new Account[5];
        size = 0;
    }

    private int find(Account account) {
        for(int i = 0; i < accounts.length; i++) {
            if(accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }
    private void grow() {
        Account[] replaceAccounts = new Account[this.accounts.length+5];
        System.arraycopy(this.accounts, 0, replaceAccounts, 0, this.accounts.length);
        this.accounts = replaceAccounts;
    }
    public boolean add(Account account) {
        if(find(account) != -1) {
            return false;
        }
        if(size == accounts.length) {
            grow();
        }
        accounts[size] = account;
        size++;
        return true;
    }
    public boolean remove(Account account) {
        int accountPosition = find(account);
        if(accountPosition == -1) {
            return false;
        }
        accounts[accountPosition] = accounts[size-1];
        accounts[size-1] = null;
        size--;
        return true;
    }
    public boolean deposit(Account account, double amount) {
        return true;
    }
    public int withdrawal(Account account, double amount) {
        return 0;
    }
    private void sortByDateOpen() {

    }
    private void sortByLastName() {

    }
    private void printByDateOpen() {

    }
    public void printByLastName() {

    }
    public void printAccounts() {

    }
}
