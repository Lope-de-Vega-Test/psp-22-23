//no me funciona, no tenia mucha idea


import java.util.ArrayList;



class Camarero //camarero
{
    ArrayList listaVasos = new ArrayList<VasosCerveza>();
    String nombre;
  
    public void Camarero(String nombre, ArrayList<String> listaVasos){
        this.nombre = nombre;
        this.listaVasos = listaVasos;
        listaVasos.add(1,"1");
        listaVasos.add(2,"0");
        listaVasos.add(3,"1");

    }

    public void servirCerveza(){
        listaVasos.get(1);
        for (int i=1; i<listaVasos.size(); i++){
            System.out.println(listaVasos.get(i));
            }
    }

}
class VasosCerveza   //VasosCerveza
{
    int id;
    int tipo;

    public void VasosCerveza(int id, int tipo){
        this.id=id;
        this.tipo=tipo;
    }  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cola{" + "id=" + id + ", tipo=" + tipo + '}';
    }
} // Fin Class ExecutionTimer

class HilosClientes extends Thread 
{
    Camarero camarero;
    private int n;
    private String nombre;

    public HilosClientes(String nombre) {
        this.nombre = nombre;
    }

    public void run() { 
        int valor = 0;       
        for(int i=0; i<5;i++)
        {
            System.out.println(camarero.toString());

            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
} // Fin Class Productor


public class ua2ex1 {
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        System.out.println("Hilos: Productor - Consumidor Final");
        System.out.println("-----------------------------------");
        VasosCerveza vasos = new VasosCerveza();

        Camarero cola = new Camarero();
        HilosClientes c = new HilosClientes("juan");        

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
              
        c.start();        

        try {
            c.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
    }
}