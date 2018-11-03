package florida.es.starwarsdb.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import florida.es.starwarsdb.R;
import florida.es.starwarsdb.SWDatabaseEntry;

public class SWJsonParser {

    protected static String RESULTS_JSON = "results";
    protected static String NAME_JSON = "name";
    protected static String HEIGHT_JSON = "height";
    protected static String MASS_JSON = "mass";
    protected static String GENDER_JSON = "gender";

    public static List<SWDatabaseEntry> parseJSONDatabase(InputStream jsonFile) throws java.io.IOException, org.json.JSONException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonFile));
        StringBuilder jsonString = new StringBuilder();
        String line = null;
        while( (line=reader.readLine())!=null ){
            jsonString.append(line);
        }
        reader.close();
        List<SWDatabaseEntry> listCharacterEntries = new ArrayList<SWDatabaseEntry>();
        JSONObject database = new JSONObject(jsonString.toString());
        JSONArray listCharacters = database.getJSONArray(RESULTS_JSON);
        int totalCharacters = listCharacters.length();
        for(int i=0;i<totalCharacters;i++){
            JSONObject character = listCharacters.getJSONObject(i);
            String name = character.getString(NAME_JSON);
            String gender = character.getString(GENDER_JSON);
            String mass = character.getString(MASS_JSON);
            String height = character.getString(HEIGHT_JSON);
            SWDatabaseEntry entry = new SWDatabaseEntry(name,height,mass,gender);
            listCharacterEntries.add(entry);
        }

        return listCharacterEntries;
    }

}
