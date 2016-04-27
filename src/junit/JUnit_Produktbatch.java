package junit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import daoimpl01917.MYSQLProduktbatchDAO;
import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit_Produktbatch {

	private static MYSQLProduktbatchDAO produktbatch;
	private static ProduktBatchDTO produktbatchDTO;
	
	@Test
	public void a_getListProduktbatch(){
		produktbatch = new MYSQLProduktbatchDAO();
		produktbatchDTO = new ProduktBatchDTO(3, 2);
		try {
			List<ProduktBatchDTO> liste = produktbatch.getProduktBatchList();
			if(liste == null){
				fail("Gotten list is null!");
			}
			else{
				if(liste.size() == 0){
					fail("Either database is empty, or couldnt get proper list!");
				}
			}
		} catch (DALException e) {
			fail("Could not get list of produktbatchs!");
		}
	}
	
	@Test
	public void b_addProduktbatch(){
		try {
			List<ProduktBatchDTO> liste = produktbatch.getProduktBatchList();
			for(int i = 0; i < liste.size(); i++){
				if(liste.get(i).getStatus() == produktbatchDTO.getStatus()){
					if(liste.get(i).getReceptId() == produktbatchDTO.getReceptId()){
						fail("Already exist produktbatch with those and cant add!");
					}
				}
			}
		} catch (DALException e1) {
			fail("failed in getting list for add produktbatch");
		}
		try {
			produktbatch.createProduktBatch(produktbatchDTO);
		} catch (SQLException e) {
			fail("Could not create produktbatch");
		}
	}

	@Test
	public void c_getProduktbatch() {
		try {
			ProduktBatchDTO test = produktbatch.getProduktBatch(produktbatchDTO.getPbId());
			if(test != null){
				if(!(test.getStatus() == produktbatchDTO.getStatus())){
					fail("Gotten produktbatch not equal to DTO");
				}
			}
			else{
				fail("Gotten produktbatch is null!");
			}
		} catch (DALException e) {
			fail("Could not get produktbatch!");
		}
	}
	
	@Test
	public void d_updateProduktbatch(){
		try {
			produktbatchDTO.setStatus(10);
			produktbatch.updateProduktBatch(produktbatchDTO);
			ProduktBatchDTO temp = produktbatch.getProduktBatch(produktbatchDTO.getPbId());
			assertEquals(temp.getStatus(), 10);
		} catch (DALException e) {
			fail("error getting updating produktbatch!");
		}
	}
}