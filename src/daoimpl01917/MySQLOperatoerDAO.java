package daoimpl01917;

import java.sql.ResultSet;
//import 
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;
import java.sql.*;
import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO {
	
	
	
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		
	    try {
	    	ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
	    	if (!rs.first()) throw new DALException("Operatoeren, " + oprId + ", findes ikke.");
	    	return new OperatoerDTO (rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	    
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {  
		   try {
			   int id = 0;
		    //Connector.getInstance().getConnection().setAutoCommit(false);
		    CallableStatement createOP = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call add_operatoer(?,?,?,?)");
		    createOP.setString(1, opr.getOprNavn());
		    createOP.setString(2, opr.getIni());
		    createOP.setString(3, opr.getCpr());
		    createOP.setString(4, opr.getPassword());
		    createOP.execute();
		    //Connector.getInstance().getConnection().commit();
		    
		    ResultSet rs = Connector.getInstance().doQuery("select max(opr_id) from operatoer;");
		    if (rs.first())
		
		    
			id = Integer.parseInt(rs.getString(1));							
			opr.setOprId(id);
			
		    
		   } catch (Exception e) {
			   e.printStackTrace();
		    System.out.println("Cannot create operator");
		    //Connector.getInstance().getConnection().rollback();
		    
		   }
		   finally {
		    //Connector.getInstance().getConnection().setAutoCommit(true);
		   }
		 }
	
	public void updateOperatoer(OperatoerDTO opr, int id) throws DALException {
		try {
			
			CallableStatement updateOP = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call update_operatoer(?,?,?,?,?)");
			updateOP.setString(1, opr.getOprNavn());
			updateOP.setString(2, opr.getIni());
			updateOP.setString(3, opr.getCpr());
			updateOP.setString(4, opr.getPassword());
			updateOP.setInt(5, id);
			updateOP.execute();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM operatoer");
			while (rs.next()) 
			{
				// list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
