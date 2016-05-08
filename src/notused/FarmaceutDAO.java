package notused;

import java.util.List;

import daointerfaces01917.DALException;

public interface FarmaceutDAO {
	FarmaceutDTO getFarmaceut(int oprId) throws DALException;
	List<FarmaceutDTO> getFarmaceutList() throws DALException;
	void createFarmaceut(FarmaceutDTO opr) throws DALException;
	void updateFarmaceut(FarmaceutDTO opr, int id) throws DALException;
}