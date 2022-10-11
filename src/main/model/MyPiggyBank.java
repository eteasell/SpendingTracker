package model;

import ui.MyPiggyBankApp;

// Represents a bank account having an owner and an currentBalance
public class MyPiggyBank {
    private String owner;
    private double currentBalance;
    protected static MyPiggyBank myAccount;

    // REQUIRES: owner has length >= 1, currentBalance must be >= 0
    // MODIFIES: this
    // EFFECTS: creates a bank account representation with an associated owner name and initial currentBalance
    public MyPiggyBank(String owner, double initialBalance) {
        this.owner = owner;
        currentBalance = initialBalance;
    }

    public String getOwner() {
        return this.owner;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    // REQUIRES: e.getExpenseAmount >= currentBalance
    // MODIFIES: this
    // EFFECTS: pays an expense by subtracting its amount from currentBalance
    public void pay(Expense e) {
        this.currentBalance = this.currentBalance - e.getExpenseAmount();
    }
}
