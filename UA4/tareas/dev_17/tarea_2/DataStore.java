import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

	//Map of names to Person instances.
	private Map<Integer, Person> personMap = new HashMap<>();
	
	public DataStore(){
		//dummy data
		personMap.put(1, new Person(1,"Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(2, new Person(2,"Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(3, new Person(3,"Stanley", "Stanley is Kevin's cat.", 2007));
	}

	public Person getPerson(int id) {
		return personMap.get(id);
	}

	public void putPerson(Person person) {
		personMap.put(person.getId(), person);
	}

	public void deletePerson(int id){
		personMap.remove(id);
	}
	//eliminamos la persona que queremos modificar y la creamos de nuevo, con los nuevos datos
	public void modificarPerson(int id, Person updatePerson){
		personMap.remove(id);
		personMap.put(updatePerson.getId(), updatePerson);
	}
}