package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
	private TextField idcli;
	
	@FXML
	private Button procurar;
	
	@FXML
	private Button add;

	@FXML
	private Button edit;

	@FXML
	private Button deletar;


	
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
		add.setDisable(false);
	}

	public TelaClientes() {
		adicionarAction();
		conexao = (Connection) ModuloConexao.conector();

	}


	@FXML
	private void consultarAction(ActionEvent evt2) {

		String sql = "select * from tbclientes where idcli=?";
		try {

			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, idcli.getText());
			rs = pst.executeQuery();

			if (rs.next()) {

				nome.setText(rs.getString(2));
				telefone.setText(rs.getString(4));
				endereco.setText(rs.getString(3));
				email.setText(rs.getString(5));
				
				
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Usuário não cadastrado !");
				alert.showAndWait();
				nome.setText(null);
				telefone.setText(null);
				email.setText(null);
				endereco.setText(null);
			}
		} catch (Exception e) {

		}

	}
	@FXML
	private void adicionarAction() {

		String sql = "INSERT INTO tbclientes (idcli,nomecli,endcli,fonecli,email) VALUES (?,?,?,?,?)";

		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(2, nome.getText());
			pst.setString(3, endereco.getText());
			pst.setString(4, telefone.getText());
			pst.setString(5, email.getText());
			pst.setString(1, idcli.getText());
			if ((nome.getText().isEmpty()) || (endereco.getText().isEmpty()) || (email.getText().isEmpty()) || (telefone.getText().isEmpty())) {
				Alert alert = new Alert(AlertType.INFORMATION, "Preencha todos os campos obrigatórios!");
				alert.showAndWait();
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					Alert alert = new Alert(AlertType.INFORMATION, "Usuario adicionado com sucesso");
					alert.showAndWait();
					
					idcli.setText(null);
					nome.setText(null);
					endereco.setText(null);
					telefone.setText(null);
					email.setText(null);

				}

			}
		} catch (Exception e) {

		}

	}
	
	@FXML
	private void alterarAction() {
		String sql = "UPDATE tbclientes SET nomecli=?,endcli=?,fonecli=?,email=? where idcli=?";
		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			
			pst.setString(1, nome.getText());
			pst.setString(2, endereco.getText());
			pst.setString(3, telefone.getText());
			pst.setString(4, email.getText());
			pst.setString(5, idcli.getText());
		
			if ((email.getText().isEmpty()) ||(nome.getText().isEmpty()) || (endereco.getText().isEmpty()) || (telefone.getText().isEmpty()) ) {
            	Alert alert = new Alert(AlertType.ERROR , "Preencha todos os campos obrigatórios!");
				alert.showAndWait();
            }else {
				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					Alert alert = new Alert(AlertType.INFORMATION , "Dados do usuário alterados com sucesso!");
					alert.showAndWait();
					idcli.setText(null);
					endereco.setText(null);
					nome.setText(null);
					telefone.setText(null);
					email.setText(null);
					add.setDisable(false);
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
			String sql="DELETE FROM tbclientes WHERE idcli=?";
					try {
						pst=(PreparedStatement) conexao.prepareStatement(sql);
						pst.setString(1, idcli.getText());
						int apagado = pst.executeUpdate();
						if(apagado>0) {
							Alert alert2 = new Alert(AlertType.INFORMATION , "Usuário apagado com sucesso!");
							alert2.showAndWait();
							idcli.setText(null);
							nome.setText(null);
							telefone.setText(null);
							email.setText(null);
							endereco.setText(null);
							
						}
					}catch (Exception e) {
						
					}
		}
		
	}
	
}
	

