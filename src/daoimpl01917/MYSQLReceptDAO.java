package daoimpl01917;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.OperatoerDTO;
import dto01917.ReceptDTO;

public class MYSQLReceptDAO implements ReceptDAO{
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		 try {
		    	ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
		    	if (!rs.first()) throw new DALException("Recept " + receptId + " findes ikke");
		    	return new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn"));
		    }
		    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
	List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM recept");
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {

		try {
			Connector.getInstance().doUpdate(" Insert into recept (recept_id, recept_navn)"
					+ " VALUES (" + recept.getReceptId() + ", '" + recept.getReceptNavn() + "' )");
			
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		try {
			Connector.getInstance().doUpdate(
					"UPDATE recept SET  recept_navn= '" + recept.getReceptNavn() + "' WHERE recept_id = " +
					recept.getReceptId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
