package ui;

import model.MyPiggyBank;
import model.MonthlyFinances;
import model.MySpending;
import model.Expense;

import java.util.Calendar;
import java.util.Scanner;

// MyPiggyBank application for user interaction
public class MyPiggyBankApp {

    // NOTE: Some code in this class is written using the sample TellerApp project as a template to guide me
    // through building a UI. I acknowledge this source, and I will note '//*' when I use its code.

    private Scanner input;
    private MyPiggyBank myPiggyBank;
    private MonthlyFinances myMonthlyFinances;
    private MySpending mySpending;
    //private Calendar rightNow;

    // EFFECTS: run the PiggyBank application
    public MyPiggyBankApp() {
        myMonthlyFinances = new MonthlyFinances();
        mySpending = new MySpending();
        runPiggyBank();
    }

    private void runPiggyBank() { //* Code from TellerApp example
        String command = null;
        boolean keepGoing = false;
        initialize();
        keepGoing = true;


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: MyPiggyBank
    // EFFECTS: allows user to initialize a MyPiggyBank account
    private void initialize() {
        System.out.println("\nWelcome to the College Student's PiggyBank!");
        input = new Scanner(System.in);
        System.out.println("\nPlease enter your name");
        String ownerName = input.nextLine();
        System.out.println("\nPlease enter the current amount in your bank account");
        double initialValue = input.nextDouble();
        myPiggyBank = new MyPiggyBank(ownerName, initialValue);
        System.out.println("\nPlease enter your set monthly income, or 0 if none");
        double incomeValue = input.nextDouble();
        myMonthlyFinances.setMonthlyIncome(incomeValue);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() { //* code format inspired from TellerApp example
        System.out.println("\nSelect from:");
        System.out.println("\texpense -> add a new monthly expense");
        System.out.println("\tsavings -> set savings settings");
        System.out.println("\tbalance -> view current balance");
        System.out.println("\tpay -> pay a one time expense");
        System.out.println("\tpayMonthly -> pay a monthly expense");
        System.out.println("\tspending -> view my spending");
        System.out.println("\tseeMonthly -> see my monthly expenses");
        System.out.println("\tgoal -> see this months spending goal");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) { //* code format taken from TellerApp example
        if (command.equals("expense")) {
            newMonthlyExpense();
        } else if (command.equals("savings")) {
            setSavingsSettings();
        } else if (command.equals("balance")) {
            //doTransfer();
        } else if (command.equals("pay")) {
            //
        } else if (command.equals("payMonthly")) {
            //
        } else if (command.equals("spending")) {
            //
        } else if (command.equals("seeMonthly")) {
            //
        } else if (command.equals("goal")) {
            //
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void newMonthlyExpense() {
        System.out.println("What do you want to call this monthly expense?");
        String name = input.next();
        System.out.println("What is the amount to be paid?");
        double amount = input.nextDouble();
        System.out.println("\nPlease select a category for this expense. Choose from:");
        System.out.println("\tNeeds");
        System.out.println("\tFun");
        System.out.println("\tFood");
        System.out.println("\tShopping");
        String category = input.next();
        Expense expense = new Expense(name, amount, true, category);
        myMonthlyFinances.addExpense(expense);
        System.out.println("Expense added!");
    }

    private void setSavingsSettings() {
        System.out.println("\nWhat percent of your monthly income do you want to put into savings?");
        System.out.println("\n");
    }
}



