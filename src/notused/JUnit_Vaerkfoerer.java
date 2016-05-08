package notused;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import daointerfaces01917.DALException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit_Vaerkfoerer {

	private static MYSQLVaerkfoererDAO vaerkfoerer;
	private static VaerkfoererDTO vaerkfoererDTO;
	
	@Test
	public void a_getListVaerkfoerer(){
		vaerkfoerer = new MYSQLVaerkfoererDAO();
		vaerkfoererDTO = new VaerkfoererDTO("Morten", "MJ", "121291-1212", "testpass");
		try{
			List<VaerkfoererDTO> liste = vaerkfoerer.getVaerkfoererList();
			if(liste == null){
				fail("Gotten list is null!");
			}
			else{
				if(liste.size() == 0){
					fail("Either database is empty, or couldnt get proper list!");
				}
			}
		} catch (DALException e) {
			fail("Could not get list of vaerkfoerers!");
		}
	}
	
	@Test
	public void b_addVaerkfoerer(){
		try {
			List<VaerkfoererDTO> liste = vaerkfoerer.getVaerkfoererList();
			for(int i = 0; i < liste.size(); i++){
				if(liste.get(i).getCpr().equals(vaerkfoererDTO.getCpr())){
					fail("Already exist vaerkfoerer with those and cant add!");
				}
			}
		} catch (DALException e1) {
			fail("failed in getting list for add vaerkfoerer");
		}
		try {
			vaerkfoerer.createVaerkfoerer(vaerkfoererDTO);
		} catch (DALException e) {
			fail("Could not create vaerkfoerer");
		}
	}

	@Test
	public void c_getVaerkfoerer() {
		try {
			VaerkfoererDTO test = vaerkfoerer.getVaerkfoerer(vaerkfoererDTO.getvaerkId());
			if(test != null){
				assertEquals(test.getCpr(), vaerkfoererDTO.getCpr());
			}
			else{
				fail("Gotten vaerkfoerer is null!");
			}
		} catch (DALException e) {
			fail("Could not get vaerkfoerer!");
		}
	}
	
	@Test
	public void d_updateVaerkfoerer(){
		try {
			vaerkfoererDTO.setvaerkNavn("Mortin");
			vaerkfoerer.updateVaerkfoerer(vaerkfoererDTO, vaerkfoererDTO.getvaerkId());
			VaerkfoererDTO temp = vaerkfoerer.getVaerkfoerer(vaerkfoererDTO.getvaerkId());
			assertEquals(temp.getCpr(), vaerkfoererDTO.getCpr());
		} catch (DALException e) {
			fail("error getting updating vaerkfoerer!");
		}
	}
}