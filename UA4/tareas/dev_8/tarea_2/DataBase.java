package UA4.tareas.dev_8.tarea_2;

import java.util.HashMap;
import java.util.Map;

/**
 * Example DataBase class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataBase {

	// Map of names to Person instances.
	private Map<Integer, Person> personMap = new HashMap<>();

	public DataBase() {
		// dummy data
		personMap.put(1, new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(2, new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(3, new Person("Stanley", "Stanley is Kevin's cat.", 2007));
	}

	// public Person getPerson(String name) {
	// return personMap.get(name);
	// }

	public void putPerson(Person person) {
		personMap.put(getLastId() + 1, person);
	}

	public Person getPersonById(int id) {

		return personMap.get(id);
	}

	public int getLastId() {
		return personMap.size();
	}

	public Person updatePerson(int id, String name, String about, int birthYear) {

		Person person = personMap.get(id);

		if (name != null) {
			person.setName(name);
		}
		if (about != null) {
			person.setAbout(about);
		}
		if (String.valueOf(birthYear) != null) {
			person.setBirthYear(birthYear);
		}
		return person;
	}

	public Boolean removePerson(int id) {

		boolean deleted = false;
		Person deletedPerson = personMap.remove(id);
		if (deletedPerson != null) {
			deleted = true;
		}
		return deleted;
	}
}