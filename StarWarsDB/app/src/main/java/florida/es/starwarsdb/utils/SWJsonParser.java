package florida.es.starwarsdb.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

/**
 * An util class employed to parse the JSON that contains the data about the Star Wars characters
 */
public class SWJsonParser {

    /**
     * Static attributes that contain the key employed in the JSON to store varied information
     */
    protected static String RESULTS_JSON = "results";
    protected static String NAME_JSON = "name";
    protected static String HEIGHT_JSON = "height";
    protected static String MASS_JSON = "mass";
    protected static String GENDER_JSON = "gender";
    protected static String HAIR_COLOR_JSON = "hair_color";
    protected static String SKIN_COLOR_JSON = "skin_color";
    protected static String BIRTH_YEAR_JSON = "birth_year";

    /**
     * Static method that parses a JSON file into a List of Star Wars characters
     * @param jsonFile an input stream representing the JSON file
     * @return A list of Star Wars characters
     * @throws java.io.IOException when problems reading/finding the file
     * @throws org.json.JSONException when the JSON file is in incorrect format
     */
    public static List<SWDatabaseEntry> parseJSONDatabase(InputStream jsonFile) throws java.io.IOException, org.json.JSONException{
        //First read entire file into string
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonFile));
        StringBuilder jsonString = new StringBuilder();
        String line = null;
        while( (line=reader.readLine())!=null ){
            jsonString.append(line);
        }
        reader.close();
        //Then extract relevant information from JSON and prepare list of Star Wars characters
        List<SWDatabaseEntry> listCharacterEntries = new ArrayList<SWDatabaseEntry>();
        // Creamos un objeto JSONObject al que le pasamos la cadena del StringBuilder anterior
        JSONObject database = new JSONObject(jsonString.toString());

        JSONArray listCharacters = database.getJSONArray(RESULTS_JSON);
        int totalCharacters = listCharacters.length();
        for(int i=0;i<totalCharacters;i++){
            JSONObject character = listCharacters.getJSONObject(i);
            String name = character.getString(NAME_JSON);
            String gender = character.getString(GENDER_JSON);
            String mass = character.getString(MASS_JSON);
            String height = character.getString(HEIGHT_JSON);
            String hairColor = character.getString(HAIR_COLOR_JSON);
            String skinColor = character.getString(SKIN_COLOR_JSON);
            String birthYear = character.getString(BIRTH_YEAR_JSON);

            SWDatabaseEntry entry = new SWDatabaseEntry(name,height,mass,gender,hairColor,skinColor,birthYear);
            listCharacterEntries.add(entry);
        }

        return listCharacterEntries;
    }

}
