package notused;

/**
 * Farmaceut Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class FarmaceutDTO {
	/**
	 * Farmaceut-identifikationsnummer (farm_id) i omraadet 1-99999999. Vaelges
	 * af brugerne
	 */
	private int farmId;
	/** Farmaceutnavn (farm_navn) min. 2 max. 20 karakterer */
	private String farmNavn;
	/** Farmaceut-initialer min. 2 max. 3 karakterer */
	private String ini;
	/** Farmaceut cpr-nr 10 karakterer */
	private String cpr;
	/** Farmaceut password min. 7 max. 8 karakterer */
	private String password;

	public FarmaceutDTO(String farmNavn, String ini, String cpr, String password) {
		this.farmNavn = farmNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	}

	public FarmaceutDTO(FarmaceutDTO farm) {
		this.farmNavn = farm.getfarmNavn();
		this.ini = farm.getIni();
		this.cpr = farm.getCpr();
		this.password = farm.getPassword();
	}


	public String getfarmNavn() {
		return farmNavn;
	}

	public void setfarmNavn(String farmNavn) {
		this.farmNavn = farmNavn;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return farmId + "\t" + farmNavn + "\t" + ini + "\t" + cpr + "\t" + password;
	}

	public int getfarmId() {
		return farmId;
	}

	public void setfarmId(int farmId) {
		this.farmId = farmId;
	}
}