package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySpendingTest {

    private MySpending testSpending;
    private Expense testExpense;

    @BeforeEach
    public void setup() {
        testSpending = new MySpending();
        testExpense = new Expense("Groceries", 50, false, "Food");
    }

    @Test
    public void testAddExpenseToMySpendingOnce () {
        MySpending.addExpenseToMySpending(testExpense);
        List<Expense> testList = testSpending.getCategories("Food");
        assertTrue(testList.contains(testExpense));
        assertEquals(1, testList.size());
    }

    @Test
    public void testAddExpenseToMySpendingTwice () {
        MySpending.addExpenseToMySpending(testExpense);
        MySpending.addExpenseToMySpending(testExpense);
        List<Expense> testList = testSpending.getCategories("Food");
        assertTrue(testList.contains(testExpense));
        assertEquals(2, testList.size());
    }

}
