import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is the user interface class that handles the transactions and displays the results on the console.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class TransactionManager {

    AccountDatabase accountsDB = new AccountDatabase();

    /**
     * Handles the IO of transactions, and handles command inputs from the client
     */
    public void run() {
        System.out.println("Transaction processing starts.....");
        Scanner commandReader = new Scanner(System.in);
        StringTokenizer tokens;

        quit:
        while (true) {
            String command;
            try {
                tokens = new StringTokenizer(commandReader.nextLine());
                command = tokens.nextToken();
            } catch (NoSuchElementException e) {
                System.out.println("Please enter a command");
                continue;
            }
            if (command.length() > 2) {
                System.out.println("Command '" + command + "' not supported!");
                continue;
            }
            switch (command.charAt(0)) {
                case 'Q':
                    if (tokens.hasMoreTokens()) {
                        System.out.println("Invalid: more than one token for quit command");
                        break;
                    }
                    break quit;
                case 'O':
                    if (command.length() == 1) {
                        System.out.println("Command 'O' not supported!");
                        break;
                    }
                    switch (command.charAt(1)) {
                        case 'C':
                        case 'S':
                        case 'M':
                            openAccount(command.charAt(1), tokens);
                            break;
                        default:
                            System.out.println("Command '" + command + "' not supported!");
                    }
                    break;
                case 'C':
                    if (command.length() == 1) {
                        System.out.println("Command 'C' not supported!");
                        break;
                    }
                    switch (command.charAt(1)) {
                        case 'C':
                        case 'S':
                        case 'M':
                            closeAccount(command.charAt(1), tokens);
                            break;
                        default:
                            System.out.println("Command '" + command + "' not supported!");
                    }
                    break;
                case 'W':
                    if (command.length() == 1) {
                        System.out.println("Command 'W' not supported!");
                        break;
                    }
                    withdrawFromAccount(command.charAt(1), tokens);
                    break;
                case 'D':
                    if (command.length() == 1) {
                        System.out.println("Command 'D' not supported!");
                        break;
                    }
                    depositToAccount(command.charAt(1), tokens);
                    break;
                case 'P':
                    if (command.length() == 1) {
                        System.out.println("Command 'P' not supported!");
                        break;
                    }
                    displayAccounts(command.charAt(1));
                    break;
                default:
                    System.out.println("Command '" + command + "' not supported!");
                    break;
            }

        }

        System.out.println("Transaction processing completed.");
    }

    /**
     * Opens an account and adds it to the database
     *
     * @param tokens from user input containing user information (varies based on account type)
     */
    private void openAccount(char accType, StringTokenizer tokens) {
        if (accType == 'M') {
            if (tokens.countTokens() != 4) {
                System.out.println("Wrong number of tokens");
                return;
            }
        } else {
            if (tokens.countTokens() != 5) {
                System.out.println("Wrong number of tokens");
                return;
            }
        }

        String firstName = tokens.nextToken();
        String lastName = tokens.nextToken();
        Profile profile = new Profile(firstName, lastName);

        double balance;
        try {
            balance = Double.parseDouble(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        Date openingDate;
        try {
            StringTokenizer dateTokens = new StringTokenizer((tokens.nextToken()), "/", false);
            int month = Integer.parseInt(dateTokens.nextToken());
            int day = Integer.parseInt(dateTokens.nextToken());
            int year = Integer.parseInt(dateTokens.nextToken());
            openingDate = new Date(year, month, day);
            if (!openingDate.isValid()) {
                System.out.println(openingDate.toString() + " is not a valid date!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        switch (accType) {
            case 'C':
                String boolChecking = tokens.nextToken();
                if (!(boolChecking.equalsIgnoreCase("true") || boolChecking.equalsIgnoreCase("false"))) {
                    System.out.println("Input data type mismatch.");
                    return;
                }

                boolean directDeposit = Boolean.parseBoolean(boolChecking);

                if (accountsDB.add(new Checking(profile, balance, openingDate, directDeposit))) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
                break;
            case 'S':
                String boolSavings = tokens.nextToken();
                if (!(boolSavings.equalsIgnoreCase("true") || boolSavings.equalsIgnoreCase("false"))) {
                    System.out.println("Input data type mismatch.");
                    return;
                }

                boolean isLoyal = Boolean.parseBoolean(boolSavings);

                if (accountsDB.add(new Savings(profile, balance, openingDate, isLoyal))) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
                break;
            default:
                if (accountsDB.add(new MoneyMarket(profile, balance, openingDate, 0))) {
                    System.out.println("Account opened and added to the database.");
                } else {
                    System.out.println("Account is already in the database.");
                }
                break;
        }
    }

    /**
     * Closes account and deletes it from the database
     *
     * @param accType type of account that will be deleted
     * @param tokens  including information like account holder's name
     */
    private void closeAccount(char accType, StringTokenizer tokens) {
        if (tokens.countTokens() != 2) {
            System.out.println("Wrong number of tokens");
            return;
        }

        Profile profile = new Profile(tokens.nextToken(), tokens.nextToken());
        switch (accType) {
            case 'C':
                if (accountsDB.remove(new Checking(profile, 0, new Date(1, 1, 1), true))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
                break;
            case 'S':
                if (accountsDB.remove(new Savings(profile, 0, new Date(1, 1, 1), true))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
                break;
            default:
                if (accountsDB.remove(new MoneyMarket(profile, 0, new Date(1, 1, 1), 0))) {
                    System.out.println("Account closed and removed from the database.");
                } else {
                    System.out.println("Account does not exist");
                }
        }
    }

    /**
     * Deposits funds into an account
     *
     * @param accType type of account
     * @param tokens  information about the account, like owner
     */
    private void depositToAccount(char accType, StringTokenizer tokens) {
        if (tokens.countTokens() != 3) {
            System.out.println("Wrong number of tokens");
            return;
        }

        Profile profile = new Profile(tokens.nextToken(), tokens.nextToken());
        double amount;
        try {
            amount = Double.parseDouble(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        DecimalFormat moneyFormat = new DecimalFormat("0.00");
        switch (accType) {
            case 'C':
                if (accountsDB.deposit(new Checking(profile, amount, new Date(1, 1, 1), false), amount)) {
                    System.out.println(moneyFormat.format(amount) + " deposited to account.");
                    System.out.println("Account does not exist.");
                }
                break;
            case 'S':
                if (accountsDB.deposit(new Savings(profile, amount, new Date(1, 1, 1), false), amount)) {
                    System.out.println(moneyFormat.format(amount) + " deposited to account.");
                } else {
                    System.out.println("Account does not exist.");
                }
                break;
            case 'M':
                if (accountsDB.deposit(new MoneyMarket(profile, amount, new Date(1, 1, 1), 0), amount)) {
                    System.out.println(moneyFormat.format(amount) + " deposited to account.");
                } else {
                    System.out.println("Account does not exist.");
                }
                break;
            default:
                System.out.println("Command 'C" + accType + "' not supported!");
        }
    }

    /**
     * Withdraws funds from an account
     *
     * @param accType type of account
     * @param tokens  information about the account, like owner
     */
    private void withdrawFromAccount(char accType, StringTokenizer tokens) {
        if (tokens.countTokens() != 3) {
            System.out.println("Wrong number of tokens");
            return;
        }

        Profile profile = new Profile(tokens.nextToken(), tokens.nextToken());
        double amount;
        try {
            amount = Double.parseDouble(tokens.nextToken());
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return;
        }

        DecimalFormat moneyFormat = new DecimalFormat("0.00");
        switch (accType) {
            case 'C':
                switch (accountsDB.withdrawal(new Checking(profile, amount, new Date(1, 1, 1), false), amount)) {
                    case -1:
                        System.out.println("Account does not exist.");
                        break;
                    case 1:
                        System.out.println("Insufficient funds.");
                        break;
                    default:
                        System.out.println(moneyFormat.format(amount) + " withdrawn from account.");
                }
                break;
            case 'S':
                switch (accountsDB.withdrawal(new Savings(profile, amount, new Date(1, 1, 1), false), amount)) {
                    case -1:
                        System.out.println("Account does not exist.");
                        break;
                    case 1:
                        System.out.println("Insufficient funds.");
                        break;
                    default:
                        System.out.println(moneyFormat.format(amount) + " withdrawn from account.");
                }
                break;
            case 'M':
                switch (accountsDB.withdrawal(new MoneyMarket(profile, amount, new Date(1, 1, 1), 0), amount)) {
                    case -1:
                        System.out.println("Account does not exist.");
                        break;
                    case 1:
                        System.out.println("Insufficient funds.");
                        break;
                    default:
                        System.out.println(moneyFormat.format(amount) + " withdrawn from account.");
                }
                break;
            default:
                System.out.println("Command 'C" + accType + "' not supported!");
        }
    }

    /**
     * Prints contents of database in ways depending on
     * specification (none, by open date, or by last name)
     *
     * @param specification for how to display accounts
     */
    private void displayAccounts(char specification) {
        switch (specification) {
            case 'A':
                accountsDB.printAccounts();
                break;
            case 'D':
                accountsDB.printByDateOpen();
                break;
            case 'N':
                accountsDB.printByLastName();
                break;
            default:

        }
    }
}
