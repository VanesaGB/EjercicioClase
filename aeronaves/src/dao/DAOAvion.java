package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import datos.DB;
import modelo.Avion;


public class DAOAvion {

	private Connection con=DB.getConexion();		

	public boolean insertar(Avion a){
		
		String sql="insert into aviones (descripcion,peso) values(?,?)";	
				
		try (PreparedStatement ps=con.prepareStatement(sql)){				
			ps.setString(1,a.getDescripcion());
			ps.setDouble(2,a.getPeso());
			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch(SQLException sqle){	
			sqle.printStackTrace();
			return false;
		}		
	}	

	public boolean modificar(Avion a){
		String sql="update aviones set"
				+ " descripcion=?,"
				+ " peso=?"
				+ " where idavion=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,a.getDescripcion());
			ps.setDouble(2,a.getPeso());
			ps.setInt(3,a.getIdavion());
			ps.executeUpdate();
			return true;
		}
		catch(SQLException sqle){			
			sqle.printStackTrace();
			return false;
		}
	}
	
	
	public boolean borrar(Avion a){
		String sql="delete from aviones where idavion=?";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,a.getIdavion());
			ps.executeUpdate();
			return true;
		}
		catch(SQLException sqle){			
			sqle.printStackTrace();
			return false;
		}
	}	

	public Avion buscar(int idavion){
		Avion a=null;		
	
		String sql="select descripcion,peso from aviones where idavion=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,idavion);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				a=new Avion(idavion,rs.getString("descripcion"),rs.getDouble("peso"));
			}
			rs.close();
		}
		catch(SQLException sqle){	
			sqle.printStackTrace();
		}
		return a;
	}
	
	public ArrayList<Avion> listar(){
		ArrayList<Avion> aeronaves=new ArrayList<Avion>();
		
		String sql="select * from aviones order by idavion";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Avion a=new Avion(rs.getInt("idavion"),rs.getString("descripcion"),
						rs.getDouble("peso"));			
				aeronaves.add(a);
			}
			rs.close();
		}
		catch(SQLException sqle){			
			sqle.printStackTrace();
		}
		return aeronaves;
	}
}
