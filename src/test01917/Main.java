package test01917;

import daoimpl01917.*;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		
		
//		MYSQLRaavareDAO raav = new MYSQLRaavareDAO();
//		MYSQLRaavareBatchDAO raavBatch = new MYSQLRaavareBatchDAO();
//		MYSQLReceptKompDAO receptKomp = new MYSQLReceptKompDAO();
//		MYSQLReceptDAO recept = new MYSQLReceptDAO();
//		MYSQLProduktBatchKompDAO produktBatchKomp = new MYSQLProduktBatchKompDAO();
//		MYSQLProduktbatchDAO produktBatch = new MYSQLProduktbatchDAO();
		MySQLOperatoerDAO operatoer = new MySQLOperatoerDAO();
		

//		RaavareDTO nyraa = new RaavareDTO(8, "Jensen", "Plebias");
//		try {
//			raav.createRaavare(nyraa);
//			nyraa.setRaavareNavn("Frantsen");
//			raav.updateRaavare(nyraa);
//			if(!raav.getRaavare(8).getRaavareNavn().equals("Frantsen")){
//				throw new DALException("Doesnt Match name, error in raavare getName");
//			}
//			List<RaavareDTO> liste = raav.getRaavareList();
//			for(RaavareDTO e : liste){
//				System.out.println("Id: "+e.getRaavareId()+" & name: "+e.getRaavareNavn());
//			}
//		} catch (DALException e) {
//			e.printStackTrace();
//			System.out.println("Error in raavare.");
//		}
//		
//		RaavareBatchDTO raavareBatchDTO = new RaavareBatchDTO(8, 8, 100);
//		try{
//			raavBatch.createRaavareBatch(raavareBatchDTO);
//			raavareBatchDTO.setMaengde(500);
//			raavBatch.updateRaavareBatch(raavareBatchDTO);
//			raavBatch.getRaavareBatch(8);
//			List<RaavareBatchDTO> liste = raavBatch.getRaavareBatchList();
//			for(RaavareBatchDTO e : liste){
//				System.out.println("Id: "+e.getRbId()+" & mængde: "+e.getMaengde());
//			}
//			
//		} catch (DALException e){
//			e.printStackTrace();
//		}
		
		OperatoerDTO operatoerDTO = new OperatoerDTO("Plebnus", "PN", "121291-1212", "suckit");
		try{
			operatoer.createOperatoer(operatoerDTO);
//			operatoerDTO.setOprNavn("Mar");
//			operatoerDTO.setPassword("JegMar,erennoob");
//			operatoerDTO.setIni("KM");
//			operatoer.updateOperatoer(operatoerDTO);
//			operatoer.getOperatoer(5);
			List<OperatoerDTO> liste = operatoer.getOperatoerList();
			for(OperatoerDTO e : liste){
			//	System.out.println("Id: " & navn: "+e.getOprNavn()+" & ini: "+e.getIni()+" & pass: "+e.getPassword());
			}
		} catch (DALException e){
			e.printStackTrace();
		}
		
//		ReceptKompDTO receptKompDTO = new ReceptKompDTO(4, 8, 20, 0.1);
//		try{
//			receptKomp.createReceptKomp(receptKompDTO);
//			receptKompDTO.setNomNetto(50.0);
//			receptKomp.updateReceptKomp(receptKompDTO);
//			receptKomp.getReceptKompList(4);
//			List<ReceptKompDTO> liste = receptKomp.getReceptKompList();
//			for(ReceptKompDTO e : liste){
//				System.out.println("recept_Id: "+e.getReceptId()+" & raavare_id: "+e.getRaavareId()+" & netto: "+e.getNomNetto());
//			}
//		} catch (DALException e){
//			e.printStackTrace();
//		}
//		
//		ReceptDTO receptDTO = new ReceptDTO(4, "Plebnus");
//		try{
//			recept.createRecept(receptDTO);
//			receptDTO.setReceptNavn("Frantsen");
//			recept.updateRecept(receptDTO);
//			recept.getRecept(4);
//			List<ReceptDTO> liste = recept.getReceptList();
//			for(ReceptDTO e : liste){
//				System.out.println("Id: "+e.getReceptId()+" & navn: "+e.getReceptNavn());
//			}
//		} catch (DALException e){
//			e.printStackTrace();
//		}
//		
//		ProduktBatchKompDTO produktBatchKompDTO = new ProduktBatchKompDTO(5, 8, 0.5, 100, 5);
//		try{
//			produktBatchKomp.createProduktBatchKomp(produktBatchKompDTO);
//			produktBatchKompDTO.setNetto(200);
//			produktBatchKomp.updateProduktBatchKomp(produktBatchKompDTO);
//			produktBatchKomp.getProduktBatchKompList(5);
//			List<ProduktBatchKompDTO> liste = produktBatchKomp.getProduktBatchKompList();
//			for(ProduktBatchKompDTO e : liste){
//				System.out.println("pb_Id: "+e.getPbId()+" & rb_Id: "+e.getRbId()+" & opr_id: "+e.getOprId()+" & netto: "+e.getNetto());
//			}
//		} catch (DALException e){
//			e.printStackTrace();
//		}
//		
//		ProduktBatchDTO produktBatchDTO = new ProduktBatchDTO(6, 3, 4);
//		try{
//			produktBatch.createProduktBatch(produktBatchDTO);
//			produktBatchDTO.setStatus(10);
//			produktBatch.updateProduktBatch(produktBatchDTO);
//			produktBatch.getProduktBatch(6);
//			List<ProduktBatchDTO> liste = produktBatch.getProduktBatchList();
//			for(ProduktBatchDTO e : liste){
//				System.out.println("pb_Id: "+e.getPbId()+" & status: "+e.getStatus()+" & recept_id: "+e.getReceptId());
//			}
//		} catch (DALException e){
//			e.printStackTrace();
//		}
	}
}
