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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
     String sql ="select * from tbusuarios where login=? and senha=?";
	 try {
		 pst = (PreparedStatement) conexao.prepareStatement(sql);
		 pst.setString(1, txtUsuario.getText());
		 pst.setString(2, txtSenha.getText());
         
		 rs = pst.executeQuery();
		 
		 if(rs.next()) {
			 Stage principal = new Stage();
			 Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaPrincipal.fxml"));
			 menuItemLogin.getScene().getWindow().hide();
			 Scene scene = new Scene(root);
			 principal.setScene(scene);
			 principal.show();
			 
		 }else {
			 System.out.println("usuário / ou senha inválidos");
		 }
	 }catch (Exception e) {
		 
	 }
		
	}

	
	
	

}
