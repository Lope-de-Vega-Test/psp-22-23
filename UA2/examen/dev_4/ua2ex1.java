import java.util.ArrayList;

class VasoCerveza
{
    private int id=0;
    private int tipo=0;
    
    public VasoCerveza(int id, int tipo)
    {
        this.id=id;
        this.tipo= tipo;
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
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
   
}

class Camarero
{
    private String nombreCamarero;
    private int nVasos;
    private ArrayList <VasoCerveza> listaVasos= new ArrayList(nVasos);
    
    public Camarero(String nombreCamarero)
    {
        nVasos=3;
        this.nombreCamarero=nombreCamarero;
        for(int i=0; i<nVasos; i++)
        {
            listaVasos.add(new VasoCerveza(i,0));
        }
    } 
    
    public synchronized void servirCerveza()
    {
        while(nVasos==0)
        {
            try{
                wait();
            }catch(InterruptedException e) {}
        }
        
        int vasos=0;
        
    }
    
    public synchronized void devolverCerveza( String nombre)
    {
        int vaso=0;
        
    }
    
    public synchronized void contarVasos()
    {
        if(nVasos==0)
        {
            System.out.println("Quedan "+nVasos);
        }else if(nVasos==1)
        {
            System.out.println("Quedan "+nVasos);
        }else
        {
            System.out.println("Quedan 2 o 3 vasos");
        }
    }
}

class HilosClientes extends Thread
{
    private String nombre;
    private VasoCerveza vasos;
    private Camarero camarero;
    
    public HilosClientes(String nombre, Camarero camarero)
    {
        setName(nombre);
        this.camarero=camarero;
    }
    
    public void run()
    {
        int valor=0;
        for(int i=0; i<5; i++)
        {
            
                    
        }
        
        try{
            sleep(250);
        }catch(InterruptedException e){}
    }
    
}

public class ExamenU2Serviciosyprocesos {
    public static void main(String[] args) {
        System.out.println("-----Comienzo-----");
        Camarero Moe= new Camarero("Moe");
        HilosClientes Homer = new HilosClientes("Homer", Moe);
        HilosClientes Barney = new HilosClientes("Barney", Moe);
        HilosClientes Leeny = new HilosClientes("Leeny", Moe);
        
        
        
        Homer.start();
        Barney.start();
        Leeny.start();
        try
        {
            
            Homer.join();
        }catch(InterruptedException e) {}
        
    } 
}
