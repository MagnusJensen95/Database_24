package daoimpl01917;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import dto01917.RaavareDTO;
import dto01917.ReceptKompDTO;

public class MYSQLRaavareDAO implements RaavareDAO{
	
	private Connector connector = new Connector();

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		try {
	    	ResultSet rs = connector.doQuery("SELECT * FROM raavare WHERE recept_id = " + raavareId);
	    	if (!rs.first()) throw new DALException("Raavare " + raavareId + " findes ikke");
	    	return new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		
		try
		{
			ResultSet rs = connector.doQuery("SELECT * FROM raavare");
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		try {
			connector.doUpdate(" Insert into receptkomponent (raavare_id, raavare_navn, leverandoer) VALUES ("
					+raavare.getRaavareId()+ ", "
					+raavare.getRaavareNavn() + ", "
					+raavare.getLeverandoer() + ", ");
						
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		try {
			connector.doUpdate(
					"UPDATE raavare SET  raavare_navn= '" + raavare.getRaavareNavn() + "', leverandoer = '"
							+ raavare.getLeverandoer() + "' WHERE raavare_id = " + raavare.getRaavareId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
	}
	
	


