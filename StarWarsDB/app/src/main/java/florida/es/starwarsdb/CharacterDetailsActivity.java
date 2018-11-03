package florida.es.starwarsdb;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import florida.es.starwarsdb.adapter.StarWarsDatabaseAdapter;
import florida.es.starwarsdb.utils.ImageGetter;
import florida.es.starwarsdb.utils.SWDatabaseEntry;
import florida.es.starwarsdb.utils.SWJsonParser;

public class CharacterDetailsActivity extends AppCompatActivity {
    // Creamos los elementos gráficos
    protected ImageView mCharacterIcon = null;
    protected TextView mCharacterName = null;
    protected TextView mGender = null;
    protected TextView mHeight = null;
    protected TextView mMass = null;
    protected TextView mHairColor = null;
    protected TextView mSkinColor = null;
    protected TextView mBirthYear = null;

    // int que utilizaremos para recibir y enviar la posición del personaje en el ArrayList
    protected int position = 0;

    // Objeto para almacenar el JSON parseado por el método databaseCall()
    protected List<SWDatabaseEntry> mCharacterDetailsDB = null;

    // Método que utiliza la clase SWJsonParser para rellenar el ArrayList
    protected void databaseCall(){
        try {
            /* "Cargamos" las entradas de la base de datos en el List
             *   Esto lo hacemos con el método parseJSONDatabase de la clase SWJsonParser. A este
             *   método le pasamos el archivo JSON sw_db con el método getResources y la operación
             *   openRawResource, en la que indicamos la ubicación del archivo*/
            mCharacterDetailsDB = SWJsonParser.parseJSONDatabase(getResources().openRawResource(R.raw.sw_db));
        } catch(IOException e){// Manjamos posibles excepciones de acceso al JSON
            Log.e(getClass().getName(),e.getMessage());
        } catch(JSONException e){
            Log.e(getClass().getName(),e.getMessage());
        }
    }

    // Método con el que recogemos el int que nos indica la posicion el ArrayList que debemos mostrar
    protected void getData(){
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        // Obtenemos el int con la posición del ViewHolder correspondiente al personaje
        getData();

        // Creamos un objeto de la clase SWDatabaseEntry y le pasamos la posición del personaje
        databaseCall();
        SWDatabaseEntry entry = mCharacterDetailsDB.get(position);

        // Extraemos los datos deseados
        String name = entry.getName();
        String gender = entry.getGender();
        String height = entry.getHeight();
        String mass = entry.getMass();
        String hairColor = entry.getHairColor();
        String skinColor = entry.getSkinColor();
        String birthYear = entry.getBirthYear();

        // Creamos las referencias de los elementos gráficos
        mCharacterIcon = findViewById(R.id.iv_details_char_icon);
        mCharacterName = findViewById(R.id.tv_details_char_name);
        mGender = findViewById(R.id.tv_details_char_gender);
        mHeight = findViewById(R.id.tv_details_char_height);
        mMass = findViewById(R.id.tv_details_char_mass);
        mHairColor = findViewById(R.id.tv_details_char_hair);
        mSkinColor = findViewById(R.id.tv_details_char_skin);
        mBirthYear = findViewById(R.id.tv_details_char_birth);

        // Asignamos los strings a los elementos gráficos
        mCharacterName.setText(name);
        mGender.setText(gender);
        mHeight.setText(height);
        mMass.setText(mass);
        mHairColor.setText(hairColor);
        mSkinColor.setText(skinColor);
        mBirthYear.setText(birthYear);

        // Asignamos el icono correspondiente al personaje
        int iconID = ImageGetter.getSWIcon(name);

        // Creamos un objeto Context que recibe el contexto (this) para utilizarlo en el método
        // setImageDrawable junto con el int iconID para asignar la imagen al ImageView
        Context context = this;
        mCharacterIcon.setImageDrawable(ContextCompat.getDrawable(context, iconID));
    }

    // Método que gestiona los items de la barra superior, en este caso el "back"
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        /* Cuando pulsamos back devolvemos a la actividad padre el int con la posición del caracter
            Si no implementamos lo siguiente volveríamos al personaje inicial (Luke Skywalker) en lugar
            de al personaje actual*/
        Intent intent = getIntent();
        intent.putExtra("character_position", position );
        // Indicamos que el resultado ha sido OK
        setResult(RESULT_OK, intent);
        // Finalizamos esta actividad
        finish();
        // Indicamos al sistema que la gestión del "back" ha sido correcta
        return true;
    }

}
