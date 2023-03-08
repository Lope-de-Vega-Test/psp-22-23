public class Persona {
    private String Nombre;
	private String Informacion;
	private int Nacimiento;
	
	public Persona(String Nombre, String Informacion, int Nacimiento) {
		this.Nombre = Nombre;
		this.Informacion = Informacion;
		this.Nacimiento = Nacimiento;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getInformacion() {
		return Informacion;
	}

	public int getNacimiento() {
		return Nacimiento;
	}
}
