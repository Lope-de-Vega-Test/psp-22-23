import java.util.HashMap;
import java.util.Map;


public class AlmacenamientoDatos {

    private Map<String, Persona> PersonaMap = new HashMap<>();
	
	public AlmacenamientoDatos(){
		PersonaMap.put("Ada", new Persona("Guille", "Habitante de Porcuna", 2001));
		PersonaMap.put("Kevin", new Persona("Alvaro", "El Señor de la Noche", 2000));
		PersonaMap.put("Stanley", new Persona("Carlos", "Aun queda informacion por rellenar", 2001));
	}

	public Persona getPerson(String Nombre) {
		return PersonaMap.get(Nombre);
	}

	public void putPerson(Persona persona) {
		PersonaMap.put(persona.getNombre(), persona);
	}

    public void deletePerson(String Nombre)
    {
        PersonaMap.remove(Nombre);
    }
}
