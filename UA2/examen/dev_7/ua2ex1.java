
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author 34617
 */
public class ua2ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Camarero cam = new Camarero("Moe");
        System.out.println("Camarero: "+cam.getNomCamarero());
        System.out.println("Vasos iniciales: "+"Medias pintas: "+cam.getListaVasosMediaPinta()+" Medias pintas: "+cam.getListaVasosPinta());
        HiloClientes cli1 = new HiloClientes(cam,"Homer");
        HiloClientes cli2 = new HiloClientes(cam,"Barney");
        HiloClientes cli3 = new HiloClientes(cam,"Carl");   
        HiloClientes cli4 = new HiloClientes(cam,"Lenny");
        HiloClientes cli5 = new HiloClientes(cam,"Lurleen");
        cli1.start();
        try{
        Thread.sleep(250,1000);
        }catch(Exception e){}
        cli2.start();
        try{
        Thread.sleep(250,1000);
        }catch(Exception e){}
        cli3.start();
        try{
        Thread.sleep(250,1000);
        }catch(Exception e){}
        cli4.start();
        try{
        Thread.sleep(250,1000);
        }catch(Exception e){}
        cli5.start();
        try{
        Thread.sleep(250,1000);
        }catch(Exception e){}
        try{cli1.join();cli2.join();cli3.join();cli4.join();cli5.join();
        }catch(Exception e){}
     }
}
class VasoCerveza{
int id, tipo;
boolean disponible=true;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
//Pinta equivale a 568ml media Pinta 284ml 
    public VasoCerveza(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    public VasoCerveza() {
        this.id = 0;
        this.tipo = 0;
    }
    
        @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
    

}
class Camarero{
VasoCerveza vaso1 = new VasoCerveza();
VasoCerveza vaso2 = new VasoCerveza();
VasoCerveza vaso3 = new VasoCerveza();
int listaVasosMediaPinta=0, listaVasosPinta=0, listaTotal;
String nomCamarero;
Random r=new Random();
int num =r.nextInt();

    public Camarero(String nomCamarero) {
        this.nomCamarero = nomCamarero;
        for(int i=1;i<=3;i++){
            //System.out.println(i);
            switch(i){
                case 1:
                    vaso1.id=i;
                    break;
                case 2:
                    vaso2.id=i;
                    break;
                case 3:
                    vaso3.id=i;
                    break;
            }
            //vasos[i].id=i;
        num =r.nextInt(0,2);//Genera un numero aleatorio entre 0 y 1
            //System.out.println(num);
        if(num==0){
        this.listaVasosMediaPinta++;
            switch(i){
                case 1:
                    vaso1.tipo=0;
                    break;
                case 2:
                    vaso2.tipo=0;
                    break;
                case 3:
                    vaso3.tipo=0;
                    break;
            }
            //vasos[i].tipo=i;
        }
        else{
        this.listaVasosPinta++;
            switch(i){
                case 1:
                    vaso1.tipo=1;
                    break;
                case 2:
                    vaso2.tipo=1;
                    break;
                case 3:
                    vaso3.tipo=1;
                    break;
            }
            //vasos[i].tipo=i;
        }
        }        
    }

    public int getListaVasosMediaPinta() {
        return listaVasosMediaPinta;
    }

    public void setListaVasosMediaPinta(int listaVasosMediaPinta) {
        this.listaVasosMediaPinta = listaVasosMediaPinta;
    }

    public int getListaVasosPinta() {
        return listaVasosPinta;
    }

    public void setListaVasosPinta(int listaVasosPinta) {
        this.listaVasosPinta = listaVasosPinta;
    }

    public String getNomCamarero() {
        return nomCamarero;
    }

    public void setNomCamarero(String nomCamarero) {
        this.nomCamarero = nomCamarero;
    }
    
    public synchronized int servirCerveza(int vaso){
        contarVasos();
        if(listaTotal!=0){
            System.out.println("Vasos disponibles: "+listaTotal);
        if(vaso1.disponible){
                vaso1.setDisponible(false);
            return vaso1.id;
        }
        else if(vaso2.disponible){
            vaso2.setDisponible(false);
            return vaso2.id;
        }
        if(vaso3.disponible){
            vaso3.setDisponible(false);
            return vaso3.id;
        }}
        return 0;
    }
    
    public synchronized void devolverCerveza(int id){
        switch(id){
            case 1:
                vaso1.setDisponible(true);
                System.out.println("Vaso "+vaso1.id+ " devuelto");
                break;
            case 2:
                vaso2.setDisponible(true);
                System.out.println("Vaso "+vaso2.id+ " devuelto");
                break;
            case 3:
                vaso3.setDisponible(true);
                System.out.println("Vaso "+vaso3.id+ " devuelto");
                break;
        }
    }
    public synchronized void contarVasos(){
        listaTotal=0;
        if(vaso1.disponible){
        listaTotal++;}
        if(vaso2.disponible){
        listaTotal++;}
        if(vaso3.disponible){
        listaTotal++;}
    }

}

class HiloClientes extends Thread{
Camarero cam;
String nomCli;
int vaso;//Pedir vaso
public static int cont=0;//Determina el orden de los souts mostrados en pantalla

    public HiloClientes(Camarero cam, String nomCli) {
        this.cam = cam;
        this.nomCli = nomCli;
    }

    public Camarero getCam() {
        return cam;
    }

    public void setCam(Camarero cam) {
        this.cam = cam;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }
    
    public void run(){
        
        int cantidad;
        HiloClientes.cont++;
       
        System.out.println("("+HiloClientes.cont+")"+" Soy "+this.getNomCli()+" Pido un vaso.");
        switch(cam.servirCerveza(vaso)){
            case 1:
                if(cam.vaso1.tipo==1){
                cantidad=568;
                }
                else{
                cantidad=284;
                }
                System.out.println("He recibido el vaso "+cam.vaso1.getId()+" con "+cantidad+"ml");
                try{
                Thread.sleep(200, 6000);
                }catch(Exception e){};
                System.out.println("Me he bebido la cerveza");
                cam.devolverCerveza(1);
                break;
            case 2:
                if(cam.vaso2.tipo==1){
                cantidad=568;
                }
                else{
                cantidad=284;
                }
                System.out.println("He recibido el vaso "+cam.vaso2.getId()+" con "+cantidad+"ml");
                try{
                Thread.sleep(200, 6000);
                }catch(Exception e){};
                System.out.println("Me he bebido la cerveza");
                cam.devolverCerveza(2);
                break;
            case 3:
                if(cam.vaso3.tipo==1){
                cantidad=568;
                }
                else{
                cantidad=284;
                }
                System.out.println("He recibido el vaso "+cam.vaso3.getId()+" con "+cantidad+"ml");
                try{
                Thread.sleep(200, 6000);
                }catch(Exception e){};
                System.out.println("Me he bebido la cerveza");
                cam.devolverCerveza(3);
                break;
            default:
                System.out.println("Vasos disponibles 0:");
                System.out.println("No he recibido cerveza");
        }

    }

    
}
