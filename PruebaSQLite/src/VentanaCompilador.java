import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * Clase en la que se define la ventana del compilador.
 * @author Arturo
 *
 */
public class VentanaCompilador extends JFrame {

	//Definición de objetos
	private String rutaDB; //Almacena la ruta de la DB
	private JPanel contentPane;
	private JTextField textFieldRutaDB;
	private JTextField textFieldRutaDestino;
	private JLabel lblRutaAlArchivo;
	private JLabel lblRutaDeDestino;
	private JButton btnConectar;
	private JLabel lblLogotipo;

	/**
	 * Crea la ventana.
	 */
	public VentanaCompilador() {
		//Define la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 339);
		setResizable(false);
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
		btnConectar.setBounds(444, 133, 140, 23);
		contentPane.add(btnConectar);
		
		//Etiqueta ruta
		lblRutaAlArchivo = new JLabel("Ruta al archivo SQLite");
		lblRutaAlArchivo.setFont(new Font("Arial", Font.BOLD, 16));
		lblRutaAlArchivo.setBounds(10, 11, 250, 29);
		contentPane.add(lblRutaAlArchivo);
		
		//Etiqueta ruta de destino
		lblRutaDeDestino = new JLabel("Ruta de destino");
		lblRutaDeDestino.setFont(new Font("Arial", Font.BOLD, 16));
		lblRutaDeDestino.setBounds(10, 110, 250, 14);
		contentPane.add(lblRutaDeDestino);
		
		//Caja de texto ruta de destino
		textFieldRutaDestino = new JTextField();
		textFieldRutaDestino.setBounds(10, 135, 400, 20);
		contentPane.add(textFieldRutaDestino);
		textFieldRutaDestino.setColumns(10);
		
		//Logotipo
		lblLogotipo = new JLabel("");
		lblLogotipo.setForeground(SystemColor.desktop);
		lblLogotipo.setIcon(new ImageIcon("c:/users/arturo/desktop/fmt/logo.png"));
		lblLogotipo.setBounds(483, 188, 101, 111);
		contentPane.add(lblLogotipo);
		
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
