package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dal.ModuloConexao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaClientes implements Initializable {

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
	private Button pesquisar;
	
	@FXML
	private Button add;

	@FXML
	private Button edit;

	@FXML
	private Button deletar;


	
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
		
	}

	public TelaClientes() {
		adicionarAction();
		conexao = (Connection) ModuloConexao.conector();

	}



	@FXML
	private void adicionarAction() {

		String sql = "INSERT INTO tbclientes (nomecli,endcli,fonecli,email) VALUES (?,?,?,?)";

		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, nome.getText());
			pst.setString(2, endereco.getText());
			pst.setString(3, telefone.getText());
			pst.setString(4, email.getText());
			if ((nome.getText().isEmpty()) || (endereco.getText().isEmpty()) || (email.getText().isEmpty()) || (telefone.getText().isEmpty())) {
				Alert alert = new Alert(AlertType.INFORMATION, "Preencha todos os campos obrigatórios!");
				alert.showAndWait();
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					Alert alert = new Alert(AlertType.INFORMATION, "Usuario adicionado com sucesso");
					alert.showAndWait();
					
					nome.setText(null);
					endereco.setText(null);
					telefone.setText(null);
					email.setText(null);

				}

			}
		} catch (Exception e) {

		}

	}
	
	
}
	

