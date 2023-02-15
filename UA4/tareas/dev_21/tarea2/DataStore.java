import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

	private static int id = 3;
	// Map of names to Person instances.
	private Map<Integer, Person> personMap = new HashMap<>();

	public DataStore() {
		// dummy data
		personMap.put(0, new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(1, new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(2, new Person("Stanley", "Stanley is Kevin's cat.", 2007));
	}

	/**
	 * Returns a Person object for the given key value
	 * 
	 * @param key - int
	 * @return Person - Person object
	 */
	public Person getPerson(int key) {
		return personMap.get(key);
	}

	/**
	 * Returns a List of Person objects which names match with the given name
	 * 
	 * @param name - String
	 * @return persons - ArrayList<Person>
	 */
	public ArrayList<Person> getPersonsByName(String name) {
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person = null;
		for (int i = 0; i < personMap.size(); i++) {
			person = getPerson(i);
			if (person != null) {
				if (person.getName().equals(name)) {
					persons.add(person);
				}
			}
		}
		return persons;
	}

	/**
	 * Returns a List of Person objects which birth years match with the given birth
	 * year
	 * 
	 * @param birthYear - int
	 * @return persons - ArrayList<Person>
	 */
	public ArrayList<Person> getPersonsByBirthYear(int birthYear) {
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		for (int i = 0; i < personMap.size(); i++) {
			if ((person = getPerson(i)).getBirthYear() == birthYear) {
				persons.add(person);
			}
		}
		return persons;
	}

	/**
	 * Adds a Person to the Person Map and increases the ID count
	 * 
	 * @param person - Person object
	 */
	public void addPerson(Person person) {
		personMap.put(id, person);
		id++;
	}

	/**
	 * Modifies a Person in the Person Map
	 * 
	 * @param key    - int
	 * @param person - Person objects
	 */
	public void modifyPerson(int key, Person person) {
		personMap.replace(key, person);
	}

	/**
	 * Deletes a Person from the Person Map
	 * 
	 * @param key - int
	 */
	public void deletePerson(int key) {
		personMap.remove(key);
	}
}