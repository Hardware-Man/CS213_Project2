import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * This is the user interface class that handles the transactions and displays the results on the console.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class TransactionManager {

    Scanner commandReader = new Scanner(System.in);
    AccountDatabase accountsDB = new AccountDatabase();


    public void run() {
        System.out.println("Transaction processing starts.....");
        StringTokenizer tokens;

        quit:
        while (true) {
            tokens = new StringTokenizer(commandReader.nextLine());
            String command = tokens.nextToken();

            switch (command.charAt(0)) {
                case 'Q':
                    if (tokens.hasMoreTokens()) {
                        System.out.println("Invalid: more than one token for quit command");
                        break;
                    }
                    break quit;
                case 'O':
                    switch (command.charAt(1)) {
                        case 'C':
                            openCheckingAccount(accountsDB, tokens);
                            break;
                        case 'S':
                            openSavingsAccount(accountsDB, tokens);
                            break;
                        case 'M':
                            openMoneyMarketAccount(accountsDB, tokens);
                            break;
                        default:
                            System.out.println("Command \'" + command + "\' not supported!");
                            break;
                    }

                    break;
                case 'C':
                    closeAccount(accountsDB,command.charAt(1),tokens);
                    break;
                case 'W':

                    break;
                case 'D':

                    break;
                case 'P':
                    displayAccounts(accountsDB,command.charAt(1));
                    break;
                default:
                    System.out.println("Command \'" + command + "\' not supported!");
                    break;
            }

        }

        System.out.println("Transaction processing completed.");
    }

    private void openCheckingAccount(AccountDatabase accounts, StringTokenizer tokens) {
        if (tokens.countTokens() != 5) {
            System.out.println("Wrong number of tokens");
            return;
        }

        String firstName = tokens.nextToken();
        String lastName = tokens.nextToken();
        Profile profile = new Profile(firstName, lastName);

        double balance;
        try {
            balance = Double.valueOf(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        Date openingDate;
        try {
            StringTokenizer dateTokens = new StringTokenizer((tokens.nextToken()), "/", false);
            int day = Integer.parseInt(dateTokens.nextToken());
            int month = Integer.parseInt(dateTokens.nextToken());
            int year = Integer.parseInt(dateTokens.nextToken());
            openingDate = new Date(year, month, day);
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        boolean directDeposit = Boolean.parseBoolean(tokens.nextToken());

        if (accountsDB.add(new Checking(profile, balance, openingDate, directDeposit))) {
            System.out.println("Account opened and added to the database.");
        } else {
            System.out.println("Account is already in the database.");
        }
    }

    private void openSavingsAccount(AccountDatabase accounts, StringTokenizer tokens) {
        if (tokens.countTokens() != 5) {
            System.out.println("Wrong number of tokens");
            return;
        }

        String firstName = tokens.nextToken();
        String lastName = tokens.nextToken();
        Profile profile = new Profile(firstName, lastName);

        double balance;
        try {
            balance = Double.valueOf(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        Date openingDate;
        try {
            StringTokenizer dateTokens = new StringTokenizer((tokens.nextToken()), "/", false);
            int day = Integer.parseInt(dateTokens.nextToken());
            int month = Integer.parseInt(dateTokens.nextToken());
            int year = Integer.parseInt(dateTokens.nextToken());
            openingDate = new Date(year, month, day);
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        boolean isLoyal = Boolean.parseBoolean(tokens.nextToken());

        if (accountsDB.add(new Savings(profile, balance, openingDate, isLoyal))) {
            System.out.println("Account opened and added to the database.");
        } else {
            System.out.println("Account is already in the database.");
        }
    }

    private void openMoneyMarketAccount(AccountDatabase accounts, StringTokenizer tokens) {
        if (tokens.countTokens() != 4) {
            System.out.println("Wrong number of tokens");
            return;
        }

        String firstName = tokens.nextToken();
        String lastName = tokens.nextToken();
        Profile profile = new Profile(firstName, lastName);

        double balance;
        try {
            balance = Double.valueOf(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        Date openingDate;
        try {
            StringTokenizer dateTokens = new StringTokenizer((tokens.nextToken()), "/", false);
            int day = Integer.parseInt(dateTokens.nextToken());
            int month = Integer.parseInt(dateTokens.nextToken());
            int year = Integer.parseInt(dateTokens.nextToken());
            openingDate = new Date(year, month, day);
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        if (accountsDB.add(new MoneyMarket(profile, balance, openingDate, 0))) {
            System.out.println("Account opened and added to the database.");
        } else {
            System.out.println("Account is already in the database.");
        }
    }

    private void closeAccount(AccountDatabase accounts, char accType, StringTokenizer tokens) {
        if (tokens.countTokens() != 2) {
            System.out.println("Wrong number of tokens");
            return;
        }
        Profile profile = new Profile(tokens.nextToken(), tokens.nextToken());
        switch (accType) {
            case 'C':
                if (accounts.remove(new Checking(profile, 0, new Date(1, 1, 1), true))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
            case 'S':
                if (accounts.remove(new Savings(profile, 0, new Date(1, 1, 1), true))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
            case 'M':
                if (accounts.remove(new MoneyMarket(profile, 0, new Date(1, 1, 1), 0))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
            default:
                System.out.println("Command \' C" + accType + "\' not supported!");

        }


    }


    private void depositToAccount(AccountDatabase accounts) {

    }

    private void withdrawFromAccount(AccountDatabase accounts) {

    }

    private void displayAccounts(AccountDatabase accounts, char specification) {
        switch (specification) {
            case 'A':
                accounts.printAccounts();
                break;
            case 'D':
            case 'N':
            default:

        }

    }


}
