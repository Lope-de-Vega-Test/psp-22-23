import java.lang.reflect.Constructor;
import java.util.Arrays;

// Compilar y ejecutar: javac ua2ex1.java && java ua2ex1

class VasoCerveza{
    private int id = 0;
    private int tipo = 0;
    boolean enUso = false;

    public VasoCerveza(int id, int tipo, boolean enUso){
        this.id = id;
        this. tipo = tipo;
        this.enUso = enUso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTipo(int valor) {
        valor = (int)(Math.random()*(1-0+1)+0);
        this.tipo = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    @Override
    public String toString() {
        return "id: " + id + ", tipo: "+ tipo;
    }

}

class Camarero{
    private VasoCerveza VC1;
    private VasoCerveza VC2;
    private VasoCerveza VC3;
    private String nombre;
    private String listaVasos[] = new String[3];

    public Camarero(String nombre, VasoCerveza VC1, VasoCerveza VC2, VasoCerveza VC3){
        this.nombre = nombre;
        this.VC1 = new VasoCerveza(0,0,false);
        this.VC2 = new VasoCerveza(1,0,false);
        this.VC3 = new VasoCerveza(2, 0,false);
        listaVasos[0] ="" + VC1.getTipo();
        listaVasos[1] ="" +  VC2.getTipo();
        listaVasos[2] ="" +  VC3.getTipo();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized VasoCerveza servirCerveza(){
        int posicion = (int)(Math.random()*(2-0+1)+0);
        String tipoVaso = listaVasos[posicion];
        VasoCerveza devolver = VC1;


        switch (posicion){
            case 0:

                while(VC1.enUso != false) {
                    try {
                        wait();
                    } catch(InterruptedException e) {}
                }
                System.out.println( getNombre() + " sirve el vaso: " + posicion + ", es de tipo " + tipoVaso);
                VC1.setEnUso(true);
                devolver = VC1;
                //break;

            case 1:

                while(VC2.enUso != false) {
                    try {
                        wait();
                    } catch(InterruptedException e) {}
                }
                System.out.println( getNombre() + " sirve el vaso: " + posicion + ", es de tipo " + tipoVaso);
                VC2.setEnUso(true);
                devolver = VC2;
                //break;

            case 2:

                while(VC3.enUso != false) {
                    try {
                        wait();
                    } catch(InterruptedException e) {}
                }
                System.out.println( getNombre() + " sirve el vaso: " + posicion + ", es de tipo " + tipoVaso);
                VC3.setEnUso(true);
                devolver = VC3;
                //break;
        }
        return devolver;
    }

    public synchronized void devolverCerveza(VasoCerveza vasocerveza){
        if ( vasocerveza.getId() == VC1.getId()){
            try {
                wait();         // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}
            VC1.setEnUso(false);
        }

        if ( vasocerveza.getId() == VC2.getId()){
            try {
                wait();         // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}
            VC1.setEnUso(false);
        }

        if ( vasocerveza.getId() == VC3.getId()){
            try {
                wait();         // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}
            VC3.setEnUso(false);
        }
    }
}

class HilosClientes extends Thread{
    private Camarero camarero;
    private String nombre;
    float litros=0;
    float centilitros=0;

    public HilosClientes(Camarero c, String nombre) {
        camarero = c;
        this.nombre = nombre;
    }

    public void run() {
        for(int i=0; i<5;i++)
        {
            VasoCerveza vasocerveza = camarero.servirCerveza();
            System.out.println(i + " => El cliente : "  + nombre + " consume la cerveza: " + vasocerveza.getId());
            switch (vasocerveza.getTipo()){
                case 0:
                    centilitros += 284;
                    if(centilitros>=1000){
                        litros++;
                        centilitros = centilitros%1000;
                    }
                    break;
                case 1:
                    centilitros += 584;
                    if(centilitros>=1000){
                        litros++;
                        centilitros = centilitros%1000;
                    }
                    break;
            }
            System.out.println("El cliente " + nombre + " lleva consumido: " + litros + " litros y " + centilitros + " centilitros");
            camarero.devolverCerveza(vasocerveza);
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
}

public class ua2ex1 {
    public static void main(String[] args) {
        int tipo = (int)(Math.random()*(1-0+1)+0);
        VasoCerveza vasocerveza1 = new VasoCerveza(0, tipo,false);
        VasoCerveza vasocerveza2 = new VasoCerveza(1, tipo,false);
        VasoCerveza vasocerveza3 = new VasoCerveza(2, tipo,false);
        Camarero camarero = new Camarero("Moe",vasocerveza1,vasocerveza2,vasocerveza3);
        HilosClientes cliente1 = new HilosClientes(camarero, "Homer");
        HilosClientes cliente2 = new HilosClientes(camarero, "Barney");
        HilosClientes cliente3 = new HilosClientes(camarero, "Carl");
        HilosClientes cliente4 = new HilosClientes(camarero, "Lenny");
        HilosClientes cliente5 = new HilosClientes(camarero, "Lurleen");

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();

        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }
    }
}
