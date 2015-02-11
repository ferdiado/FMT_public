import java.sql.*;

public class SeleccionarDatos
{
  public static void main( String args[] )
  {
	//Definici�n de variables  
    Connection conexion = null;
    Statement stmt = null;
    
    try {
      //Cargamos la clase JDBC
      Class.forName("org.sqlite.JDBC");
      //Conectamos a la BD
      conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Arturo/Desktop/FMT/BBDDSerlitrans.db");
      //Deshabilitamos la confirmaci�n autom�tica para la conexi�n
      conexion.setAutoCommit(false);
      //Mostramos el siguiente mensaje en consola
      System.out.println("La Base de Datos se ha abierto correctamente");

      //Utilizamos el m�todo createStatement con la BD a la que estamos conectados
      stmt = conexion.createStatement();
      //Guardamos el resultado de la consulta en una variable
      ResultSet resultadoConsulta = stmt.executeQuery( "SELECT * FROM USUARIOS;" );
      
      /*
 	  * Probamos el funcionamiento:
 	  * Seleccionando datos
 	  * Imprimi�ndolos en la consola 
 	  */
      while ( resultadoConsulta.next() ) {
         String usuario = resultadoConsulta.getString("usuario");
         String password = resultadoConsulta.getString("password");
         System.out.println( "USUARIO = " + usuario );
         System.out.println( "PASSWORD = " + password );
         System.out.println();
      }
      
      //Cerramos la consulta
      resultadoConsulta.close();
      //Cerramos el m�todo statement
      stmt.close();
      //Cerramos la conexi�n con la BD
      conexion.close();
      
    /*
     *  Si obtenemos un error imprimimos un mensaje en la consola y salimos  
     */
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    
    //Si todo se ha realizado correctamente imprimimos el siguiente mensaje
    System.out.println("La operaci�n se ha realizado con �xito");
  }
}