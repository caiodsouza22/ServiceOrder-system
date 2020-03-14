package FXML;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dal.ModuloConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class OSController implements Initializable {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@FXML
	private JFXRadioButton odS;

	@FXML
	private JFXRadioButton orC;

	@FXML
	private ToggleGroup orcaOuServico;

	@FXML
	private JFXButton procurar;

	@FXML
	private Button alterar;

	@FXML
	private Button adicionar;

	@FXML
	private Button pesquisarOS;

	@FXML
	private Button deletar;

	@FXML
	private TextField numero, txtProcurar;

	@FXML
	private TextField data1;

	@FXML
	private TextField id;

	@FXML
	private TextField equipamento;

	@FXML
	private TextField defeito;

	@FXML
	private TextField servico;

	@FXML
	private TextField tecnico;

	@FXML
	private TextField valor;

	@FXML
	private String tipo;

	@FXML
	private ComboBox<String> situacao;

	@FXML
	private TableView<TableModel2> table;

	@FXML
	private TableColumn<TableModel2, String> nomecli;

	@FXML
	private TableColumn<TableModel2, String> idcli;

	@FXML
	private TableColumn<TableModel2, String> fonecli;

	ObservableList<TableModel2> listview = FXCollections.observableArrayList();

	private Object label;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		situacao.setStyle("-fx-text-inner-color: #a0a2ab;");
		situacao.getItems().addAll("Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando peças",
				"Abandonado pelo cliente", "Na bancada", "Retornou");

		idcli.setCellValueFactory(new PropertyValueFactory<>("idcli"));
		nomecli.setCellValueFactory(new PropertyValueFactory<>("nomecli"));
		fonecli.setCellValueFactory(new PropertyValueFactory<>("fonecli"));

		try {
			String sql = "select * from tbclientes ";
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				listview.add(new TableModel2(rs.getString("idcli"), rs.getString("nomecli"), rs.getString("fonecli")));
			}
			table.setItems(listview);

		} catch (Exception e) {

		}
	}

	public OSController() {
		conexao = (Connection) ModuloConexao.conector();
		emitir_os();

	}

	@FXML
	private void setar_campos() {
		id.setText(table.getSelectionModel().getSelectedItem().getIdcli());
	}

	@FXML
	private void emitir_os() {

		String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";

		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, getTipo());
			pst.setString(2, situacao.getValue().toString());
			pst.setString(3, equipamento.getText());
			pst.setString(4, defeito.getText());
			pst.setString(5, servico.getText());
			pst.setString(6, tecnico.getText());
			pst.setString(7, valor.getText().replace(",", "."));
			pst.setString(8, id.getText());

			if ((id.getText().isEmpty()) || (equipamento.getText().isEmpty()) || (defeito.getText().isEmpty())) {

				Alert alert = new Alert(AlertType.INFORMATION, "Preencha todos os campos obrigatórios!");

				alert.showAndWait();

			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {

					Alert alert = new Alert(AlertType.INFORMATION, "Ordem de serviço emitida com sucesso!");
					alert.showAndWait();
					id.setText(null);
					equipamento.setText(null);
					defeito.setText(null);
					servico.setText(null);
					tecnico.setText(null);
					valor.setText(null);
				}

			}
		} catch (Exception e) {

		}
	}

	public String getTipo() {
		String esc = "";

		if (orC.isSelected()) {
			esc = "Orçamento";

		} else if (odS.isSelected()) {
			esc = "OrdemdeServiço";
		}

		return esc;

	}

	@FXML
	private void pesquisar_os(ActionEvent evt2) {

		String num_os = JOptionPane.showInputDialog("Número da OS");
		String sql = "select * from tbos where os= " + num_os;

		try {

			pst = (PreparedStatement) conexao.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				numero.setText(rs.getString(1));
				data1.setText(rs.getString(2));
				String rbtTipo = rs.getString(3);

				if (rbtTipo.equals("OrdemdeServiço")) {
					odS.setSelected(true);

				} else {
					orC.setSelected(true);

				}
				situacao.getSelectionModel().select(rs.getString(4));
				equipamento.setText(rs.getString(5));
				defeito.setText(rs.getString(6));
				servico.setText(rs.getString(7));
				tecnico.setText(rs.getString(8));
				valor.setText(rs.getString(9));
				id.setText(rs.getString(10));

				adicionar.setDisable(true);
				txtProcurar.setDisable(true);
				table.setDisable(true);

			} else {
				JOptionPane.showMessageDialog(null, "OS não cadastrada");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "OS Inválida");

		}
	}

	@FXML
	private void alterarAction(ActionEvent evt5) {
		String sql = "UPDATE tbos SET tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";

		try {
			pst = (PreparedStatement) conexao.prepareStatement(sql);
			pst.setString(1, getTipo());
			pst.setString(2, situacao.getValue().toString());
			pst.setString(3, equipamento.getText());
			pst.setString(4, defeito.getText());
			pst.setString(5, servico.getText());
			pst.setString(6, tecnico.getText());
			pst.setString(7, valor.getText().replace(",", "."));
			pst.setString(8, numero.getText());

			if ((id.getText().isEmpty()) || (equipamento.getText().isEmpty()) || (defeito.getText().isEmpty())) {

				Alert alert = new Alert(AlertType.INFORMATION, "Preencha todos os campos obrigatórios!");

				alert.showAndWait();

			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {

					Alert alert = new Alert(AlertType.INFORMATION, "OS alterada com sucesso!");
					alert.showAndWait();

					numero.setText(null);
					data1.setText(null);
					id.setText(null);
					equipamento.setText(null);
					defeito.setText(null);
					servico.setText(null);
					tecnico.setText(null);
					valor.setText(null);

					adicionar.setDisable(false);
					txtProcurar.setDisable(false);
					table.setDisable(false);

				}

			}
		} catch (Exception e) {

		}
	}

	@FXML
	private void deleteAction(ActionEvent evt6) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja remover este usuário? ", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			String sql = "delete from tbos where os=?";

			try {
				pst = (PreparedStatement) conexao.prepareStatement(sql);
				pst.setString(1, numero.getText());

				int apagado = pst.executeUpdate();

				if (apagado > 0) {
					Alert alert2 = new Alert(AlertType.INFORMATION, "Usuário apagado com sucesso!");
					alert2.showAndWait();

					numero.setText(null);
					data1.setText(null);
					id.setText(null);
					equipamento.setText(null);
					defeito.setText(null);
					servico.setText(null);
					tecnico.setText(null);
					valor.setText(null);

					adicionar.setDisable(false);
					txtProcurar.setDisable(false);
					table.setDisable(false);
				}

			} catch (Exception e) {

			}
		}

	}

}
