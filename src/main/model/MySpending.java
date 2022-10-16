package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Stores expenses that have been paid, and separates them into categories for future analysis
public class MySpending {

    private String month;
    private static Map<String, ArrayList<Expense>> mySpending;

    public MySpending(String month) {
        this.month = month;
        this.mySpending = new HashMap<>();
        ArrayList<Expense> needs = new ArrayList<>();
        ArrayList<Expense> fun = new ArrayList<>();
        ArrayList<Expense> food = new ArrayList<>();
        ArrayList<Expense> shopping = new ArrayList<>();

        mySpending.put("Needs", needs);
        mySpending.put("Fun", fun);
        mySpending.put("Food", food);
        mySpending.put("Shopping", shopping);
    }

    // REQUIRES: key is one of Needs, Fun, Food, or Shopping
    // MODIFIES: this
    // EFFECTS: adds expense e to its corresponding locker in MySpending
    public static void addExpenseToMySpending(Expense e) {
        String key = e.getCategory();
        List<Expense> findLocker = mySpending.get(key);
        findLocker.add(e);
    }

    // REQUIRES: key is one of Needs, Fun, Food, or Shopping
    // EFFECTS: returns the list of expenses from inside the locker with the given key
    public List<Expense> getCategories(String key) {
        List<Expense> findLocker = mySpending.get(key);
        return findLocker;
    }
}
