package persistance;

import model.MyPiggyBank;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that read MyPiggyBank data from the stored JSON file
public class JsonReader {

    // code in this class taken from JsonSerializationDemo program provided in the course material

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyPiggyBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
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
        //addThingies(wr, jsonObject);
        return account;
    }

    //TODO: design functions to be called in parseMyPiggyBank to rebuild my account
}
