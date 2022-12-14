package tarea3;


public class tarea3 {
    
    public static void main(String[] args) {
        int n = 0;
		
		if(args.length < 1){
            System.out.println("no hay nada con valor 1");
            System.exit(1);
		}else{

		if(args.length == 1){
            String a = ((Object)args[0]).getClass().getSimpleName();

        if(esNumero(args[0]) == true){
            n = Integer.parseInt(args[0]);
            a = "Integer";
        }
	
        if(a.equals("String")){
            System.out.println("no hay nada con valor 2");
            System.exit(2);

        }else{
            if(a.equals("Integer") && n < 0){
            
            System.out.println("3");
            System.exit(3);
                
            }
        else{
            System.out.println("0");
            System.exit(0);
        }
        }
        }else{
            System.out.println("0");
            System.exit(0);
        }
        }
		
    }

    public static boolean esNumero(String cadena)
    {
        
        boolean dev;
    
    try
    {
    Integer.parseInt(cadena);
    dev = true;
    }catch(NumberFormatException excepcion)
    {
    dev = false;
    }
    return dev;  
    }
}
