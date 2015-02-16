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
	private int pulsado = 0; //Representa el botón que se ha pulsado
	private String rutaDB; //Almacena la ruta de la DB
	private String rutaDestino; //Almacena la ruta de destino
	private JPanel contentPane;
	private JTextField textFieldRutaDB;
	private JTextField textFieldRutaDestino;
	private JLabel lblRutaAlArchivo;
	private JLabel lblRutaDeDestino;
	private JButton btnConectar;
	private JButton botonAbrir;
	private JButton botonGuardar;
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
		textFieldRutaDB.setBounds(10, 51, 529, 20);
		textFieldRutaDB.setEditable(false);
		contentPane.add(textFieldRutaDB);
		textFieldRutaDB.setColumns(10);
		
		//Caja de texto ruta de destino
		textFieldRutaDestino = new JTextField();
		textFieldRutaDestino.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldRutaDestino.setBounds(10, 135, 529, 20);
		textFieldRutaDestino.setEditable(false);
		contentPane.add(textFieldRutaDestino);
		textFieldRutaDestino.setColumns(10);
				
		
		//Botón para conectar a la DB
		btnConectar = new JButton("Compilar");
		btnConectar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnConectar.setBounds(10, 181, 140, 23);
		contentPane.add(btnConectar);
		
		//Etiqueta ruta
		lblRutaAlArchivo = new JLabel("Ruta al archivo SQLite");
		lblRutaAlArchivo.setFont(new Font("Arial", Font.BOLD, 16));
		lblRutaAlArchivo.setBounds(10, 11, 250, 29);
		contentPane.add(lblRutaAlArchivo);
		
		//Etiqueta ruta de destino
		lblRutaDeDestino = new JLabel("Ruta de destino");
		lblRutaDeDestino.setFont(new Font("Arial", Font.BOLD, 16));
		lblRutaDeDestino.setBounds(10, 98, 250, 29);
		contentPane.add(lblRutaDeDestino);
		
		//Logotipo
		lblLogotipo = new JLabel("");
		lblLogotipo.setForeground(SystemColor.desktop);
		lblLogotipo.setIcon(new ImageIcon("c:/users/arturo/desktop/Google Drive/fmt/logo.png"));
		lblLogotipo.setBounds(483, 188, 101, 111);
		contentPane.add(lblLogotipo);
		
		//Botón abrir
		botonAbrir = new JButton("...");
		botonAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ejecuta la clase examinar en el modo abrir
				int pulsado = 1;
				Examinar examinar = new Examinar(pulsado);
				textFieldRutaDB.setText(Examinar.rutaSeleccionada);
			}
		});
		botonAbrir.setFont(new Font("Arial", Font.PLAIN, 14));
		botonAbrir.setBounds(549, 51, 35, 20);
		contentPane.add(botonAbrir);
		
		//Botón guardar
		botonGuardar = new JButton("...");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ejecuta la clase examinar en el modo guardar
				int pulsado = 2;
				Examinar examinar = new Examinar(pulsado);
				textFieldRutaDestino.setText(Examinar.rutaSeleccionada);
			}
		});
		botonGuardar.setFont(new Font("Arial", Font.PLAIN, 14));
		botonGuardar.setBounds(549, 135, 35, 20);
		contentPane.add(botonGuardar);
		
		//Botón compilar
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rutaDB = textFieldRutaDB.getText(); //Guarda el texto introducido en textFieldRutaDB
					rutaDestino = textFieldRutaDestino.getText(); //Guarda el texto introducido en textFieldRutaDestino
					SeleccionarDatos conectarYSeleccionar = new SeleccionarDatos(rutaDB, rutaDestino); //Ejecuta la clase SeleccionarDatos y le pasa la variable RutaDB
				} catch (Exception e) {
					e.printStackTrace(); //Imprime el error en la consola
				}
			}
		});
	}
}
