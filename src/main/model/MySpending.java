package model;

import model.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Stores expenses that have been paid, and separates them into categories for future analysis
public class MySpending {

    private String month;
    private static Map<String, List<Expense>> map;

    public MySpending(String month) {
        this.month = month;
        Map<String, List<Expense>> map = new HashMap<>();
        List<Expense> needs = new ArrayList<>();
        List<Expense> fun = new ArrayList<>();
        List<Expense> food = new ArrayList<>();
        List<Expense> shopping = new ArrayList<>();

        map.put("Needs", needs);
        map.put("Fun", fun);
        map.put("Food", food);
        map.put("Shopping", shopping);
    }

    // REQUIRES: key is one of Needs, Fun, Food, or Shopping
    // MODIFIES: this
    // EFFECTS: adds expense e to its corresponding locker in MySpending
    public static void addExpenseToMySpending(Expense e) {
        String key = e.getCategory();
        List<Expense> findLocker = map.get(key);
        findLocker.add(e);
    }

    // REQUIRES: key is one of Needs, Fun, Food, or Shopping
    // EFFECTS: returns the list of expenses from inside the locker with the given key
    public List<Expense> getCategories(String key) {
        List<Expense> findLocker = map.get(key);
        return findLocker;
    }
}
