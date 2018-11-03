package florida.es.starwarsdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import org.json.JSONException;

import java.io.IOException;
import java.util.List;


import florida.es.starwarsdb.adapter.StarWarsDatabaseAdapter;
import florida.es.starwarsdb.utils.SWDatabaseEntry;
import florida.es.starwarsdb.utils.SWJsonParser;

/* Antes de empezar con un RecyclerView hay que añaidr la dependencia
    en el archivo gradle.build(module app) asegurándonos la corrección de la versión
    'com.android.support:recyclerview-v7:28.0.0-rc02'*/

public class MainActivity extends AppCompatActivity {

    // Lista para recoger las entradas de los personajes parseadas de la base de datos
    protected List<SWDatabaseEntry> mListCharacters = null;

    // Objeto para referenciar el RecyclerView
    protected RecyclerView mRecyclerView = null;

    // Objeto para referenciar el Adapter que rellenará el RecyclerView
    protected StarWarsDatabaseAdapter mAdapter = null;

    // Objeto para referenciar el LayoutManager que dipondrá las vistas del RecyclerView
    protected LinearLayoutManager mManager = null;

    protected int PICK_IMAGE = 100;

    protected Bitmap image = null;

    public Bitmap getImage() {
        return image;
    }

    protected void databaseCall(){
        try {
            /* "Cargamos" las entradas de la base de datos en el List
             *   Esto lo hacemos con el método parseJSONDatabase de la clase SWJsonParser. A este
             *   método le pasamos el archivo JSON sw_db con el método getResources y la operación
             *   openRawResource, en la que indicamos la ubicación del archivo*/
            mListCharacters = SWJsonParser.parseJSONDatabase(getResources().openRawResource(R.raw.sw_db));
        } catch(IOException e){// Manjamos posibles excepciones de acceso al JSON
            Log.e(getClass().getName(),e.getMessage());
        } catch(JSONException e){
            Log.e(getClass().getName(),e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseCall();

        // Creamos la referencia del Recycler pasándole el elemento gráfico con el método findVeiwById
        mRecyclerView = findViewById(R.id.rv_database_viewer);
        // Creamos la referncia del adapter pasándole como parámetro a su constructor el List en el que hemos cargado la base de datos
        mAdapter = new StarWarsDatabaseAdapter(mListCharacters);
        // Creamos la referencia del LayoutManager pasándole el contexto (this)
        mManager = new LinearLayoutManager(this);
        // Cambiamos la orientación del LayoutManager
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // Creadas las referencias le asignamos al RecyclerView su Adapter u su LayoutManager
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);
    }

    // Método que regoge el resultado del método changePic y lo pasa al objeto image del tipo Bitmap
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE){
            if (resultCode == RESULT_OK){
                image = (Bitmap)data.getExtras().get("data");
            }
        }
    }
}
