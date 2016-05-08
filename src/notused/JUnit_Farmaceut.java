package notused;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import daointerfaces01917.DALException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit_Farmaceut {

	private static MYSQLFarmaceutDAO farmaceut;
	private static FarmaceutDTO farmaceutDTO;
	
	@Test
	public void a_getListFarmaceut(){
		farmaceut = new MYSQLFarmaceutDAO();
		farmaceutDTO = new FarmaceutDTO("Morten", "MJ", "121291-1212", "testpass");
		try{
			List<FarmaceutDTO> liste = farmaceut.getFarmaceutList();
			if(liste == null){
				fail("Gotten list is null!");
			}
			else{
				if(liste.size() == 0){
					fail("Either database is empty, or couldnt get proper list!");
				}
			}
		} catch (DALException e) {
			fail("Could not get list of farmaceuts!");
		}
	}
	
	@Test
	public void b_addFarmaceut(){
		try {
			List<FarmaceutDTO> liste = farmaceut.getFarmaceutList();
			for(int i = 0; i < liste.size(); i++){
				if(liste.get(i).getCpr().equals(farmaceutDTO.getCpr())){
					fail("Already exist farmaceut with those and cant add!");
				}
			}
		} catch (DALException e1) {
			fail("failed in getting list for add farmaceut");
		}
		try {
			farmaceut.createFarmaceut(farmaceutDTO);
		} catch (DALException e) {
			fail("Could not create farmaceut");
		}
	}

	@Test
	public void c_getFarmaceut() {
		try {
			FarmaceutDTO test = farmaceut.getFarmaceut(farmaceutDTO.getfarmId());
			if(test != null){
				assertEquals(test.getCpr(), farmaceutDTO.getCpr());
			}
			else{
				fail("Gotten farmaceut is null!");
			}
		} catch (DALException e) {
			fail("Could not get farmaceut!");
		}
	}
	
	@Test
	public void d_updateFarmaceut(){
		try {
			farmaceutDTO.setfarmNavn("Mortin");
			farmaceut.updateFarmaceut(farmaceutDTO, farmaceutDTO.getfarmId());
			FarmaceutDTO temp = farmaceut.getFarmaceut(farmaceutDTO.getfarmId());
			assertEquals(temp.getCpr(), farmaceutDTO.getCpr());
		} catch (DALException e) {
			fail("error getting updating farmaceut!");
		}
	}
}

