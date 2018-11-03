package florida.es.starwarsdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import florida.es.starwarsdb.adapters.SWDatabaseAdapter;
import florida.es.starwarsdb.utils.SWJsonParser;


public class MainActivity extends AppCompatActivity {

    protected List<SWDatabaseEntry> mListCharacters = null;
    protected SWDatabaseAdapter mRecyclerViewAdapter = null;
    protected RecyclerView mRecyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mListCharacters = SWJsonParser.parseJSONDatabase(getResources().openRawResource(R.raw.sw_db));
        } catch(IOException e){
            Log.e(getClass().getName(),e.getMessage());
        } catch(JSONException e){
            Log.e(getClass().getName(),e.getMessage());
        }
        mRecyclerViewAdapter = new SWDatabaseAdapter(mListCharacters);
        mRecyclerView = findViewById(R.id.rv_character_db);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);

    }
}
