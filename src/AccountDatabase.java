import java.util.StringTokenizer;

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
        StringTokenizer targetTokens = new StringTokenizer(account.toString(),"*");
        String targetAccType = targetTokens.nextToken();
        String targetProfileHolder = targetTokens.nextToken();
        for(int i = 0; i < size; i++) {
            StringTokenizer tokens = new StringTokenizer(accounts[i].toString(),"*");
            if(targetAccType.equals(tokens.nextToken())){
                if(targetProfileHolder.equals(tokens.nextToken())){
                    return i;
                }
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
        System.out.println("\n--Printing statements by date opened--\n");
        sortByDateOpen();
        for(int i = 0; i < size ; i++) {
            System.out.println(accounts[i].toString() + "\n-interest: "
             + accounts[i].monthlyInterest() + "\n-fee: " + accounts[i].monthlyFee()
                    + "\n-new balance: " + accounts[i].getBalance());

        }
        System.out.println("--end of printing--");
    }

    public void printByLastName() {
        System.out.println("\n--Printing statements by last name--\n");
        sortByLastName();
        for(int i = 0; i < size ; i++) {
            System.out.println(accounts[i].toString() + "\n-interest: "
                    + accounts[i].monthlyInterest() + "\n-fee: " + accounts[i].monthlyFee()
                    + "\n-new balance: " + accounts[i].getBalance());
        }
        System.out.println("--end of printing--");
    }

    public void printAccounts() {
        System.out.println("--Listing accounts in the database--");
        for(int i = 0; i < size ; i++) {
            System.out.println(accounts[i].toString());
        }
        System.out.println("--end of listing--");
    }


}
