import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class xmlToJSON {

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String XML_PATH;

    static {
        try {
            XML_PATH = new String(Files.readAllBytes(Paths.get("sampleXML.xml")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(XML_PATH);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            //System.out.println(jsonPrettyPrintString);
            FileWriter fileWriter = new FileWriter("convertedJSON.json");
            fileWriter.write(jsonPrettyPrintString);
            fileWriter.flush();

            //to read particular key
            JSONArray properties = xmlJSONObj.getJSONObject("dataFlow").getJSONObject("nodes").getJSONArray("node");
            String jsonPrettyPrintString1 = properties.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString1);

        } catch (JSONException je) {
            System.out.println(je);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}