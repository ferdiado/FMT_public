import java.sql.*;
import java.io.*;

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
  public SeleccionarDatos(String rutaDB, String rutaDestino)
  {
	//Definición de variables  
    Connection conexion = null;
    Statement stmt = null;
	FileWriter fw = null;
    
    try {
      //Preparamos el editor de texto	
      fw = new FileWriter(rutaDestino);
	  BufferedWriter bw = new BufferedWriter(fw);
	  PrintWriter salArch = new PrintWriter(bw);
	  
	  //Conectamos con la DB
      Class.forName("org.sqlite.JDBC"); //Cargamos la clase JDBC
      conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaDB); //Conectamos a la DB
      conexion.setAutoCommit(false); //Deshabilitamos la confirmación automática para la conexión
      System.out.println("La Base de Datos se ha abierto correctamente"); //Mostramos el siguiente mensaje en consola

      stmt = conexion.createStatement(); //Utilizamos el método createStatement con la BD a la que estamos conectados
      ResultSet resultadoConsulta = stmt.executeQuery( "SELECT * FROM VEHICULOS;" ); //Guardamos el resultado de la consulta en una variable
      
      //Compilamos la consulta en un archivo
      while ( resultadoConsulta.next() ) {
         String id = resultadoConsulta.getString("id"); //Obtenemos los datos de la columna id
         String matricula = resultadoConsulta.getString("matricula"); //Obtenemos los datos de la columna matricula
         String lineaDeDatos = resultadoConsulta.getString("id") + "," + resultadoConsulta.getString("matricula"); //Generamos un String con los resultados
         salArch.println (lineaDeDatos); //Imprimimos el String en un archivo
      }
      
      salArch.close(); //Cerramos el editor de texto
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