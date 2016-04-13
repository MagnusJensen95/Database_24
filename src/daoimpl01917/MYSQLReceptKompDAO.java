package daoimpl01917;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class MYSQLReceptKompDAO implements ReceptKompDAO {
	



	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		 try {
		    	ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId + ", and raavare_id = " + raavareId);
		    	if (!rs.first()) throw new DALException("Recept " + receptId + " findes ikke");
		    	return new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		    }
		    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM receptkomponent where recept_id = " + receptId);
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
	List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM receptkomponent");
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try {
			Connector.getInstance().doUpdate(" Insert into receptkomponent (recept_id, raavare_id, nom_netto, tolerance) VALUES ("
					+receptkomponent.getReceptId() + ", "
					+receptkomponent.getRaavareId() + ", "
					+receptkomponent.getNomNetto() + ", "
					+receptkomponent.getTolerance() + ")");
						
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
	
	}
	
	

}
