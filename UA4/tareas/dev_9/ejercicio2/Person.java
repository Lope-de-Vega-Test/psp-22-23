public class Person {
	private String name;
	private String about;
	private int birthYear;
	private int id;
	
	public Person(int id, String name, String about, int birthYear) {
		this.id=id;
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
	}

	public String getName() {
		return name;
	}

	public String getAbout() {
		return about;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getId() {
		return id;
	}
	
}
