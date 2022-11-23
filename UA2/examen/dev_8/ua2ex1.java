package UA2.examen.dev_8;

import java.util.ArrayList;

/**
 * 
 */
class VasoCerveza {
    int id;
    int tipoPinta;

    public VasoCerveza(int id) {
        this.id = id;
        this.tipoPinta = setTipoPintaAleatorio();
    }

    public int getId() {
        return id;
    }

    public int getTipoPinta() {
        return tipoPinta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoPinta(int tipoPinta) {
        this.tipoPinta = tipoPinta;
    }

    public int setTipoPintaAleatorio() {
        return (int) (Math.random() * 2 + 0);
    }

    @Override
    public String toString() {
        return "VasoCerveza [id=" + id + ", tipoPinta=" + tipoPinta + "]";
    }

}

/**
 * Camarero
 */
class Camarero {

    ArrayList<VasoCerveza> vasos = new ArrayList<VasoCerveza>();
    VasoCerveza vasoCerveza;

    /**
     * 
     * @param n
     * @param nombre
     */
    public Camarero(int n, String nombre) {

        for (int i = 0; i < n; i++) {
            VasoCerveza pinta = new VasoCerveza(i);
            vasos.add(pinta);
            System.out.println(
                    "añadiendo a " + nombre + " la pinta " + i + " y la pinta será de tipo: " + pinta.getTipoPinta());
        }

    }

    /**
     * Este método cogerá un vaso de la lista y devolverá aleatoriamente uno de
     * ellos
     * 
     * @return
     */
    public VasoCerveza servirCerveza() {

        int nVasos = vasos.size();
        if (nVasos < 0) {
            System.out.println("---------------------------");
            System.out.println("Nos hemos quedado sin Birra");
            System.out.println("---------------------------");

            return null;
        }
        int cervezaAleatoria = (int) (Math.random() * (nVasos) + 0); // Buscamos la cerveza aleatoria
        VasoCerveza vasoADevolver = vasos.get(cervezaAleatoria); // La asignamos a una variable para luego devolverla
        vasos.remove(cervezaAleatoria); // Sacamos la cerveza de la lista

        System.out.println("---------------------------");
        System.out.println("La birra devuelta es la: " + vasoADevolver.getId());
        System.out.println("Nos quedan " + vasos.size() + " Birras");
        System.out.println("---------------------------");

        return vasoADevolver;

    }

    /**
     * 
     */
    public void devolverCerveza(VasoCerveza pinta) {

        int nVasos = vasos.size();

        if (nVasos < 3) { // En el caso de que el valor de la lista sea menor que 3 metemos una nueva
                          // pinta
            vasos.add(pinta);

            System.out.println("---------------------------");
            System.out.println("!Atention! Volvemos a tener birra");
            System.out.println("Total de birras: " + vasos.size());
            System.out.println("---------------------------");
        } else {
            System.out.println("---------------------------");
            System.out.println("No puede haber más de 3 vasos en la barra");
            System.out.println("Tienes que esperar a que devuelvan un vaso");
            System.out.println("---------------------------");
        }

    }

    /**
     * Este método devolverá número de vasos disponibles
     * 
     * @return
     */
    public int contarVasos() {
        return vasos.size();
    }

}

/**
 * Esta será la clase hilos clientes, será donde ejecutaremos tood lo que nos hace falta
 */
class HilosClientes extends Thread {

    private String nombre;
    private Camarero camarero;
    private float litros = 0;

    /**
     * Inicializamos la clase hilos clientes
     * @param c
     * @param nombre
     */
    public HilosClientes(Camarero c, String nombre) {

        camarero = c;
        this.nombre = nombre;
    }

    /**
     * 
     */
    public void run() {

        while (true) {

            VasoCerveza vaso = camarero.servirCerveza(); // Pedir cerveza
            camarero.devolverCerveza(vaso); // Devolvemos el vaso vacio

            if (vaso.getTipoPinta() == 0) {
                litros += 0.568;  // Sumamos lo correspondiente por 1/2 pinta
            } else if (vaso.getTipoPinta() == 1) {
                litros += (0.568 * 2); // Sumamos lo correspondiente por 2/2 pinta
            }
            System.out.println("total de litros: " + litros);

            try {
                sleep((int) (Math.random() * 500 + 250));
                
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}

public class ua2ex1 {

    public static void main(String[] args) {

        System.out.println("-----------------------------------");
        System.out.println("Bienvenidos al Bar de Moe");
        System.out.println("-----------------------------------");

        Camarero moe = new Camarero(3, "MOE");
        HilosClientes homer = new HilosClientes(moe, "homer");
        HilosClientes barney = new HilosClientes(moe, "barney");
        HilosClientes carl = new HilosClientes(moe, "carl");
        HilosClientes lenny = new HilosClientes(moe, "lenny");
        HilosClientes lurleen = new HilosClientes(moe, "lurleen");

        System.out.println("Comienza la ejecución de los hilos ");
        System.out.println("--------------------------------------");

        homer.start();
        barney.start();
        carl.start();
        lenny.start();
        lurleen.start();


        // VasoCerveza pinta = camarero.servirCerveza();
        // camarero.devolverCerveza(pinta);

    }

}
