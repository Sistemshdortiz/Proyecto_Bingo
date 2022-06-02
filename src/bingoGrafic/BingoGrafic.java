package bingoGrafic;
import java.awt.Color;
import java.awt.EventQueue;
//import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;


public class BingoGrafic {
	static ArrayList<Jugadores1> nuevaLista = new ArrayList<Jugadores1>();
	static ArrayList<String> nuevaLista1 = new ArrayList<String>();

	private JFrame frame;
	static String s = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
						public void run() {
				try {
					BingoGrafic window = new BingoGrafic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BingoGrafic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaptionText);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		ImageIcon imag1 = new ImageIcon(getClass().getResource("bingo (2).gif"));
		lblNewLabel.setIcon(imag1);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(122, 0, 142, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JButton jugar = new JButton("Empezar a jugar");

		jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contadorF = 0;
				
				nuevaLista = Bingo1.inicio();
				for (Jugadores1 imprimir : nuevaLista) {
					s += imprimir.toString() + "\n";
				}
				JOptionPane.showMessageDialog(jugar, s);
				int contador = 1;
				for (Jugadores1 imprimir : nuevaLista) {
					for (int j = 0; j < imprimir.getCantidad(); j++) {
						JOptionPane.showMessageDialog(jugar, imprimir.getNombre() + "\n" + "Carton " + contador + ": "
								+ "\n" + imprimir.getListaCarton().get(j).toString());
						contador++;
					}
					contador = 1;
				}
				
			
				JTextArea info = new JTextArea();
				info.setBounds(101, 116, 194, 96);
				info.setBackground(UIManager.getColor("Tree.selectionBackground"));
				Border border = BorderFactory.createLineBorder(Color.YELLOW);
				info.setBorder(border);
				info.setFont(new Font("Tahoma", Font.PLAIN, 12));
				info.setEditable(false);
				frame.getContentPane().add(info);
				info.setText("Datos de recaudacion" + "\n" + "La recaudacion total es " + Bingo1.recaudacion(nuevaLista)
						+ " €" + "\n" + "El premio mayor es " + Bingo1.premioMayor(nuevaLista) + " €" + "\n"
						+ "El premio menor es " + Bingo1.premioMenor(nuevaLista) + " €" + "\n" + "Recaudación de la casa "
						+ Bingo1.recaudacionCasa(nuevaLista) + " €");

				nuevaLista1 = Bingo1.fin(Bingo1.rellenar(), nuevaLista);
				for (String bola : nuevaLista1) {
					if (bola.length() > 0 && bola.length() < 3) {
						JOptionPane.showMessageDialog(jugar, "Ha salido la bolilla n: " + bola);
					}
					if (bola.contains("fila") && contadorF == 0) {
						JOptionPane.showMessageDialog(jugar, bola);
						contadorF++;
					}
					if (bola.contains("BINGO")) {
						JOptionPane.showMessageDialog(jugar, bola);
						break;
					}
				}
				JOptionPane.showMessageDialog(jugar, "Fin del juego");
				info.setText("\n\n     ¡¡¡GRACIAS POR JUGAR!!!");
				Border border1 = BorderFactory.createLineBorder(Color.RED);
				info.setBorder(border1);
			}
		});
		jugar.setBounds(122, 51, 150, 43);
		frame.getContentPane().add(jugar);
		jugar.setBackground(UIManager.getColor("Tree.selectionBackground"));
		
		JButton salir = new JButton("Salir");
		salir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}

		});
		salir.setBounds(122, 223, 150, 40);
		salir.setBackground(UIManager.getColor("Tree.selectionBackground"));
		frame.getContentPane().add(salir);
										
			}
}
