package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import daoimpl01917.MYSQLOperatoerDAO;
import daoimpl01917.MYSQLRollerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.RollerDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit_Roller {

	private static MYSQLRollerDAO roller;
	private static MYSQLOperatoerDAO operatoer;
	private static RollerDTO rollerDTO;
	private static OperatoerDTO operatoerDTO; //only used because there must a operatoerDTO with a roller
	
	@Test
	public void a_getListRoller(){
		roller = new MYSQLRollerDAO();
		operatoerDTO = new OperatoerDTO("Morten", "MJ", "121291-1212", "testpass");
		rollerDTO = new RollerDTO(0, true, false, false);
		operatoer = new MYSQLOperatoerDAO();
		try {
			List<RollerDTO> listtest = roller.getRolleList();
			if(listtest == null){
				fail("Gotten list is null!");
			}
			else{
				if(listtest.size() == 0){
					fail("Either database is empty, or couldnt get proper list!");
				}
			}
		} catch (DALException e) {
			fail("Could not get list of roller!");
		}
	}
	
	@Test
	public void b_addOperatoer(){
		try {
			List<OperatoerDTO> listTest = operatoer.getOperatoerList();
			for(int i = 0; i < listTest.size(); i++){
				if(listTest.get(i).getOprNavn().equals(operatoerDTO.getOprNavn())){
					if(listTest.get(i).getCpr().equals(operatoerDTO.getCpr())){
						fail("Already exist operatoer with those and cant add!");
					}
				}
			}
		} catch (DALException e1) {
			fail("failed in getting list for addoperatoer");
		}
		try {
			operatoer.createOperatoer(operatoerDTO);
			rollerDTO.setOpr_id(operatoerDTO.getOprId());
		} catch (DALException e) {
			fail("Could not createoperatoer");
		}
	}

	@Test 
	public void c_addRoller(){
		try {
			roller.createRolle(rollerDTO);
		} catch (DALException e) {
			e.printStackTrace();
			fail("Could not createroller");
		}
	}

	@Test
	public void d_getRoller() {
		try {
			RollerDTO test = roller.getRolle(rollerDTO.getOpr_id());
			if(test != null){
				if(!(test.isAdministrator() == rollerDTO.isAdministrator())){
					fail("Gotten rolle not equal to DTO");
				}
			}
			else{
				fail("Gotten rolle is null!");
			}
		} catch (DALException e) {
			fail("Could not get rolle!");
		}
	}

	@Test
	public void e_updateRoller(){
		try {
			roller.updateRolle(rollerDTO, false, false, true);
			RollerDTO temp = roller.getRolle(rollerDTO.getOpr_id());
			assertEquals(rollerDTO.isFarmaceut(), temp.isFarmaceut());
		} catch (DALException e) {
			fail("error getting updating roller!");
		}
	}
}