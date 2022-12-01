package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that read MyPiggyBank data from the stored JSON file
public class JsonReader {

    // code in this class taken from JsonSerializationDemo program provided in the course material

    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyPiggyBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Account loaded from: " + this.source));
        return parseMyPiggyBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MyPiggyBank from JSON object and returns it
    private MyPiggyBank parseMyPiggyBank(JSONObject jsonObject) {
        String owner = jsonObject.getString("name");
        double balance = jsonObject.getDouble("currentBalance");
        MyPiggyBank account = new MyPiggyBank(owner, balance);
        addMySpending(account, jsonObject);
        addMonthlyFinances(account, jsonObject);
        addThisMonthsFinances(account, jsonObject);
        return account;
    }

    // MODIFIES: account
    // EFFECTS: parses mySpending from JSON object and adds it to account
    private void addMySpending(MyPiggyBank account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mySpending");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            MySpending mySpending = account.getMySpending();
            mySpending.addExpenseToMySpending(getExpenseFromJson(nextExpense));
        }
    }


    // EFFECTS: parses expense from JSON object
    private Expense getExpenseFromJson(JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        double amount = jsonObject.getDouble("expenseAmount");
        boolean dueMonthly = jsonObject.getBoolean("dueMonthly");
        String category = jsonObject.getString("category");
        return new Expense(name, amount, dueMonthly, category);
    }

    // EFFECTS: parses MonthlyFinances from JSON
    private void addMonthlyFinances(MyPiggyBank account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("myMonthlyFinances");
        JSONObject data = jsonObject.getJSONObject("myMonthlyFinancesData");
        MonthlyFinances myMonthlyFinances = account.getMyMonthlyFinances();
        myMonthlyFinances.setMonthlyIncome(data.getDouble("monthlyIncome"));
        myMonthlyFinances.setPercentToSave(data.getDouble("percentToSave"));
        myMonthlyFinances.setPercentToSaveRoughMonth(data.getDouble("percentToSaveRoughMonth"));
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            myMonthlyFinances.addExpense(getExpenseFromJson(nextExpense));
        }
    }

    // EFFECTS: parses ThisMonthsFinances from JSON
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addThisMonthsFinances(MyPiggyBank account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thisMonthsExpenses");
        JSONArray overdue = jsonObject.getJSONArray("overdueExpenses");
        JSONObject data = jsonObject.getJSONObject("thisMonthsFinancesData");
        JSONArray monthly = jsonObject.getJSONArray("paidThisMonth");
        JSONArray nonMonthly = jsonObject.getJSONArray("nonMonthlyPaid");
        ThisMonthsFinances thisMonthsFinances = account.getThisMonthsFinances();
        thisMonthsFinances.setThisMonthsSaving(data.getDouble("saving"));
        thisMonthsFinances.setThisMonthsSpending(data.getDouble("spending"));
        for (Object json : overdue) {
            JSONObject nextExpense = (JSONObject) json;
            thisMonthsFinances.addOverdueExpense(getExpenseFromJson(nextExpense));
        }
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            thisMonthsFinances.addToThisMonthsExpenses(getExpenseFromJson(nextExpense));
        }
        for (Object json : nonMonthly) {
            JSONObject nextExpense = (JSONObject) json;
            thisMonthsFinances.addToNonMonthlyPaid(getExpenseFromJson(nextExpense));
        }
        for (Object json : monthly) {
            JSONObject nextExpense = (JSONObject) json;
            thisMonthsFinances.addToPaidThisMonth(getExpenseFromJson(nextExpense));
        }
    }
}
