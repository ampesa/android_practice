package florida.es.starwarsdb;

public class SWDatabaseEntry {

    protected String name = null;
    protected String height = null;
    protected String mass = null;
    protected String gender = null;

    public SWDatabaseEntry(String name, String height, String mass, String gender){
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public String getHeight(){
        return height;
    }

    public String getMass(){
        return mass;
    }

    public String getGender(){
        return gender;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Gender: " + gender + " Mass: " + mass + " Height: " + height;
    }

}
