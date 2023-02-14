import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

	//Map of names to Person instances.
	private Map<Integer , Person> personMap = new HashMap<>();
	
	public DataStore(){
		//dummy data
		personMap.put(1, new Person(1, "Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(2, new Person(2, "Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(3, new Person(3, "Stanley", "Stanley is Kevin's cat.", 2007));
	}

	public Person getPerson(int id) {
		return personMap.get(id);
	}

	public void putPerson(Person person) {
		personMap.put(person.getId(), person);
	}

	public void deleteperson(Person person){
		personMap.clear();
	}
	
	public void updatePerson(int id, Person person, Person person2) {
		personMap.replace(id, person, person2);
	}
}
