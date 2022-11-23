//clase VasoCerveza
class VasoCerveza {

    int id;
    int pinta;
    int mediapinta;

    public VasoCerveza(int id, int pinta, int mediapinta) {
        this.id = id;
        this.pinta = pinta;
        this.mediapinta = mediapinta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinta() {
        return pinta;
    }

    public void setPinta(int pinta) {
        this.pinta = pinta;
    }

    public int getMediapinta() {
        return mediapinta;
    }

    public void setMediapinta(int mediapinta) {
        this.mediapinta = mediapinta;
    }

    @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", pinta=" + pinta + ", mediapinta=" + mediapinta + '}';
    }

}

//clase Camarero
class Camarero {

    int listaVasos;

    public Camarero(int listaVasos) {
        this.listaVasos = listaVasos;

    }

    //funciones de la clase camarero
    public int servirCerveza() {

        return 0;
    }

    public int devolverCerveza() {

        return 0;

    }

    public void contarVasos() {

        System.out.println("Los vasos disponibles en el bar son: " + listaVasos);

    }

}

class HilosClientes extends Thread {

}

//clase donde se ejecuta el codigo
public class Ua2ex1 {

    public static void main(String[] args) {

    }

}
