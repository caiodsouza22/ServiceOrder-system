package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaClientes implements Initializable{

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField telefone;
	
	@FXML
	private Button add;
	
	@FXML
    private Button edit;
	
	@FXML
	private Button deletar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
}
