package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dal.ModuloConexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaLoginController implements Initializable {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@FXML
	private JFXButton menuItemLogin;

	@FXML
	private JFXTextField txtUsuario;

	@FXML
	private JFXPasswordField txtSenha;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		txtUsuario.setStyle("-fx-text-inner-color: #a0a2ab;");
		txtSenha.setStyle("-fx-text-inner-color: #a0a2ab;");

	}
	
	public TelaLoginController() {
		loginAction(null);
		conexao = (Connection) ModuloConexao.conector();
		if(conexao != null) {
			System.out.println("Conectado");
			
		}else {
			System.out.println("Nao conectado");
		}
		
	}
	

	@FXML
	public void loginAction(ActionEvent evt) {

	
		
	}

	
	
	

}
