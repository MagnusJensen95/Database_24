package daointerfaces01917;

import java.util.List;

import dto01917.OperatoerDTO;

public interface OperatoerDAO {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr, boolean administrator, boolean farmaceut, boolean vaerkfoerer) throws DALException;
	void updateOperatoer(OperatoerDTO opr, int id) throws DALException;
}
