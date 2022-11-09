package ui;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// MyPiggyBank application for text user interaction
public class MyPiggyBankApp {

    // NOTE: Some code in this class is written using the sample TellerApp project or the JsonSerializationDemo
    // I acknowledge these sources, and I will note '//*' when I use its code.

    private static final String JSON_STORE = "./data/MyPiggyBankAccount.json"; //*
    private Scanner input;
    private MyPiggyBank myPiggyBank;
    private MySpending mySpending;
    private MonthlyFinances myMonthlyFinances;
    private ThisMonthsFinances thisMonthsFinances;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // MODIFIES: this
    // EFFECTS: starts the PiggyBank application
    public MyPiggyBankApp() {
        jsonWriter = new JsonWriter(JSON_STORE); //*
        jsonReader = new JsonReader(JSON_STORE); //*
        runPiggyBank();
    }

    // MODIFIES: this
    // EFFECTS: runs the application
    private void runPiggyBank() { //* Code from TellerApp example
        String command = null;
        boolean keepGoing = false;
        initialize();
        keepGoing = true;


        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                System.out.println("\nWould you like to save before you go?");
                System.out.println("\nType 'y' for yes and 'n' for no");
                String answer = input.next();
                if (Objects.equals(answer, "y")) {
                    savePiggyBank();
                }
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
        System.out.println("\n Type 'load' if you would like to load a saved account");
        System.out.println("\n Type 'new' if you would like to create a new account");
        String answer = input.next();
        if (answer.equals("load")) {
            loadPiggyBank();
        } else if (answer.equals("new")) {
            System.out.println("\nPlease enter your name");
            String ownerName = input.next();
            System.out.println("\nPlease enter the current amount in your bank account");
            double initialValue = input.nextDouble();
            myPiggyBank = new MyPiggyBank(ownerName, initialValue);
            System.out.println("\nPlease enter your set monthly income, or 0 if none");
            double incomeValue = input.nextDouble();
            MonthlyFinances.setMonthlyIncome(incomeValue);
            input.useDelimiter("\n");
            mySpending = myPiggyBank.getMySpending();
            myMonthlyFinances = myPiggyBank.getMyMonthlyFinances();
            thisMonthsFinances = myPiggyBank.getThisMonthsFinances();
            thisMonthsFinances.thisMonthsIncome(incomeValue);
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() { //* code format inspired from TellerApp example
        System.out.println("\nSelect from:");
        System.out.println("\texpense -> add a new monthly expense");
        System.out.println("\tbalance -> view current balance");
        System.out.println("\tadd -> add amount to balance");
        System.out.println("\tpay -> pay a one time expense");
        System.out.println("\tpayMonthly -> pay a monthly expense");
        System.out.println("\tspending -> view my spending");
        System.out.println("\tseeMonthly -> see my monthly expenses");
        System.out.println("\tgoal -> see this months spending goal");
        System.out.println("\tthisMonth -> see this months overdue and on-time expenses");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes the command given by the user
    private void processCommand(String command) { //* code format taken from TellerApp example
        if (command.equals("expense")) {
            newMonthlyExpense();
        } else if (command.equals("balance")) {
            showBalance();
        } else if (command.equals("add")) {
            add();
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
        } else if (command.equals("thisMonth")) {
            thisMonth();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: MonthlyFinances, Expense
    // EFFECTS: adds a new monthly expense
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
        thisMonthsFinances.addToThisMonthsExpenses(expense);
        System.out.println("Expense added!");
    }

    // EFFECTS: prints the current balance
    public void showBalance() {
        System.out.println(myPiggyBank.getCurrentBalance());
    }

    // MODIFIES: MyPiggyBank, Expense
    // EFFECTS: creates and pays a one time expense
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

    // MODIFIES: ThisMonthsFinances, MyPiggyBank
    // EFFECTS: removes the expense from ThisMonthsFinances and pay it
    public void payMonthlyExpense() {
        System.out.println(thisMonthsFinances.getThisMonthsExpenses());
        System.out.println("What is the name of the expense you want to pay?");
        String answer = input.next();
        Expense e = thisMonthsFinances.getSingleExpense(answer);
        myPiggyBank.pay(e);
        System.out.println("Expense paid!");
    }

    // EFFECTS: getter for MySpending
    public void seeMySpending() {
        System.out.println(mySpending.getCategories("Food"));
        System.out.println(mySpending.getCategories("Needs"));
        System.out.println(mySpending.getCategories("Shopping"));
        System.out.println(mySpending.getCategories("Fun"));
    }

    // EFFECTS getter for thisMonthsFinances
    public void seeMonthly() {
        System.out.println(thisMonthsFinances.getMonthlyFinances());
        System.out.println(thisMonthsFinances.getOverdueExpenses());
    }

    // EFFECTS: getter for the amount left to be spent this month
    public void seeGoal() {
        System.out.println("The amount left for spending this month is " + thisMonthsFinances.getThisMonthsSpending());
    }

    // REQUIRES: answer >= 0
    // MODIFIES: MyPiggyBank
    // EFFECTS: adds the given amount to the piggy bank
    public void add() {
        System.out.println("How much would you like to add?");
        double answer = input.nextDouble();
        myPiggyBank.addAmountToPiggyBank(answer);
        System.out.println("Amount added! Your balance is: " + myPiggyBank.getCurrentBalance());
    }

    public void thisMonth() {
        System.out.println("Would you like to add any overdue expenses from last month?");
        System.out.println("Type 'y for yes or 'n' for no...");
        String answer = input.next();
        if (answer.equals("y")) {
            System.out.println("What do you want to call this overdue expense?");
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
            thisMonthsFinances.addOverdueExpense(expense);
            System.out.println("Overdue expense added!");
        }
        System.out.println("Overdue Expenses from last month:");
        System.out.println(thisMonthsFinances.getOverdueExpenses());
        System.out.println("Expenses left to be paid this month:");
        System.out.println(thisMonthsFinances.getThisMonthsExpenses());
    }

    // EFFECTS: saves the piggy bank to file
    public void savePiggyBank() { //*
        try {
            jsonWriter.open();
            jsonWriter.write(myPiggyBank);
            jsonWriter.close();
            System.out.println("Saved " + myPiggyBank.getOwner() + "'s account to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the saved PiggyBank accounts
    public void loadPiggyBank() { //*
        try {
            myPiggyBank = jsonReader.read();
            System.out.println("Loaded " + myPiggyBank.getOwner() + "'s from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



