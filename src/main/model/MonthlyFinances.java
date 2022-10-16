package model;

import java.util.ArrayList;

// Represents a list of expenses that are to be paid every month
public class MonthlyFinances {
    protected static ArrayList<Expense> monthlyFinances;
    protected static double monthlyIncome;
    protected double percentToSave; //percent of monthly income that should be put into savings
    protected double percentToSaveRoughMonth; // percent of monthly income to put into savings when income is low

    public MonthlyFinances() {
        monthlyFinances = new ArrayList<>();
        percentToSave = 0.2;
        percentToSaveRoughMonth = 0.1;
    }

    // REQUIRES: e is not already in MonthlyFinances
    // MODIFIES: this
    // EFFECTS: adds e to MonthlyFinances if e is to be paid monthly
    public static void addExpense(Expense e) {
        if (e.getStatus()) {
            monthlyFinances.add(e);
        }
    }

    // REQUIRES: e is in MonthlyFinances
    // MODIFIES: this
    // EFFECTS: removes e from MonthlyFinances
    public void removeExpense(Expense e) {
        monthlyFinances.remove(e);
    }

    // EFFECTS: Getter for MonthlyFinances
    public ArrayList<Expense> getMonthlyFinances() {
        return this.monthlyFinances;
    }

    // EFFECTS: Getter for MonthlyIncome
    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    // EFFECTS: Setter for MonthlyIncome
    public static void setMonthlyIncome(double income) {
        monthlyIncome = income;
    }

    // EFFECTS: Getter for percentToSave
    public double getPercentToSave() {
        return this.percentToSave;
    }

    // EFFECTS: Getter for percentToSaveRoughMonth
    public double getPercentToSaveRoughMonth() {
        return this.percentToSaveRoughMonth;
    }

    // EFFECTS: Setter for percentToSave
    public void setPercentToSave(double num) {
        this.percentToSave = num;
    }

    // EFFECTS: Setter for percentToSaveRoughMonth
    public void setPercentToSaveRoughMonth(double num) {
        this.percentToSaveRoughMonth = num;
    }
}
