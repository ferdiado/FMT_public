import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase encargada de conectar a la Base de Datos
 */
public final class Conectar {
	
	/**
	 * Conecta a una base de datos en la ruta pasada como argumento
	 * Si el archivo se�alado en la ruta no existe, se crea
	 * @param ruta - La ruta de la DB a conectar
	 * @return - La conexi�n a la ruta
	 */
	public static Connection conectarA(String ruta){
		try {
			//Cargamos el driver de SQLite
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			//Esto se ejecuta si hay un error con el driver de la BD
			e.printStackTrace();
		}
		
		//Declaramos la conexi�n:
		Connection conn = null;
		
		try {
			//Aqu� se obtiene la conexi�n
			conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
			//Un mensaje en la consola para saber si se ha realizado la conexi�n
			System.out.println("Conexi�n realizada con �xito - Ruta de la Base de Datos:" + ruta);
			}
		catch (SQLException e) {
			//Esto se ejecuta si hay un error en la Base de Datos
			e.printStackTrace();
		}
		
		//Devolvemos la conexi�n
		return conn;
	}
}