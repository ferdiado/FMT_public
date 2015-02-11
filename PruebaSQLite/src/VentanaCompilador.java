import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Clase en la que se define la ventana del compilador.
 * @author Arturo
 *
 */
public class VentanaCompilador extends JFrame {

	//Definición de objetos
	private JPanel contentPane;
	private String rutaDB; //Almacena la ruta de la DB
	private JTextField textFieldRutaDB;
	private JButton btnConectar;
	private JLabel lblRutaAlArchivo;

	/**
	 * Crea la ventana.
	 */
	public VentanaCompilador() {
		//Define la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Caja de texto para la ruta de la DB
		textFieldRutaDB = new JTextField();
		textFieldRutaDB.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldRutaDB.setText("c:/user/carpeta1/carpeta2/archivo.db");
		textFieldRutaDB.setBounds(10, 51, 400, 20);
		contentPane.add(textFieldRutaDB);
		textFieldRutaDB.setColumns(10);
		
		//Botón para conectar a la DB
		btnConectar = new JButton("Compilar");
		btnConectar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnConectar.setBounds(434, 50, 140, 23);
		contentPane.add(btnConectar);
		
		//Etiqueta ruta
		lblRutaAlArchivo = new JLabel("Ruta al archivo SQLite");
		lblRutaAlArchivo.setFont(new Font("Arial", Font.BOLD, 16));
		lblRutaAlArchivo.setBounds(10, 11, 250, 29);
		contentPane.add(lblRutaAlArchivo);
		
		//Botón compilar
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rutaDB = textFieldRutaDB.getText(); //Guarda el texto introducido en rutaDB
					SeleccionarDatos conectarYSeleccionar = new SeleccionarDatos(rutaDB); //Ejecuta la clase SeleccionarDatos y le pasa la variable RutaDB
				} catch (Exception e) {
					e.printStackTrace(); //Imprime el error en la consola
				}
			}
		});
	}
}
