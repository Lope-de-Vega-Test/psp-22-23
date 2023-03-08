package UA4.tareas.dev_8.tarea_3;

public class Person {

	private int id;
	private String name;
	private String about;
	private int birthYear;
	
	public Person(String name, String about, int birthYear) {
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}

	public String getAbout() {
		return this.about;
	}

	public int getBirthYear() {
		return this.birthYear;
	}

}