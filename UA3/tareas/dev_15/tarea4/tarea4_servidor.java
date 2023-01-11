import java.io.*; 
import java.net.*; 

public class  tarea4_servidor
{ 
	public static void main(String args[]) throws Exception 
	{ 
		DatagramSocket serverSocket = new DatagramSocket(9876); 

		byte[] receiveData = new byte[1024]; 
		byte[] sendData = new byte[1024]; 

		while(true) 
		{ 
			//Recepcion del mensaje del cliente
			DatagramPacket receivePacket = 
					new DatagramPacket(receiveData, receiveData.length); 
			serverSocket.receive(receivePacket); 
			String sentence = new String(receivePacket.getData()); 

			//Conversion del mensaje a mayusculas
			String capitalizedSentence = sentence.toUpperCase(); 

			//Envio del mensaje convertido al cliente
			InetAddress IPAddress = receivePacket.getAddress(); 

			int port = receivePacket.getPort(); 

			sendData = capitalizedSentence.getBytes(); 

			DatagramPacket sendPacket = 
					new DatagramPacket(sendData, sendData.length, IPAddress, 
							port); 

			serverSocket.send(sendPacket); 
		} 
	} 
}