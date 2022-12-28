package sockets5;

public class Numeros {
	int numero;
	long cubo;
	long cuadrado;
	public Numeros(){
		
	}
	public Numeros(int numero, long cuadrado, long cubo){
		this.numero=numero;
		this.cuadrado=cuadrado;
		this.cubo=cubo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public long getCubo() {
		return cubo;
	}
	public void setCubo(long cubo) {
		this.cubo = cubo;
	}
	public long getCuadrado() {
		return cuadrado;
	}
	public void setCuadrado(long cuadrado) {
		this.cuadrado = cuadrado;
	}
	@Override
	public String toString() {
		return "Numeros [numero=" + numero + ", cubo=" + cubo + ", cuadrado=" + cuadrado + "]";
	}
	
}
