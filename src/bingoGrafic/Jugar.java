package bingoGrafic;

import java.util.ArrayList;

public class Jugar {

	public static void main(String[] args) {
		System.out.println("*****************************");
		System.out.println("*Bienvenido al Bingo de Java*");
		System.out.println("*****************************");
		System.out.println();
		Bingo1.timer(5000);
		// INICIO
		ArrayList<Jugadores1> nuevaLista = new ArrayList<Jugadores1>();
		nuevaLista = Bingo1.inicio();// aqui asignamos al Arraylist<Jugadores> la lista de jugadores que participan
									// en el juego.
		System.out.println();
		System.out.println("Empiezan a salir los numeros del bombo. Atentos!");
		Bingo1.timer(5000);
		System.out.println("----------------------");
		// INICO-Fin JUEGO
		Bingo1.fin(Bingo1.rellenar(), nuevaLista);
		System.out.println("----------------------");
		
	}

}