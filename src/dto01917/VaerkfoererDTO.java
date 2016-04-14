package dto01917;

/**
 * Vaerkfoerer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class VaerkfoererDTO {
	/**
	 * Vaerkfoerer-identifikationsnummer (vaerk_id) i omraadet 1-99999999. Vaelges
	 * af brugerne
	 */
	int vaerkId;
	/** Vaerkfoerernavn (vaerk_navn) min. 2 max. 20 karakterer */
	String vaerkNavn;
	/** Vaerkfoerer-initialer min. 2 max. 3 karakterer */
	String ini;
	/** Vaerkfoerer cpr-nr 10 karakterer */
	String cpr;
	/** Vaerkfoerer password min. 7 max. 8 karakterer */
	String password;

	public VaerkfoererDTO(String vaerkNavn, String ini, String cpr, String password) {

		this.vaerkNavn = vaerkNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	}

	public VaerkfoererDTO(VaerkfoererDTO vaerk) {

		this.vaerkNavn = vaerk.getvaerkNavn();
		this.ini = vaerk.getIni();
		this.cpr = vaerk.getCpr();
		this.password = vaerk.getPassword();
	}


	public String getvaerkNavn() {
		return vaerkNavn;
	}

	public void setvaerkNavn(String vaerkNavn) {
		this.vaerkNavn = vaerkNavn;
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
		return vaerkId + "\t" + vaerkNavn + "\t" + ini + "\t" + cpr + "\t" + password;
	}

	public int getvaerkId() {
		return vaerkId;
	}

	public void setvaerkId(int vaerkId) {
		this.vaerkId = vaerkId;
	}
}

