package notused;

import java.util.List;

import daointerfaces01917.DALException;

public interface VaerkfoererDAO {
	VaerkfoererDTO getVaerkfoerer(int oprId) throws DALException;
	List<VaerkfoererDTO> getVaerkfoererList() throws DALException;
	void createVaerkfoerer(VaerkfoererDTO opr) throws DALException;
	void updateVaerkfoerer(VaerkfoererDTO opr, int id) throws DALException;
}