class camarero {
    private int listavasos = 0; // Datos de la clase camarero, recibe la lista de vasos
    private String nombre;
    public camarero(String nombre) {
        this.nombre = nombre;
    }

    public int getListavasos() {
        return listavasos;
    }

    public void setListavasos(int listavasos) {
        this.listavasos = listavasos;
    }

    private boolean disponible = false; // Iniciamos la cola de vasos vacia

    public synchronized int get() {
        while (!disponible) { // Si hay pintas
            try {
                wait(); // Espero a poder acceder al objeto sincronizado
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Se consume: " + listavasos + " pinta");
        disponible = false; // Se pone la cola vacía ...
        notify(); // El objeto sincronizado queda libre
        return listavasos; // Se devuelven la lista de vasos
    }

    public synchronized void put(int pintas) {
        while (disponible) { // Si hay datos ...
            try {
                wait(); // Espero a poder acceder al objeto sincronizado
            } catch (InterruptedException e) {
            }
        }
        listavasos = pintas; // Coloca pintas en la cola
        disponible = true; // Disponible para consumir
        System.out.println("Se produce: " + listavasos + " pinta");
        notify(); // El objeto sincronizado queda libre
    }

    

} // Fin Class Contador

class Productor extends Thread {
    private camarero cola;
    private int n;

    public Productor(camarero c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            cola.put(i); // Pongo datos
            // System.out.println(i + " => Productor : " + n + " produce: " +i);

            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
} // Fin Class Productor

class HilosClientes extends Thread {
    private camarero cola;
    public HilosClientes(String name, String nombre) {
        super(name);
        this.nombre = nombre;
    }

    String nombre;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private int n;
    private String nom;

    public HilosClientes(camarero c, int n, String nom) {
        cola = c;
        this.n = n;
        this.nom=nom;
    }

    public void run() {
        int pintas = 0;
        for (int i = 0; i < 4; i++) {
            pintas = cola.get(); // Pongo datos
            // System.out.println(i + " => Consumidor : " + n + " consume: " + pintas);

            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
} // Fin Class Productor

public class ua2ex1 {
    public static void main(String[] args) {
        camarero cola = new camarero("moe");
        Productor p = new Productor(cola, 1);
        HilosClientes homer = new HilosClientes(cola, 1,"homer");
        HilosClientes moe = new HilosClientes(cola, 2,"Barney");
        HilosClientes carl = new HilosClientes(cola, 3,"Carl");
        HilosClientes Lenny = new HilosClientes(cola, 4,"Lenny");
        HilosClientes Lurleen = new HilosClientes(cola, 5,"Lurleen");


        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        p.start();     
        moe.start();
        homer.start();
        carl.start();
        Lenny.start();
        Lurleen.start();

        try {
            p.join();     
            moe.join();
            homer.join();
            carl.join();
            Lenny.join();
            Lurleen.join();
            
        } catch (InterruptedException e) {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");

    }

    public class VasoCerveza {
        private int id;

        public VasoCerveza(int id, int tipo) {
            this.id = id;
            this.tipo = tipo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int tipo;

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

    }
}