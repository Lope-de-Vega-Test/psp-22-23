import java.lang.Math;


class VasoCerveza {
    int id ;
    int tipo;

    public VasoCerveza() {
        this.id=0;
        this.tipo=(int) (Math.random()*1+0);
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public int getId() {
        return id;
    }
    public int getTipo() {
        return tipo;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}

class Camarero {
    private double listaVasos;
    String nombre;
    

    public Camarero() {
        this.nombre ="";
        for (int j=0;j<3;j++) {
            this.listaVasos=(Math.random()*1+0);
        }
    }

    public void servirCerveza() {

    }
    public void devolverCerveza() {

    }
    public void contarVasos() {
        
    }
}

class hilosClientes extends Thread {

}
public class ua2ex1 {
public static void main(String[] args) {
    
}    
}

