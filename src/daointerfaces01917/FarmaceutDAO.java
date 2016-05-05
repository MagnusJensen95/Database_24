package daointerfaces01917;

import java.util.List;

import dto01917.FarmaceutDTO;

public interface FarmaceutDAO {
	FarmaceutDTO getFarmaceut(int oprId) throws DALException;
	List<FarmaceutDTO> getFarmaceutList() throws DALException;
	void createFarmaceut(FarmaceutDTO opr) throws DALException;
	void updateFarmaceut(FarmaceutDTO opr, int id) throws DALException;
}