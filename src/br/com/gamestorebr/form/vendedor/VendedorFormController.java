package br.com.gamestorebr.form.vendedor;


import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.repository.VendedorRepository;
import br.com.gamestorebr.util.AlertHelper;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Window;

public class VendedorFormController implements Initializable {


  private VendedorRepository vendedorRepository;

  @FXML
  public Button addVendedorButton;
  
  @FXML
  public TableColumn editCol;

  @FXML
  private TableView<VendedorForm> vendedoresTabView;

  @FXML
  public TableColumn<VendedorForm, String> nomeCol;

  @FXML
  public TableColumn<VendedorForm, String> cnpjCol;

  @FXML
  public TableColumn<VendedorForm, String> actionCol;

  @FXML
  private TextField filtroCnpjField;

  @FXML
  private Button buscarVendedorButton;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    vendedorRepository = DataBase.getVendedorRepository();

    nomeCol.setCellValueFactory(
        new PropertyValueFactory<>("nome"));

    cnpjCol.setCellValueFactory(
        new PropertyValueFactory<>("cnpj"));

    actionCol.setCellValueFactory(
        new PropertyValueFactory<>("action"));

    editCol.setCellValueFactory(new PropertyValueFactory<>("editButton"));

    vendedoresTabView.setItems(this.listVendedores());

    vendedoresTabView.refresh();
  }

  private ObservableList<VendedorForm> listVendedores() {
    return FXCollections.observableArrayList(
        vendedorRepository
            .listAll()
            .stream()
            .map(vendedor -> new VendedorForm(vendedor.getNome(), vendedor.getDocumento(), vendedor.getSaldoFormatado()))
            .collect(Collectors.toList()));
  }

  @FXML
  protected void handleBuscarButtonAction(ActionEvent event) {

    Window owner = buscarVendedorButton.getScene().getWindow();

    if (filtroCnpjField.getText() == null || filtroCnpjField.getText().isEmpty()) {
      AlertHelper.showAlert(
          AlertType.WARNING, owner, "Atenção", "Informe o CNPJ do Vendedor");
      return;
    }

    ObservableList<VendedorForm> vendedoresFiltrados = FXCollections.observableArrayList(
        vendedorRepository
            .listAll()
            .stream()
            .filter(vendedor ->
                vendedor.getDocumento().contains(filtroCnpjField.getText())
            )
            .map(vendedor -> new VendedorForm(vendedor.getNome(), vendedor.getDocumento(),
                vendedor.getSaldoFormatado()))
            .collect(Collectors.toList()));

    if(vendedoresFiltrados.isEmpty()){
      AlertHelper.showAlert(
          AlertType.WARNING, owner, "Atenção", "Vendedor com CNPJ "+filtroCnpjField.getText()+" não encontrado");
      return;
    }

    vendedoresTabView.setItems(vendedoresFiltrados);
  }

  public void handleVoltarButtonAction(ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/menu/menu_form.fxml");
  }

//  https://riptutorial.com/javafx/example/28033/creating-custom-dialog

  public void handleAddVendedorButtonAction(ActionEvent actionEvent) {
    Dialog<ButtonType> dialog
        = getCheckPrintDialog(primaryStage, "Enter starting check number");

    dialog.showAndWait()
        .filter(result -> result == printButtonType)
        .ifPresent(result -> {
          // this is for this example only, normaly you already have this value
          endingNumber.setValue(startingNumber.getValue() + 1);
          printChecks(startingNumber.getValue(), endingNumber.getValue());
        });
  }
}
