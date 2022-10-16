package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

// MyPiggyBank application for user interaction
public class MyPiggyBankApp {

    // NOTE: Some code in this class is written using the sample TellerApp project as a template to guide me
    // through building a UI. I acknowledge this source, and I will note '//*' when I use its code.

    private Scanner input;
    private MyPiggyBank myPiggyBank;
    private MonthlyFinances myMonthlyFinances;
    private MySpending mySpending;
    private ThisMonthsFinances thisMonth;

    // EFFECTS: run the PiggyBank application
    public MyPiggyBankApp() {
        myMonthlyFinances = new MonthlyFinances();
        thisMonth = new ThisMonthsFinances();
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
            //command = command.toLowerCase();

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
        MonthlyFinances.setMonthlyIncome(incomeValue);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() { //* code format inspired from TellerApp example
        System.out.println("\nSelect from:");
        System.out.println("\texpense -> add a new monthly expense");
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
        } else if (command.equals("balance")) {
            showBalance();
        } else if (command.equals("pay")) {
            payOneTimeExpense();
        } else if (command.equals("payMonthly")) {
            payMonthlyExpense();
        } else if (command.equals("spending")) {
            seeMySpending();
        } else if (command.equals("seeMonthly")) {
            seeMonthly();
        } else if (command.equals("goal")) {
            seeGoal();
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
        this.myMonthlyFinances.addExpense(expense);
        this.thisMonth.addToThisMonthsExpenses(expense);
        System.out.println("Expense added!");
    }

    public void showBalance() {
        System.out.println(myPiggyBank.getCurrentBalance());
    }

    public void payOneTimeExpense() {
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
        Expense expense = new Expense(name, amount, false, category);
        myPiggyBank.pay(expense);
        System.out.println("Expense paid!");
    }

    public void payMonthlyExpense() {
        System.out.println(thisMonth.getThisMonthsExpenses());
        System.out.println("What is the number in the list of the expense you want to pay?");
        int answer = input.nextInt();
        Expense e = thisMonth.getThisMonthsExpenses().get(answer);
        myPiggyBank.pay(e);
        System.out.println("Expense paid!");
    }

    public void seeMySpending() {
        System.out.println(mySpending);
    }

    public void seeMonthly() {
        System.out.println(thisMonth);
    }

    public void seeGoal() {
        System.out.println("The amount left for spending this month is " + thisMonth.getThisMonthsSpending());
    }
}



