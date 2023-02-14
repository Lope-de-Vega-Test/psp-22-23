import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

    // Map of names to Person instances.
    private Map<Integer, Person> personMap = new HashMap<>();

    public DataStore() {
        // dummy data
        personMap.put(1, new Person(1, "Ada", "Ada Lovelace was the first programmer.", 1815));
        personMap.put(2, new Person(2, "Kevin", "Kevin is the author of HappyCoding.io.", 1986));
        personMap.put(3, new Person(3, "Stanley", "Stanley is Kevin's cat.", 2007));
    }

    public Person getPerson(Integer id) {
        //Usamos el metodo get, y haciendo uso de la variable name conseguimos la persona que queramos
        return personMap.get(id);
    }

    public void putPerson(Person person) {
        //Usando el metodo put, añadimos una persona con los datos de la variable tipo persona
        personMap.put(person.getId(), person);
    }

    public void delPerson(Person person) {
        //Haciendo uso del clear borramos la persona que indiquemos
        personMap.clear();
    }

    public void actPerson(Person person, Person person2) {
        //Con el metodo replace, intercambiamos "person", que es la persona que estamos apunto de actualizar
        //por "person2" que es la persona con los datos nuevos
        personMap.replace(person.getId(), person, person2);
    }
}
