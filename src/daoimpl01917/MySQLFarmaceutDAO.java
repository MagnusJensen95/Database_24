package daoimpl01917;

import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

//import 
import java.sql.*;
import daointerfaces01917.DALException;
import daointerfaces01917.FarmaceutDAO;
import dto01917.FarmaceutDTO;
import dto01917.OperatoerDTO;

public class MySQLFarmaceutDAO implements FarmaceutDAO {
	
	
	
	public FarmaceutDTO getFarmaceut(int farmId) throws DALException {
		
		
	    try {
	    	
	    	 CallableStatement getFarm = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call get_Farmaceut(?)");
			    getFarm.setInt(1, farmId);
			    ResultSet rs = getFarm.executeQuery();
			    if (rs.first()){			    	
			    	String farm_navn = rs.getString(2);
			    	String farm_ini = rs.getString(3);
			    	String farm_cpr = rs.getString(4);
			    	String farm_password = rs.getString(5);
			    	
			    	FarmaceutDTO newfarm = new FarmaceutDTO(farm_navn, farm_ini, farm_cpr, farm_password);
			    	newfarm.setfarmId(farmId);
			    	return newfarm;
			    }
	    }
	    catch (SQLException e) {throw new DALException(e); }
	    return null;
		
	}
	
	public void createFarmaceut(FarmaceutDTO farm) throws DALException {  
		   try {
			   int id = 0;
		   
		    CallableStatement createFARM = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call add_Farmaceut(?,?,?,?)");
		    createFARM.setString(1, farm.getfarmNavn());
		    createFARM.setString(2, farm.getIni());
		    createFARM.setString(3, farm.getCpr());
		    createFARM.setString(4, farm.getPassword());
		    createFARM.execute();
		    
		    
		    ResultSet rs = Connector.getInstance().doQuery("select max(farm_id) from Farmaceut;");
		    if (rs.first()){   
			id = Integer.parseInt(rs.getString(1));		
		    }
			farm.setfarmId(id);
			
		    
		   } catch (Exception e) {
			   e.printStackTrace();
		    System.out.println("Cannot create farmaceut");
		    
		    
		   }
		   finally {
		    
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
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM view_farmaceut");
			while (rs.next()) 
			{
				FarmaceutDTO current = new FarmaceutDTO(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				current.setfarmId(rs.getInt(1));
				list.add(current);
				 
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		System.out.println("Farmaceuter \n");
		return list;
	}
		
		
}
	
