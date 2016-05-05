package daointerfaces01917;

import java.util.List;

import dto01917.VaerkfoererDTO;

public interface VaerkfoererDAO {
	VaerkfoererDTO getVaerkfoerer(int oprId) throws DALException;
	List<VaerkfoererDTO> getVaerkfoererList() throws DALException;
	void createVaerkfoerer(VaerkfoererDTO opr) throws DALException;
	void updateVaerkfoerer(VaerkfoererDTO opr, int id) throws DALException;
}