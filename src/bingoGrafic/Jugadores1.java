package bingoGrafic;


import java.util.ArrayList;

/**
 * @author Ammirata Kevin, Ortiz David
 *Clase jugadores: declara nombre, cantidad y un arrayList.
 *Posee un único constructor donde recibe nombre y cantidad; además inicializa setListaCarton.
 *Setters and Getters de nombre, cantidad y setListaCarton que recibe cantidad y crea objetos tipo carton. 
 *Método toString para mostrar los detalles del jugador.
 */
public class Jugadores1 {

	private String nombre;
	private int cantidad;
	
	private ArrayList<Carton1> listaCarton= new ArrayList<>();
	
//CONSTRUCTOR
	public Jugadores1(String nombre, int cantidad) { 
		setNombre(nombre);
		setCantidad(cantidad);
		setListaCarton(cantidad);
	}

//SETTERS AND GETTERS
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	private void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Carton1> getListaCarton() {
		return listaCarton;
	}

	public void setListaCarton(int cantidad) {
		
		for (int i = 0; i < cantidad; i++) {
			Carton1 c= new Carton1();
		
			this.listaCarton.add(c);
		
		}

	}

	// METODO TOSTRING
	@Override
	public String toString() {
		return "Nombre jugador: " + nombre + "\n"+ "Cantidad cartones comprados: " + cantidad;
	}

}
