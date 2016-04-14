package daoimpl01917;

import java.sql.ResultSet;
//import 
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;
import java.sql.*;
import daointerfaces01917.DALException;
import daointerfaces01917.FarmaceutDAO;
import dto01917.FarmaceutDTO;

public class MySQLFarmaceutDAO implements FarmaceutDAO {
	
	
	
	public FarmaceutDTO getFarmaceut(int farmId) throws DALException {
		
	    try {
	    	ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM Farmaceut WHERE farm_id = " + farmId);
	    	if (!rs.first()) throw new DALException("Farmaceuten, " + farmId + ", findes ikke.");
	    	return new FarmaceutDTO (rs.getString("farm_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	    
		
	}
	
	public void createFarmaceut(FarmaceutDTO farm) throws DALException {  
		   try {
			   int id = 0;
		    //Connector.getInstance().getConnection().setAutoCommit(false);
		    CallableStatement createFARM = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call add_Farmaceut(?,?,?,?)");
		    createFARM.setString(1, farm.getfarmNavn());
		    createFARM.setString(2, farm.getIni());
		    createFARM.setString(3, farm.getCpr());
		    createFARM.setString(4, farm.getPassword());
		    createFARM.execute();
		    //Connector.getInstance().getConnection().commit();
		    
		    ResultSet rs = Connector.getInstance().doQuery("select max(farm_id) from Farmaceut;");
		    if (rs.first()){   
			id = Integer.parseInt(rs.getString(1));		
		    }
			farm.setfarmId(id);
			
		    
		   } catch (Exception e) {
			   e.printStackTrace();
		    System.out.println("Cannot create farmaceut");
		    //Connector.getInstance().getConnection().rollback();
		    
		   }
		   finally {
		    //Connector.getInstance().getConnection().setAutoCommit(true);
		   }
		 }
	
	public void updateFarmaceut(FarmaceutDTO farm, int id) throws DALException {
		try {
			
			CallableStatement updateFARM = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call update_Farmaceut(?,?,?,?,?)");
			updateFARM.setString(1, farm.getfarmNavn());
			updateFARM.setString(2, farm.getIni());
			updateFARM.setString(3, farm.getCpr());
			updateFARM.setString(4, farm.getPassword());
			updateFARM.setInt(5, id);
			updateFARM.execute();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<FarmaceutDTO> getFarmaceutList() throws DALException {
		List<FarmaceutDTO> list = new ArrayList<FarmaceutDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM Farmaceut");
			while (rs.next()) 
			{
				// list.add(new FarmaceutDTO(rs.getInt("farm_id"), rs.getString("farm_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
