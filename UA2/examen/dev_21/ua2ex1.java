
import java.util.Random;

public class ua2ex1 {

    public static void main(String[] args) {
        // VasoCerveza a = new VasoCerveza(1, 1);
        Camarero c = new Camarero("Moe");
        
        HiloClientes h1 = new HiloClientes("Homer", c);
        HiloClientes h2 = new HiloClientes("Barney", c);
        HiloClientes h3 = new HiloClientes("Carl", c);
        HiloClientes h4 = new HiloClientes("Lenny", c);
        HiloClientes h5 = new HiloClientes("Lurleen", c);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class VasoCerveza {

    // Variables
    private int id;
    private int tipo;

    // Constructores
    public VasoCerveza(int id, int tipo) {
        setId(id);
        setTipo(tipo);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public int getTipo() {
        return this.tipo;
    }

    // Setters
    public void setId(int id) {
        this.id = id > -1 ? id : -1;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo == 0 ? 0 : tipo == 1 ? 1 : -1;
    }

    // ToString
    @Override
    public String toString() {
        return "VasoCerveza{" + "id: " + this.id + ", tipo: " + this.tipo + '}';
    }

}

class Camarero {

    // Variables
    Random r = new Random();

    String nombre;
    VasoCerveza listaVasos[];

    // Constructores
    public Camarero(String nombre) {
        // Set its own name
        setNombre(nombre);
        // Generate 3 random vasos
        this.listaVasos = new VasoCerveza[3];
        generateListaVasos();
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }

    public VasoCerveza[] getListaVasos() {
        return this.listaVasos;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaVasos() {
        generateListaVasos();
    }

    // Useful methods
    public VasoCerveza generateVaso(int id) {
        return new VasoCerveza(id, r.nextInt(0, 2));
    }

    public void generateListaVasos() {
        for(int i = 0; i < 3; i++) {
            listaVasos[i] = generateVaso(i);
        }
    }

    public String getVasos() {
        String vasos = "";
        for(int i = 0; i < this.listaVasos.length; i++) {
            vasos += (this.listaVasos[i].toString());
            if (i < (this.listaVasos.length - 1)) {
                vasos += '\n';
            }
        }

        return vasos;
    }

    public synchronized VasoCerveza servirCerveza() {
        VasoCerveza vaso = null;
        int index = 0;
        boolean sel = false;
        int loops=0;

        while (!sel) {
            if (contarVasos() > 0) {
                index = r.nextInt(0, 3);
                for(int i = 0; i < this.listaVasos.length; i++) {
                    if (this.listaVasos[i] != null) {
                        vaso = this.listaVasos[index];
                        this.listaVasos[index] = null;
                        sel=true;
                        break;
                    }
                }
            }
            loops++;
            if (loops==3) break;
        }
        return vaso;
    }

    public synchronized void devolverCerveza(VasoCerveza v) {
        this.listaVasos[v.getId()] = v;
    }

    public synchronized int contarVasos() {
        int NUM_VASOS = 0;
        for(int i = 0; i < this.listaVasos.length; i++) {
            if (this.listaVasos[i] != null) {
                NUM_VASOS++;
            }
        }
        return NUM_VASOS;
    }

    // To String
    @Override
    public String toString() {
        return "Camarero{" + "nombre: " + this.nombre + ", listaVasos:\n"
                + getVasos()
                + "\n}";
    }
}

class HiloClientes extends Thread {

    // Variables
    Random r = new Random();
    Camarero camarero;
    VasoCerveza vaso;
    boolean ebrio;
    float litros;
    static float PINTA = 473.0f;
    static float MEDIA_PINTA = 236.0f;

    public HiloClientes(String nombre, Camarero camarero) {
        this.setName(nombre);
        this.camarero = camarero;
        this.vaso = null;
        this.ebrio = false;
        this.litros = 0;

    }

    // Getters
    public VasoCerveza getVaso() {
        return vaso;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public boolean isEbrio() {
        return ebrio;
    }

    public float getLitros() {
        return litros;
    }

    // Setters
    public void setVaso(VasoCerveza vaso) {
        this.vaso = vaso;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    public void setEbrio() {
        this.ebrio = this.litros >= 6.0f ? true : false;
    }

    public void setEbrio(boolean ebrio) {
        this.ebrio = ebrio;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    // Useful methods
    public void aumentarLitros(VasoCerveza v) {
        if (v.getTipo() == 0) {
            this.litros += (MEDIA_PINTA / 1000);
        } else if (v.getTipo() == 1) {
            this.litros += (PINTA / 1000);
        } else {
            System.out.println("Vaso roto");
        }
    }

    public float pintasToLitros(int cantidad) {
        return cantidad / 1000;
    }

    @Override
    public void run() {
        this.ebrio = false;
        System.out.println("Soy " + this.getName() + " y acabo de entrar al bar");
        while (!isEbrio()) {
            this.vaso = camarero.servirCerveza();
            if (this.vaso != null) {
                if (this.vaso.getTipo() < 0 || this.vaso.getTipo() > 1) {
                    System.out.println("Soy " + this.getName() + " y el vaso estaba roto. Me voy de este antro");
                    this.interrupt();
                    this.stop();
                    break;
                } else {
                    System.out.println("Soy el camarero y he servido el vaso " + this.vaso.toString());
                    System.out.println("Soy " + this.getName() + " y me he bebido una cerveza del vaso " + this.vaso.toString());
                    aumentarLitros(vaso);
                    setEbrio();
                    camarero.devolverCerveza(vaso);
                    System.out.println("Soy el camarero y he recogido el vaso " + this.vaso.toString());
                }
            } else {
                System.out.println("Soy el camarero y no me quedan mas vasos");
            }

            try {
                this.sleep(r.nextInt(250, 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Soy " + this.getName() + " y ya estoy borracho. He consumido " + this.litros + " litros");
    }
}
