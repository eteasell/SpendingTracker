package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class MonthlyFinancesTest {

    private MonthlyFinances testMonthlyFinances;
    private Expense testExpenseRent;
    private Expense testExpenseHair;

    @BeforeEach
    public void setup() {
        this.testMonthlyFinances = new MonthlyFinances();
        testExpenseRent = new Expense("Rent", 50, true, "Needs");
        testExpenseHair = new Expense("Hair", 20, false, "Shopping");

    }

    @Test
    public void testAddExpenseMonthly() {
        testMonthlyFinances.addExpense(testExpenseRent);
        ArrayList<Expense> testerList = testMonthlyFinances.getMonthlyFinances();
        assertEquals(1, testerList.size());
        assertTrue(testerList.contains(testExpenseRent));
    }

    @Test
    public void testAddExpenseNotMonthly() {
        testMonthlyFinances.addExpense(testExpenseHair);
        ArrayList<Expense> testerList = testMonthlyFinances.getMonthlyFinances();
        assertEquals(0, testerList.size());
        assertFalse(testerList.contains(testExpenseHair));
    }

    @Test
    public void testRemoveExpense() {
        testMonthlyFinances.addExpense(testExpenseRent);
        testMonthlyFinances.removeExpense(testExpenseRent);
        ArrayList<Expense> testerList = testMonthlyFinances.getMonthlyFinances();
        assertEquals(0, testerList.size());
        assertFalse(testerList.contains(testExpenseRent));
    }

    @Test
    public void testMonthlyIncome() {
        testMonthlyFinances.setMonthlyIncome(100);
        double testIncome = testMonthlyFinances.getMonthlyIncome();
        assertEquals(100, testIncome);
    }

}
