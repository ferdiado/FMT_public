import java.sql.*;

/**
 * Desde esta clase conectamos a la BD y seleccionamos los datos.
 * @author Arturo
 *
 */
public class SeleccionarDatos
{
  /**
   * Constructor de la clase SeleccionarDatos.	
   * @param rutaDB
   */
  public SeleccionarDatos(String rutaDB)
  {
	//Definición de variables  
    Connection conexion = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC"); //Cargamos la clase JDBC
      conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaDB); //Conectamos a la DB
      conexion.setAutoCommit(false); //Deshabilitamos la confirmación automática para la conexión
      System.out.println("La Base de Datos se ha abierto correctamente"); //Mostramos el siguiente mensaje en consola

      stmt = conexion.createStatement(); //Utilizamos el método createStatement con la BD a la que estamos conectados
      ResultSet resultadoConsulta = stmt.executeQuery( "SELECT * FROM USUARIOS;" ); //Guardamos el resultado de la consulta en una variable
      
      /*
 	  * Probamos el funcionamiento:
 	  * Seleccionando datos
 	  * Imprimiéndolos en la consola 
 	  */
      while ( resultadoConsulta.next() ) {
         String usuario = resultadoConsulta.getString("usuario");
         String password = resultadoConsulta.getString("password");
         System.out.println( "USUARIO = " + usuario );
         System.out.println( "PASSWORD = " + password );
         System.out.println();
      }
      
      resultadoConsulta.close(); //Cerramos la consulta
      stmt.close(); //Cerramos el método statement
      conexion.close(); //Cerramos la conexión con la BD
      
    //Si obtenemos un error imprimimos un mensaje en la consola y salimos  
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    
    //Si todo se ha realizado correctamente imprimimos el siguiente mensaje
    System.out.println("La operación se ha realizado con éxito");
  }
}