package daoimpl01917;

import java.sql.CallableStatement;
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

			CallableStatement getReceptKomponent = (CallableStatement) Connector.getInstance().getConnection()
					.prepareCall("call get_receptkomponent(?,?)");
			getReceptKomponent.setInt(1, receptId);
			ResultSet rs = getReceptKomponent.executeQuery();
			if (rs.first()) {
				int recept_id = rs.getInt(1);
				int raavare_id = rs.getInt(2);
				double nom_netto = rs.getDouble(3);
				double tolerance = rs.getDouble(4);

				ReceptKompDTO newRecKom = new ReceptKompDTO(recept_id, raavare_id, nom_netto, tolerance);

				return newRecKom;
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return null;

	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		try {
			ResultSet rs = Connector.getInstance()
					.doQuery("SELECT * FROM view_receptkomponent where recept_id = " + receptId);
			while (rs.next()) {
				ReceptKompDTO current = new ReceptKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));

				list.add(current);
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}

		System.out.println("Receptkomponenter: \n");
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();

		try {
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM view_receptkomponent");
			while (rs.next()) {
				ReceptKompDTO current = new ReceptKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));

				list.add(current);
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}

		System.out.println("Receptkomponenter: \n");
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		
		try {
		    CallableStatement createRecept = (CallableStatement) Connector.getInstance().getConnection().prepareCall("call add_receptkomponent(?,?,?,?)");
		    createRecept.setInt(1, receptkomponent.getReceptId());
		    createRecept.setInt(2, receptkomponent.getRaavareId());
		    createRecept.setDouble(3, receptkomponent.getNomNetto());
		    createRecept.setDouble(4, receptkomponent.getTolerance());
		    createRecept.execute();
		   } catch (Exception e) {
			   e.printStackTrace();
			   System.out.println("Cannot create receptkomponent");
		  }
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try {
			Connector.getInstance().doUpdate(
					"UPDATE receptkomponent SET  raavare_id = " + receptkomponent.getRaavareId()
					+ ", nom_netto =  " + receptkomponent.getNomNetto()
					+ ", tolerance = " + receptkomponent.getTolerance()+ " WHERE recept_id = " +
					receptkomponent.getReceptId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
