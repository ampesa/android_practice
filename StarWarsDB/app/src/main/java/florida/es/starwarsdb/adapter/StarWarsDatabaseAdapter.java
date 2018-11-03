package florida.es.starwarsdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import florida.es.starwarsdb.CharacterDetailsActivity;
import florida.es.starwarsdb.MainActivity;
import florida.es.starwarsdb.utils.ImageGetter;
import florida.es.starwarsdb.utils.SWDatabaseEntry;

import static florida.es.starwarsdb.R.*;

/* Esta clase extiende RecyclerView.Adapter y se ocupará de rellenar el RecyclerView con los datos
*   que correspondan*/
public class StarWarsDatabaseAdapter extends RecyclerView.Adapter<StarWarsDatabaseAdapter.StarWarsDatabaseViewHolder> {

    // Objeto List para referenciar las entradas de la base dedatos
    protected List<SWDatabaseEntry> mCharacterDB = null;

    protected MainActivity mainActivity = null;

    public List<SWDatabaseEntry> getCharacterDB() {
        return new ArrayList<SWDatabaseEntry>(mCharacterDB);
    }

    // Constructor de la clase, recibe un List y lo asigna a mCharacterDB
    public StarWarsDatabaseAdapter(List<SWDatabaseEntry> characterList){
        // Le asignamos a mCharacterDB el ArrayList con la carga de la base de datos recibida desde MainActivity
        mCharacterDB = new ArrayList<SWDatabaseEntry>(characterList);
    }

    /* Método onCreateViewHolder, creará el número limitado de vistas por primera vez
    *   Recibe el ViewGroup padre, es decir el RecyclerView y el tipo de vista a crear en forma de int
    *   */
    @NonNull
    @Override
    public StarWarsDatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        /*Creamos una vista view que recibe, a través de un LayoutInflater con el contexto del ViewGroup padre
        * (RecyclerView) a través del metodo inflate:
        *  - El layout con las vistas (archivo XML)
        *  - El contexto
        *  - Boolean attachToRoot establecido en false, en caso true el viewholder se añadirá a la jerarquía
        *  de vistas del padre (RecyclerView)*/
        View view =
                LayoutInflater.from(viewGroup.getContext()).
                        inflate(layout.star_wars_view_holder_layout, viewGroup, false);

        // El médoto devuelve el ViewHolder "inflado"
        return new StarWarsDatabaseViewHolder(view);
    }

    /* Método onBindViewHolder, se ejecutará cada vez que se tenga que cambiar el contenido de un ViewHolder
    *   Básicamente es el que va a reutilizar (Recycler) los ViewHolder.
    *   Recibe el ViewHolder creado en el método onCreateViewHolder y un entero con la posición del contenido
    *   a mostrar*/
    @Override
    public void onBindViewHolder(@NonNull StarWarsDatabaseViewHolder starWarsDatabaseViewHolder,
                                 int position) {
        /* Creamos un objeto de la clase SWDatabaseEntry (entry) y le pasamos los valores de la posición
        *   en la que se encuentre el ArrayList mCharaterDB (el que había recibido las entradas de la BBDD*/
        SWDatabaseEntry entry = mCharacterDB.get(position);

        // Creamos un String name y le asignamos el valor del nombre para esa posición. Hacemos lo mismo con el resto de campos
        String name = entry.getName();

        // Asignamos a los elementos gráficos del ViewHolder los strings anteriores
        starWarsDatabaseViewHolder.mCharacterName.setText(name);

        // Creamos un int que recogerá el ID del icono del personaje a través de la clase ImageGetter
        // y su método getSWIcon que recibrá como parámetro el nombre del personaje
        int iconID = ImageGetter.getSWIcon(name);

        // Creamos un objeto Context que recibe el contexto del ViewHolder (itemView) a través de su constructor
        Context context = starWarsDatabaseViewHolder.itemView.getContext();
        starWarsDatabaseViewHolder.mIcon.setImageDrawable(ContextCompat.getDrawable(context, iconID));

    }

    @Override
    public int getItemCount() {
        return mCharacterDB.size();
    }

    public class StarWarsDatabaseViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

        // Creamos los elementos gráficos del ViewHolder
        protected TextView mCharacterName = null;
        protected ImageView mIcon = null;


        // Añadimos los botones de compartir y cambiar imagen
        protected ImageButton mShareButton = null;
        protected ImageButton mEditPicButton = null;

        // Creamos un objeto Context para trabajar con los intents
        protected Context mContext = null;

        protected int PICK_IMAGE = 100;



        protected void showCharacterDetails(){
            // int que recoge la posición del Adapter al hacer la pulsación
            int position = getAdapterPosition();
            // Intent con el context y la nueva actividad
            Intent newActivity = new Intent(mContext, CharacterDetailsActivity.class);
            // Pasamos el int al Bundle
            newActivity.putExtra("position", position);
            // Iniciamos la actividad
            mContext.startActivity(newActivity);
        }

        // Método para compartir el personaje cuando se pulsa el botón mShareButton
        protected void shareCharacter(){
            // Creamos un Intent
            Intent intentToShare = new Intent();
            // Definimos al acción a realizar por el Intent
            intentToShare.setAction(Intent.ACTION_SEND);
            // Definimos el typo de datos
            intentToShare.setType("text/plain");
            // Pasamos al Bundle el texto almacenado en los strings de recursos y el nombre del personaje
            intentToShare.putExtra(Intent.EXTRA_TEXT, String.valueOf(string.favorite_character)
                    + mCharacterName.getText().toString());

            if (intentToShare.resolveActivity(mContext.getPackageManager()) != null) {
                // Hay una aplicación capaz de resolver la petición
                mContext.startActivity(intentToShare);
            } else {
                // No hay aplicación para resolver esta petición
                Toast showNoApp = Toast.makeText(mContext, String.valueOf(string.no_app_available), Toast.LENGTH_LONG);
                showNoApp.show();
            }
        }

        //Método para cambiar el icono del personaje por una imagen de la galería
        protected void changePic() {
            // Creamos el Intent
            Intent intentToChangePic = new Intent ();
            // Definimos la acción a realizar por el Intent
            intentToChangePic.setAction(Intent.ACTION_GET_CONTENT);
            // Definimos el tipo de datos (image)
            intentToChangePic.setType("image/*");
            if (intentToChangePic.resolveActivity(mContext.getPackageManager()) != null) {
                // Hay una aplicación para acceder al contenido
                ((MainActivity) mContext).startActivityForResult(Intent.createChooser(intentToChangePic,
                        String.valueOf(string.select_picture)), PICK_IMAGE);
            }else {
                // No hay aplicación para resolver esta petición
                Toast showNoApp = Toast.makeText(mContext, string.no_app_available, Toast.LENGTH_LONG);
                showNoApp.show();
            }
        }

        // Constructor de la inner class ViewHolder
        public StarWarsDatabaseViewHolder(@NonNull View itemView){
            super (itemView);
            // Referenciamos los elemetos gráficos asociándolos a los elementos del XML a través de sus IDs
            // Ponemos los listeners a cada elemento
            mCharacterName = itemView.findViewById(id.tv_char_name);
            mCharacterName.setOnClickListener(this);
            mIcon = itemView.findViewById(id.iv_char_icon);
            mIcon.setOnClickListener(this);

            // Referenciamos el contextos del ViewHolder
            mContext = itemView.getContext();

            // Referenciamos los botones y les ponemos el listener
            mShareButton = itemView.findViewById(id.ib_share);
            mShareButton.setOnClickListener(this);
            mEditPicButton = itemView.findViewById(id.ib_edit_pic);
            mEditPicButton.setOnClickListener(this);

            // Ponemos los listener a los layouts de la tarjeta
            itemView.findViewById(id.rl_inner_card).setOnClickListener(this);
            itemView.findViewById(id.ll_outer_card).setOnClickListener(this);

        }

        // Implementación del método onClick. Cada vez que pulsemos cambiará la visibilidad de
        // los elementos: género, altura y peso
        @Override
        public void onClick(View v) {
            // Ahora haremos cosas diferentes en función de dónde se haga click.
            int viewId = v.getId();

            if (viewId == mIcon.getId() || viewId == mCharacterName.getId()){
                // Si pulsa en el icono o en el personaje lanzamos el método showCharacterDetails
                showCharacterDetails();
            }else if (viewId == mShareButton.getId()){
                // si pulsa boton compartir, lanzamos el método shareCharacter()
                shareCharacter();
            }else if (viewId == mEditPicButton.getId()){
                // si pulsa boton editar imagen, intent lanzamos el método changePic()
                changePic();
                // Asignamos al personaje la imagen seleccionada desde la galería
                mIcon.setImageBitmap(((MainActivity)mContext).getImage());
            }

        }

    }
}
