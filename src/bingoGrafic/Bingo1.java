package bingoGrafic;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @author Ammirata Kevin, Ortiz David Clase Bingo, donde definimos las
 *         variables: Lista (ArrayList), que es el "bombo"; Precios carton,
 *         premio menor y mayor, y porcentaje de casa, como final static para
 *         calcular la parte económica del juego.
 */
public class Bingo1 {

	ArrayList<String> Lista;
	static final double PRECIO_CARTON = 20;
	static final double PREMIO_MENOR = 0.10;
	static final double PREMIO_MAYOR = 0.70;
	static final double PORCENTAJE_CASA = 0.20;
	static boolean fila = false;
	static int contador = 0;

// METODO RELLENAR
	/**
	 * Metodo rellenar crea y devuelve un arrayList con Strings del 1-90.
	 * 
	 * @return ArrayList Lista.
	 */
	public static ArrayList<String> rellenar() {
		String valor = "";
		ArrayList<String> Lista = new ArrayList<>();
		for (int i = 1; i < 91; i++) {
			valor = Integer.toString(i);
			Lista.add(valor);
		}
		return Lista;
	}

//METODO PARA EXTRAER BOLAS CADA 10SEGUNDOS
	/**
	 * Método extraerBolas recibe @param ArrayList Lista El metodo utliza un random
	 * del 1-90, convertido a String y si se encuentra en Lista muestra en pantalla
	 * el número, y lo borra de la lista para que no vuelva a aparecer. Además se
	 * encuentra en un bucle booleano, validado también por una eventual
	 * Lista.isEmpty. devuelve @return un String (una bolilla)
	 */
	public static String extraerBolas(ArrayList<String> Lista) {
		int random1;
		String random2;
		boolean bolilla = false;
		do {
			Random rand = new Random();
			random1 = rand.nextInt(90) + 1;
			random2 = Integer.toString(random1);
			if (Lista.isEmpty() == true) {
				bolilla = true;
				random1 = 0;
			} else if (Lista.contains(random2)) {
				//System.out.println("Salió la bolilla numero: " + random1);
				Lista.remove(random2);
				bolilla = true;
			}
		} while (bolilla == false);
		return random2;

	}

//METODO Total FACTURADO	
	/**
	 * Metodo recaudacion coge el @param Listaj y devuelve @return el total de lo
	 * vendido
	 */
	public static double recaudacion(ArrayList<Jugadores1> Listaj) {
		double total = 0;
		for (Jugadores1 ele : Listaj) {
			total = total + (PRECIO_CARTON * (ele.getCantidad()));
		}
		return total;
	}

//METODO PREMIO MAYOR
	/**
	 * Metodo premio mayor coge el @param Listaj y devuelve @return el importe del
	 * premio mayor
	 */
	public static double premioMayor(ArrayList<Jugadores1> Listaj) {
		double premioM;
		premioM = PREMIO_MAYOR * recaudacion(Listaj);
		return premioM;
	}

//METODO PREMIO MENOR
	/**
	 * Metodo premio menor coge el @param Listaj y devuelve @return el importe del
	 * premio menor
	 */
	public static double premioMenor(ArrayList<Jugadores1> Listaj) {
		double premioM;
		premioM = PREMIO_MENOR * recaudacion(Listaj);
		return premioM;
	}

//METODO RECAUDACION
	/**
	 * Metodo recaudacionCasa coge el @param Listaj y devuelve @return el importe
	 * que queda para la casa.
	 */
	public static double recaudacionCasa(ArrayList<Jugadores1> Listaj) {
		double premioC;
		premioC = PORCENTAJE_CASA * recaudacion(Listaj);
		return premioC;
	}

//METODO INICIO JUEGO
	/**
	 * Metodo inicio Se ingresan los nombres de los jugadores, y la cantidad de
	 * cartones Debe haber mínimo 5 jugadores, y en total se deben haber vendido 10
	 * cartones para validar esta parte del metodo. (En caso de no ser validad se
	 * borra la Listaj y se vuelve a comenzar). Una vez validada la cantidad de
	 * jugadores y la cantidad se crea el objeto jugador y se lo agrega a Listaj. Se
	 * imprimen los objetos jugadores con sus datos (nombre, cantidad y cartones).
	 * Se muestra, además, la información de la recaudacion y premios.
	 * Devuelve @return Listaj.
	 */
	public static ArrayList<Jugadores1> inicio() {

		ArrayList<Jugadores1> Listaj = new ArrayList<Jugadores1>();
		int validarcantidad = 0;
		int cantidad1;
		int jugador = 0;
		String REG_VALIDAR_NUM_JUGADORES = "^[0-9]{1,}$";

		do {
			do {
				String jugadores = JOptionPane
						.showInputDialog("Ingrese la cantidad de jugadores: \n Deben ser mínimo 2");
				if (jugadores.matches(REG_VALIDAR_NUM_JUGADORES)) {
					jugador = Integer.parseInt(jugadores);
				}

			} while (jugador < 2);
			if (jugador >= 2) {
				for (int i = 0; i < jugador; i++) {
					String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
					do {
						String cantidad = JOptionPane.showInputDialog("Ingrese cantidad de cartones del jugador:");
						cantidad1 = Integer.parseInt(cantidad);
					} while (cantidad1 <= 0 || cantidad1 > 100);
					validarcantidad = validarcantidad + cantidad1;
					Jugadores1 jug = new Jugadores1(nombre, cantidad1);
					Listaj.add(jug);
				}
			}
			if (validarcantidad < 4) {
				System.out.println("La cantidad mínima de cartones vendidos en total deben ser 10");
				Listaj.clear();
			}

		} while (validarcantidad < 4);

	//	for (Jugadores1 imprimir : Listaj) {
	//		System.out.println(imprimir.toString());
	//		System.out.println("----------------------------");
	//		for (int j = 0; j < imprimir.getCantidad(); j++) {
	//			imprimir.getListaCarton().get(j).imprimir();
	//		}

	//		System.out.println("----------------------------");
	//	}
		
		return Listaj;
	}

//METODO Fin
	/**
	 * Metodo Fin coge el parametro @param Lista y Listaj. Define String bola, y
	 * bingo boolean. 1)Llama al metodo extraerBolas y la bollia que devuelve se
	 * guarda en bola. 2)Llama al metodo quitarNumero (de clase Carton) y les
	 * proporciona bola y Listaj. 3)Llama al metodo comprobarCarton(de clase Carton)
	 * con el parametro Listaj, y guarda el resultado en bingo. 4)Si bingo es true,
	 * se acaba en ese instante el juego y se notifica en pantalla 5)Si Lista se
	 * vacia, se acaba el juego.
	 */
	public static ArrayList<String> fin(ArrayList<String> Lista, ArrayList<Jugadores1> Listaj) {
		String bola;
		ArrayList<String> bolillero = new ArrayList<String>();
		String bingo[] = new String[2];
		do {
			bola = extraerBolas(Lista);
			bolillero.add(bola);
			Carton1.quitarNumero(bola, Listaj);	
			bingo = Carton1.comprobarCarton(Listaj);	
			if (bingo[1].length() > 2) {
				bolillero.add(bingo[1]);
			}
		

		} while (!Lista.isEmpty());
		return bolillero;
	
	}

//METODO Timer
	/**
	 * Metodo timer nos permite un tiempo de 10 segundos de espera entre ejecuciones
	 * cada vez que lo llamemos
	 */
	public static void timer(int a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}