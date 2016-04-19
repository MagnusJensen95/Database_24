package daoimpl01917;

import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

//import 
import java.sql.*;
import daointerfaces01917.DALException;
import daointerfaces01917.VaerkfoererDAO;
import dto01917.VaerkfoererDTO;
import dto01917.VaerkfoererDTO;
import dto01917.VaerkfoererDTO;

public class MySQLVaerkfoererDAO implements VaerkfoererDAO {
	
	
	
	public VaerkfoererDTO getVaerkfoerer(int vaerkId) throws DALException {
		
		
	    try {
	    	
	    	 CallableStatement getOP = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call get_vaerkfoerer(?)");
			    getOP.setInt(1, vaerkId);
			    ResultSet rs = getOP.executeQuery();
			    if (rs.first()){			    	
			    	String vaerk_navn = rs.getString(2);
			    	String vaerk_ini = rs.getString(3);
			    	String vaerk_cpr = rs.getString(4);
			    	String vaerk_password = rs.getString(5);
			    	
			    	VaerkfoererDTO newvaerk = new VaerkfoererDTO(vaerk_navn, vaerk_ini, vaerk_cpr, vaerk_password);
			    	newvaerk.setvaerkId(vaerkId);
			    	return newvaerk;
			    }
	    }
	    catch (SQLException e) {throw new DALException(e); }
	    return null;
		
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
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM view_Vaerkfoerer");
			while (rs.next()) 
			{
				VaerkfoererDTO current = new VaerkfoererDTO(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				current.setvaerkId(rs.getInt(1));
				list.add(current);
				 
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		System.out.println("Vaerkfoerere \n");
		return list;
	}
		
		
}
	
