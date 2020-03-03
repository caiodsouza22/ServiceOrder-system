package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dal.ModuloConexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class TelaUsuarios implements Initializable {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@FXML
	private TextField id;

	@FXML
	private TextField login;

	@FXML
	private TextField senha;

	@FXML
	private TextField telefone;

	@FXML
	private TextField nome;

	@FXML
	private Button adicionar;

	@FXML
	private Button alterar;

	@FXML
	private Button deletar;

	@FXML
	private Button procurar;

	@FXML
	private JFXComboBox<String> comboValor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setStyle("-fx-text-inner-color: #a0a2ab;");
		senha.setStyle("-fx-text-inner-color: #a0a2ab;");
		login.setStyle("-fx-text-inner-color: #a0a2ab;");
		telefone.setStyle("-fx-text-inner-color: #a0a2ab;");
		nome.setStyle("-fx-text-inner-color: #a0a2ab;");
		comboValor.setStyle("-fx-text-inner-color: #a0a2ab;");
		comboValor.getItems().addAll("admin", "user");

	}

	public TelaUsuarios() {
		adicionarAction();
		conexao = (Connection) ModuloConexao.conector();

	}

	@FXML
	private void consultarAction(ActionEvent evt2) {

		String sql = "select * from tbusuarios where iduser=?";
		try {

			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, id.getText());
			rs = pst.executeQuery();

			if (rs.next()) {

				nome.setText(rs.getString(2));
				telefone.setText(rs.getString(3));
				login.setText(rs.getString(4));
				senha.setText(rs.getString(5));
				comboValor.getSelectionModel().select(rs.getString(6));
				comboValor.getItems().addAll(rs.getString(6));
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Usuário não cadastrado !");
				alert.showAndWait();
				nome.setText(null);
				telefone.setText(null);
				login.setText(null);
				senha.setText(null);
				comboValor.getSelectionModel().select(null);
			}
		} catch (Exception e) {

		}

	}

	@FXML
	private void adicionarAction() {

		String sql = "INSERT INTO tbusuarios (iduser,usuario,fone,login,senha,perfil) VALUES (?,?,?,?,?,?)";

		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, id.getText());
			pst.setString(2, nome.getText());
			pst.setString(3, telefone.getText());
			pst.setString(4, login.getText());
			pst.setString(5, senha.getText());
			pst.setString(6, comboValor.getValue().toString());
            if ((id.getText().isEmpty()) ||(nome.getText().isEmpty()) || (login.getText().isEmpty()) || (senha.getText().isEmpty()) ) {
            	Alert alert = new Alert(AlertType.ERROR , "Preencha todos os campos obrigatórios!");
				alert.showAndWait();
            }else {
            
			
			int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					Alert alert = new Alert(AlertType.INFORMATION , "Usuario adicionado com sucesso");
					alert.showAndWait();
					id.setText(null);
					nome.setText(null);
					telefone.setText(null);
					login.setText(null);
					senha.setText(null);		

				}
            
            }
		} catch (Exception e) {

		}

	}

	@FXML
	private void alterarAction(ActionEvent evt4) {
		String sql = "UPDATE tbusuarios SET usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, nome.getText());
			pst.setString(2, telefone.getText());
			pst.setString(3, login.getText());
			pst.setString(4, senha.getText());
			pst.setString(5, comboValor.getValue().toString());
			pst.setString(6, id.getText());
			if ((id.getText().isEmpty()) ||(nome.getText().isEmpty()) || (login.getText().isEmpty()) || (senha.getText().isEmpty()) ) {
            	Alert alert = new Alert(AlertType.ERROR , "Preencha todos os campos obrigatórios!");
				alert.showAndWait();
            }else {
				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					Alert alert = new Alert(AlertType.INFORMATION , "Dados do usuário alterados com sucesso!");
					alert.showAndWait();
					id.setText(null);
					nome.setText(null);
					telefone.setText(null);
					login.setText(null);
					senha.setText(null);
				}
            }
		} catch (Exception e) {

		}
		
	}
	
	@FXML
	private void deletarAction(ActionEvent evt5) {
		Alert alert = new Alert(AlertType.CONFIRMATION , "Tem certeza que deseja remover este usuário? ", ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			String sql="DELETE FROM tbusuarios WHERE iduser=?";
					try {
						pst=(PreparedStatement) conexao.prepareStatement(sql);
						pst.setString(1, id.getText());
						int apagado = pst.executeUpdate();
						if(apagado>0) {
							Alert alert2 = new Alert(AlertType.INFORMATION , "Usuário apagado com sucesso!");
							alert2.showAndWait();
							id.setText(null);
							nome.setText(null);
							telefone.setText(null);
							login.setText(null);
							senha.setText(null);
						}
					}catch (Exception e) {
						
					}
		}
		
	}

}
