package model;

import model.MyPiggyBank;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyPiggyBankTest {

    private MyPiggyBank testAccount;
    private Expense testExpenseRent;
    private Expense testExpenseHair;

    @BeforeEach
    public void setup() {
        testAccount = new MyPiggyBank("Ella", 50);
        testExpenseRent = new Expense("Rent", 50, true, "Needs");
        testExpenseHair = new Expense("Hair", 20, false, "Shopping");
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
}
