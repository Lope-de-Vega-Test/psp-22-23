public class Ua2ex1 {

    public static void main(String[] args) {
        VasoCerveza cerveza=new VasoCerveza();
         Camarero camarero=new Camarero("Moe");
         HilosClientes homer=new HilosClientes("Homer", cerveza, camarero);
         HilosClientes barney=new HilosClientes("Barney", cerveza, camarero);
         HilosClientes carl=new HilosClientes("Carl", cerveza, camarero);
         HilosClientes lenny=new HilosClientes("Lenny", cerveza, camarero);
         HilosClientes lurleen=new HilosClientes("Lurleen", cerveza, camarero);
         homer.start();
         barney.start();
         carl.start();
         lenny.start();
         lurleen.start();
         try{
             homer.join();
             barney.join();
             carl.join();
             lenny.join();
             lurleen.join();
         }
         catch(InterruptedException e){}
    }
}
class VasoCerveza{
    private int id;
    private int tipo;
    private boolean vasodisponible=true;
    public VasoCerveza(int id) {
        this.id = id;
        this.tipo=(int)Math.random()*2;
        
    }
    public VasoCerveza(){
        id=0;
    }
    
    public boolean isVasodisponible() {
        return vasodisponible;
    }

    public void setVasodisponible(boolean vasodisponible) {
        this.vasodisponible = vasodisponible;
    }
    
    public int getId() {
        return id;
    }
    public int getTipo() {
        return tipo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }  
}

class Camarero{
    private VasoCerveza[] vaso=new VasoCerveza[3];
    private String nombre;
    public Camarero(String nombre) {
        this.nombre = nombre;
        for(int i=0; i<3; i++){
            vaso[i]=new VasoCerveza(i);
        }
        
    }
    public synchronized void servirCerveza(){
        if(vaso[0].isVasodisponible()==true){
            vaso[0].setVasodisponible(false);
            System.out.println("Se ha entregado la cerveza con id "+vaso[0].getId());
        }
        else if(vaso[1].isVasodisponible()==true){
            vaso[1].setVasodisponible(false);
            System.out.println("Se ha entregado la cerveza con id "+vaso[1].getId());
        }
        else if(vaso[2].isVasodisponible()==true){
            vaso[2].setVasodisponible(false);
            System.out.println("Se ha entregado la cerveza con id "+vaso[2].getId());
        }
        else{
            System.out.println("No hay vasos disponibles.");
        }
    }
    public synchronized void devolverCerveza(){
        for(int i=0; i<3; i++){
            if(vaso[i].isVasodisponible()==false){
                vaso[i].setVasodisponible(true);
                System.out.println("Se ha devuelto el vaso numero "+vaso[i].getId());
            }
        }
    }
    public int contarCerveza(){
        int contador=0;
        for(int i=0; i<3; i++){
            if(vaso[i].isVasodisponible()==true){
                contador++;
            }
        }
        return contador;
    }
    
}

class HilosClientes extends Thread{
    private Camarero camarero;
    private String nombre;
    private double litros;
    private int num=0;
    VasoCerveza cerveza;
    public HilosClientes(String nombre, VasoCerveza cerveza, Camarero camarero) {
        this.nombre = nombre;
        this.cerveza=cerveza;
        this.camarero=camarero;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public VasoCerveza getCerveza() {
        return cerveza;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    public void setCerveza(VasoCerveza cerveza) {
        this.cerveza = cerveza;
    }
    
    public double getLitros() {
        return litros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double Litro(){
        if(cerveza.getTipo()==1){
            litros+=0.568261;
        }
        else if(cerveza.getTipo()==0){
            litros+=(0.568261/2);
        }
        
        return litros;
    }
    public void run(){
        while(!cerveza.isVasodisponible()){
            try{
                wait();
            }
            catch(InterruptedException e){};
        }
        while(num==0){
        System.out.println("El cliente "+getNombre()+" va a pedir una cerveza.");
        System.out.println(getNombre()+": Voy a pedir una cerveza.");
        if(camarero.contarCerveza()!=0){
            camarero.servirCerveza();
        }
        System.out.println(getNombre()+": Que rica esta la cerveza.");
        System.out.println(getNombre()+": He bebido "+Litro()+" litros.");
        System.out.println(getNombre()+": Voy a devolver la cerveza.");
        camarero.devolverCerveza();
        try{
            sleep((int)Math.random()*750+250);
        }
        catch(InterruptedException e){}
    }
    }
}
