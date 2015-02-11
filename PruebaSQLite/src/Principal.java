import java.awt.EventQueue;


/**
 * Desde esta clase ejecutamos la aplicaci�n.
 * @author Arturo
 *
 */
public class Principal {

	/**
	 * Punto de partida desde el cual se inicia la aplicaci�n.
	 * @param args
	 */
	public static void main(String[] args) {
				try {
					VentanaCompilador frame = new VentanaCompilador(); //Genera la ventana del compilador
					frame.setVisible(true); //Hace visible la ventana
				} catch (Exception e) {
					e.printStackTrace(); //Imprime el error en la consola
				}
	}

}
