package model;

import org.json.JSONObject;
import persistance.Writable;

// Represents an expense, either to be paid one time or monthly, with an amount, a name, and a category
public class Expense implements Writable {
    private String title;
    private double expenseAmount;
    private Boolean dueMonthly; // True if the expense is to be paid monthly, false otherwise
    private String category; // One of Needs, Fun, Food, or Shopping

    public Expense(String title, double expenseAmount, Boolean status, String category) {
        this.title = title;
        this.expenseAmount = expenseAmount;
        this.dueMonthly = status;
        this.category = category;
    }

    // EFFECTS: Getter for category
    public String getCategory() {
        return this.category;
    }

    // EFFECTS: Getter for title
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: Getter for expenseAmount
    public double getExpenseAmount() {
        return this.expenseAmount;
    }

    // EFFECTS: Getter for status
    public Boolean getStatus() {
        return this.dueMonthly;
    }

    // EFFECTS: overrides the java method to return the expenses title
    @Override
    public String toString() {
        return this.title;
    }

    // EFFECTS: converts expenses to JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("expenseAmount", this.expenseAmount);
        json.put("dueMonthly", this.dueMonthly);
        json.put("category", this.category);
        return json;
    }
}
