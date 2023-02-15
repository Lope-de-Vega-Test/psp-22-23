public class Person {
	private int id;
	private String name;
	private String about;
	private int birthYear;
	
	public Person(int id, String name, String about, int birthYear) {
		this.id = id;
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
	}

	public int getId(){
		return id;
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
}