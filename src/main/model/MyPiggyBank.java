package model;

import ui.MyPiggyBankApp;
import model.MySpending;
import model.ThisMonthsFinances;

// Represents a bank account having an owner and an currentBalance
public class MyPiggyBank {
    private String owner;
    private double currentBalance;
   // protected static MyPiggyBank myAccount;
    private MySpending mySpending;
    private MonthlyFinances myMonthlyFinances;
    private ThisMonthsFinances thisMonthsFinances;

    // REQUIRES: owner has length >= 1, currentBalance must be >= 0
    // MODIFIES: this
    // EFFECTS: creates a bank account representation with an associated owner name and initial currentBalance
    public MyPiggyBank(String owner, double initialBalance) {
        this.owner = owner;
        currentBalance = initialBalance;
        this.mySpending = new MySpending();
        this.myMonthlyFinances = new MonthlyFinances();
        this.thisMonthsFinances = new ThisMonthsFinances();
    }

    // REQUIRES: e.getExpenseAmount <= currentBalance
    // MODIFIES: this, MySpending, ThisMonthsFinances
    // EFFECTS: pays an expense by subtracting its amount from currentBalance and adds expense to MySpending
    public void pay(Expense e) {
        this.currentBalance = this.currentBalance - e.getExpenseAmount();
        mySpending.addExpenseToMySpending(e);
        thisMonthsFinances.payExpense(e);
    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    // EFFECTS: increases current balance by amount.
    public void addAmountToPiggyBank(double amount) {
        currentBalance = currentBalance + amount;
    }

    // EFFECTS: Getter for Owner
    public String getOwner() {
        return this.owner;
    }

    // EFFECTS: Getter for currentBalance
    public double getCurrentBalance() {
        return this.currentBalance;
    }

    // EFFECTS: Getter for mySpending
    public MySpending getMySpending() {
        return this.mySpending;
    }

    // EFFECTS: Getter for myMonthlyFinances
    public MonthlyFinances getMyMonthlyFinances() {
        return this.myMonthlyFinances;
    }

    // EFFECTS: Getter for thisMonthsFinances
    public ThisMonthsFinances getThisMonthsFinances() {
        return this.thisMonthsFinances;
    }
}
