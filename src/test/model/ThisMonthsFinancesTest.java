package model;

import model.ThisMonthsFinances;
import model.MonthlyFinances;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThisMonthsFinancesTest {

    private ThisMonthsFinances testFinances;
    private MonthlyFinances monthlyFinances;
    private Expense testExpenseCar;
    private Expense testExpenseClothes;
    private Expense testExpenseRent;

    @BeforeEach
    public void setup() {
        this.testExpenseCar = new Expense("Car", 300, true, "Needs");
        this.testExpenseClothes = new Expense("Clothes", 50, false, "Shopping");
        this.testExpenseRent = new Expense("Rent", 100, true, "Needs");
        this.monthlyFinances = new MonthlyFinances();
        this.testFinances = new ThisMonthsFinances();
        monthlyFinances.setMonthlyIncome(100);
        MonthlyFinances.addExpense(testExpenseRent);
        testFinances.addToThisMonthsExpenses(testExpenseCar);
    }

    @Test
    public void testThisMonthsIncomeLow() {
        testFinances.thisMonthsIncome(90);
        double savings = 90 * monthlyFinances.getPercentToSaveRoughMonth();
        assertEquals(savings, testFinances.getThisMonthsSaving());
        assertEquals(90 - savings, testFinances.getThisMonthsSpending());
    }

    @Test
    public void testThisMonthsIncomeExact() {
        testFinances.thisMonthsIncome(100);
        double savings = 100 * monthlyFinances.getPercentToSave();
        assertEquals(savings, testFinances.getThisMonthsSaving());
        assertEquals(100 - savings, testFinances.getThisMonthsSpending());
    }


    @Test
    public void testThisMonthsIncomeHigh() {
        testFinances.thisMonthsIncome(500);
        double savings = 500 * monthlyFinances.getPercentToSave();
        assertEquals(savings, testFinances.getThisMonthsSaving());
        assertEquals(500 - savings, testFinances.getThisMonthsSpending());
    }

    @Test
    public void testPayExpensePresentInList() {
        testFinances.setThisMonthsSpending(400);
        testFinances.payExpense(testExpenseCar);
        assertFalse(testFinances.getThisMonthsExpenses().contains(testExpenseCar));
        assertEquals(100, testFinances.getThisMonthsSpending());
    }

    @Test
    public void testPayExpenseNotPresentInList() {
        assertFalse(testFinances.getThisMonthsExpenses().contains(testExpenseClothes));
        testFinances.setThisMonthsSpending(400);
        testFinances.payExpense(testExpenseClothes);
        assertFalse(testFinances.getThisMonthsExpenses().contains(testExpenseClothes));
        assertEquals(350, testFinances.getThisMonthsSpending());
    }

    @Test
    public void testResetMonth1stDaySomethingOverdue() {
        testFinances.setDayOfMonth(1);
        testFinances.resetMonth();
        assertTrue(testFinances.getOverdueExpenses().contains(testExpenseCar));
        assertEquals(1, testFinances.getMonthlyFinances().size());
        assertTrue(testFinances.getThisMonthsExpenses().contains(testExpenseRent));
    }

    @Test
    public void testResetMonth1stDayNothingOverdue() {
        testFinances.setDayOfMonth(1);
        testFinances.getThisMonthsExpenses().remove(testExpenseCar);
        testFinances.resetMonth();
        assertEquals(0, testFinances.getOverdueExpenses().size());
        assertEquals(1, testFinances.getMonthlyFinances().size());
        assertTrue(testFinances.getThisMonthsExpenses().contains(testExpenseRent));
    }

    @Test
    public void testResetMonthNot1stDay() {
        testFinances.setDayOfMonth(2);
        testFinances.resetMonth();
        assertEquals(1, testFinances.getMonthlyFinances().size());
        assertTrue(testFinances.getThisMonthsExpenses().contains(testExpenseCar));
    }

    @Test
    public void testGetSingleExpensePresent() {
        assertEquals(testExpenseCar, testFinances.getSingleExpense("Car"));
    }

    @Test
    public void testGetSingleExpenseNotPresent() {
        assertEquals(null, testFinances.getSingleExpense("Rent"));
    }
}
