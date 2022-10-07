package model;

// Represents a bank account having an owner and an currentBalance
public class MyPiggyBank {
    private String owner;
    private double currentBalance;

    // REQUIRES: owner has length >= 1, currentBalance must be >= 0
    // MODIFIES: this
    // EFFECTS: creates a bank account representation with an associated owner name and initial currentBalance
    public MyPiggyBank(String owner, double initialBalance) {
        this.owner = owner;
        this.currentBalance = initialBalance;
    }

    public String getOwner() {
        return this.owner;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }
}
