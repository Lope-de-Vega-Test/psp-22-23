public class Principal {
    
    public static void main(String[] args) {
		//Creamos una clase con un saldo inicial
        //Creamos los hilos
		CuentaCorriente cuenta = new CuentaCorriente(100);
		Hilo user1 = new Hilo(cuenta, 500, "Carlos");
		Hilo user2 = new Hilo(cuenta, 700, "Jose");
		Hilo user3 = new Hilo(cuenta, 750, "Miriam");

		//Ejecutamos los hilos
		user1.start();
		user2.start();
		user3.start();
		
        //Iniciamos los hilos con un join
		try {
			user1.join();
			user2.join();
			user3.join();

		} catch (Exception e) {

		}
		System.out.println("Saldo total: " + cuenta.getSaldo());
	}

}