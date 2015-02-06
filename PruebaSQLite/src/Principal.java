import java.sql.SQLException;
import java.sql.Connection;

public class Principal {
	
	public static void main(String[] args) {
		//Pasamos una ruta a la cual conectar
		Connection conn = Conectar.conectarA("C:/users/arturo/desktop/fmt/BBDDSerlitrans.db");
		
		/*
		 * Aquí realizamos las operaciones en la BD
		 */
		
		try {
			//Cerramos la conexión
			conn.close();
		}
		catch (SQLException e) {
			//Esto se ejecuta si hay algún problema al realizar la conexión
			e.printStackTrace();
		}
	}
}