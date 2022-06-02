package bingoGrafic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Carton1 {
	// ------Propiedad--------
	private String[][] carton1 = new String[3][9];
	static boolean xfila = false;
	static boolean xbingo = false;
	static int primeraFilaCheck = 0;
	static int BingoCheck = 0;
	static String nombre = "";

	/**
	 * Constructor
	 */
	public Carton1() {
		setCarton1();
	}

	public String[][] getCarton1() {
		return carton1;
	}

	/**
	 * Este set nos va a generar un carton de forma aleatoria dentro de cada objeto
	 * de tipo Carton utilizando para ello el método {@link #generarCartones()}.
	 */
	public void setCarton1() {
		this.carton1 = Carton1.generarCartones();
	}

//METODO ALEATORIOS
	public static int aleatorio(int min, int max) {
		Random r = new Random();
		int n;
		return n = r.nextInt(max - min + 1) + min;
	}

	// METODO GENERAR CARTONES
	/**
	 * M�todo que genera un array bidimensional con numeros aleatorios que no se
	 * repiten habra una decena distinta en cada columna, desde el uno al 90,
	 * utiliza método {@link aleatorio}, el cual genera números al azar.
	 * 
	 * @return devuelve array con lista de numeros aleatorios, que seré utilizada
	 *         como carton para el juego del Bingo.
	 */
	public static String[][] generarCartones() {
		String[][] carton = new String[3][9];
		ArrayList<String> numerosGenerados = new ArrayList<>();
		String n;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				do {
					switch (j) {
					case 0: // Primera columna
						n = Integer.toString(aleatorio(1, 9));
						break;

					case 8: // �ltima columna
						n = Integer.toString(aleatorio(80, 90));
						break;

					default: // Columnas intermedias9
						n = Integer.toString(aleatorio(10 * j, (10 * j) + 9));
						break;
					}
				} while (numerosGenerados.contains(n));

				numerosGenerados.add(n);
				carton[i][j] = n;
			}
		}

		// Ordena las columnas
		for (int x = 0; x < 9; x++) {
			ArrayList<String> columna = sacaColumna(x, carton);
			Collections.sort(columna);
			meteColumna(x, columna, carton);
		}
		meteHuecos(carton);
		// pintaCarton(carton);
//				 return new Bingo(carton);
		return carton;
	}

// METODO VACIO CARTON
	public static void pintaCarton(String[][] carton) {
		for (String[] fila : carton) {
			for (String numero : fila) {
				if (numero == "X") {
					System.out.print(" ");
				} else {
					System.out.printf("%s ", numero);
				}
			}
			System.out.println();
		}
	}

// METODO SACAR COLUMNA
	/**
	 * Metodo auxiliar que se utiliza en el metodo @link #generarCartones()}. Divide
	 * el carton en columnas
	 * 
	 * @param j      parametro que marca el indice de la columna que se saca
	 * @param carton array bidimensional que recibe para dividir en columnas
	 * @return devuelve una Arraylist de tres numeros que representan una columna.
	 */
	public static ArrayList<String> sacaColumna(int j, String[][] carton) {
		ArrayList<String> aux = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			aux.add(carton[i][j]);
		}
		return aux;
	}

// METODO PONER COLUMNA
	/**
	 * Metodo auxiliar que se utiliza dentro del metodo {@link #generarCartones()}.
	 * Una vez ordenada la columna sacada por el metodo
	 * {@link #sacaColumna(int, int[][])}, se vuelve a introducir en el array
	 * carton.
	 * 
	 * @param j       este marca el indice de la columna que se esta metiendo.
	 * @param columna es la columna sacada por el m�todo
	 *                {@link #sacaColumna(int, int[][])}.
	 * @param carton  array al cual se le va a meter la columna despues de haber
	 *                sido ordenada.
	 */
	public static void meteColumna(int j, ArrayList<String> columna, String[][] carton) {
		for (int i = 0; i < 3; i++) {
			carton[i][j] = columna.get(i);
		}
	}

// METODO CALCULAR VACIOS
	/**
	 * Metodo que introduce un cero de forma aleatoria de manera que exista al menos
	 * 4 posiciones vacias en cada fila del carton.
	 * 
	 * @param carton
	 */
	public static void meteHuecos(String[][] carton) {
		for (int i = 0; i < 3; i++) {
			int huecos = 0;
			do {
				int posicion = aleatorio(0, 8);
				if (!carton[i][posicion].equals("X") && validarColumnas(posicion, carton)) {
					carton[i][posicion] = "X";
					huecos++;
				}
			} while (huecos < 4);
		}
	}

// METODO PARCHE
	/**
	 * Metodo que se utiliza para corregir un problema que ocasiona el metodo
	 * {@link #meteHuecos(int[][])}, el cual nos genera un carton con al menos 4
	 * huecos por fila, pero dada la aleatoriedad hay veces que este genera columnas
	 * todalmente vacias. Este metodo evita que existan columnas vacias. Este metodo
	 * va contando el n�mero de ceros en una columna y evita que haya tres en la
	 * misma.
	 * 
	 * @param posicion va marcando la columna el la que se encuentra
	 * @param carton
	 * @return Devuelve true si no hay mas de dos ceros en una columna, y false si
	 *         los hubiese.
	 * 
	 */
	public static boolean validarColumnas(int posicion, String[][] carton) {
		boolean validado = false;
		int exceso = 0;
		for (int i = 0; i < 3; i++) {
			if (carton[i][posicion].equals("X")) {
				exceso++;
			}
			if ((exceso != 1 && exceso != 2) || (exceso < 1)) {
				validado = true;
			}
		}
		return validado;
	}

	// METODO QUITARNUMERO
	/**
	 * Metodo que recorre la lista de jugadores, y a su vez la lista de cartones de
	 * cada uno de ellos, en el caso de que coincida el numero que ha salido del
	 * bombo con alguno contenido en los cartones, lo sustituye por un 0.
	 * 
	 * @param bolilla numero que se va a comparar con cada uno del carton de cada
	 *                jugador.
	 * @param Lista   lista de jugadores que participan en el juego.
	 */
	public static void quitarNumero(String bolilla, ArrayList<Jugadores1> Lista) {
		// Con este for se recorre la lista de jugadores
		for (int i = 0; i < Lista.size(); i++) {
			recorrerListaCartones(i, Lista, bolilla);
		}
	}

	// METODO QUE RECORRE LA LISTA DE CARTONES DE CADA JUGADOR
	/**
	 * Metodo auxiliar usado en el metodo {@link #quitarNumero(int, ArrayList)}, con
	 * el objeto de recorrer la lista de cartones de cada jugador, con ayuda del
	 * metodo {@link #recorrerCarton(int[][], int)}, nos modificara el numero de
	 * cada carton que coincida con el de bolilla por un cero.
	 * 
	 * @param indiceJugador indice del jugador que se analizará
	 * @param Lista         ArrayList que contiene todos los jugadores que
	 *                      participan en el juego.
	 * @param bolilla       entero que representa la bola que sale del bombo del
	 *                      Bingo. Este entero lo da el metodo
	 *                      {@link Bingo1#extraerBolas(ArrayList)}.
	 */
	public static void recorrerListaCartones(int indiceJugador, ArrayList<Jugadores1> Lista, String bolilla) {
		for (int i = 0; i < Lista.get(indiceJugador).getCantidad(); i++) {
			recorrerCarton(Lista.get(indiceJugador).getListaCarton(), bolilla);

		}
	}

	// METODO QUE RECORRE CADA CARTON DE LA LISTA CARTONES
	/**
	 * Metodo auxiliar utilizado en
	 * {@link #recorrerListaCartones(int, ArrayList, int)}, con el fin de recorrer
	 * cada uno de los valores contenidos en un carton y si coincide con el valor
	 * extraido del bombo dado por metodo {@link Bingo1#extraerBolas(ArrayList)},
	 * modifica dicho valor por un cero.
	 * 
	 * @param cartonARecorrer ArrayList que contiene los cartones que se van a
	 *                        recorrer para ver coincidencias con bolilla.
	 * @param bolilla         entero que representa bola extraida del bombo,
	 *                        generado por el metodo
	 *                        {@link Bingo1#extraerBolas(ArrayList)}.
	 */
	public static void recorrerCarton(ArrayList<Carton1> cartonARecorrer, String bolilla) {

		for (Carton1 vect : cartonARecorrer) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					if (vect.getCarton1()[i][j].equals(bolilla)) {
						vect.getCarton1()[i][j] = "X";
					}
				}
			}
		}
	}

	// METODO COMPROBAR CARTON
	public static String[] comprobarCarton(ArrayList<Jugadores1> Lista) {
		int contador = 0;
		
		String[] datos = new String[2];
		datos[0] = "0";
		datos[1] = "";

		for (int i = 0; i < Lista.size(); i++) {

			nombre = recorrerListaCartones1(i, Lista);

			if (nombre.contains("fila") && contador == 0) {
				datos[1] = nombre;
				contador++;
			}
			if (nombre.contains("BINGO")) {
				datos[1] = nombre;
			}
		}
		return datos;		
	}

	public static String recorrerListaCartones1(int indiceJugador, ArrayList<Jugadores1> Lista) {

		for (int i = 0; i < Lista.get(indiceJugador).getCantidad(); i++) {
			nombre = recorrerCarton1(Lista.get(indiceJugador).getListaCarton(), Lista.get(indiceJugador).getNombre(),
					Lista);
		}	
		return nombre;
	}

	public static String recorrerCarton1(ArrayList<Carton1> CartonARecorrer, String name, ArrayList<Jugadores1> Lista) {

		for (Carton1 vect : CartonARecorrer) {
			int fila1 = 0;
			int fila2 = 0;
			int fila3 = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					if (i == 0) {
						if (vect.getCarton1()[i][j].equals("X")) {
							fila1++;
						}
					} else if (i == 1) {
						if (vect.getCarton1()[i][j].equals("X")) {
							fila2++;
						}

					} else if (i == 2) {
						if (vect.getCarton1()[i][j].equals("X")) {
							fila3++;
						}
					}
					if (fila(fila1, fila2, fila3) && primeraFilaCheck == 0) {
						primeraFilaCheck++;
					//	System.out.format("Se ha hecho fila en el carton de %s y se lleva el premio menor de %.2f € \n",
					//			name, Bingo1.premioMenor(Lista));
						nombre = "Se ha hecho fila en el carton de " + name + " y se lleva el premio menor de "
								+ Bingo1.premioMenor(Lista);
						Bingo1.timer(4000);

					}
					if ((bingo(fila1, fila2, fila3) && BingoCheck == 0)) {
						BingoCheck++;
					//	System.out.format("BINGO en el carton de %s y se lleva el premio mayor de %.2f € \n", name,
					//								Bingo1.premioMayor(Lista));
						nombre = "BINGO en el carton de " + name + " y se lleva el premio mayor de "
								+ Bingo1.premioMayor(Lista);
					}
				}
			}
		}
		return nombre;

	}

//METODO FILA			
	public static boolean fila(int fila1, int fila2, int fila3) {
		if (fila1 == 9 || fila2 == 9 || fila3 == 9) {
			xfila = true;
		} else
			xfila = false;
		return xfila;
	}

//METODO BINGO
	public static boolean bingo(int fila1, int fila2, int fila3) {
		if (fila1 == 9 && fila2 == 9 && fila3 == 9) {
			xbingo = true;
		} else
			xbingo = false;
		return xbingo;
	}

	// METODO TOSTRING

	public void imprimir() {
		System.out.println("------------------------------");
		for (String[] vect : getCarton1()) {

			System.out.println(Arrays.toString(vect));

		}
		System.out.println("------------------------------");
	}

	@Override
	public String toString() {
		String s = "";
		for (String[] vect : getCarton1()) {

			s = s + Arrays.toString(vect) + "\n";
		}
		return s;
	}

}