package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaUsuarios implements Initializable {

	@FXML
	private JFXTextField id;
	
	@FXML
	private JFXTextField login;
	
	@FXML
	private JFXTextField senha;
	
	@FXML
	private JFXTextField telefone;
	
	@FXML
	private JFXTextField nome;
	
	@FXML
	private JFXButton adicionar;
	
	@FXML
	private JFXButton alterar;
	
	@FXML
	private JFXButton deletar;
	
	@FXML
	private JFXButton procurar;
	
	@FXML 
	private JFXComboBox<String> Combovalor;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setStyle("-fx-text-inner-color: #a0a2ab;");
		senha.setStyle("-fx-text-inner-color: #a0a2ab;");
		login.setStyle("-fx-text-inner-color: #a0a2ab;");
		telefone.setStyle("-fx-text-inner-color: #a0a2ab;");
		nome.setStyle("-fx-text-inner-color: #a0a2ab;");
		Combovalor.getItems().setAll("usuário", "admin");
	}
	
	
	

	
}
