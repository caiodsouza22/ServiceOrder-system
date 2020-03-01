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
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class TelaPrincipal implements Initializable {

	@FXML
	private MenuItem sair;
		
	@FXML
	private MenuItem sobre;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		
	}
	
	@FXML
	 private void sairAction(ActionEvent evt) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja sair? ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
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
}
