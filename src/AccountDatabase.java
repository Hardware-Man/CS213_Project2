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
        for(int i = 0; i < size; i++) {
            if(accounts[i].toString().equals(account.toString())) {
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
        int accountPosition = find(account);
        if(accountPosition == -1) {
            return false;
        }
        accounts[accountPosition].credit(amount);
        return true;
    }

    public int withdrawal(Account account, double amount) {
        int accountPosition = find(account);
        if(accountPosition == -1) {
            return -1;
        }
        double currBal = accounts[accountPosition].getBalance();
        if(currBal < amount) {
            return 1;
        }
        accounts[accountPosition].debit(amount);
        return 0;
    }


    private void sortByDateOpen() {
        for (int i = 0; i < size - 1; i++)//selection sort
        {
            int earliestDateIndex = i;
            for (int j = i + 1; j < size; j++)
                if (accounts[j].getDateOpen()
                        .compareTo(accounts[earliestDateIndex].getDateOpen()) < 1) {
                    earliestDateIndex = j;
                }
            Account acc = accounts[earliestDateIndex];
            accounts[earliestDateIndex] = accounts[i];
            accounts[i] = acc;
        }
    }


    private void sortByLastName() {
        for (int i = 0; i < size - 1; i++)//selection sort
        {
            int alphSmallerIndex = i;
            for (int j = i + 1; j < size; j++)
                if (accounts[j].getHolder().getLastNameFirstName()
                        .compareTo(accounts[alphSmallerIndex].getHolder().getLastNameFirstName()) < 1) {
                    alphSmallerIndex = j;
                }
            Account acc = accounts[alphSmallerIndex];
            accounts[alphSmallerIndex] = accounts[i];
            accounts[i] = acc;
        }
    }

    private void printByDateOpen() {
        sortByDateOpen();
        for(Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    public void printByLastName() {
        sortByLastName();
        for(Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    public void printAccounts() {
        for(int i = 0; i < size;i++){
            System.out.println(accounts[i]);
        }
        /*for(Account account : accounts) {
            System.out.println(account.toString());
        }*/
    }
}
