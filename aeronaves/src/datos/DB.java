package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
private static Connection con;	
	
	public static void init(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aeronaves","root","");
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("--- No se ha podido cargar el driver. Por favor, consulte a su administrador de sistemas---");
		}
		catch(SQLException sqle){
			System.out.println("---Base de datos inexistente o no se puede contectar. Por favor, contacte con el ademninstrador de la base de datos---");
		}
	}
	
	public static Connection getConexion(){
		return con;
	}
	

}
