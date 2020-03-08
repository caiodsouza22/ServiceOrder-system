package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class OSController implements Initializable {

	@FXML
	private JFXRadioButton odS;
	
	@FXML
	private JFXRadioButton orC;
	
	@FXML
	private TextField numero;
	
	@FXML
	private TextField data1;	
	
	@FXML
	private ToggleGroup escolhas;
	
	@FXML 
	private ComboBox<String> situacao;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		situacao.setStyle("-fx-text-inner-color: #a0a2ab;");
		situacao.getItems().addAll("Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação","Aguardando peças","Abandonado pelo cliente","Na bancada","Retornou");
	}

}
