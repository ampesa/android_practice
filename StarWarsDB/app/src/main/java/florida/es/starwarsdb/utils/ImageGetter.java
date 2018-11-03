package florida.es.starwarsdb.utils;

import florida.es.starwarsdb.R;

/**
 * Class that returns an icon to draw according to the Star Wars character
 */
public class ImageGetter {

    /**
     * Static method that returns the id of an icon to draw
     * @param name The name of the Star Wars character
     * @return An integer representing the id of an icon in the resource tree
     */
    public static int getSWIcon(String name){
        switch(name){
            case "Luke Skywalker" :
                return R.drawable.luke_skywalker;
            case "C-3PO" :
                return R.drawable.c3p0;
            case "R2-D2":
                return R.drawable.r2d2;
            case "Darth Vader":
                return R.drawable.darth_vader;
            case "Leia Organa" :
                return R.drawable.princess_leia;
            case "Obi-Wan Kenobi":
                return R.drawable.obiwan_kenobi;
            case "Chewbacca":
                return R.drawable.chewbacca;
            case "Han Solo":
                return R.drawable.han_solo;
            case "Yoda":
                return R.drawable.yoda;
            case "Palpatine":
                return R.drawable.emperor_palpatine;
            case "Boba Fett":
                return R.drawable.boba_fett;
            case "Lando Calrissian":
                return R.drawable.lando_calrissian;
            case "Qui-Gon Jinn":
                return R.drawable.qui_gon_jinn;
            case "Darth Maul":
                return R.drawable.darth_maul;
            default:
                return R.drawable.lightsaber_luke_anh;
        }
    }


}
