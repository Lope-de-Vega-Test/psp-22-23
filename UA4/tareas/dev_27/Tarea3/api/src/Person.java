import org.json.JSONObject;

/**
 * Esta clase representa a una persona con su nombre, descripción y año de nacimiento.
 */
public class Person {
    private String name;
    private String about;
    private int birthYear;

    public Person(String name, String about, int birthYear) {
        this.name = name;
        this.about = about;
        this.birthYear = birthYear;
    }

    /**
     * Devuelve el nombre de la persona.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la descripción de la persona.
     */
    public String getAbout() {
        return about;
    }

    /**
     * Devuelve el año de nacimiento de la persona.
     */
    public int getBirthYear() {
        return birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    
    JSONObject jsonObject= new JSONObject();
    
}