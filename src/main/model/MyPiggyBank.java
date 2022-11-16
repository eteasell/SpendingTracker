package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.List;

// Represents a bank account having an owner and an currentBalance
public class MyPiggyBank implements Writable {
    private String owner;
    private double currentBalance;
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
        if (e.getStatus()) {
            thisMonthsFinances.payMonthly(e);
        } else {
            thisMonthsFinances.payNonMonthly(e);
        }
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

    // EFFECTS: Creates a JSON array for this PiggyBank
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.owner);
        json.put("currentBalance", this.currentBalance);
        json.put("mySpending", mySpendingToJson());
        json.put("mySpendingMonth", mySpending.getMonth());
        json.put("myMonthlyFinances", myMonthlyFinancesToJson());
        json.put("myMonthlyFinancesData", myMonthlyFinancesDataToJson());
        json.put("thisMonthsExpenses", thisMonthsExpensesToJson());
        json.put("overdueExpenses", overdueExpensesToJson());
        json.put("thisMonthsFinancesData", thisMonthsFinancesDataToJson());
        return json;
    }

    // EFFECTS: returns mySpending in this MyPiggyBank as a JSON array
    private JSONArray mySpendingToJson() {
        JSONArray jsonArray = new JSONArray();

        List<Expense> myNeeds = mySpending.getCategories("Needs");
        List<Expense> myFun = mySpending.getCategories("Fun");
        List<Expense> myFood = mySpending.getCategories("Food");
        List<Expense> myShopping = mySpending.getCategories("Shopping");

        for (Expense e : myNeeds) {
            jsonArray.put(e.toJson());
        }
        for (Expense e : myFun) {
            jsonArray.put(e.toJson());
        }
        for (Expense e : myFood) {
            jsonArray.put(e.toJson());
        }
        for (Expense e : myShopping) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns just the list of myMonthlyFinances in the PiggyBank as a JSONArray
    private JSONArray myMonthlyFinancesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : myMonthlyFinances.getMonthlyFinances()) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns the non-list fields of data from myMonthlyFinances in the PiggyBank as a JSONObject
    private JSONObject myMonthlyFinancesDataToJson() {
        JSONObject json = new JSONObject();
        json.put("monthlyIncome", myMonthlyFinances.getMonthlyIncome());
        json.put("percentToSave", myMonthlyFinances.getPercentToSave());
        json.put("percentToSaveRoughMonth", myMonthlyFinances.getPercentToSaveRoughMonth());

        return json;
    }

    // EFFECTS: returns just thisMonthsExpenses from thisMonthsFinances in the PiggyBank as a JSONArray
    private JSONArray thisMonthsExpensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : thisMonthsFinances.getThisMonthsExpenses()) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns just overdueExpenses from thisMonthsFinances in the PiggyBank as a JSONArray
    private JSONArray overdueExpensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : thisMonthsFinances.getOverdueExpenses()) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns the non-list fields of data from thisMonthsFinances in the PiggyBank as a JSONObject
    private JSONObject thisMonthsFinancesDataToJson() {
        JSONObject json = new JSONObject();
        json.put("saving", thisMonthsFinances.getThisMonthsSaving());
        json.put("spending", thisMonthsFinances.getThisMonthsSpending());

        return json;
    }

}
