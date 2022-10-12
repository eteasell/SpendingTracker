package model;

import model.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Stores expenses that have been paid, and separates them into categories for future analysis
public class MySpending {

    private String month;
    private Map<String, List<Expense>> map;

    public MySpending(String month) {
        this.month = month;
        Map<String, List<Expense>> map = new HashMap<>();
        List<Expense> monthlyPayments = new ArrayList<>();
        List<Expense> leisure = new ArrayList<>();
        List<Expense> groceries = new ArrayList<>();
        List<Expense> restaurants = new ArrayList<>();
        List<Expense> necessities = new ArrayList<>();

        map.put("Monthly Payments", monthlyPayments);
        map.put("Leisure", leisure);
        map.put("Groceries", groceries);
        map.put("Restaurants", restaurants);
        map.put("Necessities", necessities);
    }

    public void addExpenseToMySpending(Expense e, String key) {
        List<Expense> findLocker = map.get(key);
        findLocker.add(e);
    }

    // I want to be able to return the map corresponding to the given month
    public Map<String, List<Expense>> getMap(String month) {
        return null;
    }
}
