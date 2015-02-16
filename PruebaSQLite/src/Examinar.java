import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Clase que lanza un File Chooser
 * @author Arturo
 *
 */
public class Examinar {
	//Definición de objetos
	public static String rutaSeleccionada; //Almacena la ruta seleccionada en una variable global
	
	private JPanel contentPane;
	private JFileChooser fileChooser = new JFileChooser();
	private File fileRuta;
	private String ruta;

	public Examinar(int pulsado) {
		contentPane = new JPanel(); //Crea la ventana
		switch (pulsado) {
		case 1:
			int abrir = fileChooser.showOpenDialog(contentPane); //Ventana abrir
			fileRuta = fileChooser.getSelectedFile(); //Obtiene la selección
		break;
		case 2:
			int guardar = fileChooser.showSaveDialog(contentPane); //Ventana guardar
			fileRuta = fileChooser.getSelectedFile(); //Obtiene la selección
		}
		String ruta = (fileRuta.getPath()); //Guardamos la ruta del archivo
		ruta = ruta.replaceAll("\\\\", "/"); //Reemplaza \ por /
        System.out.println(ruta);
        rutaSeleccionada = ruta;
	}

}
