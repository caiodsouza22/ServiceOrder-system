package FXML;

public class TableModel2 {

	String idcli,nomecli,fonecli;

	public TableModel2(String idcli, String nomecli, String fonecli) {
	
		this.idcli = idcli;
		this.nomecli = nomecli;
		this.fonecli = fonecli;
		
	}

	public TableModel2() {
		
		
	}
	
	public String getNomecli() {
		return nomecli;
	}

	public void setNomecli(String nomecli) {
		this.nomecli = nomecli;
	}

	public String getFonecli() {
		return fonecli;
	}

	public void setFonecli(String fonecli) {
		this.fonecli = fonecli;
	}

	public String getIdcli() {
		return idcli;
	}

	public void setIdcli(String idcli) {
		this.idcli = idcli;
	}
	
	
}
