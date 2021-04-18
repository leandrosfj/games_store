package br.com.gamestorebr.form.vendedor;

import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.model.pessoa.Vendedor;
import br.com.gamestorebr.repository.VendedorRepository;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VisualizarVendedorFormController implements Initializable {

  private VendedorRepository vendedorRepository;

  @FXML public TextField nomeField;
  @FXML public TextField saldoField;
  @FXML public TextField documentoField;

  @FXML private TableView<TableViewCatalogoItem> catalogoTabView;

  @FXML public TableColumn<TableViewCatalogoItem, Integer> catalogoIdCol;
  @FXML public TableColumn<TableViewCatalogoItem, String> catalogoNomeCol;
  @FXML public TableColumn<TableViewCatalogoItem, String> catalogoPrecoUnitarioCol;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {

    vendedorRepository = DataBase.getVendedorRepository();

    catalogoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    catalogoNomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

    catalogoPrecoUnitarioCol.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
  }

  @FXML
  public void handleVoltarButtonAction(final ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/vendedor/vendedor_form.fxml");
  }

  @FXML
  public void carregarVendedor(final String documento) {

    final Vendedor vendedorVisualizar =
        vendedorRepository.listAll().stream()
            .filter(vendedor -> vendedor.getDocumento().equals(documento))
            .findFirst()
            .get();

    nomeField.setText(vendedorVisualizar.getNome());
    documentoField.setText(vendedorVisualizar.getDocumento());
    saldoField.setText(vendedorVisualizar.getSaldoFormatado());

    final ObservableList<TableViewCatalogoItem> tableViewCatalogoItems =
        FXCollections.observableArrayList(
            vendedorVisualizar.getCatalogo().values().stream()
                .map(
                    produto ->
                        new TableViewCatalogoItem(
                            produto.getCodigo(),
                            produto.getNome(),
                            produto.getPrecoUnitarioFormatado()))
                .collect(Collectors.toList()));

    catalogoTabView.setItems(tableViewCatalogoItems);

    catalogoTabView.refresh();
  }
}
