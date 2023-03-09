
package com.victoraljama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStorage {

	int id;

	// Map of names to Person instances.
	private Map<Integer, Person> personMap = new HashMap<>();

	public DataStorage() {
		// dummy data
		personMap.put(1, new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(2, new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(3, new Person("Stanley", "Stanley is Kevin's cat.", 2007));

		id = 4;
	}

	public Person getPerson(int id) {
		return personMap.get(id);
	}

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

	public void putPerson(Person person) {
		personMap.put(id, person);
		id++;
	}

	public void delPerson(int id) {
		personMap.remove(String.valueOf(id));
	}

	public void replacePerson(int id, Person person) {
		personMap.replace(id, person);
	}
}
