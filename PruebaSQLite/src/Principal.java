import java.sql.SQLException;
import java.sql.Connection;

public class Principal {
	
	public static void main(String[] args) {
		//Pasamos una ruta a la cual conectar
		Connection conn = Conectar.conectarA("C:/users/arturo/desktop/fmt/BBDDSerlitrans.db");
		
		/*
		 * Aqu� realizamos las operaciones en la BD
		 */
		
		try {
			//Cerramos la conexi�n
			conn.close();
		}
		catch (SQLException e) {
			//Esto se ejecuta si hay alg�n problema al realizar la conexi�n
			e.printStackTrace();
		}
	}
}