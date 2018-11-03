package florida.es.starwarsdb.utils;

/**
 * Class that represents an entry of the Star Wars character database
 */
public class SWDatabaseEntry {

    /**
     * Attributes representing the name, height, mass and gender of the character
     */
    protected String sName = null;
    protected String sHeight = null;
    protected String sMass = null;
    protected String sGender = null;
    protected String sHairColor = null;
    protected String sSkinColor = null;
    protected String sBirthYear = null;

    // Constructor, recibe cuatro String con name, height, mass y gender
    public SWDatabaseEntry(String name, String height, String mass, String gender, String hairColor,
                           String skinColor, String birthYear){
        this.sName = name;
        this.sHeight = height;
        this.sMass = mass;
        this.sGender = gender;
        this.sHairColor = hairColor;
        this.sSkinColor = skinColor;
        this.sBirthYear = birthYear;
    }

    // Getters para devolver el valor de cada elemento
    public String getName(){
        return sName;
    }

    public String getHeight(){
        return sHeight;
    }

    public String getMass(){
        return sMass;
    }

    public String getGender(){
        return sGender;
    }

    public String getHairColor() { return sHairColor; }

    public String getSkinColor() { return sSkinColor; }

    public String getBirthYear() { return sBirthYear; }

    // Metodo que devuelve un String con los valores correspondientes a los 4 elementos
    @Override
    public String toString(){
        return "Name: " + sName + " Gender: " +sGender + " Mass: " + sMass + " Height: " + sHeight
                + " Hair Color: " + sHairColor + " Skin Color: " + sSkinColor + " Birth Year: " + sBirthYear;
    }

}
