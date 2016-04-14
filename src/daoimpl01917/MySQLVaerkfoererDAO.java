package daoimpl01917;

import java.sql.ResultSet;
//import 
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;
import java.sql.*;
import daointerfaces01917.DALException;
import daointerfaces01917.VaerkfoererDAO;
import dto01917.VaerkfoererDTO;

public class MySQLVaerkfoererDAO implements VaerkfoererDAO {
	
	
	
	public VaerkfoererDTO getVaerkfoerer(int vaerkId) throws DALException {
		
	    try {
	    	ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM Vaerkfoerer WHERE vaerk_id = " + vaerkId);
	    	if (!rs.first()) throw new DALException("Vaerkfoereren, " + vaerkId + ", findes ikke.");
	    	return new VaerkfoererDTO (rs.getString("vaerk_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	    
		
	}
	
	public void createVaerkfoerer(VaerkfoererDTO vaerk) throws DALException {  
		   try {
			   int id = 0;
		    //Connector.getInstance().getConnection().setAutoCommit(false);
		    CallableStatement createVAERK = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call add_Vaerkfoerer(?,?,?,?)");
		    createVAERK.setString(1, vaerk.getvaerkNavn());
		    createVAERK.setString(2, vaerk.getIni());
		    createVAERK.setString(3, vaerk.getCpr());
		    createVAERK.setString(4, vaerk.getPassword());
		    createVAERK.execute();
		    //Connector.getInstance().getConnection().commit();
		    
		    ResultSet rs = Connector.getInstance().doQuery("select max(vaerk_id) from Vaerkfoerer;");
		    if (rs.first())
		
		    
			id = Integer.parseInt(rs.getString(1));							
			vaerk.setvaerkId(id);
			
		    
		   } catch (Exception e) {
			   e.printStackTrace();
		    System.out.println("Cannot create vaerkfoerer");
		    //Connector.getInstance().getConnection().rollback();
		    
		   }
		   finally {
		    //Connector.getInstance().getConnection().setAutoCommit(true);
		   }
		 }
	
	public void updateVaerkfoerer(VaerkfoererDTO vaerk, int id) throws DALException {
		try {
			
			CallableStatement updateVAERK = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call update_Vaerkfoerer(?,?,?,?,?)");
			updateVAERK.setString(1, vaerk.getvaerkNavn());
			updateVAERK.setString(2, vaerk.getIni());
			updateVAERK.setString(3, vaerk.getCpr());
			updateVAERK.setString(4, vaerk.getPassword());
			updateVAERK.setInt(5, id);
			updateVAERK.execute();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<VaerkfoererDTO> getVaerkfoererList() throws DALException {
		List<VaerkfoererDTO> list = new ArrayList<VaerkfoererDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM Vaerkfoerer");
			while (rs.next()) 
			{
				// list.add(new VaerkfoererDTO(rs.getInt("vaerk_id"), rs.getString("vaerk_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
