package model;

import model.MyPiggyBank;
import model.Expense;
import model.ThisMonthsFinances;
import model.MySpending;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyPiggyBankTest {

    private MyPiggyBank testAccount;
    private Expense testExpenseRent;
    private Expense testExpenseHair;
    private MySpending testSpending;
    private ThisMonthsFinances testFinances;

    @BeforeEach
    public void setup() {
        testAccount = new MyPiggyBank("Ella", 50);
        testExpenseRent = new Expense("Rent", 50, true, "Needs");
        testExpenseHair = new Expense("Hair", 20, false, "Shopping");
        testSpending = new MySpending();
        testFinances = new ThisMonthsFinances();
    }

    @Test
    public void testPayExactlyEnough() {
        testAccount.pay(testExpenseRent);
        assertEquals(0, testAccount.getCurrentBalance());
    }

    @Test
    public void testPayMoreThanEnough() {
        testAccount.pay(testExpenseHair);
        assertEquals(30, testAccount.getCurrentBalance());
    }

    @Test
    public void testAddAmountToPiggyBankOnce() {
        testAccount.addAmountToPiggyBank(5);
        assertEquals(55, testAccount.getCurrentBalance());
    }

    @Test
    public void testAddAmountToPiggyBankTwice() {
        testAccount.addAmountToPiggyBank(5);
        testAccount.addAmountToPiggyBank(100);
        assertEquals(155, testAccount.getCurrentBalance());
    }

}
