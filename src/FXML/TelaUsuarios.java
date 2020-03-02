package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dal.ModuloConexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class TelaUsuarios implements Initializable {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

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
	private Button adicionar;

	@FXML
	private Button alterar;

	@FXML
	private Button deletar;

	@FXML
	private Button procurar;

	@FXML
	private JFXComboBox<String> Combovalor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setStyle("-fx-text-inner-color: #a0a2ab;");
		senha.setStyle("-fx-text-inner-color: #a0a2ab;");
		login.setStyle("-fx-text-inner-color: #a0a2ab;");
		telefone.setStyle("-fx-text-inner-color: #a0a2ab;");
		nome.setStyle("-fx-text-inner-color: #a0a2ab;");
		Combovalor.setStyle("-fx-text-inner-color: #a0a2ab;");
		Combovalor.getItems().setAll("admin", "user");
	}

	public TelaUsuarios() {
		adicionarAction(null);
		conexao = (Connection) ModuloConexao.conector();

	}

	@FXML
	private void adicionarAction(ActionEvent evt) {

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
				Combovalor.getSelectionModel().select(rs.getString(6));

			} else {
				Alert alert = new Alert(AlertType.ERROR, "Usuário nao encontrado ! ");
				alert.showAndWait();
				nome.setText(null);
				telefone.setText(null);
				login.setText(null);
				senha.setText(null);
				Combovalor.getSelectionModel().select(null);
			}
		} catch (Exception e) {

		}
	}

}
