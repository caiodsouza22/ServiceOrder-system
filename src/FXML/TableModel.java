package FXML;

public class TableModel {

	String nomecli,fonecli,emailcli,endcli;

	public TableModel(String nomecli, String fonecli, String emailcli, String endcli) {
	
		this.nomecli = nomecli;
		this.fonecli = fonecli;
		this.emailcli = emailcli;
		this.endcli = endcli;
	}

    public  TableModel () {
    	
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

	public String getEmailcli() {
		return emailcli;
	}

	public void setEmailcli(String emailcli) {
		this.emailcli = emailcli;
	}

	public String getEndcli() {
		return endcli;
	}

	public void setEndcli(String endcli) {
		this.endcli = endcli;
	}
	
}
