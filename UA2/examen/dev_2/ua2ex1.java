/*
 * @author Alfonso Alcaraz
 */
import java.util.ArrayList;

class VasoCerveza
{
	private int id;
	private int tipo;
	
	VasoCerveza(int id, int tipo){
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
		return "VasoCerveza [id=" + id + ", tipo=" + tipo + "]";
	}
    
    
} // Fin Class VasoCerveza


class Camarero
{
    ArrayList<VasoCerveza> listaVasos;
    VasoCerveza vasoSucio;
   // ArrayList<VasoCerveza> vasoSucio;
    String nombre;
    Camarero(String nombre,ArrayList<VasoCerveza> listaVasos ){
    	this.nombre=nombre;
    	//vasoSucio=new ArrayList<VasoCerveza>();
    	this.listaVasos=new ArrayList<VasoCerveza>();
    	VasoCerveza vasoListo1=new VasoCerveza(1,1);
    	VasoCerveza vasoListo2=new VasoCerveza(2,1);
    	VasoCerveza vasoListo3=new VasoCerveza(3,1);
    	this.listaVasos.add(vasoListo1);
    	this.listaVasos.add(vasoListo2);
    	this.listaVasos.add(vasoListo3);
    	
    }
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public synchronized VasoCerveza servirCerveza() {
		
		if(listaVasos.isEmpty()) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}else {
			
			int aleatorio=(int)(Math.random()*3+1);
			
			//vasoSucio.add(listaVasos.get(aleatorio));
			vasoSucio=listaVasos.get(aleatorio-1);
			listaVasos.remove(aleatorio-1);
		}
		 notifyAll();
    	
    	return vasoSucio; 
    }
    public synchronized void devolverCerveza(VasoCerveza vasoSucio) {
    	if(listaVasos.size()==3) {
    		try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
    	}else {
    		
    		listaVasos.add(vasoSucio);
    	}
    	notifyAll();
    }
    
    public synchronized void contarVasos() {
    	
    	System.out.println("Quedan "+listaVasos.size()+" en la cola");
    }
    
	
} // Fin Class Camarero

class HiloClientes extends Thread 
{
	private Camarero camarero;
    private String nombre;
    VasoCerveza vasoSucio;
    Double litros=0.0;

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HiloClientes(Camarero c,String nombre) {
    	this.nombre=nombre;
        camarero = c;
        
    }

    public void run() { 
       while(true) {
    	   vasoSucio=camarero.servirCerveza();
    	   System.out.println("El camarero "+camarero.getNombre()+" sirve una rica cerveza");
    	   System.out.println("El cliente "+getNombre()+" se bebe su rica cerveza");
    	   System.out.println("El cliente "+getNombre()+" devuelve el vaso de su rica cerveza");
    	   if(vasoSucio.getTipo()==1) {
    		   litros=litros+vasoSucio.getTipo()*0.57;
    		   
    	   }else {
    		   litros=litros+(vasoSucio.getTipo()*0.57)/2;
    	   }
    	   camarero.devolverCerveza(vasoSucio);
    	   
    	   System.out.println("El cliente "+getNombre()+" lleva "+litros+" de su rica cerveza");
    	   camarero.contarVasos();
    	   try {
    		   sleep((int)(Math.random()*1000+250));
    	   }catch(Exception e) {
    		   
    	   }
    	   
    	   
       }
    }

	
} // Fin Class HiloClientes





public class ua2ex1 {
    public static void main(String[] args) {
    	ArrayList lista =new ArrayList();
       Camarero Moe= new Camarero("Moe",lista);
       HiloClientes Homer=new HiloClientes(Moe,"Homer");
       HiloClientes Barney=new HiloClientes(Moe,"Barney");
       HiloClientes Carl=new HiloClientes(Moe,"Carl");
       HiloClientes Lenny =new HiloClientes(Moe,"Lenny ");
       HiloClientes Lurleen=new HiloClientes(Moe,"Lurleen");
       
       Homer.start();
       Barney.start();
       Carl.start();
       Lenny.start();
       Lurleen.start();
       
       
       
       try {
           Homer.join();
           Barney.join();
           Carl.join();
           Lenny.join();
           Lurleen.join();
           
       }
       catch (InterruptedException e)
       {
          
       }
    }
}
