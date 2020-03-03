package FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TelaPrincipal implements Initializable {

	@FXML
	private MenuItem sair;

	@FXML
	private MenuItem sobre;

	@FXML
	private MenuItem usuarios;

	@FXML
	private Menu relatorio;
	
	@FXML
	private MenuItem clientes;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
   
		 
	}

	@FXML
	private void sairAction(ActionEvent evt) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja sair? ", ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			System.exit(0);
		}

	}

	@FXML
	private void sobreAction(ActionEvent evt1) throws IOException {
		Stage principal = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaSobre.fxml"));
		Scene scene = new Scene(root);
		principal.setScene(scene);

		principal.show();

	}

	@FXML
	private void usuariosAction(ActionEvent evt2) throws IOException {
        Stage principal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaUsuarios.fxml"));
        Scene scene = new Scene(root);
        principal.setScene(scene);
        principal.show();
        principal.setResizable(false);
  
	}
	
	@FXML
	private void clientesAction(ActionEvent evt3) throws IOException {
        Stage principal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaClientes.fxml"));
        Scene scene = new Scene(root);
        principal.setScene(scene);
        principal.show();
        principal.setResizable(false);
  
	}
	
	

}
