package br.com.gamestorebr.form.cliente;

import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.repository.CompradorRepository;
import br.com.gamestorebr.util.AlertHelper;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

public class ClienteFormController implements Initializable {

  private CompradorRepository compradorRepository;

  @FXML public Button addClienteButton;

  @FXML public TableColumn visualizarCol;

  @FXML private TableView<TableViewClienteItem> clientesTabView;

  @FXML public TableColumn<TableViewClienteItem, String> nomeCol;

  @FXML public TableColumn<TableViewClienteItem, String> cpfCol;

  @FXML public TableColumn<TableViewClienteItem, String> saldoCol;

  @FXML private TextField filtroCpfField;

  @FXML private Button filtrarClienteButton;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {

    compradorRepository = DataBase.getCompradorRepository();

    nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

    cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));

    saldoCol.setCellValueFactory(new PropertyValueFactory<>("saldo"));

    visualizarCol.setCellValueFactory(new PropertyValueFactory<>("visualizarButton"));

    clientesTabView.setItems(listClientes());

    clientesTabView.refresh();
  }

  private ObservableList<TableViewClienteItem> listClientes() {
    return FXCollections.observableArrayList(
        compradorRepository.listAll().stream()
            .map(
                cliente ->
                    new TableViewClienteItem(
                        cliente.getNome(), cliente.getDocumento(), cliente.getSaldoFormatado()))
            .collect(Collectors.toList()));
  }

  @FXML
  protected void handleFiltrarButtonAction(final ActionEvent event) {

    final Window owner = filtrarClienteButton.getScene().getWindow();

    final ObservableList<TableViewClienteItem> clientesFiltrados;

    if (filtroCpfField.getText() == null || filtroCpfField.getText().isEmpty()) {
      clientesFiltrados =
          FXCollections.observableArrayList(
              compradorRepository.listAll().stream()
                  .map(
                      cliente ->
                          new TableViewClienteItem(
                              cliente.getNome(),
                              cliente.getDocumento(),
                              cliente.getSaldoFormatado()))
                  .collect(Collectors.toList()));
    } else {
      clientesFiltrados =
          FXCollections.observableArrayList(
              compradorRepository.listAll().stream()
                  .filter(cliente -> cliente.getDocumento().contains(filtroCpfField.getText()))
                  .map(
                      cliente ->
                          new TableViewClienteItem(
                              cliente.getNome(),
                              cliente.getDocumento(),
                              cliente.getSaldoFormatado()))
                  .collect(Collectors.toList()));
    }

    if (clientesFiltrados.isEmpty()) {
      AlertHelper.showAlert(
          AlertType.WARNING,
          owner,
          "Atenção",
          "Cliente com CPF  " + filtroCpfField.getText() + " não encontrado");
      return;
    }

    clientesTabView.setItems(clientesFiltrados);
  }

  public void handleVoltarButtonAction(final ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/menu/menu_form.fxml");
  }
}
